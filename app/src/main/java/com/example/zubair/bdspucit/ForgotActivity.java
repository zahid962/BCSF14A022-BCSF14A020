package com.example.zubair.bdspucit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    EditText etUser;
    EditText etFavPlace;
    EditText etPass;
    Button bNew;
    DatabaseHelper dbh;
    String usr,fav,pss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        findControl();
        bNew.setOnClickListener(new myClass());
    }
    private  void findControl(){
        etUser=(EditText)findViewById(R.id.UserNamef);
        etFavPlace=(EditText)findViewById(R.id.favPlace);
        etPass=(EditText)findViewById(R.id.newPass);
        bNew=(Button)findViewById(R.id.ResetPassButton);
    }
    class  myClass implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            getData();
            dbh=new DatabaseHelper(ForgotActivity.this);
            boolean flag=dbh.updatePass(usr,fav,pss);
            if(flag==true){
                Toast t=Toast.makeText(getApplicationContext(),"Password changed!",Toast.LENGTH_LONG);
                t.show();
                Intent i2=new Intent(ForgotActivity.this,MainActivity.class);
                startActivity(i2);
            }else{
                Toast t=Toast.makeText(getApplicationContext(),"Info not Correct!",Toast.LENGTH_SHORT);
                t.show();
            }

        }
    }
    private void getData(){
        usr=etUser.getText().toString();
        fav=etFavPlace.getText().toString();
        pss=etPass.getText().toString();
    }
}
