package com.example.saojeong;

import android.app.Activity;

import com.example.saojeong.fragment.CommunitySearchFragment;
import com.example.saojeong.fragment.CommunityTabFragment;
import com.example.saojeong.fragment.CommunityUserFragment;
import com.example.saojeong.login.AllLoginManager;

public class SingletonClass {
    private SingletonClass() { }



    private static class CommunitySearchFragmentHolder {
        public static final CommunitySearchFragment INSTANCE = new CommunitySearchFragment();
    }
    public static CommunitySearchFragment getCommunitySearchInstance() {
        return CommunitySearchFragmentHolder.INSTANCE;
    }
    private static class CommunityUserFragmentHolder {
        public static final CommunityUserFragment INSTANCE = new CommunityUserFragment();
    }
    public static CommunityUserFragment getCommunityUserInstance() {
        return CommunityUserFragmentHolder.INSTANCE;
    }
    private static class CommunityTabFragmentHolder {
        public static final CommunityTabFragment INSTANCE = new CommunityTabFragment();
    }
    public static CommunityTabFragment getCommunityTabInstance() {
        return CommunityTabFragmentHolder.INSTANCE;
    }



}
