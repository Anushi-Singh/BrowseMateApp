package com.mootarca.browsemate.browsemate;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Typeface type=Typeface.createFromAsset(getAssets(),"fonts/ARDESTINE.ttf");
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setTypeface(type);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/cour.ttf");
        EditText email=(EditText) findViewById(R.id.email);
        EditText password=(EditText) findViewById(R.id.password);
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
        Toast.makeText(SignInActivity.this, "sign in", Toast.LENGTH_SHORT).show();
    }
    public void signUp(View v){
        Toast.makeText(SignInActivity.this, "sign up", Toast.LENGTH_SHORT).show();

    }
    public void googleAuth(View v){
        Toast.makeText(SignInActivity.this, "google", Toast.LENGTH_SHORT).show();

    }
    public void fbAuth(View v){
        Toast.makeText(SignInActivity.this, "fb", Toast.LENGTH_SHORT).show();

    }
}
