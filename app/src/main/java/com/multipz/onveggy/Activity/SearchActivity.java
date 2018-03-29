package com.multipz.onveggy.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Adapter.VegItemAdapter;
import com.multipz.onveggy.Cart.MyCartActivity;
import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.ManageCart;
import com.multipz.onveggy.Util.Shared;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    GridView gridView;
    Shared shared;
    Context context;
    EditText edit_search_item;
    TextView item_count;
    ArrayList<VegModel> veg_items = new ArrayList<VegModel>();
    ArrayList<VegModel> temp_vegitem = new ArrayList<VegModel>();
//    ArrayList<VegModel> veg_items,temp_vegitem;
    ImageView img_back;
    VegItemAdapter adapter;
    RelativeLayout rel_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context=this;
        shared=new Shared(context);
        refrence();
        init();
    }

    private void init() {
        veg_items = new ArrayList<>();
        temp_vegitem = new ArrayList<>();
        setStaticData();

        item_count.setText(ManageCart.getNoFitem()+"");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Application.currentveg=temp_vegitem.get(i);
                Intent veggy=new Intent(SearchActivity.this,VegDetailActivity.class);
                startActivity(veggy);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart=new Intent(SearchActivity.this,MyCartActivity.class);
                startActivity(cart);
            }
        });

        adapter = new VegItemAdapter(SearchActivity.this, temp_vegitem);
        gridView.setAdapter(adapter);

        edit_search_item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence constraint, int i, int i1, int i2) {
                temp_vegitem.clear();
                if (constraint != null && constraint.toString().length() > 0) {
                    for (VegModel item : veg_items) {
                        if (item.getName().toString().toLowerCase().contains(constraint)) {
                            temp_vegitem.add(item);
                        }
                    }
                } else {
                    temp_vegitem.addAll(veg_items);
                }
                adapter = new VegItemAdapter(SearchActivity.this, temp_vegitem);
                gridView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void refrence() {
        edit_search_item=(EditText)findViewById(R.id.edit_search_item);
        gridView=(GridView)findViewById(R.id.grid_veg_item);
        img_back=(ImageView) findViewById(R.id.img_back);
        item_count=(TextView)findViewById(R.id.item_count);
        rel_cart=(RelativeLayout)findViewById(R.id.rel_cart);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

    private void setStaticData() {
//        for (int i=0;i<6;i++) {
        veg_items.add(new VegModel("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Onion_growing_shoots.jpg/220px-Onion_growing_shoots.jpg", "Onion", "12", "120", "7:00"));
        veg_items.add(new VegModel("http://www.freepngimg.com/download/tomato/10-tomato-png-image.png", "Tomato", "15", "200", "10:00"));
        veg_items.add(new VegModel("http://pngimg.com/uploads/garlic/garlic_PNG12800.png", "Garlik", "16", "100", "4:00"));
        veg_items.add(new VegModel("http://freewebpsd.com/images/FreeWebPsd/Product/Large/lady-s-finger-png-image_png00293.png", "Lady Finger", "10", "200", "2:00"));
        veg_items.add(new VegModel("http://freewebpsd.com/images/FreeWebPsd/Product/Large/onion-png-image_png00302.png", "Onion", "14", "220", "10:00"));
        veg_items.add(new VegModel("http://pngimg.com/uploads/cabbage/cabbage_PNG8809.png", "Cabbiage", "20", "300", "5:00"));
//        }
    }

}
