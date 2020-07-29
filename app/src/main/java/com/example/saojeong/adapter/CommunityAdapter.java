package com.example.saojeong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.saojeong.fragment.CommunityTabFragment;

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
                return new CommunityTabFragment(position);
            case 2:
                return new CommunityTabFragment(position);
            default:
                return new CommunityTabFragment(position);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
