package com.multipz.onveggy.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.MyAsyncTask;
import com.multipz.onveggy.Util.Shared;

import org.json.JSONObject;

public class ContactUsActivity extends AppCompatActivity implements MyAsyncTask.AsyncInterface{

    ImageView img_back;
    TextView txt_name, txt_contactus, txt_submit;
    EditText edit_email, edit_message;
    RelativeLayout rel_submit;
    Context context;
    Shared shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        context = this;
        shared = new Shared(this);

        refrence();
        init();
    }

    private void init() {
        rel_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=edit_email.getText().toString();
                String message=edit_message.getText().toString();

                if (email.contentEquals("")) {
                    Toast.makeText(context, "Enter Email Adress", Toast.LENGTH_SHORT).show();
                } else if (message.contentEquals("")) {
                    Toast.makeText(context, "Enter Message", Toast.LENGTH_SHORT).show();
                }
//                else if (Constant_method.checkConn(context)) {
//                    String param = "{\"email\":\"" + email + "\",\"message\":\""+message+"\",\"action\":\""+Config.Action_add_contact+"\"}";
//                    MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, ContactUsActivity.this, param, Config.API_Add_Contact);
//                    myAsyncTask.execute();
//                }
                else {
                    Intent intent=new Intent(ContactUsActivity.this,DrawerActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, "Sent Succesfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void refrence() {
        img_back = (ImageView) findViewById(R.id.img_back);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_contactus = (TextView) findViewById(R.id.txt_contactus);
        txt_submit = (TextView) findViewById(R.id.txt_submit);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_message = (EditText) findViewById(R.id.edit_message);
        rel_submit = (RelativeLayout) findViewById(R.id.rel_submit);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));

//        txt_name.setTypeface(Application.fonttitilliumRegular);
//        txt_contactus.setTypeface(Application.fonttitilliumRegular);
//        txt_submit.setTypeface(Application.fonttitilliumRegular);
//        edit_email.setTypeface(Application.fonttitilliumRegular);
//        edit_message.setTypeface(Application.fonttitilliumRegular);
    }

    @Override
    public void onResponseService(String response, int flag) {
        int success;
        String msg;
        if (flag == Config.API_Add_Contact) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce", jsonObject.toString());
                success=jsonObject.getInt("status");
                msg=jsonObject.getString("msg");

                if(success==1) {
                    Intent intent=new Intent(ContactUsActivity.this,DrawerActivity.class);
                    startActivity(intent);
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
