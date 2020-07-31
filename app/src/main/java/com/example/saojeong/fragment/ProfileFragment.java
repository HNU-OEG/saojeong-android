package com.example.saojeong.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;

import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_CODE = 0;

    private Button btn_change_picture;
    private Button btn_save;
    private EditText et_new_name;
    private TextView tv_duplicate_err;
    private ImageView iv_profile;

    private String currentName = "test";
    private String newName;
    private String text1 = "*중복되는 별명입니다. 다른 닉네임으로 작성해주세요.";
    private String text2 = "*사용가능한 별명입니다.";

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_edit_profile, container, false);
        btn_change_picture = view.findViewById(R.id.btn_change_picture);
        btn_save = view.findViewById(R.id.btn_save);
        et_new_name = view.findViewById(R.id.et_name);
        tv_duplicate_err = view.findViewById(R.id.tv_duplicate_err);
        iv_profile = view.findViewById(R.id.iv_profile);

        //권한 확인
        checkSelfPermission((Activity) view.getContext());

        btn_change_picture.setOnClickListener((v) -> {
            //사진변경
            // TODO: 2020-08-01-001 로컬에서 이미지 불러오기
            // TODO: 2020-08-01-002 서버에 이미지 전송

            ((MainActivity)getActivity()).closeKeyBoard(view);

            Intent intent = new Intent();
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, REQUEST_CODE);

        });

        et_new_name.addTextChangedListener(new TextWatcher()  {

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
            // TODO: 2020-08-01-003 서버에 변경된 이름 전송
            ((MainActivity)getActivity()).closeKeyBoard(view);
            ((Activity)view.getContext()).onBackPressed();
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            int length = permissions.length; for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    Log.d("MainActivity","권한 허용 : " + permissions[i]);
                }
            }
        }
    }

    public void checkSelfPermission(Activity activity) {
        String temp = "";
        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " "; }
            //파일 쓰기 권한 확인
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
            }
            if (TextUtils.isEmpty(temp) == false) {
                // 권한 요청
                ActivityCompat.requestPermissions(activity, temp.trim().split(" "),1);
            }else {
                // 모두 허용 상태
                Toast.makeText(activity, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                try{
                    InputStream in = ((MainActivity)getActivity()).getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    iv_profile.setImageBitmap(img);
//                    Uri selectedImageUri = data.getData();
//                    iv_profile.setImageURI(selectedImageUri);


                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
