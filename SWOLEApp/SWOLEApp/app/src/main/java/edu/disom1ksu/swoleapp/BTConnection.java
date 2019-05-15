package edu.disom1ksu.swoleapp;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

public class BTConnection extends Application {
    private static BTConnection sInstance;

    public static BTConnection getConnection() {
        return sInstance;
    }

   private BluetoothSocket btSocket = null;
    private BluetoothCommunicationService btComm = null;
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public void storeBluetoothConnection( BluetoothCommunicationService bt)
    {
        btComm = bt;

    }

    public BluetoothCommunicationService getCurrentBluetoothConnection() //BluetoothSocket
    {
        //return btSocket;
        return btComm;
    }


}
