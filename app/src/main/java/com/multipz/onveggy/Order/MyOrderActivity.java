package com.multipz.onveggy.Order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Activity.DrawerActivity;
import com.multipz.onveggy.Adapter.OrderAdapter;
import com.multipz.onveggy.Model.OrderModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Shared;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {

    ImageView img_back;
    TextView txt_title;
    ListView Orderlist;
    ArrayList<OrderModel> orderItems;
    View no_order;
    RelativeLayout rel_btn_startshop,rel_btn_startshop_order;
    Context context;
    Shared shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__order);
        context=this;
        shared=new Shared(context);
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

        rel_btn_startshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start=new Intent(MyOrderActivity.this, DrawerActivity.class);
                startActivity(start);
            }
        });

        rel_btn_startshop_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start=new Intent(MyOrderActivity.this, DrawerActivity.class);
                startActivity(start);
            }
        });

        Orderlist = (ListView) findViewById(R.id.list_order);
        orderItems=new ArrayList<>();
        orderItems.add(new OrderModel());
        orderItems.add(new OrderModel());
        OrderAdapter orderAdapter = new OrderAdapter(getApplicationContext(),orderItems);
        Orderlist.setAdapter(orderAdapter);

        if (orderItems.size()>0){
            no_order.setVisibility(View.GONE);
        }else {
            no_order.setVisibility(View.VISIBLE);
        }
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
        img_back=(ImageView)findViewById(R.id.img_back);
        txt_title=(TextView)findViewById(R.id.txt_title);
        no_order=(View)findViewById(R.id.no_order);
        rel_btn_startshop=(RelativeLayout)findViewById(R.id.rel_btn_startshop);
        rel_btn_startshop_order=(RelativeLayout)findViewById(R.id.rel_btn_startshop_order);
    }
}
