package com.example.saojeong.login;

import android.app.Application;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;

public class kakaoSDKAdapter extends KakaoAdapter {
    public Application mContext;

    public kakaoSDKAdapter(Application application) {
        mContext = application;
    }

    public ISessionConfig getSessionConfig() {
        return new ISessionConfig() {
            public boolean isSaveFormData() {
                return true;
            }

            public boolean isSecureMode() {
                return false;
            }

            public boolean isUsingWebviewTimer() {
                return false;
            }

            public AuthType[] getAuthTypes() {
                return new AuthType[]{AuthType.KAKAO_TALK, AuthType.KAKAO_ACCOUNT, AuthType.KAKAO_LOGIN_ALL};
            }

            public ApprovalType getApprovalType() {
                return ApprovalType.INDIVIDUAL;
            }
        };
    }

    public IApplicationConfig getApplicationConfig() {
        return () -> kakaoSDKAdapter.this.mContext;
    }
}
