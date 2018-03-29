package com.multipz.onveggy.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.squareup.picasso.Picasso;

public class ZoomImageViewActivity extends AppCompatActivity {

    Context context;
    ImageView close,zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image_view);
        context=this;

        refrence();
        init();
    }

    private void init() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Picasso.with(context).load(Application.currentveg.getImage()).into(zoom);
    }

    private void refrence() {
        close=(ImageView)findViewById(R.id.img_close);
        zoom=(ImageView)findViewById(R.id.img_zoom);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

}
