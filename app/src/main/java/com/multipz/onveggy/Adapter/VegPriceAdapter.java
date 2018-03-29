package com.multipz.onveggy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Model.VegpriceModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

import java.util.ArrayList;

/**
 * Created by Admin on 31-08-2017.
 */

public class VegPriceAdapter extends BaseAdapter {

    private Context context;
    private String name,price;
    ArrayList<VegpriceModel> vegpriceModels;
    LayoutInflater inflter;

    public VegPriceAdapter(Context applicationContext, ArrayList<VegpriceModel> items) {
        this.vegpriceModels = items;
        this.context=applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return vegpriceModels.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view=convertView;
        Holder holder;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(context);
            holder=new Holder();
            view = inflater.inflate(R.layout.grid_price, null);
            holder.txt_vegprice = (TextView) view.findViewById(R.id.txt_vegprice);
            Application.setFontDefault((RelativeLayout)view.findViewById(R.id.rel_root));
            view.setTag(holder);
        }

        holder = (Holder) view.getTag();
        holder.txt_vegprice.setText(vegpriceModels.get(position).getName());

        if(vegpriceModels.get(position).is_check()){
            holder.txt_vegprice.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dark_border));
        }else {
            holder.txt_vegprice.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border));
        }
        final Holder finalHolder = holder;
        holder.txt_vegprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetChecked(position);
                notifyDataSetChanged();
            }

            private void resetChecked(int positi) {

                for (int i=0; i<vegpriceModels.size(); i++) {
                    if (i == positi) {
                        vegpriceModels.get(i).setIs_check(true);
                    } else {
                        vegpriceModels.get(i).setIs_check(false);
                    }
                    vegpriceModels.set(i, vegpriceModels.get(i));
                }
            }
        });
        return view;
    }

    class Holder{
        TextView txt_vegprice;
    }
}
