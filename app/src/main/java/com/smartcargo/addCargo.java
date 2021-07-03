package com.smartcargo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartcargo.Database.Cargo;

import static com.smartcargo.AddNewOrder.vm;

public class addCargo extends Fragment {

    EditText material,weight,truckType,truckReq,loadValue,loadingDate,pickupLoc,dropLoc;
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
        loadingDate = itemView.findViewById(R.id.loadingDate);
        pickupLoc = itemView.findViewById(R.id.pickupLoc);
        dropLoc = itemView.findViewById(R.id.dropLoc);
        b = itemView.findViewById(R.id.okc);

        b.setOnClickListener(view -> {
            Cargo cargo = new Cargo(
                    material.getText().toString().trim(),
                    truckType.getText().toString().trim(),
                    Integer.parseInt(loadValue.getText().toString().trim()),
                    pickupLoc.getText().toString().trim(),
                    Integer.parseInt(weight.getText().toString().trim()),
                    Integer.parseInt(truckReq.getText().toString().trim()),
                    loadingDate.getText().toString().trim(),
                    dropLoc.getText().toString().trim()
            );
            vm.AddCargo(cargo);
            Toast.makeText(requireContext(), "Added New Cargo", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
        });
    }

}