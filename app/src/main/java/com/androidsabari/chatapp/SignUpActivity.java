package com.androidsabari.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    private CircleImageView imageViewCircle;
    private TextInputEditText editTextEmailSignup,editTextPasswordSignup,editTextUsernameSignup;
    private Button buttonRegister;
    Boolean imageControl = false;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imageViewCircle = findViewById(R.id.imageViewCircle);
        editTextEmailSignup = findViewById(R.id.editTextEmailSignup);
        editTextPasswordSignup = findViewById(R.id.editTextPasswordSignup);
        editTextUsernameSignup = findViewById(R.id.editTextUserNameSignup);
        buttonRegister = findViewById(R.id.buttonRegister);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        imageViewCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmailSignup.getText().toString().trim();
                String password = editTextPasswordSignup.getText().toString().trim();
                String username = editTextEmailSignup.getText().toString().trim();

                if(!email.equals("") && !password.equals("") && !username.equals("")){
                    signup(email,password,username);
                }
            }
        });




    }

    public void imageChooser(){
        Intent intent = new Intent();
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    public void signup(String email,String password,String username){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    reference.child("Users").child(auth.getUid()).child("userName").setValue(username);

                    if(imageControl){


                    }else{

                        reference.child("Users").child(auth.getUid()).child("image").setValue("null");

                    }

                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    intent.putExtra("userName",username);
                    startActivity(intent);
                    finish();

                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageViewCircle);
            imageControl= true;
        }else{
            imageControl = false;
        }
    }
}