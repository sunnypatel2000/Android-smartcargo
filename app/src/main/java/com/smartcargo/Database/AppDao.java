package com.smartcargo.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {

    //Raw queries
    @Query("SELECT * FROM Cargo")
    LiveData<List<Cargo>> getAllCargo();

    @Query("SELECT * FROM Load")
    LiveData<List<Load>> getAllLoad();

    @Query("SELECT * FROM Cargo where id = :id")
    Cargo getCargo(int id);

    @Query("SELECT * FROM Load where id = :id")
    Load getLoad(int id);

    //Insertion operations
    @Insert
    void AddLoad(Load details);

    @Insert
    void AddCargo(Cargo record);

//    Update operations
    @Update
    void UpdateLoad(Load details);

    @Update
    void UpdateCargo(Cargo rec);


//    Deletion operations
    @Delete
    void RemoveLoad(Load details);

    @Delete
    void RemoveCargo(Cargo debit);

}
