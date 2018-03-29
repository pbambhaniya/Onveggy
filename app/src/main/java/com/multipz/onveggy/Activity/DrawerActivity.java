package com.multipz.onveggy.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multipz.onveggy.Adapter.VegItemAdapter;
import com.multipz.onveggy.Address.MyAddressActivity;
import com.multipz.onveggy.Cart.MyCartActivity;
import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.Order.MyOrderActivity;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.ManageCart;
import com.multipz.onveggy.Util.Shared;

import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {

    ArrayList<VegModel> veg_items;
    ImageView img_setting,img_back,img_menu,img_search;
    ImageView btn_bag;
    DrawerLayout drawer;
    GridView gridView;
    Shared shared;
    Context context;
    public static TextView item_count;
    RelativeLayout rel_logout,rel_rate,rel_bag;
    TextView txt_add_to_cart,txt_profile_name,txt_location,txt_my_info,txt_my_address,txt_my_order,txt_my_offer,txt_my_cart,txt_my_wallet,txt_my_other,txt_contactus,txt_share,txt_about,txt_rateus,txt_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        context=this;
        shared=new Shared(this);

        refrence();
        init();
    }

    private void init() {
        changecartvalue();
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer = (DrawerLayout) findViewById(R.id.rel_root);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        veg_items = new ArrayList<>();
        setStaticData();

        VegItemAdapter adapter = new VegItemAdapter(DrawerActivity.this, veg_items);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Application.currentveg = (VegModel) parent.getItemAtPosition(i);
                Intent veggy=new Intent(DrawerActivity.this,VegDetailActivity.class);
                startActivity(veggy);
            }
        });
        gridView.setAdapter(adapter);



        rel_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shared.putBoolean(Config.isLogin, false);
                Intent logout=new Intent(DrawerActivity.this,SigninSignupActivity.class);
                startActivity(logout);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,SettingActivity.class);
                startActivity(setting);
            }
        });

        txt_my_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,MyAddressActivity.class);
                startActivity(setting);
            }
        });

        txt_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,MyOrderActivity.class);
                startActivity(setting);
            }
        });

        txt_my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,MyCartActivity.class);
                startActivity(setting);
            }
        });

        txt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,AboutUsActivity.class);
                startActivity(setting);
            }
        });

        txt_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,ContactusFAQActivity.class);
                startActivity(setting);
            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent setting=new Intent(DrawerActivity.this,SearchActivity.class);
                startActivity(setting);
            }
        });

        btn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent i=new Intent(DrawerActivity.this,MyCartActivity.class);
                Toast.makeText(DrawerActivity.this, "Cart Selected", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        txt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                String appPackageName = DrawerActivity.this.getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Check out App at: https://play.google.com/store/apps/details?id=" + appPackageName);
                sendIntent.setType("text/plain");
                DrawerActivity.this.startActivity(sendIntent);
            }
        });

        rel_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent rate = new Intent(Intent.ACTION_VIEW, uri);

                rate.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(rate);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });
    }

    private void refrence() {
        Application.setFontDefault((DrawerLayout)findViewById(R.id.rel_root));
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img_menu=(ImageView)findViewById(R.id.img_menu);
        gridView=(GridView)findViewById(R.id.grid_veg_item);
        img_back=(ImageView)findViewById(R.id.img_back);
        img_setting=(ImageView)findViewById(R.id.img_setting);

        txt_profile_name=(TextView)findViewById(R.id.txt_profile_name);
        txt_add_to_cart=(TextView)findViewById(R.id.txt_add_to_cart);
        txt_location=(TextView)findViewById(R.id.txt_location);
        txt_my_info=(TextView)findViewById(R.id.txt_my_info);
        txt_my_address=(TextView)findViewById(R.id.txt_my_address);
        txt_my_order=(TextView)findViewById(R.id.txt_my_order);
        txt_my_offer=(TextView)findViewById(R.id.txt_my_offer);
        txt_my_cart=(TextView)findViewById(R.id.txt_my_cart);
        txt_my_wallet=(TextView)findViewById(R.id.txt_my_wallet);
        txt_my_other=(TextView)findViewById(R.id.txt_my_other);
        txt_contactus=(TextView)findViewById(R.id.txt_contactus);
        txt_share=(TextView)findViewById(R.id.txt_share);
        txt_about=(TextView)findViewById(R.id.txt_about);
        item_count=(TextView)findViewById(R.id.item_count);
        img_search=(ImageView)findViewById(R.id.img_search);
//        txt_rateus=(TextView)findViewById(R.id.txt_rateus);
        rel_logout=(RelativeLayout)findViewById(R.id.rel_logout);
        rel_rate=(RelativeLayout)findViewById(R.id.rel_rate);
        btn_bag=(ImageView)findViewById(R.id.btn_bag);

//        img_logout=(ImageView)findViewById(R.id.img_logout);
//        img_rateus=(ImageView)findViewById(R.id.img_rateus);
        drawer = (DrawerLayout) findViewById(R.id.rel_root);
//          txt_profile_name.setTypeface(Application.fonttitilliumRegular);
//        txt_location.setTypeface(Application.fonttitilliumRegular);
//        txt_my_info.setTypeface(Application.fonttitilliumRegular);
//        txt_my_address.setTypeface(Application.fonttitilliumRegular);
//        txt_my_order.setTypeface(Application.fonttitilliumRegular);
//        txt_my_offer.setTypeface(Application.fonttitilliumRegular);
//        txt_my_cart.setTypeface(Application.fonttitilliumRegular);
//        txt_my_wallet.setTypeface(Application.fonttitilliumRegular);
//        txt_my_other.setTypeface(Application.fonttitilliumRegular);
//        txt_contactus.setTypeface(Application.fonttitilliumRegular);
//        txt_share.setTypeface(Application.fonttitilliumRegular);
//        txt_about.setTypeface(Application.fonttitilliumRegular);
//        txt_rateus.setTypeface(Application.fonttitilliumRegular);
//        txt_add_to_cart.setTypeface(Application.fonttitilliumBold);
        Application.setFontDefault((DrawerLayout)findViewById(R.id.rel_root));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.rel_root);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            ExitDialogue();
        }
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.drawer, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        MenuItem item1 = menu.findItem(R.id.actionbar_item);
//        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
//        cart1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
//        textCart = cart1.findViewById(R.id.item_count);
//
//        //textCart = (TextView) menu.findItem(R.id.menu_find).getActionView();
//        cart1.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(DrawerActivity.this,MyCartActivity.class);
//                startActivity(i);
////                Toast.makeText(DrawerActivity.this, "Cart Selected", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Intent i=new Intent(DrawerActivity.this,SearchActivity.class);
//                startActivity(i);
////                Toast.makeText(DrawerActivity.this, "Search Selected", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }

    public static void changecartvalue(){
        item_count.setText(ManageCart.getNoFitem()+"");
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.actionbar_item:
////                Intent i=new Intent(DrawerActivity.this,My_Cart_Activity.class);
////                startActivity(i);
//                Toast.makeText(this, "Cart Selected", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void ExitDialogue(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to Exit");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
//                                shared.putBoolean(Config.isLogin, false);
//                                Intent logout=new Intent(DrawerActivity.this,SigninSignupActivity.class);
//                                startActivity(logout);
                                  finish();
//                                Toast.makeText(DrawerActivity.this,"Logout",Toast.LENGTH_LONG).show();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}
