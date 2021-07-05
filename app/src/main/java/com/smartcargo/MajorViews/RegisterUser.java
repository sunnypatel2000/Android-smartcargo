package com.smartcargo.MajorViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.smartcargo.R;

public class RegisterUser extends AppCompatActivity {

    FirebaseAuth auth;
    EditText email, pass, confPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);
        confPass = findViewById(R.id.confPass);
    }


    public void registerUser(View view) {
        if(!pass.getText().toString().trim().equals(confPass.getText().toString().trim())){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }else{
            auth.createUserWithEmailAndPassword(email.getText().toString().trim(),
                    pass.getText().toString().trim()).addOnCompleteListener(task -> {
                       if(task.isSuccessful()) {
                           finish();
                           startActivity(new Intent(this, Ack.class));
                       }
                    });
        }
    }

}