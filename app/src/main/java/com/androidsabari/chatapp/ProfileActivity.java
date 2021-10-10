package com.androidsabari.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView imageViewCircleProfile;
    private TextInputEditText editTextUserNameProfile;
    private Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageViewCircleProfile = findViewById(R.id.imageViewCircleProfile);
        editTextUserNameProfile = findViewById(R.id.editTextUserNameProfile);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        
        buttonUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }
}