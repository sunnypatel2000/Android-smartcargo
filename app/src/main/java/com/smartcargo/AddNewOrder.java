package com.smartcargo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AddNewOrder extends AppCompatActivity {

    Boolean isCargo = true;
    TextView cargo, load;
    public static ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        cargo = findViewById(R.id.addCargo);
        load = findViewById(R.id.addLoad);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentPH, new addCargo(), "AddCargo").commit();
        vm = new ViewModelProvider(this).get(ViewModel.class);
    }


    public void changeToLoad(View view) {
        if(isCargo){
            isCargo = false;
            cargo.setTextSize(20);
            load.setTextSize(25);
            cargo.setTextColor(getResources().getColor(R.color.black));
            load.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentPH, new addLoad(), "AddLoad").commit();
        }
    }

    public void changeToCargo(View view) {
        if(!isCargo){
            isCargo = true;
            cargo.setTextSize(25);
            load.setTextSize(20);
            load.setTextColor(getResources().getColor(R.color.black));
            cargo.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentPH, new addCargo(), "AddCargo").commit();
        }
    }
}