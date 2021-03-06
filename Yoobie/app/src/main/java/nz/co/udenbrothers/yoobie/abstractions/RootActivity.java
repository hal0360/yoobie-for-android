package nz.co.udenbrothers.yoobie.abstractions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import nz.co.udenbrothers.yoobie.interfaces.Cmd;
import nz.co.udenbrothers.yoobie.interfaces.CmdInt;
import nz.co.udenbrothers.yoobie.interfaces.CmdView;


public abstract class RootActivity extends AppCompatActivity {

    private Handler handler;
    private float startingY;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        handler = new Handler();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    protected final void overlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            //    alert("Hey! Please enable draw over other app.");
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

    protected final void delay(int milsec, Cmd cmd){
        handler.postDelayed(cmd::exec, milsec);
    }

    protected final void clicked(int id, CmdView cmd){
        findViewById(id).setOnClickListener(cmd::exec);
    }

    protected final void clicked(View v, CmdView cmd){
        v.setOnClickListener(cmd::exec);
    }

    public final void pushActivity(Class actClass){
        Intent intent = new Intent(this, actClass);
        startActivity(intent);
    }

    public final void toActivity(Class actClass){
        Intent intent = new Intent(this, actClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View viewCurrent = getCurrentFocus();
        if(viewCurrent == null) return super.dispatchTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                float deltaa = startingY - event.getRawY();
                if (deltaa > 100)
                {
                    TextView nextField = (TextView)viewCurrent.focusSearch(View.FOCUS_DOWN);
                    if(nextField != null) nextField.requestFocus();
                    else {
                        viewCurrent.clearFocus();
                        imm.hideSoftInputFromWindow(viewCurrent.getWindowToken(), 0);
                    }
                }
                else{
                    int scrcoords[] = new int[2];
                    viewCurrent.getLocationOnScreen(scrcoords);
                    float x = event.getRawX() + viewCurrent.getLeft() - scrcoords[0];
                    float y = event.getRawY() + viewCurrent.getTop() - scrcoords[1];
                    if (x < viewCurrent.getLeft() || x > viewCurrent.getRight() || y < viewCurrent.getTop() || y > viewCurrent.getBottom()) imm.hideSoftInputFromWindow(viewCurrent.getWindowToken(), 0);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                startingY = event.getRawY();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
