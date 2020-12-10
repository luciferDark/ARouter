package com.ll.arouter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ll.arouter_annotation.ARouter;

@ARouter(path = "/app/MainActivity2")
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}