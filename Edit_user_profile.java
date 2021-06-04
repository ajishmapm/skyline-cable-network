package com.example.skylinecablenetworks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Edit_user_profile extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3,e4,post,pin,dis;
    Button btsave;
    String url,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);
        e4=(EditText)findViewById(R.id.editText5);

        post=(EditText)findViewById(R.id.p);
        pin=(EditText)findViewById(R.id.pin);
        dis=(EditText)findViewById(R.id.dis);
        btsave=(Button)findViewById(R.id.button2);
        btsave.setOnClickListener(this);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip","");
        url="http://"+ip+":5000/update_cus_profile";

        e1.setText(View_profile.name1);
        e2.setText(View_profile.place1);
        e3.setText(View_profile.email1);
        e4.setText(View_profile.phone1);
        post.setText(View_profile.post1);
        pin.setText(View_profile.pin1);
        dis.setText(View_profile.dis1);

    }
    @Override
    public void onClick(View view) {
        final String name=e1.getText().toString();
        final String address=e2.getText().toString();
        final String email=e3.getText().toString();
        final String phone_no=e4.getText().toString();

        final String post1=post.getText().toString();
        final String pin1=pin.getText().toString();
        final String dis1=dis.getText().toString();

        if(name.equalsIgnoreCase("")) {
            e1.setError("Required field");
        }else if(address.equalsIgnoreCase("")) {
            e2.setError("Required field");
        }else if(post1.equalsIgnoreCase("")) {
            post.setError("Required field");
        }
        else if(pin1.equalsIgnoreCase("")) {
            pin.setError("Required field");
        }
        else if(dis1.equalsIgnoreCase("")) {
            dis.setError("Required field");
        }
        else if(email.equalsIgnoreCase("")) {
            e3.setError("Required field");
        }else if(phone_no.equalsIgnoreCase("")) {
            e4.setError("required field");
        }


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                Toast.makeText(Edit_user_profile.this, " Successfully Updated", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), View_profile.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
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
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("name", name);//passing to python
                params.put("address", address);
                params.put("email",email);
                params.put("phone_no",phone_no);

                params.put("post",post1);
                params.put("pin",pin1);
                params.put("dis",dis1);
                params.put("lid",sh.getString("login_id",""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent( getApplicationContext(), View_profile.class ) );
        finish();
    }
}

