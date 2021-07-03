package com.smartcargo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.smartcargo.Database.Cargo;
import com.smartcargo.Database.Load;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.smartcargo.MainActivity.l;

public class CargoListAdapter extends RecyclerView.Adapter<CargoListAdapter.CargoListAdapterVH> {

    private List<Cargo> cargoList = new ArrayList<>();

    public CargoListAdapter(List<Cargo> cargoList){
        if(cargoList != null)
            this.cargoList = cargoList;
    }

    @NonNull
    @Override
    public CargoListAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CargoListAdapterVH(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cargo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CargoListAdapter.CargoListAdapterVH holder, int position) {
        Cargo record = this.cargoList.get(position);
        holder.material.setText(record.materialType);
        holder.weight.setText(String.valueOf(record.weight));
        holder.truckType.setText(record.truckType);
        holder.truckReq.setText(String.valueOf(record.trucksRequired));
        holder.loadValue.setText(String.valueOf(record.loadValue));
        holder.loadingDate.setText(record.date);
        holder.pickupLoc.setText(record.pickupLocation);
        holder.dropLoc.setText(record.dropLocation);
    }

    public void setList(List<Cargo> list){
        this.cargoList = list;
        notifyDataSetChanged();
        l("adapter list " + this.cargoList.size());
    }

    @Override
    public int getItemCount() {
        return this.cargoList.size();
    }

    public static class CargoListAdapterVH extends RecyclerView.ViewHolder{

        TextView material,weight,truckType,truckReq,loadValue,loadingDate,pickupLoc,dropLoc;

        public CargoListAdapterVH(@NonNull View itemView) {
            super(itemView);
            material = itemView.findViewById(R.id.material);
            weight = itemView.findViewById(R.id.weight);
            truckType = itemView.findViewById(R.id.truckType);
            truckReq = itemView.findViewById(R.id.truckReq);
            loadValue = itemView.findViewById(R.id.loadValue);
            loadingDate = itemView.findViewById(R.id.loadingDate);
            pickupLoc = itemView.findViewById(R.id.pickupLoc);
            dropLoc = itemView.findViewById(R.id.dropLoc);
        }
    }

}
