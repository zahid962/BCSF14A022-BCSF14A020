package com.example.zubair.bdspucit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    DatabaseHelper dbh;
    ArrayList<DatabaseAttributes> arr;
    MyAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        findControl();
        stuff();

    }
    private  void findControl(){
        lv=(ListView)findViewById(R.id.llist);
    }
    public void stuff(){
        dbh=new DatabaseHelper(this);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        String bg=b.getString("bg").toString();
        //arr=dbh.getData();
        arr=dbh.getDataonBG(bg);
        if(arr.isEmpty()){
            Toast t=Toast.makeText(getApplicationContext(),"No "+bg+" Donor!",Toast.LENGTH_LONG);
            t.show();
        }else{
            ma=new MyAdapter(this,R.layout.cell,arr);
            lv.setAdapter(ma);
        }
    }

}
