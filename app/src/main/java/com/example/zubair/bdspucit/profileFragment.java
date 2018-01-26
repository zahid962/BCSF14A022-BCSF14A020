package com.example.zubair.bdspucit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {
    /*Button bNext;
    EditText edName;
    EditText eduser;
    EditText edEmail;
    EditText edPass;*/
    View r;
    TextView tvName;
    TextView tvusr;
    TextView tvgroup;
    TextView tvdob;
    TextView tvweight;
    TextView tvEmail;
    TextView tvphone;
    TextView tvaddress;
    DatabaseHelper dbTemp;
    DatabaseAttributes dbAttTemp;

    DatabaseAttributes dbHelp;
    public profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        r= inflater.inflate(R.layout.activity_person, container, false);
        findControl();
        fillObject();
        //registerWidgets();
        return  r;
    }

    //private void registerWidgets(){
      //  bNext.setOnClickListener(new myClass());

//    }
   /* class myClass implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent myIntent=new Intent(getActivity(),SignupContActivity.class);
            fillObject();
            myIntent.putExtra("obj",dbHelp);
            startActivity(myIntent);
        }
    }*/
    private void findControl(){
        tvaddress=(TextView)r.findViewById(R.id.myadd);
        tvdob=(TextView)r.findViewById(R.id.mydob);
        tvEmail=(TextView)r.findViewById(R.id.myemail);
        tvgroup=(TextView)r.findViewById(R.id.myblood);
        tvName=(TextView)r.findViewById(R.id.myname);
        tvphone=(TextView)r.findViewById(R.id.myPhone);
        tvusr=(TextView)r.findViewById(R.id.myusername);
        tvweight=(TextView)r.findViewById(R.id.myweight);
    }
    private void fillObject(){
        UserNameClass usr=new UserNameClass();
        tvusr.setText(usr.logUser);
        dbTemp=new DatabaseHelper(getActivity());
        dbAttTemp=dbTemp.getUser(usr.logUser);
        tvweight.setText(""+dbAttTemp.getWeight());
        tvusr.setText(dbAttTemp.getUsername());
        tvphone.setText(dbAttTemp.getPhone());
        tvName.setText(dbAttTemp.getName());
        tvgroup.setText(dbAttTemp.getBloodGroup());
        tvEmail.setText(dbAttTemp.getEmail());
        tvdob.setText(dbAttTemp.getDob());
        tvaddress.setText(dbAttTemp.getAddress());

    }

}
