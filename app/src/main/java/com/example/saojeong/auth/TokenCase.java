package com.example.saojeong.auth;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.saojeong.login.LoginToken;
import com.nhn.android.naverlogin.OAuthLogin;

import java.util.Date;
import java.util.Map;

public class TokenCase {
    public static String getToken() {
        return LoginToken.getToken();
    }

    public static String getGuestToken() {
        return LoginToken.getToken();
    }

    public static Date getExp() {
        JWT jwt = new JWT(LoginToken.getToken());
        Map userMap = jwt.getClaims();
        Claim id = (Claim) userMap.get("exp");
        return id.asDate();
    }

    //member_id nickname usertype exp provider username
    public static String getUserResource(String type) {
        String str11 = LoginToken.getToken();
        JWT jwt = new JWT(LoginToken.getToken());
        Map userMap = jwt.getClaims();
        if (type.equals("member_id")) {
            Claim id = (Claim) userMap.get("member_id");
            String str = id.asString();
            return str;
        }
        if (type.equals("nickname")) {
            Claim id = (Claim) userMap.get("nickname");
            String str = id.asString();
            return str;
        }
        if (type.equals("usertype")) {
            Claim id = (Claim) userMap.get("usertype");
            String str = id.asString();
            return str;
        }
        if (type.equals("provider")) {
            Claim id = (Claim) userMap.get("provider");
            String str = id.asString();
            return str;
        }
        if (type.equals("username")) {
            Claim id = (Claim) userMap.get("username");
            String str = id.asString();
            return str;
        }
        return "error";
    }

}

