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

import com.smartcargo.Database.Cargo;
import com.smartcargo.R;

import static com.smartcargo.MajorViews.AddNewOrder.vm;

public class addCargo extends Fragment {

    EditText weight,truckReq,loadValue,loadingDate,pickupLoc,dropLoc;
    Spinner material, truckType;
    String selectedOption, selectedOptionType;
    Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_cargo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View itemView){
        material = itemView.findViewById(R.id.material);
        weight = itemView.findViewById(R.id.weight);
        truckType = itemView.findViewById(R.id.truckType);
        truckReq = itemView.findViewById(R.id.truckReq);
        loadValue = itemView.findViewById(R.id.loadValue);
        loadingDate = itemView.findViewById(R.id.loadingDateEt);
        pickupLoc = itemView.findViewById(R.id.pickupLoc);
        dropLoc = itemView.findViewById(R.id.dropLoc);
        b = itemView.findViewById(R.id.okc);

        String[] options = new String[]{"Alcohol & Spirits", "Automobile Component",
                "Building Materials", "Fruits & Vegetables", "Dairy Products"};
        selectedOption = options[0];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, options);

        String[] optionType = new String[]{"LCV Open Body TATA", "Open Body Taurus",
                "Trailer", "Container", "less 750 kg"};
        selectedOptionType = optionType[0];
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, optionType);

        material.setAdapter(adapter);
        truckType.setAdapter(adapterType);

        material.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOption = options[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        truckType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOptionType = optionType[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        b.setOnClickListener(view -> {

            if(!selectedOption.isEmpty() &&
                !selectedOptionType.trim().isEmpty() &&
                !loadValue.getText().toString().trim().isEmpty() &&
                !pickupLoc.getText().toString().trim().isEmpty() &&
                !weight.getText().toString().trim().isEmpty() &&
                !truckReq.getText().toString().trim().isEmpty() &&
                !loadingDate.getText().toString().trim().isEmpty() &&
                !dropLoc.getText().toString().trim().isEmpty()) {

                Cargo cargo = new Cargo(
                        selectedOption,
                        selectedOptionType.trim(),
                        Integer.parseInt(loadValue.getText().toString().trim()),
                        pickupLoc.getText().toString().trim(),
                        Integer.parseInt(weight.getText().toString().trim()),
                        Integer.parseInt(truckReq.getText().toString().trim()),
                        loadingDate.getText().toString().trim(),
                        dropLoc.getText().toString().trim()
                );
                vm.AddCargo(cargo);
                Toast.makeText(requireContext(), "Successful Added Cargo", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }else
                Toast.makeText(requireContext(), "Please fill every fields", Toast.LENGTH_SHORT).show();
        });
    }

}