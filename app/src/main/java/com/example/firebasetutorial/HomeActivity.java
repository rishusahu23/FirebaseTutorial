package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signOutButton,deleteButton, pushButton,displayButton;

    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intialiseUI();

        signOutButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        pushButton.setOnClickListener(this);
        displayButton.setOnClickListener(this);


    }

    private void intialiseUI() {

        signOutButton=findViewById(R.id.sign_out_id);
        deleteButton=findViewById(R.id.delete_id);
        pushButton =findViewById(R.id.push_data_id);
        displayButton=findViewById(R.id.display_data_id);

        //FIREBASE
        mAuth=FirebaseAuth.getInstance();
      //  mFirebaseDatabase =FirebaseDatabase.getInstance();
        //mDatabaseReference=mFirebaseDatabase.getReference("students");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sign_out_id:
                mAuth.signOut();
                Toast.makeText(this, "user is sign out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                finish();
                break;

            case R.id.delete_id:
                deleteAccount();
                break;

            case R.id.push_data_id:
                startActivity(new Intent(HomeActivity.this,PushToFirebase.class));
                break;

            case R.id.display_data_id:
                startActivity(new Intent(HomeActivity.this,DisplayActivity.class));
                break;
        }
    }



    private void deleteAccount() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            user.delete().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(HomeActivity.this, "account id deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,MainActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(HomeActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
