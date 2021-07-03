package com.smartcargo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartcargo.Database.Load;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.smartcargo.MainActivity.l;

public class LoadListAdapter extends RecyclerView.Adapter<LoadListAdapter.LoadListAdapterVH> {

    private List<Load> loadList = new ArrayList<>();

    public LoadListAdapter(List<Load> loadList){
        if(loadList != null)
            this.loadList = loadList;
    }

    @NonNull
    @Override
    public LoadListAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LoadListAdapterVH(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.load_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoadListAdapter.LoadListAdapterVH holder, int position) {
        Load record = this.loadList.get(position);
        holder.truckTypeLoad.setText(record.truckType);
        holder.pickupLocation.setText(record.pickupLocation);
        holder.expPrice.setText(String.valueOf(record.expectedPrice));
        holder.loadingDateLoad.setText(record.loadingDate);
        holder.dropLocation.setText(record.dropLocation);
        holder.comments.setText(record.comments);
    }

    public void setList(List<Load> list){
        this.loadList = list;
        notifyDataSetChanged();
        l("adapter list " + this.loadList.size());
    }

    @Override
    public int getItemCount() {
        return this.loadList.size();
    }

    public static class LoadListAdapterVH extends RecyclerView.ViewHolder{

        TextView truckTypeLoad, pickupLocation, expPrice, loadingDateLoad, dropLocation, comments;

        public LoadListAdapterVH(@NonNull View itemView) {
            super(itemView);
            truckTypeLoad = itemView.findViewById(R.id.truckTypeLoad);
            pickupLocation = itemView.findViewById(R.id.pickupLocation);
            expPrice = itemView.findViewById(R.id.expPrice);
            loadingDateLoad = itemView.findViewById(R.id.loadingDateLoad);
            dropLocation = itemView.findViewById(R.id.dropLocation);
            comments = itemView.findViewById(R.id.comments);
        }
    }

}
