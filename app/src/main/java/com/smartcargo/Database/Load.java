package com.smartcargo.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Load {


    @PrimaryKey(autoGenerate = true)
    int id;

    public String truckType;
    public String pickupLocation;
    public int expectedPrice;
    public String loadingDate;
    public String dropLocation;
    public String comments;

    public Load(String truckType, String pickupLocation, int expectedPrice,
                String loadingDate, String dropLocation, String comments) {

        this.truckType = truckType;
        this.pickupLocation = pickupLocation;
        this.expectedPrice = expectedPrice;
        this.loadingDate = loadingDate;
        this.dropLocation = dropLocation;
        this.comments = comments;
    }
}
