package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.saojeong.fragment.CommunityFragment;
import android.widget.ImageView;
import com.example.saojeong.fragment.PriceFragment;

import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.saojeong.fragment.HomeFragment;
import com.example.saojeong.fragment.MyPageFragment;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private MyPageFragment myPageFragment;
    private PriceFragment priceFragment;
    private CommunityFragment communityFragment;
    private ImageView mhome;
    private ImageView mprice;
    private ImageView mcommunity;
    private ImageView mchatbot;
    private ImageView mmypage;

    private InputMethodManager imm;

    private StoreService storeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // store service 생성
        storeService = ServiceGenerator.createService(StoreService.class, "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZWFtLk9qZW9uZ2RvbmcuRWNvbm9taWNzLkd1YXJkaWFucyIsImV4cCI6MTU5NzU4ODU3MSwibWVtYmVyX2lkIjoiMEJSNGkwTU92SnA5SzdNWlJCdWNsYWFpWjdFQiIsIm5pY2tuYW1lIjoi7J2166qF7J2YIOuRkOuNlOyngCIsInVzZXJ0eXBlIjoxfQ.G0SdapZG7h9Lr5kJf0P8ecl71DXiLFHicq6805RHDvY");

        // 하단바 클릭시 색상 변경
        mhome = findViewById(R.id.miv_home);
        mprice = findViewById(R.id.miv_price);
        mcommunity = findViewById(R.id.miv_community);
        mchatbot = findViewById(R.id.miv_chatbot);
        mmypage = findViewById(R.id.miv_mypage);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment(); // 홈 Fragment 선언
        myPageFragment = new MyPageFragment(); // MyPage Fragment 선언
        priceFragment = new PriceFragment(); // 시세 Fragment 선언
        communityFragment = new CommunityFragment(); // 커뮤니티 Fragment 선언
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        transaction.replace(R.id.frameLayout_main, homeFragment)
                .commitAllowingStateLoss(); //시작화면에 Home 띄우기
        mhome.setImageResource(R.drawable.home_orange); //시작과 동시에 홈 오렌지색으로 변경


        loadStores();
    }

    private void loadStores() {
        Log.d("LOADSTORES HERE", "HERE");
        storeService.getStoreListOrderByGrade().enqueue(new Callback<List<StoreDto>>() {
            @Override
            public void onResponse(Call<List<StoreDto>> call,
                                   Response<List<StoreDto>> response) {
                if (response.isSuccessful()) {
                    // response.body()
                    // response.body()에서 넘어오는 데이터로 Adapter에 뿌려주기
                } else {
                    Log.d("REST FAILED MESSAGE", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<StoreDto>> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }
    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();
        switch (view.getId())
        {
            case R.id.ll_home:
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, homeFragment) // frameLayout에 홈 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();

                    mhome.setImageResource(R.drawable.home_orange);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage);
                break;

            case R.id.ll_price:
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, priceFragment) // frameLayout에 시세 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();

                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price_orange);
                    mcommunity.setImageResource(R.drawable.community);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage);
                break;

            case R.id.ll_community:
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, communityFragment) // frameLayout에 커뮤니티 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();

                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community_orange);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage);
                break;

            case R.id.ll_chatbot:
                break;

            case R.id.ll_myPage:
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, myPageFragment) // frameLayout에 MyPage Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();

                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage_orange);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //만약 더이상의 백스택이 없다면 홈으로 돌아가기
        int backstackcount = fragmentManager.getBackStackEntryCount();

        if(backstackcount == 0) {
            mhome.setImageResource(R.drawable.home_orange); //시작과 동시에 홈 오렌지색으로 변경
            mprice.setImageResource(R.drawable.price);
            mcommunity.setImageResource(R.drawable.community);
            mchatbot.setImageResource(R.drawable.chatbot);
            mmypage.setImageResource(R.drawable.mypage);
        }
    }

    public void replaceFragment(Fragment newFragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_main, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceHomeFragment(Fragment homeFragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_home, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void signOut(View view) {
        Toast.makeText(view.getContext(),"test", Toast.LENGTH_LONG).show();
    }

    public void closeKeyBoard(View view) {
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
