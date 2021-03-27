package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button b_login;
    private EditText username, password;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        b_login = findViewById(R.id.login);
        username = (EditText) findViewById(R.id.textin_user);
        password = (EditText) findViewById(R.id.textin_password);
    }

    public void login(View view) {
        if(username.getText() == null || password.getText() == null)
            Toast.makeText(this, "Rellene los campos", Toast.LENGTH_LONG).show();
        else {
            String email = username.getText().toString();
            String pass = password.getText().toString();
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "logInWithEmail:success");
                                Intent intent = new Intent(LoginActivity.this, EmptyActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                Log.w(TAG, "logInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
