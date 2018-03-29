package com.multipz.onveggy.Util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multipz.onveggy.Model.VegModel;

public class Application extends android.app.Application {

    public static Shared shared;
    public static Typeface fonttitilliumRegular,fonttitilliumBold;
    public static Typeface FontCoral;
    public static VegModel currentveg;

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "Titillium/Titillium-Regular.ttf");
        fonttitilliumRegular = Typeface.createFromAsset(getAssets(), "Titillium/Titillium-Regular.ttf");
        fonttitilliumBold = Typeface.createFromAsset(getAssets(), "Titillium/Titillium-Bold.ttf");
        FontCoral = Typeface.createFromAsset(getAssets(), "corals/TTCoralsThinDEMO.ttf");
        shared = new Shared(this);
    }

    public static void setFontDefault(ViewGroup group) {
        setFont(group, Application.fonttitilliumRegular);
    }

    public static void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View v;
        for(int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if(v instanceof TextView)
                ((TextView)v).setTypeface(font);
            else if(v instanceof ViewGroup)
                setFont((ViewGroup)v, font);
        }
    }
}
