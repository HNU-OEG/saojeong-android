package com.example.saojeong.util;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class AlertBuilder {

    public static AlertDialog createDialog(Context context,
                                           String title,
                                           String message) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        return builder.create();
    }
}
