package com.example.skylinecablenetworks;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class internet_channel_req_status extends AppCompatActivity  {
    ListView lv;
    SharedPreferences sh;
    String ip,url;
    static     String[] cid,pname,speed,rate,bundle,channels,provider,rid,status;
    public  static  int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_internet_channel_package);
        lv=(ListView)findViewById(R.id.li);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String id=sh.getString("login_id","");
        url=sh.getString("url","")+"internet_channel_req_status";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                      Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");//from python
                                cid=new String[js.length()];
                                provider=new String[js.length()];
                                pname=new String[js.length()];
                                rate=new String[js.length()];
                                speed=new String[js.length()];
                                bundle=new String[js.length()];
                                channels=new String[js.length()];
                                rid=new String[js.length()];
                                status=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    cid[i]=u.getString("internet_channel_id");//dbcolumn name
                                    provider[i]=u.getString("provider");
                                    pname[i]=u.getString("plan_name");
                                    rate[i]=u.getString("starting_price");
                                    speed[i]=u.getString("max_download_speeds");
                                    bundle[i]=u.getString("bundle_savings");
                                    channels[i]=u.getString("tv_channels");
                                    rid[i]=u.getString("customer_request_id");
                                    status[i]=u.getString("status");



                                }
                                lv.setAdapter(new Custom_view_internet_channel_req_status(getApplicationContext(),cid,pname,rate,speed,bundle,channels,provider,rid,status));//custom_view_service.xml and li is the listview object


                            }

                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
params.put("lid",sh.getString("login_id",""));
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




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent( getApplicationContext(), home.class ) );
        finish();
    }
}
