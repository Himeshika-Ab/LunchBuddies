package com.perera.android_app2;

import android.os.AsyncTask;
import android.os.Bundle;
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

                String p1 = rpassword.getText().toString();
                String p2 = rconfirmpw.getText().toString();
                boolean ans = p1.equals(p2);

                if (ans) {
                    successmessage();
                } else {
                    pwmessage();
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


    class PostDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("myTag", "onPreExecute");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.d("myTag", "doinbackground");
                return postData(strings[0]);
            } catch (IOException e) {
                return "IOException" + e;
            } catch (JSONException j) {
                return "JSONException" + j;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("myTag", "onPreExecute");
            super.onPostExecute(s);


        }


        public String postData(String urlPath) throws IOException, JSONException {

            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;

            try {

                JSONObject dataToSend = new JSONObject();
                dataToSend.put("name", rname);
                dataToSend.put("email", remail);
                dataToSend.put("phone", rcontactNo);
                dataToSend.put("password", rpassword);

                URL url = new URL(urlPath);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setReadTimeout(10000);
                con.setConnectTimeout(10000);
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.connect();

                OutputStream outputStream = con.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                InputStream inputStream = con.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } finally {

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

            }

            return result.toString();

        }

    }
}

