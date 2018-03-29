
package com.multipz.onveggy.Address;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.multipz.onveggy.Adapter.AddressAdapter;
import com.multipz.onveggy.Model.AddressModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.Constant_method;
import com.multipz.onveggy.Util.ManageCart;
import com.multipz.onveggy.Util.MyAsyncTask;
import com.multipz.onveggy.Util.Shared;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyAddressActivity extends AppCompatActivity implements MyAsyncTask.AsyncInterface{

    ListView addresslist;
    ImageView img_back;
    private ArrayList<AddressModel> addressArrayList;
    View no_address;
    Shared shared;
    Context context;
    TextView txt_title,txt_map_address,txt_address_desc;
    RelativeLayout btn_add_Address;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
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

        if(Constant_method.checkConn(context)) {
            String param = "{\"user_id\":\"7\",\"action\":\""+Config.Action_get_address+"\"}";
            MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, this, param, Config.API_Get_Address);
            myAsyncTask.execute();
        }else {
            Toast.makeText(this, R.string.valid_connection, Toast.LENGTH_SHORT).show();
        }

        btn_add_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_address=new Intent(MyAddressActivity.this,AddNewAddressActivity.class);
                startActivity(new_address);
            }
        });

//        AddressModel address_model=new AddressModel();
//        address_model.setIs_default(true);

//        addressArrayList.add(new AddressModel());
//        addressArrayList.add(new AddressModel());
//        addressArrayList.add(address_model);
//        addressArrayList.add(new AddressModel());
//        addressArrayList.add(new AddressModel());
//        AddressAdapter customAdapter = new AddressAdapter(getApplicationContext(),addressArrayList);
//        addresslist.setAdapter(customAdapter);

//        addressArrayList=new ArrayList<>();
//        if (addressArrayList.size()>0){
//            no_address.setVisibility(View.GONE);
//        }else {
//            no_address.setVisibility(View.VISIBLE);
//        }
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));

        no_address=(View)findViewById(R.id.no_address);
        img_back=(ImageView)findViewById(R.id.img_back);
        btn_add_Address=(RelativeLayout)findViewById(R.id.btn_add_Address);
        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_map_address=(TextView)findViewById(R.id.txt_map_address);
        txt_address_desc=(TextView)findViewById(R.id.txt_address_desc);
        addresslist = (ListView) findViewById(R.id.list_address);
    }

    @Override
    public void onResponseService(String response, int flag) {

        int success;
        String msg;

        if (flag == Config.API_Get_Address) {

            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce",jsonObject.toString());
                success=jsonObject.getInt("status");
                msg=jsonObject.getString("msg");

                if (success==1) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    addressArrayList = new ArrayList<>();

                    for (int i1 = 0; i1 < jsonArray.length(); i1++) {
                        JSONObject c = jsonArray.getJSONObject(i1);
                        AddressModel addressModel = new Gson().fromJson(jsonArray.getJSONObject(i1).toString(), AddressModel.class);
                        addressArrayList.add(addressModel);
                    }
//                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    addressAdapter = new AddressAdapter(MyAddressActivity.this, addressArrayList);
                    addresslist.setAdapter(addressAdapter);

                }else {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
