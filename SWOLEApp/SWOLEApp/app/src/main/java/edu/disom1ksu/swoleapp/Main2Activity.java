package edu.disom1ksu.swoleapp;

import android.content.Intent;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;

import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.nio.charset.Charset;

import edu.disom1ksu.swoleapp.Workout;


public class Main2Activity extends AppCompatActivity  {

    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        workout = (Workout) i.getSerializableExtra("Workout");
        Button EndWorkout = (Button)findViewById(R.id.endWorkout);
        Button updateWorkout = (Button)findViewById(R.id.updateWorkout);
        Button RepDone = (Button)findViewById(R.id.repDone);

        final EditText weight = (EditText)findViewById(R.id.weightAmount);
        final EditText reps = (EditText)findViewById(R.id.reps);
        final EditText currSet = (EditText)findViewById(R.id.currentSet);
        final EditText totSets = (EditText)findViewById(R.id.totalSets);
        reps.setText(String.valueOf(workout.getReps()));
        weight.setText(String.valueOf(workout.getWeight()));
        currSet.setText(String.valueOf(workout.getcurrentSet()));
        totSets.setText(String.valueOf(workout.getSets()));

        RepDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRep(reps, currSet);


            }
        });

       /* updateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout.setReps();
                workout.setSets();
            }
        });
*/
        //try {
       final BluetoothCommunicationService  mBluetoothComm = BTConnection.getConnection().getCurrentBluetoothConnection();
        String s = "$1";
             mBluetoothComm.write(s.getBytes(Charset.defaultCharset()));

        //}
        //catch (Exception e){
          //  Log.e("Main2Activity", "Bluetooth connection not found " + e.getMessage());

        //}

        EndWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout.endWorkout();
                String str = "$2";
                mBluetoothComm.write(str.getBytes(Charset.defaultCharset()));
                Intent startIntent = new Intent(getApplicationContext(), MainActivity4.class);
                startIntent.putExtra("Workout", workout);
                startActivity(startIntent);

            }
        });


        new Thread( new Runnable(){
            @Override
            public void run(){
                Looper.prepare();
                while(true) {
                    if (mBluetoothComm.haveMessage()) {
                        if (mBluetoothComm.receivedString().equals("1")) {
                           doRep(reps, currSet);
                        }
                    }
                }

            }
        }).start();


    }


    public void doRep(EditText reps, EditText sets)
    {
        int newRepsLeft = workout.repComplete();
        reps.setText(String.valueOf(newRepsLeft));
        if(newRepsLeft ==  workout.getReps() && workout.isWorkoutOngoing())
        {
            sets.setText(String.valueOf(workout.getcurrentSet()));
        }


    }



}
