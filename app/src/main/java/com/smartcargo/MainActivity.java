package com.smartcargo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.smartcargo.Database.Load;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;
    CargoListAdapter cAdapter;
    LoadListAdapter lAdapter;
    Boolean isCargo = true;
    ViewModel vm;

    TextView cargo, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        list = findViewById(R.id.recyclerView);
        list.setLayoutManager(new LinearLayoutManager(this));
        cargo = findViewById(R.id.cargoBt);
        load = findViewById(R.id.loadBt);

        cAdapter = new CargoListAdapter(null);
        lAdapter = new LoadListAdapter(null);

        vm = new ViewModelProvider(this).get(ViewModel.class);

        vm.getCargoList().observe(this, list-> cAdapter.setList(list));
        vm.getLoadList().observe(this, list-> lAdapter.setList(list));


        list.setAdapter(cAdapter);

    }

    public static void l(String msg){
        Log.i("tag", msg);
    }

    public void showLoadList(View view) {

        if(isCargo) {
            list.setAdapter(lAdapter);
            isCargo = false;
            cargo.setTextSize(20);
            load.setTextSize(25);
            cargo.setTextColor(getResources().getColor(R.color.black));
            load.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }

    }

    public void showCargoList(View view) {

        if(!isCargo) {
            list.setAdapter(cAdapter);
            isCargo = true;
            cargo.setTextSize(25);
            load.setTextSize(20);
            load.setTextColor(getResources().getColor(R.color.black));
            cargo.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
    }

    public void addNewOrder(View view) {
        startActivity(new Intent(this, AddNewOrder.class));
    }
}