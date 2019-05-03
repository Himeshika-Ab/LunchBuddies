package com.ctse.whoshungry;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserModel extends AppCompatActivity {

    EditText uname;
    EditText uemail;
    EditText ucontactNo;
    Button usave;
    Button uremove;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        uname = (EditText) findViewById(R.id.userprofilename);
        uemail = (EditText) findViewById(R.id.userprofileemail);
        ucontactNo = (EditText) findViewById(R.id.userprofilecontactNo);
        usave = (Button) findViewById(R.id.userprofilesave);
        uremove = (Button) findViewById(R.id.userprofileremoveaccount);


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




}





