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
//                            .addCallback(new RoomDatabase.Callback(){
//                                @Override
//                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                                    super.onCreate(db);
//                                    new populateDB(context).execute();
//                                }
//                            })
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
            String[] materials = {};
            String[] truckTypes = {};
            int[] loadVals = {};
            String[] pickupLocs = {};
            int[] weights = {};
            int[] truckReqs = {};
            String[] dates = {};
            String[] droploc = {};

            //Load Array
            String[] tts = {};
            String[] pickLoadLocs = {};
            int[] expPrices = {};
            String[] loaddates = {};
            String[] droplocs = {};
            String[] comments = {};
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
