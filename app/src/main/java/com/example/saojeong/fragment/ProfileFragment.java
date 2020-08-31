package com.example.saojeong.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.login.AllLoginManager;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.mypage.Edit_ProfileDto;
import com.example.saojeong.rest.dto.mypage.Edit_ProfileImageDto;
import com.example.saojeong.rest.service.MyPage_Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private final String TAG = this.getClass().getName();
    private static final int REQUEST_CODE = 0;
    private MyPage_Service myPage_service;

    private Button btn_change_picture;
    private Button btn_save;
    private Button btn_sign_out;
    private TextView tv_nickname;
    private EditText et_new_name;
    private TextView tv_duplicate_err;
    private ImageView iv_profile;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Bitmap img;
    byte[] byteArray;

    private String currentName;
    private String newName;
    private String text1 = "*중복되는 별명입니다. 다른 닉네임으로 작성해주세요.";
    private String text2 = "*사용가능한 별명입니다.";
    private Activity activity;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_edit_profile, container, false);

        myPage_service = ServiceGenerator.createService(MyPage_Service.class, TokenCase.getToken());
        Context context = getActivity();
        sharedPreferences = context.getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        activity=getActivity();
        btn_change_picture = view.findViewById(R.id.btn_change_picture);
        btn_save = view.findViewById(R.id.btn_save);
        btn_sign_out = view.findViewById(R.id.btn_sign_out);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        et_new_name = view.findViewById(R.id.et_name);
        tv_duplicate_err = view.findViewById(R.id.tv_duplicate_err);
        iv_profile = view.findViewById(R.id.iv_profile);

        //currentName = getName();
        currentName = TokenCase.getUserResource("nickname");

        tv_nickname.setText(currentName);

        Toolbar toolbar = view.findViewById(R.id.toolbar_edit_profile);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(MyPageFragment.newInstance());
            }
        });
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        //권한 확인
        checkSelfPermission((Activity) view.getContext());

        btn_change_picture.setOnClickListener((v) -> {
            //사진변경

            ((MainActivity) getActivity()).closeKeyBoard(view);

            Intent intent = new Intent();
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, REQUEST_CODE);

            //이미지 서버로 전송
//            myPage_service.ModifiedImage(new Edit_ProfileImageDto(), TokenCase.getToken()).enqueue(new Callback<List<Edit_ProfileImageDto>>() {
            myPage_service.ModifiedImage(new Edit_ProfileImageDto(img)).enqueue(new Callback<List<Edit_ProfileImageDto>>() {
                @Override
                public void onResponse(Call<List<Edit_ProfileImageDto>> call, Response<List<Edit_ProfileImageDto>> response) {
                    if (response.code() == 201) {
                        Log.d(TAG, "profile 이미지 전송 완료 " + response.message());

                    }
                }

                @Override
                public void onFailure(Call<List<Edit_ProfileImageDto>> call, Throwable t) {
                    Log.d(TAG, "profile 이미지 전송 실패");
                }
            });

        });

        btn_sign_out.setOnClickListener((V) -> {
            ((MainActivity) getActivity()).replaceFragment(SignOutFragment1.newInstance());

        });

        et_new_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence name, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
            }

            @Override
            public void afterTextChanged(Editable name) {
                // 입력이 끝났을 때
                newName = name.toString();
                if (currentName.equals(newName)) {
                    tv_duplicate_err.setText(text1);
                    tv_duplicate_err.setTextColor(Color.parseColor("#f68853"));
                    btn_save.setEnabled(false);
                } else {
                    tv_duplicate_err.setText(text2);
                    tv_duplicate_err.setTextColor(Color.parseColor("#85a0b3"));
                    btn_save.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });

        btn_save.setOnClickListener((v) -> {
            ((MainActivity) getActivity()).closeKeyBoard(view);
            ((Activity) view.getContext()).onBackPressed();

            String new_name = et_new_name.getText().toString();
//            myPage_service.ModifiedName(new Edit_ProfileDto(new_name), TokenCase.getToken()).enqueue(new Callback<List<Edit_ProfileDto>>() {
//
//                @Override
//                public void onResponse(Call<List<Edit_ProfileDto>> call, Response<List<Edit_ProfileDto>> response) {
//                    if (response.code() == 201) {
//                        List<Edit_ProfileDto> body = response.body();
//                        Log.d("MYPAGE LOG", "이름 변경: " + body.toString());
//                    }
//                    editor.putString("nickname", new_name);
//                    editor.commit();
//                    Log.d("MYPAGE LOG", response.message());
//
//                }
//
//                @Override
//                public void onFailure(Call<List<Edit_ProfileDto>> call, Throwable t) {
//                    Log.d("MYPAGE LOG", "이름 변경 실패");
//                }
//            });

            //AllLoginManager loginManager = new AllLoginManager((MainActivity)getActivity(), (MainActivity)getContext());
            AllLoginManager.inst.editUsernickname(activity, new_name);
            //((MainActivity) getActivity()).replaceFragment(MyPageFragment.newInstance());
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    Log.d(TAG, "권한 허용 : " + permissions[i]);
                }
            }
        }
    }

    public void checkSelfPermission(Activity activity) {
        String temp = "";
        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }
        //파일 쓰기 권한 확인
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }
        if (TextUtils.isEmpty(temp) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(activity, temp.trim().split(" "), 1);
        } else {
            // 모두 허용 상태
            Toast.makeText(activity, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = ((MainActivity) getActivity()).getContentResolver().openInputStream(data.getData());

                    img = BitmapFactory.decodeStream(in);
                    in.close();
                    iv_profile.setImageBitmap(img);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    img.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byteArray = byteArrayOutputStream.toByteArray();

                    //todo response받은 JSON을 shararedPreferense에 저장
                    editor.putString("profileImage", Arrays.toString(byteArray));
                    editor.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getName() {
        String name = sharedPreferences.getString("nickname", null);
        return name == null ? "" : name;
    }
}
