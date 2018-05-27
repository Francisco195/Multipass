package com.example.stardust.multipass.Activities.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;


public class SplashActivity extends AppCompatActivity {
    private SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share=getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Intent log=new Intent(this, MainActivity.class);
        Intent log1=new Intent(this, com.example.stardust.multipass.Activities.Activities.log.class);
        String email=getuser();
        String pwd=getpwd();
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pwd)){
            startActivity(log1);
        }
        else{
            startActivity(log);
        }
        finish();
    }
    private String getuser(){
        return share.getString("email","");
    }
    private String getpwd(){
        return share.getString("pwd","");
    }
}
