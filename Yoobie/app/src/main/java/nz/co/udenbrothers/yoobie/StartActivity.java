package nz.co.udenbrothers.yoobie;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.wigets.FragmentLayout;
import nz.co.udenbrothers.yoobie.wigets.WaveView;

public class StartActivity extends RootActivity  {

    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView logo = findViewById(R.id.logo);
        handler = new Handler();

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_animation);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zooming);

        animation1.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {

            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                logo.startAnimation(animation1);
            }
        });

        logo.startAnimation(animation2);

        /*
        anim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });*/


    }

}
