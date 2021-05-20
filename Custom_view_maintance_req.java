package com.example.skylinecablenetworks;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_view_maintance_req extends BaseAdapter {


    private Context context;
    String[] msg1,date1,status1;

    public Custom_view_maintance_req(Context applicationContext, String[] msg, String[] date, String[] status) {

        this.context=applicationContext;
        this.msg1=msg;
        this.date1=date;
        this.status1=status;




    }

    @Override
    public int getCount() {
        return date1.length;
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
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_view_maintanance,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }


        TextView date2=(TextView)gridView.findViewById(R.id.textView17);
        TextView msg2=(TextView)gridView.findViewById(R.id.textView19);
        TextView status2=(TextView)gridView.findViewById(R.id.textView21);





        date2.setTextColor(Color.BLACK);
        msg2.setTextColor(Color.BLACK);
        status2.setTextColor(Color.BLACK);





        date2.setText(date1[i]);
        msg2.setText(msg1[i]);
        status2.setText(status1[i]);



//
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String url=sh.getString("url","")+photo[i];
////        Toast.makeText(context,url,Toast.LENGTH_LONG).show();
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(img);//circle
//
//



        return gridView;

    }
}
