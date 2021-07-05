package com.smartcargo.MajorViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.smartcargo.R;

public class Ack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ack);
    }

    public void backToHome(View view) {
        finish();
    }
}