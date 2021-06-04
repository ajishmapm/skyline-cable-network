package com.example.skylinecablenetworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Custom_view_user_internet_package extends BaseAdapter implements View.OnClickListener {
    String[] pid1,pname1,rate1,validity1,description1,data2,speed1,name1,phone1,status1,date1,rid1,place1,post1,pin1,district1;
    private Context context;


    public Custom_view_user_internet_package(Context applicationContext, String[] pid, String[] pname, String[] rate, String[] validity, String[] description, String[] data1, String[] speed, String[] cname, String[] phone, String[] date, String[] status,String[] rid,String[] place,String[] post,String[] pin,String[] district) {
        this.context=applicationContext;
        this.pid1=pid;
        this.pname1=pname;
        this.rate1=rate;
        this.validity1=validity;
        this.description1=description;
        this.data2=data1;
        this.speed1=speed;
        this.name1=cname;
        this.phone1=phone;
        this.status1=status;
        this.date1=date;
        this.rid1=rid;
        this.place1=place;
        this.post1=post;
        this.pin1=pin;
        this.district1=district;

    }



    @Override
    public int getCount() {
        return rid1.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_user_internet_package,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView pn=(TextView)gridView.findViewById(R.id.p);
        TextView speed2=(TextView)gridView.findViewById(R.id.s);
        TextView v=(TextView)gridView.findViewById(R.id.v);
        TextView r=(TextView)gridView.findViewById(R.id.r);
        TextView d=(TextView)gridView.findViewById(R.id.d);
        TextView des=(TextView)gridView.findViewById(R.id.des);
        TextView c=(TextView)gridView.findViewById(R.id.c);
        TextView date2=(TextView)gridView.findViewById(R.id.date);
        TextView add=(TextView)gridView.findViewById(R.id.add);
        TextView ph=(TextView)gridView.findViewById(R.id.ph);
        TextView sta=(TextView)gridView.findViewById(R.id.status);
        Button b=(Button) gridView.findViewById(R.id.button8);


//        b1=(Button)gridView.findViewById(R.id.button18);
//        b1.setOnClickListener(this);


        pn.setTextColor(Color.BLACK);//color setting
        speed2.setTextColor(Color.BLACK);
        v.setTextColor(Color.BLACK);
        r.setTextColor(Color.RED);
        d.setTextColor(Color.BLACK);
        des.setTextColor(Color.BLACK);
        c.setTextColor(Color.BLACK);
        date2.setTextColor(Color.BLACK);
        add.setTextColor(Color.BLACK);
        ph.setTextColor(Color.BLACK);
        sta.setTextColor(Color.GREEN);

//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//
//        b1.setTag(tid[i]);
//
        pn.setText(pname1[i]);
        speed2.setText(speed1[i]);
        v.setText(validity1[i]);
        r.setText(rate1[i]);
        d.setText(data2[i]);
        des.setText(description1[i]);
        c.setText(name1[i]);
        add.setText(place1[i]+","+post1[i]+","+pin1[i]+","+district1[i]);
        date2.setText(date1[i]);
        ph.setText(phone1[i]);
        sta.setText(status1[i]);

        if(status1[i].equalsIgnoreCase("cash_collected"))
        {
            b.setVisibility(View.INVISIBLE);
        }
        else
        {
            b.setVisibility(View.VISIBLE);
        }

b.setTag(rid1[i]);
        b.setOnClickListener(this);

        return gridView;

    }


    @Override
    public void onClick(View v) {
        String tid= v.getTag().toString();
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor e1=sh.edit();
        e1.putString("rid", tid);
        e1.commit();

        Intent i=new Intent(context,Collect_payment.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
