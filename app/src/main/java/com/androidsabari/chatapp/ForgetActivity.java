package com.androidsabari.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {

    private TextInputEditText editTextForget;
    private Button buttonForget;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        editTextForget = findViewById(R.id.editTextForget);
        buttonForget = findViewById(R.id.buttonForget);

        auth = FirebaseAuth.getInstance();
        
        buttonForget.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = editTextForget.getText().toString().trim();
                if(!email.equals("")){
                    passwordReset(email);
                }
            }
        });

    }

    public void passwordReset(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetActivity.this, "Please Check your Email", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgetActivity.this, "There is a problem! Please try again later", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}