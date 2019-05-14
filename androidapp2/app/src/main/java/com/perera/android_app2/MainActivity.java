package com.perera.android_app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    ImageView logo;
    TextView name;
    Animation fromBottom, fromTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton = (Button) findViewById(R.id.startbtn);
        logo = findViewById(R.id.logo);
        name = findViewById(R.id.name);

        fromBottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        fromTop = AnimationUtils.loadAnimation(this,R.anim.from_top);

        startbutton.setAnimation(fromBottom);
        logo.setAnimation(fromTop);
        name.setAnimation(fromTop);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);


            }
        });
    }

}
