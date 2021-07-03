package com.smartcargo.MajorViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.smartcargo.MainActivity;
import com.smartcargo.R;

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        checkLogin();
    }

    private void checkLogin() {
        if (auth.getCurrentUser() != null)
            start();
    }

    private void init() {
        userId = findViewById(R.id.username);
        password = findViewById(R.id.password);

        auth =  FirebaseAuth.getInstance();
    }

    public void login(View view){
        auth.signInWithEmailAndPassword(userId.getText().toString().trim(),
                password.getText().toString().trim()).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                start();
            else
                Toast.makeText(this, "Sorry incorrect credentials", Toast.LENGTH_SHORT).show();
        });
    }

    private void start(){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void forgetPassword(View view) {
        auth.sendPasswordResetEmail(userId.getText().toString().trim());
    }
}