package com.smartcargo.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartcargo.Database.Load;
import com.smartcargo.R;

import static com.smartcargo.MajorViews.AddNewOrder.vm;

public class addLoad extends Fragment {

    EditText pickupLocation, expPrice, loadingDateLoad, dropLocation, comments;
    Spinner truckTypeLoad;
    Button b;
    String selectedOption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_load, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View itemView){
        truckTypeLoad = itemView.findViewById(R.id.loadTruckType);
        pickupLocation = itemView.findViewById(R.id.pickupLocation);
        expPrice = itemView.findViewById(R.id.expPrice);
        loadingDateLoad = itemView.findViewById(R.id.loadingDateLoad);
        dropLocation = itemView.findViewById(R.id.dropLocation);
        comments = itemView.findViewById(R.id.comments);

        b = itemView.findViewById(R.id.okl);

        String[] option = new String[]{"LCV Open Body TATA", "Open Body Taurus",
                "Trailer", "Container", "less 750 kg"};
        selectedOption = option[0];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, option);

        truckTypeLoad.setAdapter(adapter);

        truckTypeLoad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOption = option[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        b.setOnClickListener(view -> {
            if(
                !pickupLocation.getText().toString().trim().isEmpty() &&
                !expPrice.getText().toString().trim().isEmpty() &&
                !loadingDateLoad.getText().toString().trim().isEmpty() &&
                !dropLocation.getText().toString().trim().isEmpty() &&
                !comments.getText().toString().trim().isEmpty()
            ) {
                Load load = new Load(
                        selectedOption,
                        pickupLocation.getText().toString().trim(),
                        Integer.parseInt(expPrice.getText().toString().trim()),
                        loadingDateLoad.getText().toString().trim(),
                        dropLocation.getText().toString().trim(),
                        comments.getText().toString().trim()
                );
                vm.AddLoad(load);
                Toast.makeText(requireContext(), "Successful Load", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }
            else
                Toast.makeText(requireContext(), "Please fill every fields", Toast.LENGTH_SHORT).show();
        });
    }

}