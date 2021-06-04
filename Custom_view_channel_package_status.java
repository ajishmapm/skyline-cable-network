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

public class Custom_view_channel_package_status extends BaseAdapter implements View.OnClickListener {
    String[]  cid1,cname1,rate1,validity1,status1,date1,rid1;
    private Context context;


    public Custom_view_channel_package_status(Context applicationContext, String[] cid, String[] cable, String[] rate, String[] validity,  String[] date, String[] status, String[] rid) {
        this.context=applicationContext;
        this.cid1=cid;
        this.cname1=cable;
        this.rate1=rate;
        this.validity1=validity;

        this.status1=status;
        this.date1=date;
        this.rid1=rid;


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
            gridView=inflator.inflate(R.layout.activity_custom_view_channel_package_status,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView pn=(TextView)gridView.findViewById(R.id.p);

        TextView v=(TextView)gridView.findViewById(R.id.v);
        TextView r=(TextView)gridView.findViewById(R.id.r);

        TextView date2=(TextView)gridView.findViewById(R.id.d);

        TextView sta=(TextView)gridView.findViewById(R.id.status);

        Button b=(Button) gridView.findViewById(R.id.button8);

//        b1=(Button)gridView.findViewById(R.id.button18);
//        b1.setOnClickListener(this);


        pn.setTextColor(Color.BLACK);//color setting

        v.setTextColor(Color.BLACK);
        r.setTextColor(Color.RED);


        date2.setTextColor(Color.BLACK);

        sta.setTextColor(Color.GREEN);

//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//
//        b1.setTag(tid[i]);
//
        pn.setText(cname1[i]);
        v.setText(validity1[i]);
        r.setText(rate1[i]);


        date2.setText(date1[i]);

        sta.setText(status1[i]);
//        tv4.setText(sdate[i]);
//        tv5.setText(edate[i]);

        if(status1[i].equalsIgnoreCase("approve"))
        {
            b.setVisibility(View.VISIBLE);
        }
        else
        {
            b.setVisibility(View.INVISIBLE);
        }
        b.setTag(rid1[i]+","+rate1[i]);
        b.setOnClickListener(this);

        return gridView;

    }


    @Override
    public void onClick(View v) {
        String tid= v.getTag().toString();
        String[] y=tid.split(",");
        String rid=y[0];
        String r=y[1];
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor e1=sh.edit();
        e1.putString("rid", rid);
        e1.putString("amount", r);
        e1.commit();

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/collect_payment";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jj1 = new JSONObject(response);

                            if (jj1.getString("status").equalsIgnoreCase("ok")) {

                                Intent i=new Intent(context,Upipay.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                            }



                            else {
                                Toast.makeText(context, "Already Paid", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                Map<String, String> params = new HashMap<String, String>();

                params.put("rid",sh.getString("rid",""));
//                params.put("pid",sh.getString("pid",""));
//                params.put("lid",sh.getString("lid",""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);




    }
}
