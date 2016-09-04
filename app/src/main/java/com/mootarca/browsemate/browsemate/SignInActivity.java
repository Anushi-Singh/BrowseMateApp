package com.mootarca.browsemate.browsemate;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    FirebaseAuth fa,fae;
    GoogleApiClient gac;
    FirebaseAuth.AuthStateListener fal;
    String emailVar,passwordVar;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        fa=FirebaseAuth.getInstance();
        fae=FirebaseAuth.getInstance();
        fal= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser fu=firebaseAuth.getCurrentUser();
                if (fu!=null){

                }
                else {

                }
            }
        };
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        gac=new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        Typeface type=Typeface.createFromAsset(getAssets(),"fonts/ARDESTINE.ttf");
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setTypeface(type);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/cour.ttf");
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        email.setTypeface(typeface);
        password.setTypeface(typeface);

        Typeface t=Typeface.createFromAsset(getAssets(),"fonts/courbd.ttf");
        Button login=(Button)findViewById(R.id.login);
        Button signup=(Button)findViewById(R.id.signup);
        login.setTypeface(t);
        signup.setTypeface(t);

        Typeface type1=Typeface.createFromAsset(getAssets(),"fonts/cour.ttf");
        TextView text=(TextView) findViewById(R.id.textView2);
        text.setTypeface(type1);

        Typeface type2=Typeface.createFromAsset(getAssets(),"fonts/BROADW.ttf");
        TextView text1=(TextView) findViewById(R.id.or);
        text1.setTypeface(type2);

        TextView forgotpass=(TextView) findViewById(R.id.forgotpass);
        forgotpass.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
    public void signIn(View v){
        emailVar=email.getText().toString().trim();
        passwordVar=password.getText().toString().trim();
        fae.signInWithEmailAndPassword(emailVar,passwordVar)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(SignInActivity.this,HomeActivity.class));
                        }
                        else {

                        }
                    }
                });
    }
    public void signUp(View v){
        startActivity(new Intent(SignInActivity.this,SignUpActivity.class));

    }
    public void googleAuth(View v){
        Intent i= Auth.GoogleSignInApi.getSignInIntent(gac);
        startActivityForResult(i,9999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==9999){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                GoogleSignInAccount account=result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        fa.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(SignInActivity.this,HomeActivity.class));
                    finish();
                }
            }
        });
    }

    public void fbAuth(View v){
        Toast.makeText(SignInActivity.this, "fb", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        fae.addAuthStateListener(fal);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fae.removeAuthStateListener(fal);
    }
    public void forgotPassword(View v){
        emailVar=email.getText().toString().trim();
        fae.sendPasswordResetEmail(emailVar)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Check Your Mail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
