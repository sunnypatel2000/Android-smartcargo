package com.smartcargo;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mainMenuLogout){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return true;
        }else if(item.getItemId() == R.id.registerUserMenu){
            Intent i = new Intent(this, RegisterUser.class);
            startActivity(i);
            return true;
        }

        return false;
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

    public void addNewOrder(View view) {
        startActivity(new Intent(this, AddNewOrder.class));
    }
}