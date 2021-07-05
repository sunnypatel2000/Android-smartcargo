package com.smartcargo.MajorViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartcargo.Database.ViewModel;
import com.smartcargo.Fragments.addCargo;
import com.smartcargo.Fragments.addLoad;
import com.smartcargo.R;

import static com.smartcargo.MajorViews.MainActivity.TYPECAGRO;
import static com.smartcargo.MajorViews.MainActivity.TYPEKEY;
import static com.smartcargo.MajorViews.MainActivity.TYPELOAD;
import static com.smartcargo.MajorViews.MainActivity.l;

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
        l(getIntent().getStringExtra(TYPEKEY));
        if (getIntent().getStringExtra(TYPEKEY).equals(TYPECAGRO)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentPH, new addCargo(), "AddCargo").commit();
            isCargo = true;
            cargo.setTextSize(25);
            load.setTextSize(20);
            setTitle("Add new Cargo");
            load.setTextColor(getResources().getColor(R.color.black));
            cargo.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
        else if (getIntent().getStringExtra(TYPEKEY).equals(TYPELOAD)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentPH, new addLoad(), "AddLoad").commit();
            isCargo = false;
            cargo.setTextSize(20);
            load.setTextSize(25);
            setTitle("Add new Load");
            cargo.setTextColor(getResources().getColor(R.color.black));
            load.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
        vm = new ViewModelProvider(this).get(ViewModel.class);
        setTitle("Add new Cargo");
    }


    public void changeToLoad(View view) {
        if(isCargo){
            isCargo = false;
            cargo.setTextSize(20);
            load.setTextSize(25);
            setTitle("Add new Load");
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
            setTitle("Add new Cargo");
            load.setTextColor(getResources().getColor(R.color.black));
            cargo.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentPH, new addCargo(), "AddCargo").commit();
        }
    }

}