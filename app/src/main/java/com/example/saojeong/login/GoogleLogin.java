package com.example.saojeong.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.saojeong.R;
import com.example.saojeong.TutorialActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GoogleLogin implements LoginControl, GoogleApiClient.OnConnectionFailedListener {
    private Context mContext;
    private Activity mActivity;
    private LoginHandler mhandler;

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_GET_AUTH_CODE = 9003;
    private GoogleSignInClient mGoogleSignInClient;
    public static GoogleSignInAccount account;
    public GoogleLogin(Activity Activity, Context context, LoginControl.LoginHandler handler)
    {
        mActivity=Activity;
        mContext=context;
        mhandler=handler;
        init();
    }

    public void init() {
        String serverClientId = mContext.getString(R.string.google_client_id);
        String projectId = mContext.getString(R.string.project_id);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode(serverClientId)
                //requestIdToken() 서버용 토큰?
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(mActivity, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(mContext);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
// Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_GET_AUTH_CODE) {
            // [START get_auth_code]
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account1 = task.getResult(ApiException.class);
                this.account=account1;
                mhandler.success();
                // Show signed-un UI
                // TODO(developer): send code to server and exchange for access/refresh/ID tokens
            } catch (ApiException e) {
                Log.w(TAG, "Sign-in failed", e);
            }
            // [END get_auth_code]
        }
    }
    @Override
    public void Login(Activity activity) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        mActivity.startActivityForResult(signInIntent, RC_GET_AUTH_CODE);
    }
    @Override
    public void Logout() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(mActivity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mhandler.cancel();
    }
}

