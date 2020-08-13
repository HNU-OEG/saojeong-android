package com.example.saojeong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.saojeong.fragment.CommunityTabFragment;
import com.example.saojeong.fragment.Community_Popularity_Fragment;
import com.example.saojeong.fragment.Community_User_Fragment;

public class CommunityAdapter extends FragmentStateAdapter {
    public CommunityAdapter(FragmentActivity fa){
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        position++;
        switch(position){
            case 1:
                return new CommunityTabFragment();
            case 2:
                return new Community_Popularity_Fragment();
            default:
                return new Community_User_Fragment();
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    public Fragment setFragment(int position) {
        position++;
        switch(position){
            case 1:
                return new CommunityTabFragment();
            case 2:
                return new Community_Popularity_Fragment();
            default:
                return new Community_User_Fragment();
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
