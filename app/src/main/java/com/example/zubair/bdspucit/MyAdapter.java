package com.example.zubair.bdspucit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Zubair on 1/18/2018.
 */

public class MyAdapter extends ArrayAdapter {

    ArrayList<DatabaseAttributes> data=new ArrayList<DatabaseAttributes>();
    Context con;
    public MyAdapter(@NonNull Context context, int resource, ArrayList<DatabaseAttributes> objs) {
        super(context, resource);
        this.con=context;
        data.addAll(objs);

    }
 //   getV


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=null;
        ViewHolder vh;
        if(convertView==null){
            LayoutInflater li=(LayoutInflater)con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
            v=li.inflate(R.layout.cell,null,true);
            vh=new ViewHolder();
            vh.name=(TextView)v.findViewById(R.id.lname);
            vh.bgroup=(TextView)v.findViewById(R.id.lBlood);

            vh.weight=(TextView)v.findViewById(R.id.lweight);
            vh.phone=(TextView)v.findViewById(R.id.lphone);
            vh.dob=(TextView)v.findViewById(R.id.ldob);
            v.setTag(vh);
        }else{
            v=convertView;
            vh=(ViewHolder)convertView.getTag();
        }
        DatabaseAttributes dbAttr=data.get(position);
        vh.name.setText(dbAttr.getName());
        vh.dob.setText(dbAttr.getDob());
        vh.bgroup.setText(dbAttr.getBloodGroup());
        String s1 = vh.bgroup.getText().toString();
        Log.d("Abc",s1);
        vh.phone.setText(dbAttr.getPhone());
        vh.weight.setText(""+dbAttr.getWeight());
        return v;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    /*@NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v=null;
                ViewHolder vh;
                if(convertView==null){
                    LayoutInflater li=(LayoutInflater)con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
                    v=li.inflate(R.layout.cell,parent,true);
                    vh=new ViewHolder();
                    vh.name=(TextView)v.findViewById(R.id.lname);
                    vh.bgroup=(TextView)v.findViewById(R.id.lBlood);

                    vh.weight=(TextView)v.findViewById(R.id.lweight);
                    vh.phone=(TextView)v.findViewById(R.id.lphone);
                    vh.dob=(TextView)v.findViewById(R.id.ldob);
                    v.setTag(vh);
                }else{
                    v=convertView;
                    vh=(ViewHolder)convertView.getTag();
                }
                DatabaseAttributes dbAttr=data.get(position);
                vh.name.setText(dbAttr.getName());
                vh.dob.setText(dbAttr.getDob());
                vh.bgroup.setText(dbAttr.getBloodGroup());
                String s1 = vh.bgroup.getText().toString();
                Log.d("Abc",s1);
                vh.phone.setText(dbAttr.getPhone());
                vh.weight.setText(""+dbAttr.getWeight());
                return v;
            }*/
    public class ViewHolder{
        public TextView name;
        public TextView weight;
        public TextView bgroup;
        public TextView phone;
        public TextView dob;
    }
}
