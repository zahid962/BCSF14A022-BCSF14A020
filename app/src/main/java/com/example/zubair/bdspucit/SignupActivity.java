package com.example.zubair.bdspucit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity {

    Button bNext;
    EditText edName;
    EditText eduser;
    EditText edEmail;
    EditText edPass;
    EditText edAns;
    DatabaseAttributes dbHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        findControl();
        registerWidgets();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    private void registerWidgets(){
        myClass mc=new myClass();
        bNext.setOnClickListener(mc);

    }
    private void findControl(){
        bNext=(Button)findViewById(R.id.next);
        edName=(EditText)findViewById(R.id.nameVal);
        eduser=(EditText)findViewById(R.id.usernameVal);
        edEmail=(EditText)findViewById(R.id.emailVal);
        edPass=(EditText)findViewById(R.id.passVal);
        edAns=(EditText)findViewById(R.id.ansVal);
    }

    class myClass implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            boolean flag=checkExist();
            if(flag==true){
                Intent myIntent = new Intent(SignupActivity.this,SignupContActivity.class);
                fillObject();
                myIntent.putExtra("obj",dbHelp);
                startActivity(myIntent);
            }else{
                Toast t=Toast.makeText(getApplicationContext(),"Data not Correct!",Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }
    private boolean checkExist(){
        if(edName.getText().toString().trim().length() == 0)
            return  false;
        else if(eduser.getText().toString().trim().length() == 0)
            return false;
        else if (edEmail.getText().toString().trim().length() == 0 || !(android.util.Patterns.EMAIL_ADDRESS.matcher(edEmail.getText().toString()).matches()))
            return false;
        else if(edPass.getText().toString().trim().length() == 0)
            return  false;
        else if (edAns.getText().toString().trim().length() == 0)
            return false;
        //if(eduser.getText().toString().equals("admin"))
          //  return false;
        else
            return true;
    }
    private void fillObject(){

        dbHelp=new DatabaseAttributes();
        dbHelp.setName(edName.getText().toString());
        dbHelp.setUsername(eduser.getText().toString());
        dbHelp.setEmail(edEmail.getText().toString());
        dbHelp.setPass(edPass.getText().toString());
        dbHelp.setAnswer(edAns.getText().toString());
    }
}
