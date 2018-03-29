package com.multipz.onveggy.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.multipz.onveggy.Activity.VegDetailActivity;
import com.multipz.onveggy.Adapter.VegItemAdapter;
import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.Shared;

import java.util.ArrayList;

/**
 * Created by Admin on 25-08-2017.
 */

public class HomeProduct extends Fragment {

    GridView gridView;
    ArrayList<VegModel> veg_items;
    Context context;
    View rootView;
    Shared shared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fg_home_product, container, false);
        context = getActivity();
        shared=new Shared(context);
        init();
        return rootView;
    }

    private void init() {
        veg_items = new ArrayList<>();
        setStaticData();
        VegItemAdapter adapter = new VegItemAdapter(context, veg_items);
        gridView = (GridView) rootView.findViewById(R.id.grid_veg_item);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent veggy = new Intent(context, VegDetailActivity.class);
                startActivity(veggy);
            }
        });
        gridView.setAdapter(adapter);
        Application.setFontDefault((RelativeLayout)rootView.findViewById(R.id.rel_root));
    }

    private void setStaticData() {
        for (int i = 0; i < 6; i++) {
            veg_items.add(new VegModel("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Onion_growing_shoots.jpg/220px-Onion_growing_shoots.jpg", "Onion" + i, "12", "120", "7:00"));
        }
    }

}
