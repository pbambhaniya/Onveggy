package com.multipz.onveggy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.Address.AddNewAddressActivity;
import com.multipz.onveggy.Model.AddressModel;
import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

import java.util.ArrayList;

public class AddressAdapter extends BaseAdapter {

    Context context;
    ArrayList<AddressModel> rowItems;
    AddressModel data;
    LayoutInflater inflter;

    public AddressAdapter(Context applicationContext, ArrayList<AddressModel> items) {
        this.rowItems = items;
        this.context=applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return rowItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder;
        View rowView = view;

        if(rowView==null){
            holder = new Holder();
            view = inflter.inflate(R.layout.address_item, null);
            holder.img_true=(ImageView) view.findViewById(R.id.img_right);
            holder.txt_address_type=(TextView) view.findViewById(R.id.txt_address_type);
            holder.txt_receivername=(TextView) view.findViewById(R.id.txt_receivername);
            holder.txt_street=(TextView) view.findViewById(R.id.txt_street);
            holder.txt_blockno=(TextView) view.findViewById(R.id.txt_blockno);
            holder.txt_pincode=(TextView) view.findViewById(R.id.txt_pincode);
            holder.txt_state=(TextView) view.findViewById(R.id.txt_state);
            holder.txt_area=(TextView) view.findViewById(R.id.txt_area);
            holder.txt_city=(TextView) view.findViewById(R.id.txt_city);
            holder.rel_edit_address=(CardView)view.findViewById(R.id.card_edit_address);
            Application.setFontDefault((RelativeLayout)view.findViewById(R.id.rel_root));
            view.setTag(holder);
        }
        holder = (Holder) view.getTag();

        data = rowItems.get(i);

        holder.txt_address_type.setText(data.getAddress_type());
        holder.txt_receivername.setText(data.getReceivername());
        holder.txt_blockno.setText(data.getBlockno()+",");
        holder.txt_street.setText(data.getStreet()+",");
        holder.txt_pincode.setText(data.getPincode()+",");
        holder.txt_state.setText(data.getState()+".");
        holder.txt_area.setText(data.getLocality()+",");
        holder.txt_city.setText(data.getCity()+",");

        holder.rel_edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressModel item = rowItems.get(i);
                Intent intent = new Intent(context, AddNewAddressActivity.class);
                intent.putExtra("name", item.getReceivername());
                intent.putExtra("user_address_id",item.getUser_address_id());
                intent.putExtra("office_no", item.getBlockno());
                intent.putExtra("street", item.getStreet());
                intent.putExtra("address_type", item.getAddress_type());
                intent.putExtra("locality", item.getLocality());
                intent.putExtra("update", true);
                context.startActivity(intent);
            }
        });

        if(data.is_default() == true){
            holder.img_true.setVisibility(View.VISIBLE);
        }else {
            holder.img_true.setVisibility(View.GONE);
        }

        return view;
    }

    class Holder{
        ImageView img_true;
        TextView txt_address_type,txt_receivername,txt_blockno,txt_street,txt_pincode,txt_state,txt_area,txt_city;
        CardView rel_edit_address;
    }

}
