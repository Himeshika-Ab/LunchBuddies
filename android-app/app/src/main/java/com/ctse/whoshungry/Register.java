package com.ctse.whoshungry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText rname;
    EditText remail;
    EditText rcontactNo;
    EditText rpassword;
    EditText rconfirmpw;
    Button regbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        rname = (EditText) findViewById(R.id.registername);
        remail = (EditText) findViewById(R.id.registeremail);
        rcontactNo = (EditText) findViewById(R.id.registercontactNo);
        rpassword = (EditText) findViewById(R.id.registerpassword);
        rconfirmpw = (EditText) findViewById(R.id.registerconfirmpw);
        regbtn = (Button) findViewById(R.id.registerbtn);



        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rpassword.getText() != rconfirmpw.getText()){
                        pwmessage();
                }
                else{
                    successmessage();
                }




            }
        });





    }

    public void pwmessage() {
        Toast.makeText(this,"Passwords do not match!",Toast.LENGTH_LONG).show();
    }

    public void successmessage() {
        Toast.makeText(this,"Successfully Registered!",Toast.LENGTH_LONG).show();
    }

}
