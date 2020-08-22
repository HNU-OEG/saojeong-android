package com.example.saojeong.model;

import com.example.saojeong.rest.dto.Login_Dto;
import com.example.saojeong.rest.dto.Login_GuestDto;

public class LoginData {
    String ID;
    String Member_ID;
    String AccessToken;
    String RefreshToken;
    public LoginData(Login_GuestDto login_guest)
    {
        ID=login_guest.accessToken;
       Member_ID=login_guest.refreshToken;
       //UserName=login_guest.getUsername();
       //NickName=login_guest.getNickname();
       //Gender=login_guest.getGender();
       //Created_At=login_guest.getCreated_at();
    }
    public LoginData(Login_Dto login_guest)
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
    public LoginData LoginData(Login_Dto body) {
        LoginData data= new LoginData(body);

        return data;
    }
}
