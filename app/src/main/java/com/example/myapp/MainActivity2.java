package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    Button button2;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2.findViewById(R.id.button2);
        firestore=FirebaseFirestore.getInstance();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("students").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity2.this, "", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(MainActivity2.this, list.class);
                            startActivity(intent);
                            for (QueryDocumentSnapshot documentSnapshot:task.getResult()){
                                Log.d("Read data",documentSnapshot.getId()+"==="+documentSnapshot.getData());
                            }
                        }
                    }
                });
            }
        });
    }
}