package com.smartcargo.MajorViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.smartcargo.R;

import static com.smartcargo.MajorViews.MainActivity.TYPECAGRO;
import static com.smartcargo.MajorViews.MainActivity.TYPEKEY;
import static com.smartcargo.MajorViews.MainActivity.TYPELOAD;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void showList(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void addNewCargo(View view) {
        Intent i = new Intent(this, AddNewOrder.class);
        i.putExtra(TYPEKEY, TYPECAGRO);
        startActivity(i);
    }

    public void addNewLoad(View view) {
        Intent i = new Intent(this, AddNewOrder.class);
        i.putExtra(TYPEKEY, TYPELOAD);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mainMenuLogout){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return true;
        }

        return false;
    }

}