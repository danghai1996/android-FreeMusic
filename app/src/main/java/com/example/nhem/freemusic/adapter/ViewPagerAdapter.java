package com.example.nhem.freemusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nhem.freemusic.fragment.DownloadFragment;
import com.example.nhem.freemusic.fragment.FavouriteFragment;
import com.example.nhem.freemusic.fragment.MusicFragment;

/**
 * Created by NHEM on 15/10/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MusicFragment();
            case 1:
                return new FavouriteFragment();
            case 2:
                return new DownloadFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
