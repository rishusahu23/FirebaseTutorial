package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView goBackTextview;
    private Button resetButton;
    private EditText emailEdittext;

    //FIREBASE
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initialiseUI();

        mAuth=FirebaseAuth.getInstance();

        resetButton.setOnClickListener(this);
        goBackTextview.setOnClickListener(this);

    }

    private void initialiseUI() {
        goBackTextview=findViewById(R.id.go_back_id);
        resetButton=findViewById(R.id.reset_id);
        emailEdittext=findViewById(R.id.email_id);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.reset_id:
                resetPassword();
                break;
            case R.id.go_back_id:
                finish();
                break;
        }
    }

    private void resetPassword() {
        String email=emailEdittext.getText().toString();
        if(email.length()==0||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("enter required email");
            emailEdittext.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPasswordActivity.this, "we have send you instruction", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
