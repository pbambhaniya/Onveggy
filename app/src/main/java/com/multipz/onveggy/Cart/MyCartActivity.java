package com.multipz.onveggy.Cart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Activity.DrawerActivity;
import com.multipz.onveggy.Adapter.CartAdpater;
import com.multipz.onveggy.Checkout.MyCheckoutActivity;
import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.ManageCart;
import com.multipz.onveggy.Util.Shared;

import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity {

    ImageView img_back;
    TextView txt_title,txt_title_sub_total,txt_sub_total,txt_title_total_amt,txt_total_amt,txt_promo_code,txt_item_plus,txt_item_minus,txt_count_item;
    RelativeLayout btn_add_Address,rel_btn_startshop;
    LinearLayout rel_totallist;
    View no_cart;
    ListView cartlist;
    Shared shared;
    Context context;
    ArrayList<VegModel> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        context=this;
        shared=new Shared(this);

        refrence();
        init();
    }

    private void init() {
        cartItems= ManageCart.getCartList();
        CartAdpater cart_adpater = new CartAdpater(getApplicationContext(),cartItems);
        cartlist.setAdapter(cart_adpater);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_add_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkout=new Intent(MyCartActivity.this, MyCheckoutActivity.class);
                startActivity(checkout);
//                no_cart.setVisibility(View.GONE);
            }
        });

        rel_btn_startshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shop=new Intent(MyCartActivity.this, DrawerActivity.class);
                startActivity(shop);
            }
        });

        if (cartItems.size()>0){
            no_cart.setVisibility(View.GONE);
        }else {
            no_cart.setVisibility(View.VISIBLE);
            rel_totallist.setVisibility(View.GONE);
            btn_add_Address.setVisibility(View.GONE);
        }
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
        img_back=(ImageView)findViewById(R.id.img_back);
        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_title_sub_total=(TextView)findViewById(R.id.txt_title_sub_total);
        txt_sub_total=(TextView)findViewById(R.id.txt_sub_total);
        txt_title_total_amt=(TextView)findViewById(R.id.txt_title_total_amt);
        btn_add_Address=(RelativeLayout)findViewById(R.id.btn_add_Address);
        rel_btn_startshop=(RelativeLayout)findViewById(R.id.rel_btn_startshop);
        rel_totallist=(LinearLayout)findViewById(R.id.rel_totallist);
        no_cart=(View)findViewById(R.id.no_cart);

        cartlist = (ListView) findViewById(R.id.list_cart);
    }
}
