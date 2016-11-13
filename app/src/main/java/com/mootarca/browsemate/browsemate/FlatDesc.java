package com.mootarca.browsemate.browsemate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FlatDesc extends AppCompatActivity {
    TextView tv6,tv7,tv8,tv9;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_desc);
        dr= FirebaseDatabase.getInstance().getReference();
        tv6=(TextView)findViewById(R.id.textView6);
        tv7=(TextView)findViewById(R.id.textView7);
        tv8=(TextView)findViewById(R.id.textView8);
        tv9=(TextView)findViewById(R.id.textView9);
        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");
        tv6.setText(name);
       /* Query myTopPostsQuery = dr.child(name)
                .limitToFirst(100);*/

        ValueEventListener vel=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // String s=dataSnapshot.getValue().toString();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    // Query myTopPostsQuery = child.child("name");
                    String temp =child.child("name").getValue().toString();
                    if (temp.equals(name)) {
                        String price = child.child("price").getValue().toString();
                        String type = child.child("type").getValue().toString();
                        // String id =child.child("id").getValue().toString();
                        //  tv7.setText(id);
                        tv8.setText(price);
                        tv9.setText(type);
                    }
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dr.addValueEventListener(vel);


/*
        // My top posts by number of stars
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    // Query myTopPostsQuery = child.child("name");
                    String price =child.child("price").getValue().toString();
                    String type =child.child("type").getValue().toString();
                    // String id =child.child("id").getValue().toString();
                    //  tv7.setText(id);
                    tv8.setText(price);
                    tv9.setText(type);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
               // Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });*/

    }
}
