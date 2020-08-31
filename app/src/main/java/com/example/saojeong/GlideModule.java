package com.example.saojeong;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.module.AppGlideModule;

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {
    public static MultiTransformation<android.graphics.Bitmap> getCenterCropAndRoundedCorner(int weight) {
        return new MultiTransformation<>(new CenterCrop(), new RoundedCorners(weight));
    }

}
