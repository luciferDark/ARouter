package com.ll.arouter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ll.arouter_annotation.ARouter;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log("debug" , String.valueOf(BuildConfig.debug));

        if (BuildConfig.debug){
            log("Url debug",BuildConfig.debugUrl);
        } else {
            log("Url release",BuildConfig.releaseUrl);
        }

    }

    public void log(String... msg ){
        if (msg == null || msg.length <= 0){
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (String item  :  msg){
            buffer.append(item + "\t");
        }

        Log.d(this.getClass().getName(), "====" + buffer.toString());
    }
}