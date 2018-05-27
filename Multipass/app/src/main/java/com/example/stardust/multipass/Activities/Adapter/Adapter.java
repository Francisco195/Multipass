package com.example.stardust.multipass.Activities.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.stardust.multipass.Activities.Fragments.NFC;
import com.example.stardust.multipass.Activities.Fragments.Pwd;
import com.example.stardust.multipass.Activities.Fragments.Sistema;

/**
 * Created by Stardust on 01/03/2018.
 */

public class Adapter extends FragmentStatePagerAdapter {
    private int numerotabs;

    public Adapter(FragmentManager fm, int numerotabs) {
        super(fm);
        this.numerotabs=numerotabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NFC();
            case 1:
                return new Pwd();
            case 2:
                return new Sistema();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numerotabs;
    }
}
