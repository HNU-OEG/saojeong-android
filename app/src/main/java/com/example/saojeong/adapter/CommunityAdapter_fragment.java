package com.example.saojeong.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.saojeong.fragment.CommunityFragment_Freeboard;
import com.example.saojeong.fragment.CommunityFragment_Notice;

public class CommunityAdapter_fragment extends FragmentStateAdapter {
    public CommunityAdapter_fragment(FragmentActivity fa){
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        position++;
        switch(position){
            case 1:
                return new CommunityFragment_Freeboard();
            default:
                return new CommunityFragment_Notice();
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }




    @Override
    public int getItemCount() {
        return 2;
    }
}
