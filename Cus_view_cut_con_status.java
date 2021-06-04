package com.example.skylinecablenetworks;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Cus_view_cut_con_status extends BaseAdapter {
    String[] uname1,feed1,date1,status1;
    private Context context;


    public Cus_view_cut_con_status(Context applicationContext, String[] uname, String[] feed, String[] date, String[] status) {
        this.context=applicationContext;
        this.uname1=uname;
        this.feed1=feed;
        this.date1=date;
        this.status1=status;

    }
    @Override
    public int getCount() {
        return uname1.length;
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
            gridView=inflator.inflate(R.layout.activity_cus_view_cut_con_status,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView46);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView49);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView45);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView5);


//        b1=(Button)gridView.findViewById(R.id.button18);
//        b1.setOnClickListener(this);


        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//
//        b1.setTag(tid[i]);
//
        tv1.setText(uname1[i]);
        tv2.setText(feed1[i]);
        tv3.setText(date1[i]);
        tv4.setText(status1[i]);
//        tv4.setText(sdate[i]);
//        tv5.setText(edate[i]);

        return gridView;

    }



}
