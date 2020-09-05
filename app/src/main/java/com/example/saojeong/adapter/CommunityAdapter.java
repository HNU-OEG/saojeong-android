package com.example.saojeong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.saojeong.fragment.CommunitySearchFragment;
import com.example.saojeong.fragment.CommunityTabFragment;
import com.example.saojeong.fragment.CommunityUserFragment;

public class CommunityAdapter extends FragmentStateAdapter {
    public CommunityAdapter(FragmentActivity fa){
        super(fa);
        new CommunityTabFragment();
        new CommunitySearchFragment();
        new CommunityUserFragment();


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        position++;
        switch(position){
            case 1:
                return  CommunityTabFragment.inst;
            case 2:
                return  CommunitySearchFragment.inst;
            default:
                return  CommunityUserFragment.inst;
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
