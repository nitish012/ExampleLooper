package com.example.examplelooper;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyThread myThread;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        creatingThread();
        settingListener();

    }

    private void findId() {

        send=findViewById(R.id.send);
    }
    private void creatingThread() {

        //starting thread
        myThread = new MyThread();
        myThread.start();

    }

    private void settingListener() {
        //set listener for interact background thread with UI

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("tag", String.valueOf(Thread.currentThread().getId()));
                    }
                });
            }
        });
    }



}


class MyThread extends Thread{

    Handler handler;
//        public MyThread()
//        {
//
//        }

    @Override
    public void run() {

        Looper.prepare(); //initialise looper for taking message from other threads
        handler=new Handler(); //handle incoming message to mythread
        Looper.loop(); //pick each message at a time from message queue, process message queue with handler
    }
}


