package com.beri.beriid.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.beri.beriid.HistoryFragment;
import com.beri.beriid.ProfileFragment;

public class ProfilePageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public ProfilePageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new HistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return numOfTabs;
    }
}
