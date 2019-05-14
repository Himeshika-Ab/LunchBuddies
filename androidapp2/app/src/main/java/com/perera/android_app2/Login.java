package com.perera.android_app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginbtn;
    Button registerbtn;

    private String URL = "https://peaceful-mountain-19289.herokuapp.com/user/";
    UserModel userobj;
    AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        loginbtn = findViewById(R.id.Loginbutton);
        registerbtn = findViewById(R.id.loginregisterbutton);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);


            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                checkLogin();



            }
        });

    }


    private void checkLogin(){

        Log.d("user", "inside in the get data");
        client = new AsyncHttpClient();

        client.get(URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("user", response.toString());
                Log.d("user", "onSuccess");
                super.onSuccess(statusCode, headers, response);

                try{
                    JSONArray jArray = response.getJSONArray("data");
                    Log.d("user", "inside array");


                    for(int i=0;i<jArray.length();i++)
                        try {

                            JSONObject obj = jArray.getJSONObject(i);
                            userobj= new UserModel(
                                    obj.getString("email"),
                                    obj.getString("password"));

                            Log.d("user", userobj.getEmail());
                            Log.d("user", userobj.getPassword());

                            String useremail = email.getText().toString();
                            String userpw = password.getText().toString();

                            Log.d("user", useremail);
                            Log.d("user", userpw);

                            Log.d("user", "if condition");

                            if((useremail.equals(userobj.getEmail()) && userpw.equals(userobj.getPassword()))){
                                Log.d("user", "inside if condition");
                                loginsuccess();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                loginerror();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("user", e.toString());
                        }




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("user", "fail to get request");
            }
        });
    }

    private  void loginsuccess(){
        Toast.makeText(this, "Login Successfull!", Toast.LENGTH_LONG).show();
    }

    private  void loginerror(){
        Toast.makeText(this, "Email or Password does not match!", Toast.LENGTH_LONG).show();
    }

}
