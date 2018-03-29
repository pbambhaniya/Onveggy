package com.multipz.onveggy.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

public class SigninSignupActivity extends AppCompatActivity {

    private ViewPager pager_welcome;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    boolean lastPageChange = false;
    Context context;
    ImageView img_appname;
    TextView txt_name;
    RelativeLayout rel_signin,rel_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);

        context = this;
        refrence();
        init();
    }

    private void init() {
        layouts = new int[]{
                R.layout.pager_one,
                R.layout.pager_two,
                R.layout.pager_three};

        rel_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninSignupActivity.this,SocialSignUpActivity.class);
                intent.putExtra("click","signin");
                startActivity(intent);
            }
        });

        rel_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninSignupActivity.this,SocialSignUpActivity.class);
                intent.putExtra("click","signup");
                startActivity(intent);
            }
        });

        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        pager_welcome.setAdapter(myViewPagerAdapter);
        pager_welcome.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    private void refrence() {
        pager_welcome = (ViewPager) findViewById(R.id.pager_welcome);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        txt_name = (TextView) findViewById(R.id.txt_name);
        img_appname=(ImageView)findViewById(R.id.img_appname);
        rel_signin = (RelativeLayout) findViewById(R.id.rel_signin);
        rel_signup = (RelativeLayout) findViewById(R.id.rel_signup);
        Application.setFontDefault((RelativeLayout)findViewById(R.id.rel_root));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
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
        return pager_welcome.getCurrentItem() + i;
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

            int curItem = pager_welcome.getCurrentItem();
            if (curItem == lastIdx && arg0 == 1) {
                lastPageChange = true;

                // i put this here since onPageScroll gets called a couple of times.
                lastPageChange = false;
            } else {
                lastPageChange = false;
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
        TextView txt_welcome1, txt_welcome2, txt_welcome3;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            txt_welcome1 = (TextView) view.findViewById(R.id.txt_pager1);
            txt_welcome2 = (TextView) view.findViewById(R.id.txt_pager2);
            txt_welcome3 = (TextView) view.findViewById(R.id.txt_pager3);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
