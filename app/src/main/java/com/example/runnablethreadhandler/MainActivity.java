package com.example.runnablethreadhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String btnClicked="Button Clicked";
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public  void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String message=bundle.getString("key");
            textView.setText(message);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
    }

    public void buttonClick(View view) {
        Runnable run = new Runnable() {
            final String taskFinished="Task Finished";
            final Message msg= new Message();
            final Bundle bundle=new Bundle();
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    bundle.putString("key", taskFinished);
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread=new Thread(run);
        thread.start();



        textView.setText(btnClicked);

    }

}