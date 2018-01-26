package com.example.zubair.bdspucit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button blogin;
    TextView blogup;
    EditText etuser;
    EditText etpass;
    String username;
    String password;
    DatabaseHelper help;
    TextView forgotT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
        //UserNameClass.logUser = username;
        //startActivity(myIntent);
        findRegisterWidgets();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    private void findRegisterWidgets(){
        blogin=(Button)findViewById(R.id.login);
        blogup=(TextView) findViewById(R.id.sup);
        etuser=(EditText)findViewById(R.id.logUser);
        etpass=(EditText)findViewById(R.id.logPass);
        forgotT=(TextView)findViewById(R.id.forgotP);
        myClass mc1=new myClass();
        blogin.setOnClickListener(mc1);
        blogup.setOnClickListener(mc1);
        forgotT.setOnClickListener(mc1);
    }
    class myClass implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.login) {
                UserNameClass.logUser = "shoaib";
                getUserPass();
                initializeHelper();
                boolean flag=help.checkUserPass(username,password);
                if (flag==true){
                    Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
                    UserNameClass.logUser = username;
                    startActivity(myIntent);
                }else{
                    Toast t=Toast.makeText(getApplicationContext(),"User or Password Incorrect!",Toast.LENGTH_LONG);
                    t.show();
                }
            }else if(view.getId()==R.id.sup){
                Intent myIntent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(myIntent);
            }else if(view.getId()==R.id.forgotP){
                Intent myIntent = new Intent(MainActivity.this, ForgotActivity.class);
                startActivity(myIntent);
            }
        }
    }
    private void getUserPass(){
        username=etuser.getText().toString();
        password=etpass.getText().toString();
    }
    private void initializeHelper(){
        help=new DatabaseHelper(this);
    }
}
