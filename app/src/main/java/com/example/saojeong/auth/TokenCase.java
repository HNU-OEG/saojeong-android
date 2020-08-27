package com.example.saojeong.auth;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.saojeong.login.LoginToken;
import com.nhn.android.naverlogin.OAuthLogin;

import java.util.Date;
import java.util.Map;

public class TokenCase {
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZWFtLk9qZW9uZ2RvbmcuRWNvbm9taWNzLkd1YXJkaWFucyIsImV4cCI6MTU5ODkzMTk5MiwibWVtYmVyX2lkIjoiMEJSNGkwTU92SnA5SzdNWlJCdWNsYWFpWjdFQiIsIm5pY2tuYW1lIjoi7J2166qF7J2YIOuRkOuNlOyngCIsInVzZXJ0eXBlIjoxfQ.t7jf9FaSNE8BH6fjboCBbz8YvR9sGDlmSvJL8WQEDYA";

    public static String getToken() {
        return LoginToken.getToken();
    }


    public static String getGuestToken() {
        return ACCESS_TOKEN;
    }


    public static Date getExp(){
        JWT jwt = new JWT(LoginToken.getToken());
        Map userMap = jwt.getClaims();
        Claim id = (Claim) userMap.get("exp");
        return id.asDate();
    }
    //member_id nickname usertype exp provider username
    public static String getUserResource(String type) {
        JWT jwt = new JWT(LoginToken.getToken());
        Map userMap = jwt.getClaims();
        if (type == "member_id") {
            Claim id = (Claim) userMap.get("member_id");
            String str = id.asString();
            return str;
        }
        if (type == "nickname") {
            Claim id = (Claim) userMap.get("nickname");
            String str = id.asString();
            return str;
        }
        if (type == "usertype") {
            Claim id = (Claim) userMap.get("usertype");
            String str = id.asString();
            return str;
        }
        if (type == "provider") {
            Claim id = (Claim) userMap.get("provider");
            String str = id.asString();
            return str;
        }
        if (type == "username") {
            Claim id = (Claim) userMap.get("username");
            String str = id.asString();
            return str;
        }
        return "error";
    }

}

