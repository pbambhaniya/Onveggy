package com.multipz.onveggy.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multipz.onveggy.Model.SigninModel;
import com.multipz.onveggy.Model.SignupModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.Constant_method;
import com.multipz.onveggy.Util.MyAsyncTask;
import com.multipz.onveggy.Util.Shared;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignInActivity extends Activity implements MyAsyncTask.AsyncInterface {

    TextView txt_create_ac, txt_sign_in_header, txt_signin_desc, txt_forgot_password, txt_name;
    EditText edit_username, edit_password;
    ImageView img_back;
    RelativeLayout rel_signin,rel_signin_btn,rel_root;
    Shared shared;
    Context context;
    String token;
    private ArrayList<SigninModel> loginModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        context = this;
        shared = new Shared(this);
        token = shared.getString("signin", "");
        refrences();
        init();
    }

    private void init() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rel_signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();

                if (username.contentEquals("@")) {
                    if (Constant_method.isValidEmail(username)) {
                        Toast.makeText(context, R.string.valid_email_or_mobile, Toast.LENGTH_SHORT).show();
                    } else if (password.contentEquals("")) {
                        Toast.makeText(context, R.string.valid_password, Toast.LENGTH_SHORT).show();
                    } else if (Constant_method.checkConn(context)) {
                        String param = "{\"email\":\"" + username + "\",\"mobile\":\"\",\"password\":\"" + password + "\",\"socialType\":\"N\",\"socialToken\":\"\",\"token\":\"\",\"deviceType\":\"A\", \"action\":\"" + Config.Action_signin + "\"}\n";
                        MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, SignInActivity.this, param, Config.API_Signin);
                        myAsyncTask.execute();
                    }
                } else {
                    if (username.length() < 10) {
                        Toast.makeText(context, R.string.valid_email_or_mobile, Toast.LENGTH_SHORT).show();
                    } else if (password.contentEquals("")) {
                        Toast.makeText(context, R.string.valid_password, Toast.LENGTH_SHORT).show();
                    } else if (Constant_method.checkConn(context)) {
                        String param = "{\"email\":\"\",\"mobile\":\"" + username + "\",\"password\":\"" + password + "\",\"socialType\":\"N\",\"socialToken\":\"\",\"token\":\"\",\"deviceType\":\"A\", \"action\":\"" + Config.Action_signin + "\"}\n";
                        MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, SignInActivity.this, param, Config.API_Signin);
                        myAsyncTask.execute();
                    } else {
//                    shared.putBoolean(Config.isLogin, true);
//                    Intent i=new Intent(SignInActivity.this,DrawerActivity.class);
//                    startActivity(i);
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


//        rel_signin_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username=edit_username.getText().toString();
//                String password=edit_password.getText().toString();
//
//                if (username.contentEquals("")) {
//                    Toast.makeText(context, "Enter User Name", Toast.LENGTH_SHORT).show();
//                } else if (password.contentEquals("")) {
//                    Toast.makeText(context, "Enter Password", Toast.LENGTH_SHORT).show();
//                }
//                else if (Constant_method.checkConn(context)) {
//                    String param = "{\"email\":\""+username+"\",\"mobile\":\""+username+"\",\"password\":\""+password+"\",\"socialType\":\"N\",\"socialToken\":\"\",\"token\":\"\",\"deviceType\":\"A\", \"action\":\""+Config.Action_signin+"\"}\n";
//                    MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, SignInActivity.this, param, Config.API_Signin);
//                    myAsyncTask.execute();
//                }
//                else {
////                    shared.putBoolean(Config.isLogin, true);
////                    Intent i=new Intent(SignInActivity.this,DrawerActivity.class);
////                    startActivity(i);
//                    Toast.makeText(context, R.string.valid_connection, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        txt_create_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignInActivity.this,SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    //Method for all refrence with xml file
    private void refrences() {
        txt_create_ac = (TextView) findViewById(R.id.txt_create_ac);
        txt_sign_in_header = (TextView) findViewById(R.id.txt_sign_in_header);
        txt_signin_desc = (TextView) findViewById(R.id.txt_signin_desc);

        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);
        rel_signin_btn=(RelativeLayout)findViewById(R.id.rel_signin_btn);
        rel_root=(RelativeLayout)findViewById(R.id.rel_root);

        txt_forgot_password = (TextView) findViewById(R.id.txt_forgot_password);
        txt_name = (TextView) findViewById(R.id.txt_name);
        img_back = (ImageView) findViewById(R.id.img_back);
        rel_signin=(RelativeLayout)findViewById(R.id.rel_signin);

//        txt_create_ac.setTypeface(Application.fonttitilliumRegular);
//        txt_sign_in.setTypeface(Application.fonttitilliumRegular);
//        txt_just_one.setTypeface(Application.fonttitilliumRegular);
//        et_username.setTypeface(Application.fonttitilliumRegular);
//        et_password.setTypeface(Application.fonttitilliumRegular);
//        txt_forgot_password.setTypeface(Application.fonttitilliumRegular);
//        txt_name.setTypeface(Application.fonttitilliumRegular);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

    @Override
    public void onResponseService(String response, int flag) {
        int success;
        String msg;
        if (flag == Config.API_Signin) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce", jsonObject.toString());
                success=jsonObject.getInt("status");
                msg=jsonObject.getString("msg");

                if(success==1) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    loginModelArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonLogin = jsonArray.getJSONObject(i);
                        SigninModel signinModel = new SigninModel();
                        signinModel.setCustomer_master_id(jsonLogin.getString("customer_master_id"));
                        signinModel.setUsername(jsonLogin.getString("username"));
                        signinModel.setPassword(jsonLogin.getString("password"));
                        signinModel.setEmail(jsonLogin.getString("email"));
                        signinModel.setMobile(jsonLogin.getString("mobile"));
                        signinModel.setProfile_pic(jsonLogin.getString("profile_pic"));
                        signinModel.setNotify_token(jsonLogin.getString("notify_token"));
                        signinModel.setSocial_token(jsonLogin.getString("social_token"));
                        signinModel.setSocial_type(jsonLogin.getString("social_type"));
                        signinModel.setDevice_type(jsonLogin.getString("device_type"));
                        loginModelArrayList.add(signinModel);
                    }

                    shared.putBoolean(Config.isLogin, true);
                    Intent intent=new Intent(SignInActivity.this,DrawerActivity.class);
                    startActivity(intent);
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
//                    Snackbar.make(rel_root, msg, Snackbar.LENGTH_LONG)
//                            .setAction("CLOSE", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//
//                                }
//                            })
//                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
//                            .show();
                }else {
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
