package com.multipz.onveggy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.multipz.onveggy.Model.AddressModel;
import com.multipz.onveggy.Model.OrderModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

import java.util.ArrayList;

/**
 * Created by Admin on 24-08-2017.
 */

public class OrderAdapter extends BaseAdapter {

    Context context;
    ArrayList<OrderModel> rowItems;
    AddressModel data;
    String countryList[];
    int flags[];
    LayoutInflater inflter;

    public OrderAdapter(Context applicationContext, ArrayList<OrderModel> items) {
        this.rowItems = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder;
        View rowview=view;

        if (rowview==null){
            holder=new Holder();
            view = inflter.inflate(R.layout.order_item, null);
            Application.setFontDefault((RelativeLayout)view.findViewById(R.id.rel_root));
            view.setTag(holder);
        }
        holder = (Holder) view.getTag();
        return view;
    }

    class Holder{

    }
}
