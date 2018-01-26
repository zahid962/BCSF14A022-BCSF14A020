package com.example.zubair.bdspucit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class BGroupFragment extends Fragment {

    Button a1;
    Button o1;
    Button a2;
    Button o2;
    Button b2;
    Button b1;
    Button ab1;
    Button ab2;
    View v;
    public BGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.activity_groups, container, false);
        findControl();


        return v;
    }
    private void findControl(){
        a1= (Button)v.findViewById(R.id.a1);
        a1.setOnClickListener(new MyClass());
        o1= (Button)v.findViewById(R.id.o1);
        o1.setOnClickListener(new MyClass());
        a2=(Button)v.findViewById(R.id.a2);
        a2.setOnClickListener(new MyClass());

        o2=(Button) v.findViewById(R.id.o2);
        o2.setOnClickListener(new MyClass());

        ab1= (Button)v.findViewById(R.id.ab1);
        ab1.setOnClickListener(new MyClass());

        ab2=(Button) v.findViewById(R.id.ab2);
        ab2.setOnClickListener(new MyClass());

        b2= (Button)v.findViewById(R.id.b2);
        b2.setOnClickListener(new MyClass());

        b1=(Button)v.findViewById(R.id.b1);
        b1.setOnClickListener(new MyClass());



    }
    public class MyClass implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent i= new Intent(getActivity(),ListActivity.class);
            if(view.getId()==R.id.a1){
                i.putExtra("bg","A+");
            }else if (view.getId()==R.id.o1){
                i.putExtra("bg","O+");
            }
            else if (view.getId()==R.id.a2){
                i.putExtra("bg","A-");
            }
            else if (view.getId()==R.id.o2){
                i.putExtra("bg","O-");
            }
            else if (view.getId()==R.id.b1){
                i.putExtra("bg","B+");
            }
            else if (view.getId()==R.id.b2){
                i.putExtra("bg","B-");
            }
            else if (view.getId()==R.id.ab1){
                i.putExtra("bg","AB+");
            }
            else if (view.getId()==R.id.ab2){
                i.putExtra("bg","AB-");
            }
            startActivity(i);
        }
    }

}
