package com.example.zubair.bdspucit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisclaimerFragment extends Fragment {

    View v;
    EditText ed;
    Button bt;
    DatabaseHelper db;
    DatabaseAttributes dba;

    public DisclaimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.activity_disclaimer, container, false);
        findControl();

        return v;
    }
    private  void findControl(){
        ed=(EditText)v.findViewById(R.id.dltEdit);
        bt=(Button)v.findViewById(R.id.dltBtn);
        bt.setOnClickListener(new MyClass());
    }
    public class MyClass implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if(UserNameClass.logUser.equals("admin")){
                db=new DatabaseHelper(getActivity());
                String usrname=ed.getText().toString();
                if(usrname.equals("admin")){
                    Toast t=Toast.makeText(getActivity(),"Admin cannot be deleted",Toast.LENGTH_SHORT);
                    t.show();
                }else{
                    db.deleteUser(usrname);
                    Toast t=Toast.makeText(getActivity(),"User Deleted!",Toast.LENGTH_SHORT);
                    t.show();
                }

            }else{

                Toast t=Toast.makeText(getActivity(),"Only admin can delete!",Toast.LENGTH_SHORT);
                t.show();
            }

        }
    }
}
