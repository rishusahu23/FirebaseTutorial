package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEdittext,passwordEdittext;
    private Button registerButton;

    //FIREBASE AUTH
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialiseUI();

        registerButton.setOnClickListener(this);
    }

    private void initialiseUI() {
        emailEdittext=findViewById(R.id.email_id);
        passwordEdittext=findViewById(R.id.password_id);
        registerButton=findViewById(R.id.register_id);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_id:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email=emailEdittext.getText().toString().trim();
        if(email.length()==0 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("email is required");
            emailEdittext.requestFocus();
            return;
        }
        String password=passwordEdittext.getText().toString().trim();
        if(password.length()<6){
            passwordEdittext.setError("required password is of length 6 or greater");
            passwordEdittext.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "REGISTRATION IS SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"error is occurred" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
