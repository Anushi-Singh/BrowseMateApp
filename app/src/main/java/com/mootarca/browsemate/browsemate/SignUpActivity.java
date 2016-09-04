package com.mootarca.browsemate.browsemate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText et4,et5;
    String email,password;
    FirebaseAuth fa;
    FirebaseAuth.AuthStateListener fal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        et4=(EditText)findViewById(R.id.editText4);
        et5=(EditText)findViewById(R.id.editText5);
        fa=FirebaseAuth.getInstance();
        fal= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //TODO Code Here
            }
        };
    }
    public void signUp(View v){
        email=et4.getText().toString().trim();
        password=et5.getText().toString().trim();
        fa.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, R.string.registered_sign_up, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "False", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fa.addAuthStateListener(fal);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fa!=null){
            fa.removeAuthStateListener(fal);
        }
    }
}
