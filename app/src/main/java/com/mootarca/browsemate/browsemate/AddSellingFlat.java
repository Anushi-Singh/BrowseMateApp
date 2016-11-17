package com.mootarca.browsemate.browsemate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSellingFlat extends AppCompatActivity {
    EditText et8,et9,et10;
    Button b6;
    // String price,name,id;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_selling_flat);
        et8=(EditText)findViewById(R.id.editText8);
        et9=(EditText)findViewById(R.id.editText9);
        et10=(EditText)findViewById(R.id.editText10);
        b6=(Button)findViewById(R.id.button6);
    }
    public void submit(View v){
        String price=et8.getText().toString().trim();
        String name=et9.getText().toString().trim();
        String id=et10.getText().toString().trim();
        String type="selling";
        // Write a message to the database
        //AddFlat af=new AddFlat();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(id);

        myRef.setValue(id);
        myRef = database.getReference(id).child("name");
        myRef.setValue(name);
        myRef = database.getReference(id).child("price");
        myRef.setValue(price);
        myRef = database.getReference(id).child("type");
        myRef.setValue(type);
        et8.setText("");
        et9.setText("");
        et10.setText("");
        Toast.makeText(AddSellingFlat.this, "Completed", Toast.LENGTH_SHORT).show();
    }
}
