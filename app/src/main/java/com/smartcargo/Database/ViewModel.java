package com.smartcargo.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.smartcargo.Database.Cargo;
import com.smartcargo.Database.DataRepository;
import com.smartcargo.Database.Load;

import java.util.List;


public class ViewModel extends AndroidViewModel {

    private final DataRepository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
    }

//    Raw operation

    public LiveData<List<Cargo>> getCargoList(){
        return repository.getCargoList();
    }

    public LiveData<List<Load>> getLoadList(){
        return repository.getLoadList();
    }

    public Cargo getCargo(int id){
        return repository.getCargo(id);
    }

    public Load getLoad(int id){
        return repository.getLoad(id);
    }

    //    Insertion operations
    public void AddCargo(Cargo details){
        repository.AddCargo(details);
    }

    public void AddLoad(Load record){
        repository.AddLoad(record);
    }

    //    Update operations
    public void UpdateCargo(Cargo details){
        repository.UpdateCargo(details);
    }

    public void UpdatePaidDebitRecord(Load record){
        repository.UpdateLoad(record);
    }

    //    Deletion operations
    public void RemoveCargo(Cargo details){
        repository.RemoveCargo(details);
    }

    public void RemoveLoad(Load debit){
        repository.RemoveLoad(debit);
    }
}
