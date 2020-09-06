package com.example.saojeong.fragment;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.saojeong.R;
import com.example.saojeong.SingletonClass;
import com.example.saojeong.adapter.CommunityAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CommunityFragment_Freeboard extends Fragment implements View.OnClickListener  {

    private CommunityAdapter mAdapter;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private TextView mWrite;
    private EditText mBoardSearch;
    private static class CommunityFragment_FreeboardHolder {
        public static final CommunityFragment_Freeboard INSTANCE = new CommunityFragment_Freeboard();
    }
    public static CommunityFragment_Freeboard CommunityFragment_FreeboardInstance() {
        return CommunityFragment_FreeboardHolder.INSTANCE;
    }

    public CommunityFragment_Freeboard()
    {
    }

    public ViewPager2 getViewPager2(){
        return viewPager2;
    }
    public TabLayout getTabLayout(){
        return tabLayout;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = (ViewGroup) inflater.inflate(R.layout.item_community_fragment, container, false);
        mAdapter=new CommunityAdapter(getActivity());
        viewPager2=view.findViewById(R.id.viewPager);
        viewPager2.setAdapter(mAdapter);
        tabLayout = view.findViewById(R.id.tablayout);
        mWrite=view.findViewById(R.id.tv_community_btn_write);
        mWrite.setOnClickListener(this);
        mBoardSearch=view.findViewById(R.id.et_community_boardsearch);
        mBoardSearch.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    tabLayout.setScrollPosition(1,0,true);
                    viewPager2.setCurrentItem(1);
                    SingletonClass.getCommunitySearchInstance().ListUpdate(mBoardSearch.getText().toString());
                    break;
                default:
                    tabLayout.setScrollPosition(1,0,true);
                    viewPager2.setCurrentItem(1);
                    SingletonClass.getCommunitySearchInstance().ListUpdate(mBoardSearch.getText().toString());
                    break;
            }
            return true;
        });
        SpannableString content = new SpannableString(mWrite.getText());
        content.setSpan(new UnderlineSpan(), 0, mWrite.getText().length(), 0);
        mWrite.setText(content);
        final TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch(position){
                case 0:{
                    tab.setText("최신");
                    break;
                }
                case 1:{
                    tab.setText("검색");
                    break;
                }
                case 2:{
                    tab.setText("내가쓴글");
                    break;
                }
            }
        });
        tabLayoutMediator.attach();
        return view;
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id) {
            case R.id.tv_community_btn_write:
                Community_WriteFragment fragment1=new Community_WriteFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, fragment1) // frameLayout에 커뮤니티 Fragment 호출
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;

        }

    }

}