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

public class Cable_package_channels extends AppCompatActivity  {
    ListView lv;
    SharedPreferences sh;
    String ip,url;
    static     String[] chid,chname,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cable_package_channels);
        lv=(ListView)findViewById(R.id.li);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String id=sh.getString("login_id","");
        url=sh.getString("url","")+"cable_package_channels";

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
                                chid=new String[js.length()];
                                chname=new String[js.length()];
                                type=new String[js.length()];



                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    chid[i]=u.getString("channel_id");//dbcolumn name
                                    chname[i]=u.getString("channel_name");
                                    type[i]=u.getString("channel_type");




                                }
                                lv.setAdapter(new Custom_cable_package_channels(getApplicationContext(),chid,chname,type));//custom_view_service.xml and li is the listview object


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
                params.put("cid",View_cable_packages.cid[View_cable_packages.pos] );
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
        startActivity( new Intent( this, View_cable_packages.class ) );
        finish();
    }


}
