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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.Constant_method;
import com.multipz.onveggy.Util.MyAsyncTask;
import com.multipz.onveggy.Util.Shared;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends Activity implements MyAsyncTask.AsyncInterface{

    EditText edit_username,edit_email,edit_phone_number,edit_password,edit_confirm_password;
    ImageView img_back,img_edit_profile;
    CircleImageView img_profile;
    RelativeLayout rel_btn_add;
    Context context;
    Shared shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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

        rel_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edit_username.getText().toString();
                String email=edit_email.getText().toString();
                String phone=edit_phone_number.getText().toString();
                String password=edit_password.getText().toString();
                String confirm_password=edit_confirm_password.getText().toString();

                if(username.contentEquals("")){
                    Toast.makeText(SettingActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }else if(!Constant_method.isValidEmail(email)){
                    Toast.makeText(SettingActivity.this, "Email not valid", Toast.LENGTH_SHORT).show();
                }else if(!Constant_method.isValidMobile(phone)){
                    Toast.makeText(SettingActivity.this, "Phoneno not valid", Toast.LENGTH_SHORT).show();
                }else if(password.contentEquals("")){
                    Toast.makeText(SettingActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }else if(confirm_password.contentEquals("")){
                    Toast.makeText(SettingActivity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }
//                else if (Constant_method.checkConn(context)) {
//                    String param = "{\"mobile\":\"" + username + "\",\"email\":\""+email+"\",\"contact\":\""+phone+" \",\"password\":\"" + password + "\",\"confirm_password\":\""+confirm_password+"\",\"action\":\""+Config.Action_setting+"\"}";
//                    MyAsyncTask myAsyncTask = new MyAsyncTask(Config.MAIN_API, SettingActivity.this, param, Config.API_Setting);
//                    myAsyncTask.execute();
//                }
                else {
                    Intent i=new Intent(SettingActivity.this,DrawerActivity.class);
                    startActivity(i);
                    Toast.makeText(SettingActivity.this, "Sent Succesfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        img_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void refrence() {
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
        img_back=(ImageView)findViewById(R.id.img_back);
        img_profile=(CircleImageView) findViewById(R.id.img_profile);
        img_edit_profile=(ImageView)findViewById(R.id.img_edit_profile);
        edit_username=(EditText)findViewById(R.id.edit_username);
        edit_email=(EditText)findViewById(R.id.edit_email);
        edit_phone_number=(EditText)findViewById(R.id.edit_phone_number);
        edit_password=(EditText)findViewById(R.id.edit_password);
        edit_confirm_password=(EditText)findViewById(R.id.edit_confirm_password);
        rel_btn_add=(RelativeLayout)findViewById(R.id.rel_btn_add);
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("Add Profile Photo!");
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
                            ActivityCompat.requestPermissions(SettingActivity.this,new String[]{Manifest.permission.CAMERA},145);
                        }
                    }else{
                        ActivityCompat.requestPermissions(SettingActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},144);
                    }
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    if (checkPermission()) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                    }else {
                        ActivityCompat.requestPermissions(SettingActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},144);
                    }
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermission_camera(){
        int result = ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.CAMERA);
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
                    //new NewWordAdd().execute();
                    //permission granted successfully

                } else {

                    //permission denied

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

                    img_profile.setImageBitmap(bitmap);

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
                img_profile.setImageBitmap(thumbnail);
            }
        }
    }

    @Override
    public void onResponseService(String response, int flag) {
        int success;
        String msg;
        if (flag == Config.API_Setting) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.e("Responce", jsonObject.toString());
                success=jsonObject.getInt("status");
                msg=jsonObject.getString("msg");

                if(success==1) {
                    Intent intent=new Intent(SettingActivity.this,DrawerActivity.class);
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
