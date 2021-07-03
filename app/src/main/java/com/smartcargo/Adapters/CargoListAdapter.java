package com.smartcargo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.smartcargo.Database.Cargo;
import com.smartcargo.MajorViews.Details;
import com.smartcargo.R;

import java.util.ArrayList;
import java.util.List;

import static com.smartcargo.MainActivity.ID;
import static com.smartcargo.MainActivity.TYPECAGRO;
import static com.smartcargo.MainActivity.TYPEKEY;
import static com.smartcargo.MainActivity.l;

public class CargoListAdapter extends RecyclerView.Adapter<CargoListAdapter.CargoListAdapterVH> {

    private List<Cargo> cargoList = new ArrayList<>();
    Context context;

    public CargoListAdapter(Context context, List<Cargo> cargoList){
        this.context = context;
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

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(this.context, Details.class);
            i.putExtra(TYPEKEY, TYPECAGRO);
            i.putExtra(ID, position);
            context.startActivity(i);
        });

        holder.material.setText("Material Type: " + record.materialType);
        holder.loadingDate.setText("Date: " + record.date);
        holder.pickupLoc.setText("Pickup Location: " + record.pickupLocation);
        holder.dropLoc.setText("Drop Location: " + record.dropLocation);
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
            pickupLoc = itemView.findViewById(R.id.pickupLoc);
            dropLoc = itemView.findViewById(R.id.dropLoc);
            loadingDate = itemView.findViewById(R.id.loadingDateEt);
        }
    }

}
