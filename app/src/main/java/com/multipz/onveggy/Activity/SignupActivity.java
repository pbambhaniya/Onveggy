package com.multipz.onveggy.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multipz.onveggy.Model.SigninModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.Constant_method;
import com.multipz.onveggy.Util.MyAsyncTask;
import com.multipz.onveggy.Util.Shared;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends Activity implements MyAsyncTask.AsyncInterface {

    private ViewPager pager_signup;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    boolean lastPageChange = false;
    Context context;
    private TextView txt_alredy_acc,txt_sign_up,txt_name,txt_desc_one,txt_desc_two;
    ImageView img_back;
    RelativeLayout rel_btn_signup;
    CircleImageView circle_img;
    Shared shared;
    EditText edit_email, edit_contact,edit_username,edit_password,edit_confirm_password;
    private ArrayList<SigninModel> SignupModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = this;
        shared=new Shared(this);

        refrence();
        init();
    }

    private void init() {
        layouts = new int[]{
                R.layout.signup_one,
                R.layout.signup_two,
        };

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        pager_signup.setAdapter(myViewPagerAdapter);
        pager_signup.addOnPageChangeListener(viewPagerPageChangeListener);

        txt_alredy_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignupActivity.this,SignInActivity.class);
                startActivity(i);
                finish();
            }
        });

        rel_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=edit_email.getText().toString();
                String contact=edit_contact.getText().toString();

                if (txt_name.getText().toString().contentEquals("Next")){

                    if(email.contentEquals("")){
                        Toast.makeText(context,R.string.email_txt, Toast.LENGTH_SHORT).show();
                    }else if(!Constant_method.isValidEmail(email)){
                        Toast.makeText(context, R.string.valid_email, Toast.LENGTH_SHORT).show();
                    }else if(contact.contentEquals("")){
                        Toast.makeText(context,R.string.mobile_txt, Toast.LENGTH_SHORT).show();
                    }else if(!Constant_method.isValidMobile(contact)){
                        Toast.makeText(context, R.string.valid_mobile_txt, Toast.LENGTH_SHORT).show();
                    } else {
                        pager_signup.setCurrentItem(1);
                    }
                }
                else
                {
                    String username=edit_username.getText().toString();
                    String password=edit_password.getText().toString();
                    String confirm_password=edit_confirm_password.getText().toString();

                    if(edit_username.length()==0){
                        Toast.makeText(context, R.string.valid_name, Toast.LENGTH_SHORT).show();
                    }else if(edit_password.length()==0){
                        Toast.makeText(context, R.string.valid_password, Toast.LENGTH_SHORT).show();
                    }else if(edit_confirm_password.length()==0){
                        Toast.makeText(context, R.string.valid_confirm_password, Toast.LENGTH_SHORT).show();
                    }
                    else if(!edit_password.getText().toString().equals(edit_confirm_password.getText().toString())) {
                        Toast.makeText(context, R.string.valid_confirm_pwd, Toast.LENGTH_SHORT).show();
                    }
                        else if (Constant_method.checkConn(context)) {
                        String param = "{\"userName\":\""+username+"\",\"password\":\""+confirm_password+"\",\"email\":\""+email+"\",\"mobile\":\""+contact+"\",\"profilePic\":\"\",\"token\":\"\",\"socialToken\":\"\",\"socialType\":\"N\",\"deviceType\":\"A\",\"action\":\""+Config.Action_signup+"\"}";
                        MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, SignupActivity.this, param, Config.API_Signup);
                        myAsyncTask.execute();
                        }
                    else {
//                        shared.putBoolean(Config.isLogin, true);
//                        Intent i=new Intent(SignupActivity.this,DrawerActivity.class);
//                        startActivity(i);
                        Toast.makeText(context, R.string.valid_connection, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void refrence() {
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        pager_signup = (ViewPager) findViewById(R.id.pager_signup);

        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        txt_alredy_acc = (TextView) findViewById(R.id.txt_alredy_acc);
        rel_btn_signup=(RelativeLayout)findViewById(R.id.rel_btn_signup);
        txt_name = (TextView) findViewById(R.id.txt_name);
//        txt_btn_signup.setTypeface(Application.fonttitilliumRegular);
//        txt_alredy_acc.setTypeface(Application.fonttitilliumRegular);
        img_back = (ImageView) findViewById(R.id.img_back);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return pager_signup.getCurrentItem() + i;
    }

    private ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            Log.e("call", "onpageselected");
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            Log.e("call", "onpagescroll");
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            Log.e("call", "onpagescrollstatechnage");

            int lastIdx = myViewPagerAdapter.getCount() - 1;

            int curItem = pager_signup.getCurrentItem();
            if (curItem == 0) {
                txt_name.setText("Next");

            } else {
                txt_name.setText("Sign up");
            }

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);


            if (position == 0) {
                edit_email = (EditText) view.findViewById(R.id.edit_email);
                edit_contact = (EditText)view. findViewById(R.id.edit_contact);
                txt_desc_one = (TextView) view.findViewById(R.id.txt_desc_one);
            } else {
                edit_username = (EditText) view.findViewById(R.id.edit_username);
                circle_img=(CircleImageView)view.findViewById(R.id.circle_img);
                edit_password = (EditText)view. findViewById(R.id.edit_password);
                edit_confirm_password = (EditText) view.findViewById(R.id.edit_confirm_password);
                txt_desc_two = (TextView) view.findViewById(R.id.txt_desc_two);
                circle_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage();
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    public boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(SignupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermission_camera(){
        int result = ContextCompat.checkSelfPermission(SignupActivity.this, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 144:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    circle_img.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                circle_img.setImageBitmap(thumbnail);
            }
        }
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo","Choose from Gallery","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    if (checkPermission()) {
                        if(checkPermission_camera()) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            startActivityForResult(intent, 1);
                        }else {
                            ActivityCompat.requestPermissions(SignupActivity.this,new String[]{Manifest.permission.CAMERA},145);
                        }
                    }else{
                        ActivityCompat.requestPermissions(SignupActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},144);
                    }
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    if (checkPermission()) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                    }else {
                        ActivityCompat.requestPermissions(SignupActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},144);
                    }
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onResponseService(String response, int flag) {
        int success;
        String msg;
        if (flag == Config.API_Signup) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce", jsonObject.toString());
                success=jsonObject.getInt("status");
                msg=jsonObject.getString("msg");

                if(success==1) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    SignupModelArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonLogin = jsonArray.getJSONObject(i);

                        SigninModel signinModel = new SigninModel();
                        signinModel.setCustomer_master_id(jsonLogin.getString("customer_master_id"));
                        signinModel.setUsername(jsonLogin.getString("username"));
                        signinModel.setPassword(jsonLogin.getString("password"));
                        signinModel.setEmail(jsonLogin.getString("email"));
                        signinModel.setMobile(jsonLogin.getString("mobile"));
                        signinModel.setProfile_pic(jsonLogin.getString("profile_pic"));
                        signinModel.setNotify_token(jsonLogin.getString("notify_token"));
                        signinModel.setSocial_token(jsonLogin.getString("social_token"));
                        signinModel.setSocial_type(jsonLogin.getString("social_type"));
                        signinModel.setDevice_type(jsonLogin.getString("device_type"));
                        SignupModelArrayList.add(signinModel);
                    }

                    shared.putBoolean(Config.isLogin, true);
                    Intent intent=new Intent(SignupActivity.this,DrawerActivity.class);
                    startActivity(intent);
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
