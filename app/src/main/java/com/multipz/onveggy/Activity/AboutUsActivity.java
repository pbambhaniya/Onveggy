package com.multipz.onveggy.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

public class AboutUsActivity extends AppCompatActivity {

    RelativeLayout rel_header;
    ImageView img_back;
    TextView txt_title_name, txt_version, txt_about_des, txt_email, txt_website;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        context=this;
        refrence();
        init();
    }

    private void init() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void refrence() {
        rel_header = (RelativeLayout) findViewById(R.id.rel_header);
        img_back = (ImageView) findViewById(R.id.img_back);

        txt_title_name = (TextView) findViewById(R.id.txt_title_name);
        txt_version = (TextView) findViewById(R.id.txt_version);
        txt_about_des = (TextView) findViewById(R.id.txt_about_des);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_website = (TextView) findViewById(R.id.txt_website);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }
}
