package com.perera.android_app2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class UserModel extends AppCompatActivity {

    EditText uname;
    EditText uemail;
    EditText ucontactNo;
    Button usave;
    Button uremove;


    public static final String GET_URL = "http://192.168.1.103:21731/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        uname = (EditText) findViewById(R.id.userprofilename);
        uemail = (EditText) findViewById(R.id.userprofileemail);
        ucontactNo = (EditText) findViewById(R.id.userprofilecontactNo);
        usave = (Button) findViewById(R.id.userprofilesave);
        uremove = (Button) findViewById(R.id.userprofileremoveaccount);



        Toast.makeText(this,"getdatatask!",Toast.LENGTH_LONG).show();
//        try {
//            Log.d("myTag", "getdatafunction");
//            getdata();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Log.d("myTag", "calling getdata task");
        new GetDataTask().execute(GET_URL);


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
            public void onClick(View v){

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

        AlertDialog.Builder builder = new AlertDialog.Builder(UserModel.this);
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


    class GetDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("myTag", "onPreExecute");

            progressDialog = new ProgressDialog(UserModel.this);
            progressDialog.setMessage("Loading Data");
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Log.d("myTag", "getData6");
                return getData(params[0]);
            } catch (IOException ex) {
                Log.d("myTag", "getData7");
                return "Network error";
            }
//
//            try{
//                return getData(params[0]);
//            }catch (IOException e){
//                return "Error";
//            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d("myTag", "onPostExecute");
            uname.setText(result);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        public String getData(String urlPath) throws IOException {

            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = null;
            Log.d("myTag", "getData1");

            try {

                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                Log.d("ADebugTag", "url: " + url);
                Log.d("ADebugTag", "urlPath: " + urlPath);
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                Log.d("myTag", "getData2");
                urlConnection.connect();
                Log.d("myTag", "getData10");

                //Read data from server
                InputStream inputStream = urlConnection.getInputStream();
                Log.d("myTag", "getData3");
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d("myTag", "getData4");
                    result.append(line).append("\n");

                }

            } finally {
                if (bufferedReader != null) {
                    Log.d("myTag", "getData5");
                    bufferedReader.close();
                }
            }

            return result.toString();


        }

    }


    class PutDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(UserModel.this);
            progressDialog.setMessage("Updating data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return putData(params[0]);
            } catch (IOException ex) {
                return "Network error !";
            } catch (JSONException ex) {
                return "Data invalid !";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            uname.setText(result);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String putData(String urlPath) throws IOException, JSONException {

            BufferedWriter bufferedWriter = null;
            String result = null;

            try {
                //Create data to update
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("name", uname);
                dataToSend.put("email", uemail);
                dataToSend.put("phone", ucontactNo);

                //Initialize and config request, then connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(10000 /* milliseconds */);
                urlConnection.setRequestMethod("PUT");
                urlConnection.setDoOutput(true);  //enable output (body data)
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

                //Write data into server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Check update successful or not
                if (urlConnection.getResponseCode() == 200) {
                    return "Update successfully !";
                } else {
                    return "Update failed !";
                }
            } finally {
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
        }
    }








}





