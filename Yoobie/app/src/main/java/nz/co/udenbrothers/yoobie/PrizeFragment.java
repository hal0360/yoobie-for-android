package nz.co.udenbrothers.yoobie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.co.udenbrothers.yoobie.wigets.CountdownView;


public class PrizeFragment extends Fragment {

    public PrizeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_prize, container, false);




        return v;
    }
}