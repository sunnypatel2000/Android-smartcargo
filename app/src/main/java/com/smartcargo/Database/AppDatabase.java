package com.smartcargo.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.lang.ref.WeakReference;

@Database(entities = {Cargo.class, Load.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao dao();
    public static AppDatabase dbInstance;

    static AppDatabase getInstance(final Context context){

        if(dbInstance == null){
            synchronized (AppDatabase.class){
                if(dbInstance == null){
                    dbInstance = Room.databaseBuilder(context, AppDatabase.class, "LendersDetailDB")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new populateDB(context).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return dbInstance;
    }

    private static class populateDB extends AsyncTask<Void, Void, Void>{

        WeakReference<Context> context;

        public populateDB(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... v) {
            // Cargo Array
        String[] materials = {"Alcohol & Spirits", "Automobile Component","Building Materials", "Fruits & Vegetables"};
            String[] truckTypes = {"LCV Open Body TATA", "Open Body Taurus","Trailer", "Container", "less 750 kg"};
            int[] loadVals = {25000,20000,40000,10000};
            String[] pickupLocs = {"ahmedabad","surat","baroda","kokata"};
            int[] weights = {500,600,800,450};
            int[] truckReqs = {2,3,5,1};
            String[] dates = {"01/02/2021","30/06/2020","01/03/2021","21/05/2021"};
            String[] droploc = {"goa","tamilnadu","mumbai","uatterpradesh"};

            //Load Array
            String[] tts = {"LCV Open Body TATA", "Open Body Taurus","Trailer", "Container", "less 750 kg"};
            String[] pickLoadLocs = {"ahmedabad","surat","baroda","kokata"};
            int[] expPrices = {5000,25000,20000,40000};
            String[] loaddates = {"11/02/2021","05/08/2020","01/04/2021","10/07/2021"};
            String[] droplocs = {"chennai","tamilnadu","mumbai","uatter pradesh"};
            String[] comments = {"New Load","New Load","New Load","New Load"};
            AppDao dao = getInstance(context.get()).dao();
            for(int i = 0; i < 6; i++) {
                dao.AddCargo(new Cargo(materials[i], truckTypes[i]
                        ,loadVals[i], pickupLocs[i]
                        , weights[i], truckReqs[i], dates[i], droploc[i]));

                dao.AddLoad(new Load(tts[i], pickLoadLocs[i],
                        expPrices[i],loaddates[i],
                        droplocs[i], comments[i]));
            }

            return null;
        }
    }

}
