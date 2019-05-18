package com.perera.android_app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    private EditText rname;
    private EditText remail;
    private EditText rcontactNo;
    private EditText rpassword;
    private EditText rconfirmpw;
    private Button regbtn;
    TextView toolbartxt;

    Animation fromBottom;


    UserModel userObj;

    private String URL = "https://peaceful-mountain-19289.herokuapp.com/user/addUser";
    AsyncHttpClient client;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        toolbartxt = findViewById(R.id.textView);
        toolbartxt.setText("Register");

        rname =  findViewById(R.id.registername);
        remail = findViewById(R.id.registeremail);
        rcontactNo = findViewById(R.id.registercontactNo);
        rpassword = findViewById(R.id.registerpassword);
        rconfirmpw = findViewById(R.id.registerconfirmpw);
        regbtn = (Button) findViewById(R.id.registerbtn);

        fromBottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom);

        regbtn.setAnimation(fromBottom);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = rname.getText().toString();
                String email = remail.getText().toString();
                String contactNo = rcontactNo.getText().toString();
                String password = rpassword.getText().toString();
                String confirmpw = rconfirmpw.getText().toString();

                RequestParams params = new RequestParams();
                params.put("name",name);
                params.put("email",email);
                params.put("phone",contactNo);
                params.put("password",password);


                if(isValid(email)) {
                    if ((contactNo.length() == 10)) {
                        if ((password.equals(confirmpw))) {
                            addUser(params);
                            successmessage();

                            Intent intent = new Intent(Register.this, FriendList.class);
                            startActivity(intent);

                        } else {
                            pwmessage();
                        }

                    } else {
                        contactNomessage();
                    }
                }
                else{
                    emailmessage();
                }



            }
        });


    }

    public void successmessage() {
        Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_LONG).show();
    }

    public void pwmessage() {
        Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
    }

    public void contactNomessage() {
        Toast.makeText(this, "Invalid contact Number!", Toast.LENGTH_LONG).show();
    }

    public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public void emailmessage() {
        Toast.makeText(this, "Invalid email address!", Toast.LENGTH_LONG).show();
    }

    public void  addUser(RequestParams params){

        Log.d("user", "inside in the add user method");
        client = new AsyncHttpClient();
        client.post(URL, params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("user", "User registered!");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("user", errorResponse.toString());
            }
        });


    }


}

