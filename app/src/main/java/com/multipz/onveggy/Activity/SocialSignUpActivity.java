package com.multipz.onveggy.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.squareup.picasso.Picasso;

public class SocialSignUpActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    ImageView img_back;
    SignInButton btn_login;
    Context context;
    TextView txt_sign_up, txt_des, txt_email, txt_gmail, txt_facebook, txt_alredy_acc;
    RelativeLayout rel_email, rel_gmail, rel_facebook;
    LoginButton fb_login;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_social_sign_up);
        context = this;
        refrence();
        init();
    }

    private void init() {
        final String value = getIntent().getStringExtra("click");
        if (value.contentEquals("signin")) {
            txt_sign_up.setText("SIGN IN");
        } else if (value.contentEquals("signup")) {
            txt_sign_up.setText("SIGN UP");
        }

        callbackManager = CallbackManager.Factory.create();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initGoogle();

        txt_alredy_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SocialSignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
        rel_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (value.contentEquals("signin")) {
                    Intent intent = new Intent(SocialSignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else if (value.contentEquals("signup")) {
                    Intent intent = new Intent(SocialSignUpActivity.this, SignupActivity.class);
                    startActivity(intent);
                }
            }
        });
        rel_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        rel_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fb_login.performClick();
            }
        });

        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.e("Login Success", " \n" + loginResult.getAccessToken().getUserId() + "\n" + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {

                Log.e("login cancel ", "");

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout) findViewById(R.id.rel_root));
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        txt_des = (TextView) findViewById(R.id.txt_des);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_gmail = (TextView) findViewById(R.id.txt_gmail);
        txt_facebook = (TextView) findViewById(R.id.txt_facebook);
        txt_alredy_acc = (TextView) findViewById(R.id.txt_alredy_acc);
        btn_login = (SignInButton) findViewById(R.id.btn_login);
        img_back = (ImageView) findViewById(R.id.img_back);
        rel_email = (RelativeLayout) findViewById(R.id.rel_email);
        rel_gmail = (RelativeLayout) findViewById(R.id.rel_gmail);
        rel_facebook = (RelativeLayout) findViewById(R.id.rel_facebook);
        fb_login = (LoginButton) findViewById(R.id.fb_login);
    }

    private void initGoogle() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, SocialSignUpActivity.this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        } else {

            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            Log.e("Token ID", account.getId());
            String name = account.getDisplayName();
            Log.e("User Name", name);
            String email = account.getEmail();
            Log.e("Email", email);
            String img_url = account.getPhotoUrl().toString();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        signOut();
        LoginManager.getInstance().logOut();
    }

    private void signOut() {
        try {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // ...
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
