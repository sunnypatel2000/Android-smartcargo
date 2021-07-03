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
import com.smartcargo.Database.Load;

import static com.smartcargo.AddNewOrder.vm;

public class addLoad extends Fragment {

    EditText truckTypeLoad, pickupLocation, expPrice, loadingDateLoad, dropLocation, comments;
    Button b;

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
        truckTypeLoad = itemView.findViewById(R.id.truckTypeLoad);
        pickupLocation = itemView.findViewById(R.id.pickupLocation);
        expPrice = itemView.findViewById(R.id.expPrice);
        loadingDateLoad = itemView.findViewById(R.id.loadingDateLoad);
        dropLocation = itemView.findViewById(R.id.dropLocation);
        comments = itemView.findViewById(R.id.comments);

        b = itemView.findViewById(R.id.okl);

        b.setOnClickListener(view -> {
            Load load = new Load(
                    truckTypeLoad.getText().toString().trim(),
                    pickupLocation.getText().toString().trim(),
                    Integer.parseInt(expPrice.getText().toString().trim()),
                    loadingDateLoad.getText().toString().trim(),
                    dropLocation.getText().toString().trim(),
                    comments.getText().toString().trim()
            );
            vm.AddLoad(load);
            Toast.makeText(requireContext(), "Added New Load", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
        });
    }

}