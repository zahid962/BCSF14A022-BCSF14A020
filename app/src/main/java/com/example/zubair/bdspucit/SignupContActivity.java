package com.example.zubair.bdspucit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class SignupContActivity extends AppCompatActivity {

    ImageButton ibdob;
    int d,m,y;
    EditText tvdob;
    EditText edPhone;
    EditText edAddress;
    Spinner edBlood;
    EditText edDob;
    EditText edWeight;
    Button bReg;
    DatabaseAttributes dbAtt;
    DatabaseHelper dbHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_cont);
        findControl();
        registerWidgets();
        ibdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                d=calendar.get(Calendar.DAY_OF_MONTH);
                m=calendar.get(Calendar.MONTH);
                y=calendar.get(Calendar.YEAR);
                DatePickerDialog dpd=new DatePickerDialog(SignupContActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        tvdob.setText(i2+"/"+i1+"/"+i);
                    }
                }, d, m, y);
                dpd.show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    private void registerWidgets(){
        myClass mc=new myClass();
        bReg.setOnClickListener(mc);

    }
    private void findControl(){
        ibdob=(ImageButton)findViewById(R.id.dobIcon);
        tvdob=(EditText)findViewById(R.id.dobVal);
        edPhone=(EditText)findViewById(R.id.phoneVal);
        edAddress=(EditText)findViewById(R.id.addressVal);
        edBlood=(Spinner) findViewById(R.id.bloodVal);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bgNames));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edBlood.setAdapter(aa);
        edDob=(EditText)findViewById(R.id.dobVal);
        edWeight=(EditText)findViewById(R.id.weightVal);
        bReg=(Button)findViewById(R.id.registration);
    }
    class  myClass implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            boolean flag=checkExist();
            if (flag==true){
                Intent i=getIntent();
                Bundle b=i.getExtras();
                Object o=b.getSerializable("obj");
                dbAtt=(DatabaseAttributes)o;
                fillObject();
                addObject();
                Toast t=Toast.makeText(getApplicationContext(),"User added Successfully!",Toast.LENGTH_LONG);
                t.show();
                Intent i2=new Intent(SignupContActivity.this,ProfileActivity.class);
                UserNameClass.logUser=dbAtt.getUsername().toString();
                startActivity(i2);
            }else{
                Toast t=Toast.makeText(getApplicationContext(),"Data not Correct!",Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }
    private boolean checkExist(){
        if(edPhone.getText().toString().trim().length() == 0)
            return  false;
        else if(edAddress.getText().toString().trim().length() == 0)
            return false;
        else if (edBlood.getSelectedItem().toString().trim().length() == 0)
            return false;
        else if(edDob.getText().toString().trim().length() == 0)
            return  false;
        else if (edWeight.getText().toString().trim().length() == 0)
            return false;
        else
            return true;
    }
    private void fillObject(){
        dbAtt.setPhone(edPhone.getText().toString());
        dbAtt.setAddress(edAddress.getText().toString());
        //dbAtt.setBloodGroup(edBlood.getText().toString());
        dbAtt.setBloodGroup(edBlood.getSelectedItem().toString());
        dbAtt.setDob(edDob.getText().toString());
        dbAtt.setWeight(Integer.parseInt(edWeight.getText().toString()));
    }
    private void addObject(){
        dbHelp=new DatabaseHelper(this);
        dbHelp.addUser(dbAtt);
    }
}
