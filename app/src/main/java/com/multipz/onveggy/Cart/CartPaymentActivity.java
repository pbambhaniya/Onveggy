package com.multipz.onveggy.Cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Checkout.MyCheckoutActivity;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

public class CartPaymentActivity extends AppCompatActivity {

    ImageView img_back;
    TextView txt_title,txt_title_sub_total,txt_sub_total,txt_title_total_amt,txt_total_amt,txt_promo_code,txt_item_plus,txt_item_minus,txt_count_item;
    RelativeLayout btn_add_Address;
    RadioButton radio_cashdelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_payment);

        refrence();
        init();
    }

    private void init() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_add_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check-out
            }
        });
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));

        img_back=(ImageView)findViewById(R.id.img_back);
        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_title_sub_total=(TextView)findViewById(R.id.txt_title_sub_total);
        txt_sub_total=(TextView)findViewById(R.id.txt_sub_total);
        txt_title_total_amt=(TextView)findViewById(R.id.txt_title_total_amt);
        radio_cashdelivery=(RadioButton)findViewById(R.id.radio_cashdelivery);
        btn_add_Address=(RelativeLayout)findViewById(R.id.btn_add_Address);
    }

}
