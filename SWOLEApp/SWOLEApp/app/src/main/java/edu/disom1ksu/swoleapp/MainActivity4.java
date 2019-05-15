package edu.disom1ksu.swoleapp;

import android.os.Bundle;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import  java.util.StringTokenizer;

import edu.disom1ksu.swoleapp.Workout;

public class MainActivity4  extends AppCompatActivity  {
    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent i = getIntent();
        workout = (Workout) i.getSerializableExtra("Workout");
        final EditText total_Reps = (EditText)findViewById(R.id.numTotalReps);
        final EditText max_Velocity = (EditText) findViewById(R.id.maxVelocity);
        final EditText max_Force = (EditText)findViewById(R.id.maxForce);
        final EditText max_theta = (EditText)findViewById(R.id.maxTheta);
        final TextView feedback = (TextView) findViewById(R.id.Feedback);

        //feedback.setMovementMethod(new ScrollingMovementMethod());
        Button Done = (Button) findViewById(R.id.done);
        total_Reps.setText(String.valueOf(workout.getTotalReps()));
        final BluetoothCommunicationService  mBluetoothComm = BTConnection.getConnection().getCurrentBluetoothConnection();
        new Thread( new Runnable(){
            @Override
            public void run(){
                int inputCount = 1;
                String s = "";
                int flag = 1;
                Looper.prepare();
                while(true) {
                    if (mBluetoothComm.haveMessage()) {
                     /*   if(mBluetoothComm.receivedString().equals(" ")){
                            inputCount++;
                        }
                        if(inputCount == 1 && flag==1)
                        {
                            flag = 0;
                          //  while(! mBluetoothComm.receivedString().equals(" "))
                           // {
                           //     s += mBluetoothComm.receivedString();
                           // }
                            max_Velocity.setText(s);


                        }
                        if(inputCount == 2)
                        {
                           // s = "";
                           // while(! mBluetoothComm.receivedString().equals(" "))
                            //{
                            //    s += mBluetoothComm.receivedString();
                           // }
                            max_theta.setText(s);

                        }
                        if(inputCount == 3)
                        {
                        //    s = "";
                         //   while(! mBluetoothComm.receivedString().equals(" "))
                          //  {
                          //      s += mBluetoothComm.receivedString();
                           // }
                            max_Force.setText(s);

                        }

                        */
                        String input = mBluetoothComm.receivedString();
                        StringTokenizer st = new StringTokenizer(input," ");
                        max_Velocity.setText( st.nextToken()); // maxv now
                        max_theta.setText(st.nextToken());
                        float force = workout.calculateForce(Float.parseFloat(st.nextToken()));
                        max_Force.setText(Float.toString(force));//workout.calculateForce());



                    }
                }


            }
        }).start();


        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });
        int workoutForm = 50;//Integer.parseInt(st.nextToken());
        if(workoutForm <= 100)
        {
            feedback.setText("Great form, the bar stayed level while you were lifting.");
        }

        else if( workoutForm > 100 && workoutForm <= 500)
        {
            feedback.setText("Pretty good form, but could use some improvement.");
        }
        else if(workoutForm > 500)
        {
            feedback.setText("Bar not level while lifting, practice improving your form with a lighter weight.");

        }
       // switch (workoutForm)
        //{

        //}


         /*  BluetoothCommunicationService mBluetoothComm = null;
        try {
             mBluetoothComm = BTConnection.getConnection().getCurrentBluetoothConnection();
        }
        catch (Exception e){
            Log.e("Main2Activity", "Bluetooth connection not found " + e.getMessage());

        }


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
                //do work here
               // Looper.loop();

            }
        }).start();

        while(true) {
            if(mBluetoothComm.haveMessage())
            {

            

            }
        }
        */


    }

}
