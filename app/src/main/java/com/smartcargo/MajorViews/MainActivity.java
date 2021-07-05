package com.smartcargo.MajorViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.smartcargo.Adapters.CargoListAdapter;
import com.smartcargo.Adapters.LoadListAdapter;
import com.smartcargo.Database.ViewModel;
import com.smartcargo.MajorViews.AddNewOrder;
import com.smartcargo.MajorViews.LoginActivity;
import com.smartcargo.MajorViews.RegisterUser;
import com.smartcargo.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;
    CargoListAdapter cAdapter;
    LoadListAdapter lAdapter;
    Boolean isCargo = true;
    ViewModel vm;

    TextView cargo, load;
    public static final String TYPEKEY = "Type";
    public static final String TYPECAGRO = "Cargo";
    public static final String TYPELOAD = "Load";
    public static final String ID = "Id";

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

        cAdapter = new CargoListAdapter(this, null);
        lAdapter = new LoadListAdapter(this, null );

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(),
                new LinearLayoutManager(this).getOrientation());
        list.addItemDecoration(dividerItemDecoration);

        vm = new ViewModelProvider(this).get(ViewModel.class);

        vm.getCargoList().observe(this, list-> cAdapter.setList(list));
        vm.getLoadList().observe(this, list-> lAdapter.setList(list));
        setTitle("Smart Cargo | Cargo List");

        list.setAdapter(cAdapter);

    }

    public static void l(String msg){
        Log.i("tag", msg);
    }

    public void showLoadList(View view) {

        if(isCargo) {
            list.setAdapter(lAdapter);
            isCargo = false;
            setTitle("Smart Cargo | Load List");
            cargo.setTextColor(getResources().getColor(R.color.black));
            load.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }

    }

    public void showCargoList(View view) {

        if(!isCargo) {
            list.setAdapter(cAdapter);
            isCargo = true;
            setTitle("Smart Cargo | Cargo List");
            load.setTextColor(getResources().getColor(R.color.black));
            cargo.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }

    }
}