package com.smartcargo.MajorViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.smartcargo.Database.Cargo;
import com.smartcargo.Database.Load;
import com.smartcargo.Database.ViewModel;
import com.smartcargo.R;

import static com.smartcargo.MajorViews.MainActivity.ID;
import static com.smartcargo.MajorViews.MainActivity.TYPECAGRO;
import static com.smartcargo.MajorViews.MainActivity.TYPEKEY;
import static com.smartcargo.MajorViews.MainActivity.TYPELOAD;
import static com.smartcargo.MajorViews.MainActivity.l;

public class Details extends AppCompatActivity {

    String type;
    int id;
    ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        id = i.getIntExtra(ID, -1);
        type = i.getStringExtra(TYPEKEY);
        l("Type: " + type);
        l("ID: " + id);
        if(i.getStringExtra(TYPEKEY).equals(TYPECAGRO))
            setContentView(R.layout.activity_details_cargo);
        else if (i.getStringExtra(TYPEKEY).equals(TYPELOAD))
            setContentView(R.layout.activity_details_load);

        initViews();
    }

    private void initViews() {

        vm = new ViewModelProvider(this).get(ViewModel.class);

        if(type.equals(TYPECAGRO)){
            TextView material,weight,truckType,truckReq,loadValue,loadingDate,pickupLoc,dropLoc;

            Cargo record = vm.getCargo(id+1);
            l(record.toString());
            material = findViewById(R.id.material);
            pickupLoc = findViewById(R.id.pickupLoc);
            dropLoc = findViewById(R.id.dropLoc);
            loadingDate = findViewById(R.id.loadingDateEt);
            weight = findViewById(R.id.weight);
            truckType = findViewById(R.id.truckType);
            truckReq = findViewById(R.id.truckReq);
            loadValue = findViewById(R.id.loadValue);

            material.setText("Material Type: " + record.materialType);
            weight.setText("Weight: " + record.weight);
            truckType.setText("Truck Type: " + record.truckType);
            truckReq.setText("Number Of Trucks" + record.trucksRequired);
            loadValue.setText("Load Value" + record.loadValue);
            loadingDate.setText("Date: " + record.date);
            pickupLoc.setText("Pickup Location: " + record.pickupLocation);
            dropLoc.setText("Drop Location: " + record.dropLocation);

        }else if(type.equals(TYPELOAD)){
            TextView truckTypeLoad, pickupLocation, expPrice, loadingDateLoad, dropLocation, comments;

            Load record = vm.getLoad(id+1);

            l(record.toString());
            truckTypeLoad = findViewById(R.id.truckTypeLoad);
            pickupLocation = findViewById(R.id.pickupLocation);
            expPrice = findViewById(R.id.expPrice);
            loadingDateLoad = findViewById(R.id.loadingDateLoad);
            dropLocation = findViewById(R.id.dropLocation);
            comments = findViewById(R.id.comments);

            truckTypeLoad.setText("Truck Type: " + record.truckType);
            pickupLocation.setText("Pickup Loaction" + record.pickupLocation);
            expPrice.setText("Expected Price: " + record.expectedPrice);
            loadingDateLoad.setText("Loading Date: " + record.loadingDate);
            dropLocation.setText("Drop Location: " + record.dropLocation);
            comments.setText("Comments " + record.comments);

        }


    }

}