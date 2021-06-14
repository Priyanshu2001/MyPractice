package com.prbansal.authenticationactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.prbansal.authenticationactivity.databinding.ActivityRealtimeDbpracticeBinding;
import com.prbansal.authenticationactivity.models.Sample;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class RealtimeDBPractice extends AppCompatActivity {
    ActivityRealtimeDbpracticeBinding binding;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRealtimeDbpracticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String date = DateFormat.getDateInstance(DateFormat.DEFAULT).format(Calendar.getInstance().getTime());

        Sample sample = new Sample();
        sample.colors.put("red", 0);
        sample.digits.put("1", 0);
        myRef.child(date).child("1").setValue(sample);

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(date).child("1").child("noOfBids").setValue(ServerValue.increment(1));
                myRef.child(date).child("1").child("colors").child("red").setValue(ServerValue.increment(1));
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RealtimeDBPractice.this, myRef.child("testing").child("1").hashCode() + "", Toast.LENGTH_SHORT).show();
            }
        });

        myRef.child(date).child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot != null) {
                    /*ArrayList<Sample> objectList = new ArrayList<>();
                    objectList = dataSnapshot.getValue(Sample.class);*/
                    Toast.makeText(RealtimeDBPractice.this, dataSnapshot.getValue() + "", Toast.LENGTH_SHORT).show();
                    Sample value = dataSnapshot.getValue(Sample.class);
                    sample.colors = value.colors;
                    sample.digits = value.digits;
                    Toast.makeText(RealtimeDBPractice.this, value.colors + " " + value.digits, Toast.LENGTH_SHORT).show();
                    Log.e("OK", "Value is: " + value.noOfBids);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("failed", "Failed to read value.", error.toException());
            }
        });
    }
}