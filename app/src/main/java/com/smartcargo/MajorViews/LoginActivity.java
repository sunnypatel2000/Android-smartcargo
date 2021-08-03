package com.smartcargo.MajorViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.smartcargo.R;

import org.jetbrains.annotations.NotNull;

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
        if(!userId.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()) {
            auth.signInWithEmailAndPassword(userId.getText().toString().trim(),
                    password.getText().toString().trim()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    start();
                    //Logged thai jai tyare
                    Toast.makeText(this, "logged in successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    //khota password mate
                    Toast.makeText(this, "Sorry incorrect Password and Id", Toast.LENGTH_SHORT).show();
            });
        }else
            //khali re ena mate
            Toast.makeText(this, "Please fill Password and Id", Toast.LENGTH_SHORT).show();
    }

    private void start(){
        Intent i = new Intent(this, HomePage.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void forgetPassword(View view) {
        auth.sendPasswordResetEmail("sahil.patel200038@gmail.com").addOnCompleteListener(task -> {
            if(task.isSuccessful())
                Toast.makeText(this, "Sent reset password link on your email address", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Error Sending Reset password link", Toast.LENGTH_SHORT).show();
        });
    }

    public void Register(View view) {
        Intent i = new Intent(this, RegisterUser.class);
        startActivity(i);
    }
}