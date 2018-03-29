package com.multipz.onveggy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Application;

import static com.multipz.onveggy.R.id.img_minus;
import static com.multipz.onveggy.R.id.img_plus;
import static com.multipz.onveggy.R.id.img_veg_item;
import static com.multipz.onveggy.R.id.txt_kgss;
import static com.multipz.onveggy.R.id.txt_veg_detail;
import static com.multipz.onveggy.R.id.txt_veg_name;

/**
 * Created by Admin on 23-08-2017.
 */

public class VegItemlistAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] veg_name;
    private final String[] rupee;
    private final int[] Imageid;

    public VegItemlistAdapter(Context c, String[] veg_name, String[] rupee, int[] Imageid) {
        mContext = c;
        this.Imageid = Imageid;
        this.veg_name = veg_name;
        this.rupee = rupee;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return veg_name.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowview = view;
        final Holder holder;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (rowview == null) {
            holder = new Holder();
            view = inflater.inflate(R.layout.veg_item_list, null);
            holder.txt_rupees = (TextView) view.findViewById(R.id.txt_price);
            holder.txt_veg_name = (TextView) view.findViewById(txt_veg_name);
            holder.txt_veg_detail = (TextView) view.findViewById(txt_veg_detail);
            holder.img_veg_item = (ImageView) view.findViewById(img_veg_item);
            holder.img_minus = (TextView) view.findViewById(img_minus);
            holder.img_plus = (TextView) view.findViewById(img_plus);
            holder.txt_kgss = (TextView) view.findViewById(txt_kgss);

            holder.img_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int integer = Integer.parseInt(holder.txt_kgss.getText().toString());
                    if ((integer - 1) >= 1) {
                        holder.txt_kgss.setText(integer - 1 + "");
                    }
                }
            });
            holder.img_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int integer = Integer.parseInt(holder.txt_kgss.getText().toString());
                    holder.txt_kgss.setText(integer + 1 + "");
//                txt_kgss.setText(String.valueOf(integer)+1);
                }
            });
//            holder.txt_rupees.setTypeface(Application.fonttitilliumRegular);
//            holder.txt_veg_name.setTypeface(Application.fonttitilliumRegular);
//            holder.txt_veg_detail.setTypeface(Application.fonttitilliumRegular);
//            holder.txt_kgss.setTypeface(Application.fonttitilliumRegular);
            holder.txt_veg_name.setText(veg_name[position]);
            holder.img_veg_item.setImageResource(Imageid[position]);
            holder.txt_rupees.setText(rupee[position]);
            Application.setFontDefault((RelativeLayout)view.findViewById(R.id.rel_root));
        }
        return view;
    }

    public class Holder {
        TextView txt_rupees, txt_veg_name, txt_veg_detail, txt_kgss,img_minus,img_plus;
        ImageView img_veg_item;
    }

}
