package com.multipz.onveggy.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Adapter.ContactExpandableListAdapter;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactusFAQActivity extends AppCompatActivity {

    ContactExpandableListAdapter listAdapter;
    ExpandableListView expand_list_faq,expand_list_order;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    RelativeLayout btn_contact_faq;
    ImageView img_back;
    Context context;
    TextView txt_title_faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_faq);
        context=this;

        refrence();
        init();
    }

    private void init() {
        prepareListData();

        listAdapter = new ContactExpandableListAdapter(this, listDataHeader, listDataChild);
        expand_list_faq.setAdapter(listAdapter);
        expand_list_order.setAdapter(listAdapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_contact_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contact=new Intent(ContactusFAQActivity.this,ContactUsActivity.class);
                startActivity(contact);
            }
        });
    }

    private void refrence() {
        img_back=(ImageView)findViewById(R.id.img_back);
        btn_contact_faq=(RelativeLayout)findViewById(R.id.btn_contact_faq);
        txt_title_faq=(TextView)findViewById(R.id.txt_title_faq);
        expand_list_faq = (ExpandableListView) findViewById(R.id.expand_list_faq);
        expand_list_order = (ExpandableListView) findViewById(R.id.expand_list_order);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("What City Do You Operate in?");
        listDataHeader.add("Do you deliver to my location?");
        listDataHeader.add("What is the minimum order value?");
        listDataHeader.add("Can i track the status of my order?");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Surat");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("A.k Road");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("500Rs");

        List<String> order = new ArrayList<String>();
        order.add("Yes");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), order);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
