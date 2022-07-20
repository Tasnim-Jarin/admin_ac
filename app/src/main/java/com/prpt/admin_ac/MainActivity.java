package com.prpt.admin_ac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t= new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, Home.class));
                }
            }
        };
        t.start();

    }
}