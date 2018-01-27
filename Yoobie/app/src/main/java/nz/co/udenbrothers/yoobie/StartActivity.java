package nz.co.udenbrothers.yoobie;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Screen;
import nz.co.udenbrothers.yoobie.wigets.CountdownView;
import nz.co.udenbrothers.yoobie.wigets.WaveView;
import nz.co.udenbrothers.yoobie.wigets.YoobieText;

public class StartActivity extends RootActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView logo = findViewById(R.id.logo);
        ImageView dummyLogo = findViewById(R.id.dummyLogo);
        TextView title = findViewById(R.id.startTitle);

        WaveView waveView = findViewById(R.id.wave);
        CountdownView countdownView = findViewById(R.id.countdown);
        YoobieText signUpButt = findViewById(R.id.signUpButton);
        YoobieText faceButt = findViewById(R.id.facebookButton);
        TextView signInButt = findViewById(R.id.signInButton);

        countdownView.countDownStart("2018-11-22");

        clicked(signInButt, v -> pushActivity(SignInActivity.class));
        clicked(signUpButt, v -> pushActivity(SignUp1Activity.class));

        delay(1000, ()->{
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
        });

        delay(2000, ()->{
            if(Profile.userID() == null){
                waveView.setProgress(0.6f);
                countdownView.setVisibility(View.VISIBLE);
                signInButt.setVisibility(View.VISIBLE);
                signUpButt.setVisibility(View.VISIBLE);
                faceButt.setVisibility(View.VISIBLE);
            } else{

            }
        });

    }
}
