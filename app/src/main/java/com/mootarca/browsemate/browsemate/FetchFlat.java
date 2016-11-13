package com.mootarca.browsemate.browsemate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FetchFlat extends AppCompatActivity {
    ListView lv;
    DatabaseReference dr;
    List mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_flat);
        lv=(ListView)findViewById(R.id.listView);
        dr= FirebaseDatabase.getInstance().getReference();
        mylist=new ArrayList();
        ValueEventListener vel=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mylist.clear();
                // String s=dataSnapshot.getValue().toString();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    // Query myTopPostsQuery = child.child("name");
                    String name1 =child.child("name").getValue().toString();
                    mylist.add(name1);
                }
                Toast.makeText(FetchFlat.this, "Toast"+Integer.toString(mylist.size()), Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(FetchFlat.this,android.R.layout.simple_dropdown_item_1line,mylist);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dr.addValueEventListener(vel);
    }
}
