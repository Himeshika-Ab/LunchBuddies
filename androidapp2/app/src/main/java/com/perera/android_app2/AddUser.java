package com.perera.android_app2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AddUser extends AppCompatActivity {


    EditText uname;
    EditText uemail;
    EditText ucontactNo;
    Button usave;
    Button uremove;

    private String URL = "https://peaceful-mountain-19289.herokuapp.com/user/";
    UserModel userobj;
    AsyncHttpClient client;
    ArrayList<UserModel> userList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        uname = (EditText) findViewById(R.id.userprofilename);
        uemail = (EditText) findViewById(R.id.userprofileemail);
        ucontactNo = (EditText) findViewById(R.id.userprofilecontactNo);
        usave = (Button) findViewById(R.id.userprofilesave);
        uremove = (Button) findViewById(R.id.userprofileremoveaccount);


        Toast.makeText(this, "getdatatask!", Toast.LENGTH_LONG).show();
        Log.d("user", "calling get");
        getUser();

        usave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String email = uemail.getText().toString();
//                String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//
//                if (email.matches(regex)){
//                    emailmsg();
//                }
//                else{
//                    savemsg();
//                }

                savemsg();

            }
        });

        uremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removealert();

            }
        });

    }

    public void savemsg(){
        Toast.makeText(this,"Successfully Updated!",Toast.LENGTH_LONG).show();
    }

    public void emailmsg(){
        Toast.makeText(this,"Incorrect Email!",Toast.LENGTH_LONG).show();
    }

    public void removealert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(AddUser.this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void getUser(){
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
                                    obj.getString("name"),
                                    obj.getString("email"),
                                    obj.getString("phone"));

                            Log.d("user", userobj.getName());
                            Log.d("user", userobj.getEmail());
                            Log.d("user", userobj.getContactNo());

                            uname.setText(userobj.getName());
                            uemail.setText(userobj.getEmail());
                            ucontactNo.setText(userobj.getContactNo());


                           //userList.add(userobj);

                           //Log.d("user", userList.toString() );

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
                Log.d("friend", "fail to get request");
            }
        });
    }



}
