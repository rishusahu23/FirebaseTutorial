package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasetutorial.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PushToFirebase extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEdittext,nameEditText,regNumberEdittext;
    private Button pushButton;

    //FIREBASE
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_to_firebase);

        intialiseUI();

        pushButton.setOnClickListener(this);


    }

    private void intialiseUI() {
        emailEdittext=findViewById(R.id.email_id);
        nameEditText=findViewById(R.id.name_id);
        regNumberEdittext=findViewById(R.id.reg_number_id);
        pushButton=findViewById(R.id.push_button_id);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("students");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.push_button_id:
                pushData();
                break;
        }
    }

    private void pushData() {
        String name=nameEditText.getText().toString();
        String regNo=regNumberEdittext.getText().toString();
        long  regNumber=Long.parseLong(regNo);
        String email=emailEdittext.getText().toString();
        String userId=firebaseAuth.getCurrentUser().getUid();
        String randomKey=databaseReference.push().getKey();

        Student student=new Student(name,email,regNumber);


        databaseReference.child(userId).child(randomKey).setValue(student).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PushToFirebase.this, "task is pushed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PushToFirebase.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
