package com.tw2.affsuzukicup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tw2.affsuzukicup.view.fragment.BangXepHangFragment;
import com.tw2.affsuzukicup.view.fragment.DoiBongFragment;
import com.tw2.affsuzukicup.view.fragment.HomeFragment;
import com.tw2.affsuzukicup.view.fragment.KetQuaFragment;

public class HomeAdapter extends FragmentStatePagerAdapter {

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new HomeFragment();
                break;
            case 1:
                frag = new KetQuaFragment();
                break;
            case 2:
                frag = new BangXepHangFragment();
                break;
            case 3:
                frag = new DoiBongFragment();
                break;


        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
