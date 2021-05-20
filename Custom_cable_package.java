package com.example.skylinecablenetworks;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_cable_package extends BaseAdapter {
    String[] cid1,cname1,rate1,validity1;
    private Context context;


    public Custom_cable_package(Context applicationContext, String[] cid, String[] cname, String[] rate, String[] validity) {
        this.context=applicationContext;
        this.cid1=cid;
        this.cname1=cname;
        this.rate1=rate;
        this.validity1=validity;

    }
    @Override
    public int getCount() {
        return cid1.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.view_feed, null);
            gridView=inflator.inflate(R.layout.custom_cable_packages,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView pn=(TextView)gridView.findViewById(R.id.p);

        TextView v=(TextView)gridView.findViewById(R.id.v);
        TextView r=(TextView)gridView.findViewById(R.id.r);



//        b1=(Button)gridView.findViewById(R.id.button18);
//        b1.setOnClickListener(this);


        pn.setTextColor(Color.BLACK);//color setting

        v.setTextColor(Color.BLACK);
        r.setTextColor(Color.BLACK);

//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//
//        b1.setTag(tid[i]);
//
        pn.setText(cname1[i]);

        v.setText(validity1[i]);
        r.setText(rate1[i]);

//        tv4.setText(sdate[i]);
//        tv5.setText(edate[i]);

        return gridView;

    }



}
