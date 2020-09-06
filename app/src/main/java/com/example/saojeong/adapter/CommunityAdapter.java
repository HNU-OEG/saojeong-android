package com.example.saojeong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.saojeong.SingletonClass;

public class CommunityAdapter extends FragmentStateAdapter {
    public CommunityAdapter(FragmentActivity fa){
        super(fa);
        SingletonClass.getCommunityTabInstance();
        SingletonClass.getCommunitySearchInstance();
        SingletonClass.getCommunityUserInstance();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        position++;
        switch(position){
            case 1:
                return SingletonClass.getCommunityTabInstance();
            case 2:
                return  SingletonClass.getCommunitySearchInstance();
            default:
                return  SingletonClass.getCommunityUserInstance();
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @Override
    public int getItemCount() {
        return 3;
    }
}
