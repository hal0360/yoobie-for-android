package nz.co.udenbrothers.yoobie;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.serverObjects.LoginData;
import nz.co.udenbrothers.yoobie.serverObjects.LoginToken;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.Kit;
import nz.co.udenbrothers.yoobie.tools.Match;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.wigets.WaveView;
import nz.co.udenbrothers.yoobie.wigets.YoobieInput;

public class SignInActivity extends RootActivity {

    YoobieInput mail, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        WaveView waveView = findViewById(R.id.wave);

        mail = findViewById(R.id.inputMail);
        pass = findViewById(R.id.inputPassword);

        clicked(R.id.signInButton, v-> login());

        delay(500, ()-> waveView.setProgress(0.6f));
    }

    private void login(){
        if(!Match.mail(mail.getText())){
            mail.error("Invalid mail");
            return;
        }
        if(pass.getText().length() < 6){
            pass.error("Must have at least 6 lengh");
            return;
        }
        LoginData loginData = new LoginData(mail.getText(), pass.getText(), Build.MANUFACTURER + " " + android.os.Build.MODEL);
        RequestTask requestTask = new RequestTask(this, Url.SIGN_IN, null);
        requestTask.onError(r->{
            pass.error("Incorrect password");
        });
        requestTask.onSuccess(r->{
            Kit.show(this, r.content);
        });
        requestTask.execute(Json.to(loginData));
    }
}
