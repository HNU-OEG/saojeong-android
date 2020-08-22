package com.example.saojeong.model;

public class TutorialValue {
    int mImageSrc;
    boolean mLogin;
    public TutorialValue(int imageSrc, boolean login)
    {
        mImageSrc=imageSrc;
        mLogin=login;
    }

    public int GetImage()
    {return mImageSrc;}

    public boolean GetLogin()
    {return mLogin;}
}
