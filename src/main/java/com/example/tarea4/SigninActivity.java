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

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button b_signin;
    private EditText username, password;
    private static final String TAG = "SigninActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        b_signin = findViewById(R.id.signin);
        username = (EditText) findViewById(R.id.textin_newuser);
        password = (EditText) findViewById(R.id.textin_newpass);
    }

    public void signin(View view) {
        if(username.getText() == null || password.getText() == null)
            Toast.makeText(this, "Rellene los campos", Toast.LENGTH_LONG).show();
        else {
            String email = username.getText().toString();
            String pass = password.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                Intent intent = new Intent(SigninActivity.this, EmptyActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(SigninActivity.this, "Refistration failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
