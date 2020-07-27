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
    public Fragment createFragment(int position) { //Fragment 생성
        position++;
        switch(position){
            case 1:
                return new CommunityTabFragment(position); //0번째 탭 //이곳에서 뷰 보이기
            case 2:
                return new CommunityTabFragment(position); //1번째 탭
            default:
                return new CommunityTabFragment(position); //2번째 탭
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
