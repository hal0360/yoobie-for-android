package nz.co.udenbrothers.yoobie;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.abstractions.RootFragment;
import nz.co.udenbrothers.yoobie.entities.AdImage;
import nz.co.udenbrothers.yoobie.temps.Screen;
import nz.co.udenbrothers.yoobie.tools.Kit;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;
import nz.co.udenbrothers.yoobie.wigets.FragmentLayout;
import nz.co.udenbrothers.yoobie.wigets.WaveView;

public class StartActivity extends RootActivity  {

    private TextView title;
    private ImageView logo;
    private FragmentLayout fragmentLayout;
    private WaveView waveView;
    private Boolean isWaitng = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logo = findViewById(R.id.logo);
        ImageView dummyLogo = findViewById(R.id.dummyLogo);
        title = findViewById(R.id.startTitle);
        fragmentLayout = findViewById(R.id.fragment);
        waveView = findViewById(R.id.wave);


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                Screen.height(displayMetrics.widthPixels);
                Screen.width(displayMetrics.heightPixels);
            } else{
                Screen.height(displayMetrics.heightPixels);
                Screen.width(displayMetrics.widthPixels);
            }
            Screen.density(displayMetrics.density);

            Animation anime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_animation);
            logo.startAnimation(anime);
            logo.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            dummyLogo.setVisibility(View.GONE);

            List<AdImage> adImages = Sql.get(AdImage.class);
            for(AdImage adImage: adImages){
                Kit.show(this, adImage.toJson());
            }

            waveView.setProgress(0.7f);

        }, 1000);
        handler.postDelayed(() -> fragmentLayout.add(new StartFragment()), 2000);

    }

    public void waveProgress(float level){
        waveView.setProgress(level);
    }

    public void setTitle(String name){
        title.setText(name);
    }

    public void toFragment(Fragment fragment){
        fragmentLayout.replace(fragment);
    }

    public void waiting(){
        setTitle("Please wait...");
        isWaitng = true;
    }

    public void unWaiting(String name){
        setTitle(name);
        isWaitng = false;
    }

    public void logoVisibility(Boolean visable){
        if(visable) logo.setVisibility(View.VISIBLE);
        else logo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
      //  RootFragment curFrag = (RootFragment) fragmentLayout.getTopFragment();
     //   curFrag.onBackPressed();

       Kit.show(this,  waveView.getWaterLevelRatio() + "");
    }
}
