package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {
    ListView lists;
    FirebaseFirestore firestore;

    List<String> values= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lists=findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.activity_list_item,values);
        lists.setAdapter(arrayAdapter);
        firestore=FirebaseFirestore.getInstance();
        firestore.collection("students").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(list.this, "Read data", Toast.LENGTH_SHORT).show();
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Log.d("This is the data",documentSnapshot.getId()+"==="+ documentSnapshot.getData());
                        values.add(documentSnapshot.getString("course name") + documentSnapshot.getData());
                    }
                    arrayAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(list.this, "read data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}