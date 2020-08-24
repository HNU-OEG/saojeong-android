package com.example.saojeong.auth;

import com.example.saojeong.login.LoginToken;

public class TokenCase {
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZWFtLk9qZW9uZ2RvbmcuRWNvbm9taWNzLkd1YXJkaWFucyIsImV4cCI6MTU5ODkzMTk5MiwibWVtYmVyX2lkIjoiMEJSNGkwTU92SnA5SzdNWlJCdWNsYWFpWjdFQiIsIm5pY2tuYW1lIjoi7J2166qF7J2YIOuRkOuNlOyngCIsInVzZXJ0eXBlIjoxfQ.t7jf9FaSNE8BH6fjboCBbz8YvR9sGDlmSvJL8WQEDYA";

    public static String getToken() {
        return LoginToken.getToken();
    }

    public static String getGuestToken() {
        return ACCESS_TOKEN;
    }
}
