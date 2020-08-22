package com.example.saojeong.model;

import com.example.saojeong.rest.dto.Login_GuestDto;

public class LoginData {
    String ID;
    String Member_ID;
    String UserName;
    String NickName;
    String Gender;
    String Created_At;
    String Type;

    public LoginData(Login_GuestDto login_guest)
    {
        ID=login_guest.accessToken;
       Member_ID=login_guest.refreshToken;
       //UserName=login_guest.getUsername();
       //NickName=login_guest.getNickname();
       //Gender=login_guest.getGender();
       //Created_At=login_guest.getCreated_at();
    }

    public LoginData LoginData(Login_GuestDto body) {
        LoginData data= new LoginData(body);

        return data;
    }
}
