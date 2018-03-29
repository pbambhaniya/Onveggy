package com.multipz.onveggy.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Shared;

public class WelcomeActivity extends Activity {

    Shared shared;
    Context context;
    Intent intent;
    ImageView img_appname;
    RelativeLayout rel_letsgo;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        context = this;
        shared = new Shared(context);

        refrences();
        init();
    }

    private void init() {
        pref = getSharedPreferences("first", 0);
        editor = pref.edit();

        int x = pref.getInt("x", 0);

        if (x == 0) {
            x++;
            editor.putInt("x", x);
            editor.commit();
        } else {
            Intent i = new Intent(WelcomeActivity.this, SigninSignupActivity.class);
            startActivity(i);
        }

        rel_letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, SigninSignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refrences() {
        rel_letsgo = (RelativeLayout) findViewById(R.id.rel_letsgo);
        img_appname = (ImageView) findViewById(R.id.img_appname);
        txt_name = (TextView) findViewById(R.id.txt_name);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}
