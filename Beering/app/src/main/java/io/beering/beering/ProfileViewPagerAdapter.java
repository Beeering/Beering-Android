package io.beering.beering;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.beering.beering.Fragement.BadgeFragment;
import io.beering.beering.Fragement.MytasteFragment;
import io.beering.beering.Fragement.StampFragment;

/**
 * Created by hsun on 2017. 5. 24..
 */

public class ProfileViewPagerAdapter extends FragmentPagerAdapter{
    int tabCount;

    public ProfileViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                StampFragment tab1 = new StampFragment();
                return tab1;
            case 1:
                BadgeFragment tab2 = new BadgeFragment();
                return tab2;
            case 2:
                MytasteFragment tab3 = new MytasteFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
