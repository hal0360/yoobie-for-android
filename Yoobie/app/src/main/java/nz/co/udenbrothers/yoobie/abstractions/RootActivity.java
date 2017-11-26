package nz.co.udenbrothers.yoobie.abstractions;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nz.co.udenbrothers.yoobie.interfaces.CmdView;


public abstract class RootActivity extends AppCompatActivity {

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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        this.finish();
    }
}
