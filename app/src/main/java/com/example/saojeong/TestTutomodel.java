package com.example.saojeong;

public class TestTutomodel {
    int mImageSrc;
    boolean mLogin;
    public TestTutomodel(int imageSrc, boolean login)
    {
        mImageSrc=imageSrc;
        mLogin=login;
    }

    public int GetImage()
    {return mImageSrc;}

    public boolean GetLogin()
    {return mLogin;}
}
