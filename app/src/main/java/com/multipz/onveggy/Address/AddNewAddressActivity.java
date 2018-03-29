package com.multipz.onveggy.Address;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.multipz.onveggy.Activity.DrawerActivity;
import com.multipz.onveggy.Model.AddressModel;
import com.multipz.onveggy.Model.SigninModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.Constant_method;
import com.multipz.onveggy.Util.MyAsyncTask;
import com.multipz.onveggy.Util.Shared;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddNewAddressActivity extends AppCompatActivity implements MyAsyncTask.AsyncInterface {

    ImageView img_back;
    RelativeLayout btn_add_Address,btn_update_Address;
    TextView txt_title;
    RadioGroup radioGroup_address;
    RadioButton radio_home, radio_office, radio_other;
    EditText edit_label, edit_name, edit_office_no, edit_office_name, edit_locality;
    Context context;
    Shared shared;
    String id;
    private ArrayList<AddressModel> addressArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__address);
        context = this;
        shared = new Shared(context);

        refrence();
        init();

        if(getIntent().getBooleanExtra("update",false)){
            edit_name.setText(getIntent().getExtras().getString("name"));
            edit_office_no.setText(getIntent().getExtras().getString("office_no"));
            edit_office_name.setText(getIntent().getExtras().getString("street"));
//            radioGroup_address.setText(getIntent().getExtras().getString("street"));
            edit_locality.setText(getIntent().getExtras().getString("locality"));
            id=getIntent().getStringExtra("user_address_id");
            btn_add_Address.setVisibility(View.GONE);
            btn_update_Address.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        radioGroup_address.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int id = group.getCheckedRadioButtonId();
//                RadioButton btn = (RadioButton) findViewById(id);
//                Log.d("val", "" + btn.getText().toString());
//                Toast.makeText(context, ""+btn.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//          }
//        );


        btn_add_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String label=edit_label.getText().toString();
                String name = edit_name.getText().toString();
                String office_no = edit_office_no.getText().toString();
                String office_name = edit_office_name.getText().toString();
                String locality = edit_locality.getText().toString();
                final String radioAddressType =
                        ((RadioButton)findViewById(radioGroup_address.getCheckedRadioButtonId()))
                                .getText().toString();

//                if(label.contentEquals("")){
//                    Toast.makeText(AddNewAddressActivity.this, "Enter Label", Toast.LENGTH_SHORT).show();
//                }else
                if (name.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_name, Toast.LENGTH_SHORT).show();
                } else if (office_no.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_officeno, Toast.LENGTH_SHORT).show();
                } else if (office_name.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_officename, Toast.LENGTH_SHORT).show();
                } else if (locality.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_locality, Toast.LENGTH_SHORT).show();
                } else if (Constant_method.checkConn(context)) {
                    String param = "{\"address_id\":\"\",\"user_id\":\"7\",\"Address_type\":\"" + radioAddressType + "\",\"contact_Name\":\"" + name + "\",\"blockno\":\"" + office_no + "\",\"street\":\"" + office_name + "\",\"state\":\"gujrat\",\"city\":\"surat\",\"locality\":\"" + locality + "\",\"pincode\":\"741258\",\"latitude\":\"\",\"Longitude\":\"\",\"is_delivery_address\":\"0\",\"action\":\"addUpdate_address\"}";
                    MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, AddNewAddressActivity.this, param, Config.API_Add_Address);
                    myAsyncTask.execute();
                } else {
                    Intent new_address = new Intent(AddNewAddressActivity.this, MyAddressActivity.class);
                    startActivity(new_address);
                }
            }
        });

        btn_update_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String label=edit_label.getText().toString();
                String name = edit_name.getText().toString();
                String office_no = edit_office_no.getText().toString();
                String office_name = edit_office_name.getText().toString();
                String locality = edit_locality.getText().toString();
                final String radioAddressType =
                        ((RadioButton)findViewById(radioGroup_address.getCheckedRadioButtonId()))
                                .getText().toString();

//                if(label.contentEquals("")){
//                    Toast.makeText(AddNewAddressActivity.this, "Enter Label", Toast.LENGTH_SHORT).show();
//                }else
                if (name.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_name, Toast.LENGTH_SHORT).show();
                } else if (office_no.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_officeno, Toast.LENGTH_SHORT).show();
                } else if (office_name.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_officename, Toast.LENGTH_SHORT).show();
                } else if (locality.contentEquals("")) {
                    Toast.makeText(AddNewAddressActivity.this, R.string.valid_locality, Toast.LENGTH_SHORT).show();
                } else if (Constant_method.checkConn(context)) {
                    String param = "{\"address_id\":\""+id+"\",\"user_id\":\"7\",\"Address_type\":\"" + radioAddressType + "\",\"contact_Name\":\"" + name + "\",\"blockno\":\"" + office_no + "\",\"street\":\"" + office_name + "\",\"state\":\"gujrat\",\"city\":\"surat\",\"locality\":\"" + locality + "\",\"pincode\":\"741258\",\"latitude\":\"\",\"Longitude\":\"\",\"is_delivery_address\":\"0\",\"action\":\"addUpdate_address\"}";
                    MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, AddNewAddressActivity.this, param, Config.API_Update_Address);
                    myAsyncTask.execute();
                } else {
                    Intent new_address = new Intent(AddNewAddressActivity.this, MyAddressActivity.class);
                    startActivity(new_address);
                }
            }
        });
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout) findViewById(R.id.rel_root));

        img_back = (ImageView) findViewById(R.id.img_back);
        btn_add_Address = (RelativeLayout) findViewById(R.id.btn_add_Address);
        btn_update_Address = (RelativeLayout) findViewById(R.id.btn_update_Address);
        txt_title = (TextView) findViewById(R.id.txt_title);

        radioGroup_address = (RadioGroup) findViewById(R.id.radiogroup_address);
        radio_home = (RadioButton) findViewById(R.id.radio_home);
        radio_office = (RadioButton) findViewById(R.id.radio_office);
        radio_other = (RadioButton) findViewById(R.id.radio_other);

//        edit_label=(EditText)findViewById(R.id.edit_label);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_office_no = (EditText) findViewById(R.id.edit_office_no);
        edit_office_name = (EditText) findViewById(R.id.edit_office_name);
        edit_locality = (EditText) findViewById(R.id.edit_locality);
    }

    @Override
    public void onResponseService(String response, int flag) {
        int success;
        String msg;
        if (flag == Config.API_Add_Address) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce", jsonObject.toString());
                success = jsonObject.getInt("status");
                msg = jsonObject.getString("msg");

                if (success == 1) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    addressArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonLogin = jsonArray.getJSONObject(i);
                        AddressModel addressModel = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), AddressModel.class);
                        addressArrayList.add(addressModel);
                    }

                    Intent intent = new Intent(AddNewAddressActivity.this, MyAddressActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(flag == Config.API_Update_Address){
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce", jsonObject.toString());
                success = jsonObject.getInt("status");
                msg = jsonObject.getString("msg");

                if (success == 1) {
                    addressArrayList = new ArrayList<>();
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    AddressModel addressModel = new Gson().fromJson(jsonObject1.toString(), AddressModel.class);
                    addressArrayList.add(addressModel);

                    Intent intent = new Intent(AddNewAddressActivity.this, MyAddressActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
