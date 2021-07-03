package com.smartcargo.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cargo {

    @PrimaryKey(autoGenerate = true)
    int id;

    public String materialType;
    public String truckType;
    public int loadValue;
    public String pickupLocation;
    public int weight;
    public int trucksRequired;
    public String date;
    public String dropLocation;

    public Cargo(String materialType, String truckType,
                 int loadValue, String pickupLocation, int weight,
                 int trucksRequired, String date, String dropLocation) {

        this.materialType = materialType;
        this.truckType = truckType;
        this.loadValue = loadValue;
        this.pickupLocation = pickupLocation;
        this.weight = weight;
        this.trucksRequired = trucksRequired;
        this.date = date;
        this.dropLocation = dropLocation;
    }
}
