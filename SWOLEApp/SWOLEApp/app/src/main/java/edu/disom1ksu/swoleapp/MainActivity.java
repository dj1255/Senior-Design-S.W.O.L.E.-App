package edu.disom1ksu.swoleapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import edu.disom1ksu.swoleapp.Workout;

public class MainActivity extends AppCompatActivity {
    //boolean workout = false;
    boolean deviceConnected = false;
    Main2Activity main2Activity;
    Workout w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button beginWorkout = (Button)findViewById(R.id.BeginWorkout);
        Button connectDevice = (Button)findViewById(R.id.ConnectDevice);
        final EditText weight = (EditText)findViewById(R.id.weight);
        final EditText reps = (EditText)findViewById(R.id.numReps);
        final EditText sets = (EditText)findViewById(R.id.numSets);

        beginWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weightAmnt = Integer.valueOf(weight.getText().toString());
                int numSets = Integer.valueOf(sets.getText().toString());
                int numReps = Integer.valueOf(reps.getText().toString());
                Workout w = new Workout(weightAmnt, numReps,numSets);
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                startIntent.putExtra("Workout", w);
                startActivity(startIntent);
                // Toast.makeText(MainActivity.this,String.valueOf(w.getReps()),Toast.LENGTH_SHORT).show();
            }
        });

        connectDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent2 = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(startIntent2);

            }
        });
    }
}
