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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Custom_collector_view_internet_channel extends BaseAdapter implements View.OnClickListener {
    String[] cid1,pname1,rate1,speed1,bundle1,channels1,provider1,rid1,status1,phone1,place1,post1,pin1,district1,cname1,date1;
    private Context context;

    public Custom_collector_view_internet_channel(Context applicationContext, String[] cid, String[] pname, String[] rate, String[] speed, String[] bundle, String[] channels, String[] provider, String[] status, String[] cname, String[] place, String[] post, String[] pin, String[] district, String[] phone, String[] date) {
        this.context=applicationContext;
        this.cid1=cid;
        this.pname1=pname;
        this.rate1=rate;
        this.speed1=speed;
        this.bundle1=bundle;
        this.channels1=channels;
        this.provider1=provider;
        this.status1=status;
        this.phone1=phone;
        this.date1=date;
        this.cname1=cname;
        this.phone1=phone;
        this.place1=place;
        this.post1=post;
        this.pin1=pin;
        this.district1=district;

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
            gridView=inflator.inflate(R.layout.activity_custom_collector_view_internet_channel,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView pn=(TextView)gridView.findViewById(R.id.p);

        TextView v=(TextView)gridView.findViewById(R.id.v);
        TextView r=(TextView)gridView.findViewById(R.id.r);
        TextView pr=(TextView)gridView.findViewById(R.id.pro);
        TextView bu=(TextView)gridView.findViewById(R.id.b);
        TextView ch=(TextView)gridView.findViewById(R.id.ch);
        TextView st=(TextView)gridView.findViewById(R.id.st);
        TextView c=(TextView)gridView.findViewById(R.id.c);
        TextView date2=(TextView)gridView.findViewById(R.id.date);
        TextView add=(TextView)gridView.findViewById(R.id.add);
        TextView ph=(TextView)gridView.findViewById(R.id.ph);

        Button b=(Button) gridView.findViewById(R.id.button8);


//        b1=(Button)gridView.findViewById(R.id.button18);
//        b1.setOnClickListener(this);


        pn.setTextColor(Color.BLACK);//color setting

        v.setTextColor(Color.BLACK);
        r.setTextColor(Color.BLACK);
        pr.setTextColor(Color.BLACK);
        bu.setTextColor(Color.BLACK);
        ch.setTextColor(Color.BLACK);
        st.setTextColor(Color.GREEN);
        c.setTextColor(Color.BLACK);
        date2.setTextColor(Color.BLACK);
        add.setTextColor(Color.BLACK);
        ph.setTextColor(Color.BLACK);
//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//
//        b1.setTag(tid[i]);
//
        pn.setText(pname1[i]);

        v.setText(speed1[i]);
        r.setText(rate1[i]);
        pr.setText(provider1[i]);
        bu.setText(bundle1[i]);
        ch.setText(channels1[i]);
        st.setText(status1[i]);
        c.setText(cname1[i]);
        add.setText(place1[i]+","+post1[i]+","+pin1[i]+","+district1[i]);
        date2.setText(date1[i]);
        ph.setText(phone1[i]);



        if(status1[i].equalsIgnoreCase("cash_collected"))
        {
            b.setVisibility(View.INVISIBLE);
        }
        else
        {
            b.setVisibility(View.VISIBLE);
        }

        b.setTag(cid1[i]);
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
