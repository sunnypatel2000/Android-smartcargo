package com.smartcargo.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {

    private final AppDao dao;
    private final LiveData<List<Cargo>> CargoList;
    private final LiveData<List<Load>> LoadList;

    public DataRepository(final Context context){
        AppDatabase database = AppDatabase.getInstance(context);
        dao = database.dao();
        CargoList = dao.getAllCargo();
        LoadList = dao.getAllLoad();
    }

    public LiveData<List<Cargo>> getCargoList(){
        return CargoList;
    }

    public LiveData<List<Load>> getLoadList(){
        return LoadList;
    }

//    Insertion operations
    public void AddCargo(Cargo details){
        dao.AddCargo(details);
    }

    public void AddLoad(Load record){
        dao.AddLoad(record);
    }

//    Update operations
    public void UpdateCargo(Cargo details){
        dao.UpdateCargo(details);
    }

    public void UpdateLoad(Load record){
        dao.UpdateLoad(record);
    }

//    Deletion operations
    public void RemoveCargo(Cargo details){
        dao.RemoveCargo(details);
    }

    public void RemoveLoad(Load debit){
        dao.RemoveLoad(debit);
    }

}
