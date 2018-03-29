package com.multipz.onveggy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Model.VegModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;
import com.multipz.onveggy.Util.ManageCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 23-08-2017.
 */

public class VegItemAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<VegModel> veg_item;

    public VegItemAdapter(Context c, ArrayList<VegModel> veg_item) {
        mContext = c;
        this.veg_item = veg_item;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return veg_item.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return veg_item.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = view;
        Holder holder;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (v == null) {
            holder = new Holder();
            v = inflater.inflate(R.layout.veg_item, null);

            holder.rltCart = v.findViewById(R.id.rlt_addtocart);
            holder.rltItem = v.findViewById(R.id.rlt_quantity);
            holder.txt_rupees = (TextView) v.findViewById(R.id.txt_price);
            holder.txt_veg_name = (TextView) v.findViewById(R.id.txt_veg_name);
            holder.txt_veg_detail = (TextView) v.findViewById(R.id.txt_each);
            holder.img_veg_item = (ImageView) v.findViewById(R.id.img_veg_item);
            holder.img_minus = (TextView) v.findViewById(R.id.img_minus);
            holder.img_plus = (TextView) v.findViewById(R.id.img_plus);
            holder.txt_kgss = (TextView) v.findViewById(R.id.txt_kgss);
            Application.setFontDefault((RelativeLayout)v.findViewById(R.id.rel_root));

            final Holder finalHolder = holder;

            checkItemInCart(finalHolder, position);

            /*holder.rltItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeVisibleItems(finalHolder, position);
                }
            });*/

            Picasso.with(mContext).load(veg_item.get(position).getImage()).resize(250,250).into(holder.img_veg_item);
            holder.txt_veg_name.setText(veg_item.get(position).getName());
            holder.txt_rupees.setText("₹"+veg_item.get(position).getAmount()+" /kg");
//            holder.rltCart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    VegModel v = veg_item.get(position);
//                    v.setCartItem(0);
//                    addVisibleItems(finalHolder, position);
//                }
//            });
//
//            holder.img_minus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int integer = Integer.parseInt(finalHolder.txt_kgss.getText().toString());
//                    if ((integer - 1) >= 1) {
//                        VegModel v = veg_item.get(position);
//                        v.setCartItem(v.getCartItem() - 1);
//                        finalHolder.txt_kgss.setText(v.getCartItem() + "");
//                        addToCart(position);
//                    }else{
//                        removeVisibleItems(finalHolder, position);
//                    }
//                }
//            });
//
//            holder.img_plus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int integer = Integer.parseInt(finalHolder.txt_kgss.getText().toString());
//                    VegModel v = veg_item.get(position);
//                    v.setCartItem(v.getCartItem() + 1);
//                    finalHolder.txt_kgss.setText(v.getCartItem() + "");
//                    addToCart(position);
//                }
//            });
//
            v.setTag(holder);
        }

        holder = (Holder) v.getTag();

        return v;
    }

    synchronized void checkItemInCart(Holder finalHolder, int position) {
        veg_item.set(position, ManageCart.isAvailable(veg_item.get(position)));
        if (veg_item.get(position).getCartItem()>0){
            finalHolder.rltCart.setVisibility(View.GONE);
            finalHolder.rltItem.setVisibility(View.VISIBLE);
            finalHolder.txt_kgss.setText(veg_item.get(position).getCartItem() + "");
        }else{
            finalHolder.rltCart.setVisibility(View.VISIBLE);
            finalHolder.rltItem.setVisibility(View.GONE);
        }
    }

    synchronized void addVisibleItems(Holder finalHolder, int position){
        finalHolder.rltCart.setVisibility(View.GONE);
        finalHolder.rltItem.setVisibility(View.VISIBLE);
        addToCart(position);
    }

    synchronized void removeVisibleItems(Holder finalHolder, int position){
        finalHolder.rltCart.setVisibility(View.VISIBLE);
        finalHolder.rltItem.setVisibility(View.GONE);
        removeFromCart(position);
    }

    synchronized void addToCart(int position){
        ManageCart.addNewProduct(veg_item.get(position));
    }

    synchronized void removeFromCart(int position){
        VegModel v = veg_item.get(position);
        v.setCartItem(0);
        ManageCart.removeProduct(veg_item.get(position));
    }

    public class Holder {
        RelativeLayout rltCart, rltItem;
        TextView txt_rupees, txt_veg_name, txt_veg_detail, txt_kgss, img_plus, img_minus;
        ImageView img_veg_item;
    }


}
