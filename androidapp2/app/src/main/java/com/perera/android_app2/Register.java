package com.perera.android_app2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    private EditText rname;
    private EditText remail;
    private EditText rcontactNo;
    private EditText rpassword;
    private EditText rconfirmpw;
    private Button regbtn;


    UserModel userObj;

    private String URL = "https://peaceful-mountain-19289.herokuapp.com/user/addUser";
    AsyncHttpClient client;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        rname =  findViewById(R.id.registername);
        remail = findViewById(R.id.registeremail);
        rcontactNo = findViewById(R.id.registercontactNo);
        rpassword = findViewById(R.id.registerpassword);
        rconfirmpw = findViewById(R.id.registerconfirmpw);
        regbtn = findViewById(R.id.registerbtn);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String p1 = rpassword.getText().toString();
//                String p2 = rconfirmpw.getText().toString();
//                boolean ans = p1.equals(p2);
//
//                if (ans) {
//                    successmessage();
//                } else {
//                    pwmessage();
//                }

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

                addUser(params);
                successmessage();


                stopService(new Intent(Register.this, MainActivity.class));
                finish();

            }
        });


    }

    public void successmessage() {
        Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_LONG).show();
    }

    public void pwmessage() {
        Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
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

