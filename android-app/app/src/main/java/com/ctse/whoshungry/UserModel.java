package com.ctse.whoshungry;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try {
            Log.d("myTag", "getdatafunction");
            getdata();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Log.d("myTag", "calling getdata task");
        //new GetDataTask().execute(GET_URL);


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


//    class GetDataTask extends AsyncTask<String, Void, String>{
//
////        ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Log.d("myTag", "onPreExecute");
//
////            progressDialog = new ProgressDialog(UserModel.this);
////            progressDialog.setMessage("Loading Data");
////            progressDialog.show();
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
////                    try{
////                        Log.d("myTag", "getData6");
////                        return getData(params[0]);
////                    } catch (IOException ex){
////                        Log.d("myTag", "getData7");
////                        return "Network error";
////                    }
//
//            try{
//                Log.d("myTag", "doInBackground");
//                return getData(params[0]);
//            }catch (IOException e){
//                return "Error";
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            Log.d("myTag", "onPostExecute");
//            uname.setText(result);
//
////            if (progressDialog != null){
////                progressDialog.dismiss();
////            }
//        }
//
////        public String getData(String urlPath) throws IOException{
////
////            StringBuilder result = new StringBuilder();
////            BufferedReader bufferedReader = null;
////            Log.d("myTag", "getData1");
////
////            try{
////
////                URL url = new URL(urlPath);
////                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
////                Log.d("ADebugTag", "url: " + url);
////                Log.d("ADebugTag", "urlPath: " + urlPath);
////                urlConnection.setReadTimeout(10000);
////                urlConnection.setConnectTimeout(10000);
////                urlConnection.setRequestMethod("GET");
////                urlConnection.setRequestProperty("Content-Type","application/json");
////                Log.d("myTag", "getData2");
////                urlConnection.connect();
////                Log.d("myTag", "getData10");
////
////                //Read data from server
////                InputStream inputStream = urlConnection.getInputStream();
////                Log.d("myTag", "getData3");
////                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
////                String line;
////                while ((line = bufferedReader.readLine()) != null){
////                    Log.d("myTag", "getData4");
////                    result.append(line).append("\n");
////
////                }
////
////            }
////            finally {
////                if (bufferedReader != null){
////                    Log.d("myTag", "getData5");
////                    bufferedReader.close();
////                }
////            }
////
////            return result.toString();
////
////        }
//
//        public String getData(String geturl) throws IOException{
//
//
//            geturl = GET_URL;
//
//            URL url = new URL(geturl);
//            Log.d("ADebugTag", "url: " + url);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type","application/json");
//            int responseCode = con.getResponseCode();
//            Log.d("ADebugTag", "responseCode: " + responseCode);
//
//            if(responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//
//                StringBuffer response = new StringBuffer();
//
//                while ((inputLine = bf.readLine()) != null){
//                    Log.d("ADebugTag", "inputLine: " + inputLine);
//                    response.append(inputLine).append("\n");
//                }
//
//                bf.close();
//                Log.d("ADebugTag", ": " + response);
//                return response.toString();
//
//            }
//            else {
//                Log.d("myTag", "Http not working");
//                return "Httpnotworking";
//            }
//
//        }
//
//    }


    public void getdata() throws IOException {


        Log.d("myTag", "insidegetdatafunction");
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(GET_URL);
            Log.d("ADebugTag", "url: " + url);
            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                //System.out.print(current);
                Log.d("ADebugTag", "data: " + current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();


            }
        }


    }






}





