package nz.co.udenbrothers.yoobie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import nz.co.udenbrothers.yoobie.wigets.WaveView;

public class StartActivity extends AppCompatActivity {
    float sdsdsd = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        WaveView waveView =  findViewById(R.id.waveView2);

        waveView.setProgress(0);


        Button shi = findViewById(R.id.shitt);
        shi.setOnClickListener(v->{
            sdsdsd = sdsdsd + 0.3f;
            waveView.setProgress(sdsdsd);
        });
    }
}
