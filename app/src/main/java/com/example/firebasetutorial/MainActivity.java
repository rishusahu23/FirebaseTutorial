package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEdittext,passwordEdittext;
    private Button signInButton,registerButton;
    private TextView forgotPasswordTextview;

    //FIREBASE
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intialiseUI();

        signInButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        forgotPasswordTextview.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_id:
                Intent intent1=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;


            case R.id.sign_in_id:
                signInUser();
                break;


            case R.id.forgot_password_id:
                startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
                break;

        }
    }

    private void signInUser() {
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

       mAuth.signInWithEmailAndPassword(email,password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this, "user is logged in", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(MainActivity.this,HomeActivity.class));
                           finish();
                       }
                       else{
                           Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               });


    }

    private void intialiseUI() {
        emailEdittext=findViewById(R.id.email_id);
        passwordEdittext=findViewById(R.id.password_id);
        signInButton=findViewById(R.id.sign_in_id);
        registerButton=findViewById(R.id.register_id);
        mAuth=FirebaseAuth.getInstance();
        forgotPasswordTextview=findViewById(R.id.forgot_password_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }
}
