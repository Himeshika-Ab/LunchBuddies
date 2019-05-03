package com.ctse.whoshungry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText email;
    EditText password;
    Button loginbtn;
    Button registerbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.loginpassword);
        loginbtn = (Button) findViewById(R.id.Loginbutton);
        registerbtn = (Button) findViewById(R.id.loginregisterbutton);




        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);


            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,UserModel.class);
                startActivity(intent);


            }
        });




    }
}
