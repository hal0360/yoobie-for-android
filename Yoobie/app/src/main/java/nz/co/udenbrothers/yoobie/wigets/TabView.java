package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import nz.co.udenbrothers.yoobie.interfaces.CmdTab;


public class TabView extends TabLayout implements TabLayout.OnTabSelectedListener {

    private CmdTab cmDSec;
    private CmdTab cmDUnSec;

    public TabView(Context context)
    {
        super(context);
    }

    public TabView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void addPageListner(ViewPager viewPager){
        this.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    public void selected(CmdTab cmd){
        cmDSec = cmd;
    }

    public void unSelected(CmdTab cmd){
        cmDUnSec = cmd;
    }

    @Override
    public void onTabSelected(Tab tab) {
        cmDSec.exec(tab);
    }

    @Override
    public void onTabUnselected(Tab tab) {
        cmDUnSec.exec(tab);
    }

    @Override
    public void onTabReselected(Tab tab) {}
}
