package com.multipz.onveggy.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multipz.onveggy.Adapter.VegItemAdapter;
import com.multipz.onveggy.Adapter.VegPriceAdapter;
import com.multipz.onveggy.Cart.MyCartActivity;
import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.Model.VegpriceModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.ManageCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VegDetailActivity extends AppCompatActivity {

    RelativeLayout rel_header,rel_add_to_cart;
    TextView txt_percentage, txt_rate, txt_price, txt_veg_count, txt_veg_price, txt_veg_name, txt_veg_des, txt_name, txt_05kg, txt_1kg, txt_5kg, txt_10kg, txt_cost,img_minus,img_plus,img_cross,img_equal;
    ImageView img_back,img_veg,img_search,img_cart;
    TextView item_count;
    Context context;
    RelativeLayout rel_cart;
    GridView grid_price;
    private ArrayList<VegpriceModel> vegpriceList=new ArrayList<VegpriceModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_detail);
        context=this;
        refrence();
        init();
    }

    private void init() {
        vegpriceList=new ArrayList<VegpriceModel>();
        vegpriceList.add(new VegpriceModel("0.5kg","10"));
        vegpriceList.add(new VegpriceModel("1Kg","20"));
        vegpriceList.add(new VegpriceModel("5Kg","100"));
        vegpriceList.add(new VegpriceModel("10Kg","2000"));
//        vegpriceList.add(new VegpriceModel("0.5kg","10"));
//        vegpriceList.add(new VegpriceModel("1Kg","20"));
//        vegpriceList.add(new VegpriceModel("5Kg","100"));
//        vegpriceList.add(new VegpriceModel("10Kg","2000"));

        VegPriceAdapter vegItemAdapter= new VegPriceAdapter(VegDetailActivity.this, vegpriceList);
        grid_price.setAdapter(vegItemAdapter);
        grid_price.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(VegDetailActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

        img_veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zoomimage=new Intent(VegDetailActivity.this,ZoomImageViewActivity.class);
                startActivity(zoomimage);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search=new Intent(VegDetailActivity.this,SearchActivity.class);
                startActivity(search);
            }
        });

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search=new Intent(VegDetailActivity.this,MyCartActivity.class);
                startActivity(search);
            }
        });

        item_count.setText(ManageCart.getNoFitem()+"");

        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int integer = Integer.parseInt(txt_veg_count.getText().toString());

                if ((integer - 1) >= 1) {
//                    VegModel v = veg_item.get(position);
//                    v.setCartItem(v.getCartItem() - 1);
                    txt_veg_count.setText(integer-1 + "");
                    txt_veg_price.setText(" Cost : "+((integer-1)*Integer.parseInt(Application.currentveg.getAmount())));
//                    addToCart(position);
                }
            }
        });

        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int integer = Integer.parseInt(txt_veg_count.getText().toString());
                txt_veg_count.setText(integer+1 + "");
                txt_veg_price.setText(" Cost : "+((integer+1)*Integer.parseInt(Application.currentveg.getAmount())));
            }
        });

        txt_veg_name.setText(Application.currentveg.getName());
        txt_veg_des.setText(Application.currentveg.getName());
        txt_price.setText(""+Application.currentveg.getAmount()+" each");
        int integer = Integer.parseInt(txt_veg_count.getText().toString());
        txt_cost.setText(" Cost : ₹ "+Application.currentveg.getAmount());
        Picasso.with(getApplicationContext()).load(Application.currentveg.getImage()).into(img_veg);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rel_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageCart.addNewProduct(Application.currentveg);
                item_count.setText(ManageCart.getNoFitem()+"");
                DrawerActivity.changecartvalue();
                Toast.makeText(VegDetailActivity.this, "Succesfully Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });

        txt_05kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void refrence() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rel_header = (RelativeLayout) findViewById(R.id.rel_header);
        rel_add_to_cart=(RelativeLayout)findViewById(R.id.rel_add_to_cart);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_search = (ImageView) findViewById(R.id.img_search);
        rel_cart = (RelativeLayout) findViewById(R.id.rel_cart);
        txt_percentage = (TextView) findViewById(R.id.txt_percentage);
        txt_rate = (TextView) findViewById(R.id.txt_rate);
        txt_price = (TextView) findViewById(R.id.txt_price);
        txt_veg_count = (TextView) findViewById(R.id.txt_veg_count);
        txt_veg_price = (TextView) findViewById(R.id.txt_veg_price);
        txt_veg_name = (TextView) findViewById(R.id.txt_veg_name);
        txt_veg_des = (TextView) findViewById(R.id.txt_veg_des);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_05kg = (TextView) findViewById(R.id.txt_05kg);
        txt_1kg = (TextView) findViewById(R.id.txt_1kg);
        txt_5kg = (TextView) findViewById(R.id.txt_5kg);
        txt_10kg = (TextView) findViewById(R.id.txt_10kg);
        txt_cost = (TextView) findViewById(R.id.txt_cost);
        item_count = (TextView) findViewById(R.id.item_count);
        img_minus = (TextView) findViewById(R.id.img_minus);
        img_cross = (TextView) findViewById(R.id.img_cross);
        img_equal = (TextView) findViewById(R.id.img_equal);
        img_plus = (TextView) findViewById(R.id.img_plus);
        img_veg=(ImageView)findViewById(R.id.img_veg);
        grid_price=(GridView)findViewById(R.id.grid_price);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

}
