package com.multipz.onveggy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multipz.onveggy.Activity.DrawerActivity;
import com.multipz.onveggy.Model.AddressModel;
import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.ManageCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 24-08-2017.
 */

public class CartAdpater extends BaseAdapter {

    Context context;
    ArrayList<VegModel> rowItems;
    AddressModel data;
    String countryList[];
    int flags[];
    LayoutInflater inflter;

    public CartAdpater(Context applicationContext, ArrayList<VegModel> items) {
        this.context=applicationContext;
        this.rowItems = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        Holder holder;
        final View rowView = view;

        if (rowView == null){
            holder = new Holder();
            view = inflter.inflate(R.layout.cart_item, null);

            holder.txt_item_plus=(TextView)view.findViewById(R.id.txt_item_plus);
            holder.txt_item_price=(TextView)view.findViewById(R.id.txt_item_price);
            holder.txt_item_minus=(TextView)view.findViewById(R.id.txt_item_minus);
            holder.txt_count_item=(TextView)view.findViewById(R.id.txt_count_item);
            holder.txt_item_name=(TextView)view.findViewById(R.id.txt_item_name);
            holder.img_close=(ImageView)view.findViewById(R.id.img_close);
            holder.cart_img=(ImageView)view.findViewById(R.id.cart_img);
            Application.setFontDefault((RelativeLayout)view.findViewById(R.id.rel_root));
            view.setTag(holder);
        }

        holder = (Holder) view.getTag();

        holder.txt_item_name.setText(rowItems.get(i).getName());
        holder.txt_item_price.setText("₹"+rowItems.get(i).getAmount());
        Picasso.with(context).load(rowItems.get(i).getImage()).into(holder.cart_img);

        final Holder finalHolder = holder;
        holder.txt_item_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int integer = Integer.parseInt(finalHolder.txt_count_item.getText().toString());
                if ((integer-1) >= 1) {
                    finalHolder.txt_count_item.setText(integer - 1 + "");
                }
            }
        });

        holder.txt_item_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int integer = Integer.parseInt(finalHolder.txt_count_item.getText().toString());
                finalHolder.txt_count_item.setText(integer + 1 + "");
//                txt_kgss.setText(String.valueOf(integer)+1);

            }
        });

        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ManageCart.removeProduct(rowItems.get(i))) {
                    rowItems.remove(i);
                    notifyDataSetChanged();
                    DrawerActivity.changecartvalue();
                }
                Toast.makeText(context, "Remove Item", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    class Holder{
        TextView txt_item_plus,txt_item_minus,txt_count_item,txt_item_name,txt_item_price;
        ImageView img_close,cart_img;
    }
}
