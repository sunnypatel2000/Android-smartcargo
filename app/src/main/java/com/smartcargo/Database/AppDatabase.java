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
            String[] name = {"George","Janet","Emma","Eve","Charles","Tracey"};
            String[] cn = {"1234678923","2345678923","3456678923","4567678923","5678678923","6789678923"};
            AppDao dao = getInstance(context.get()).dao();
            for(int i = 0; i < 6; i++) {
                dao.AddCargo(new Cargo("material " + i, "truckType " + i
                        , i * 12, "Location " + i
                        , i * 10, i, "12/12/21", "Droplocation " + i));

                dao.AddLoad(new Load("truckType " + i, "Location " + i,
                        i*13, "12/12/21",
                        "Droplocation " + i, "Comment" + i));
            }

            return null;
        }
    }

}
