package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name,course,dept;
    Button btn;
    FirebaseFirestore firestore;
    String stname,stcourse,stdept;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editTextTextPersonName);
        course=findViewById(R.id.editTextTextPersonName2);
        dept=findViewById(R.id.editTextTextPersonName3);
        btn=findViewById(R.id.button);
        firestore= FirebaseFirestore.getInstance();



        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                stname=name.getText().toString().trim();
                stcourse=course.getText().toString().trim();
                stdept=dept.getText().toString().trim();


                Map<String ,Object> user=new HashMap<>();
                user.put("name",stname);
                user.put("course",stcourse);
                user.put("department",stdept);

                firestore.collection("students").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(MainActivity.this, "student details stored", Toast.LENGTH_SHORT).show();
                        Intent itent= new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(itent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to store data", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}