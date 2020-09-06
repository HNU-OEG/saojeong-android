package com.example.saojeong.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.saojeong.fragment.CommunityTabFragment;
import com.example.saojeong.login.AllLoginManager;

public class ForecdTerminationService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) { //핸들링 하는 부분

        Toast.makeText(this, "onTaskRemoved ", Toast.LENGTH_SHORT).show();
        AllLoginManager.getInstance().Destroy(null);
        stopSelf(); //서비스 종료
    }
}
