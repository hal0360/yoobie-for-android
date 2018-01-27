package nz.co.udenbrothers.yoobie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import nz.co.udenbrothers.yoobie.entities.Country;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;
import nz.co.udenbrothers.yoobie.wigets.YoobieInput;
import nz.co.udenbrothers.yoobie.wigets.YoobieSpinner;

public class SignUp2Activity extends AppCompatActivity {

    private YoobieInput name, phone, dob;
    private YoobieSpinner country, region, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        TextView mail = findViewById(R.id.emailTxt);
        mail.setText(Profile.mail());

        country = findViewById(R.id.inputCountry);
      //  List<Country> countries = Sql.
       // country.init(Sql.get(Country.class));
    }
}
