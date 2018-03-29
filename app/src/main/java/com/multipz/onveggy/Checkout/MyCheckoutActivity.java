package com.multipz.onveggy.Checkout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Address.MyAddressActivity;
import com.multipz.onveggy.Cart.CartPaymentActivity;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Constant_method;
import com.multipz.onveggy.Util.Shared;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyCheckoutActivity extends AppCompatActivity {

    ImageView img_back;
    TextView txt_address,txt_office,txt_full_address,txt_edit_button,txt_title_Date,start_date,txt_enddate,txt_title_time,txt_morning,txt_afternoon,txt_evening,txt_name;
    RelativeLayout btn_add_Address;
    Shared shared;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__checkout);
        context=this;
        shared=new Shared(this);
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

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

        String todayAsString = dateFormat.format(today);
        String tomorrowAsString = dateFormat.format(tomorrow);

        start_date.setText(todayAsString);
        txt_enddate.setText(tomorrowAsString);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDate(view.getId());
            }
        });

        txt_enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDate(view.getId());
            }
        });

        txt_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDate(view.getId());
            }
        });

        txt_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDate(view.getId());
            }
        });

        txt_afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDate(view.getId());
            }
        });

        txt_edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent address=new Intent(MyCheckoutActivity.this, MyAddressActivity.class);
                startActivity(address);
            }
        });

        btn_add_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payment=new Intent(MyCheckoutActivity.this, CartPaymentActivity.class);
                startActivity(payment);
            }
        });
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));

        img_back=(ImageView)findViewById(R.id.img_back);
        txt_address=(TextView)findViewById(R.id.txt_address);
        txt_office=(TextView)findViewById(R.id.txt_office);
        txt_full_address=(TextView)findViewById(R.id.txt_full_address);
        txt_edit_button=(TextView)findViewById(R.id.txt_edit_button);
        txt_title_Date=(TextView)findViewById(R.id.txt_title_Date);
        start_date=(TextView)findViewById(R.id.start_date);
        txt_enddate=(TextView)findViewById(R.id.txt_enddate);
        txt_title_time=(TextView)findViewById(R.id.txt_title_time);
        txt_morning=(TextView)findViewById(R.id.txt_morning);
        txt_afternoon=(TextView)findViewById(R.id.txt_afternoon);
        txt_evening=(TextView)findViewById(R.id.txt_evening);
        txt_name=(TextView)findViewById(R.id.txt_name);
        btn_add_Address=(RelativeLayout)findViewById(R.id.btn_add_Address);
    }

    private void changeDate(int value){
        switch (value){
            case R.id.start_date:{
                start_date.setBackground(getResources().getDrawable(R.drawable.option_selected));
                txt_enddate.setBackground(getResources().getDrawable(R.drawable.rectangle));
                start_date.setTextColor(getResources().getColor(R.color.white));
                txt_enddate.setTextColor(getResources().getColor(R.color.gray));
                break;
            }
            case R.id.txt_enddate:{
                start_date.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_enddate.setBackground(getResources().getDrawable(R.drawable.option_selected));
                txt_enddate.setTextColor(getResources().getColor(R.color.white));
                start_date.setTextColor(getResources().getColor(R.color.gray));
                break;
            }

            case R.id.txt_morning:{
                txt_morning.setBackground(getResources().getDrawable(R.drawable.option_selected));
                txt_afternoon.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_evening.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_morning.setTextColor(getResources().getColor(R.color.white));
                txt_afternoon.setTextColor(getResources().getColor(R.color.gray));
                txt_evening.setTextColor(getResources().getColor(R.color.gray));
                break;
            }

            case R.id.txt_afternoon:{
                txt_morning.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_afternoon.setBackground(getResources().getDrawable(R.drawable.option_selected));
                txt_evening.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_morning.setTextColor(getResources().getColor(R.color.gray));
                txt_afternoon.setTextColor(getResources().getColor(R.color.white));
                txt_evening.setTextColor(getResources().getColor(R.color.gray));
                break;
            }

            case R.id.txt_evening:{
                txt_morning.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_afternoon.setBackground(getResources().getDrawable(R.drawable.rectangle));
                txt_evening.setBackground(getResources().getDrawable(R.drawable.option_selected));
                txt_morning.setTextColor(getResources().getColor(R.color.gray));
                txt_afternoon.setTextColor(getResources().getColor(R.color.gray));
                txt_evening.setTextColor(getResources().getColor(R.color.white));
                break;
            }
        }
    }
}
