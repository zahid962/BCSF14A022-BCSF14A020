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
public class UpdateFragment extends Fragment {
    EditText ename;
    EditText eemail;
    EditText ePhone;
    EditText eAdd;
    EditText eDob;
    EditText eWeight;
    String uname;
    DatabaseHelper db;
    DatabaseAttributes dba;
    Button ub;

    View v;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v =inflater.inflate(R.layout.activity_update, container, false);
        findControl();
        setFields();
        regiserButton();
        return v;
    }
    private void regiserButton(){
        MyClass mc=new MyClass();
        ub.setOnClickListener(mc);
    }
    private void setFields(){
        db=new DatabaseHelper(getActivity());
        dba=new DatabaseAttributes();
        dba=db.getUser(UserNameClass.logUser);
        ename.setText(dba.getName().toString());
        eDob.setText(dba.getDob().toString());
        eemail.setText(dba.getEmail().toString());
        ePhone.setText(dba.getPhone().toString());
        eAdd.setText(dba.getBloodGroup().toString());
        eWeight.setText(""+dba.getWeight());
    }
    public void findControl(){
        ename=(EditText)v.findViewById(R.id.editName);
        eDob=(EditText)v.findViewById(R.id.editDOB);
        eemail=(EditText)v.findViewById(R.id.editEmail);
        ePhone=(EditText)v.findViewById(R.id.editPhone);
        eAdd=(EditText)v.findViewById(R.id.editAddress);
        eWeight=(EditText)v.findViewById(R.id.editWeight);
        ub=(Button)v.findViewById(R.id.UpdateButton);
    }
    public class MyClass implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            setValues();
            db.updateUser(dba);
            Toast t=Toast.makeText(getActivity(),"Updated",Toast.LENGTH_SHORT);
            t.show();

        }
    }
    private void setValues(){
        dba.setName(ename.getText().toString());
        dba.setDob(eDob.getText().toString());
        dba.setEmail(eemail.getText().toString());
        dba.setPhone(ePhone.getText().toString());
        dba.setBloodGroup(eAdd.getText().toString());
        dba.setWeight(Integer.parseInt(eWeight.getText().toString()));
        DatabaseAttributes temp=db.getUser(UserNameClass.logUser);
        dba.setUsername(temp.getUsername());
        dba.setPass(temp.getPass());
        dba.setAddress(temp.getAddress());
        dba.setAnswer(temp.getAnswer());
    }


}
