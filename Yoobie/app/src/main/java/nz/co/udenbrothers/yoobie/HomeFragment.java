package nz.co.udenbrothers.yoobie;


import nz.co.udenbrothers.yoobie.abstractions.RootFragment;
import nz.co.udenbrothers.yoobie.tools.Popup;
import nz.co.udenbrothers.yoobie.wigets.CountdownView;


public class HomeFragment extends RootFragment {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void created() {
        CountdownView countdownView = findViewById(R.id.countdown);
        countdownView.countDownStart("2018-10-09");

        Popup lastImgPop = new Popup(parent, R.layout.last_image_popup);
        lastImgPop.clicked(R.id.goToWebbutton, v -> lastImgPop.dismiss());
        lastImgPop.show();
    }
}
