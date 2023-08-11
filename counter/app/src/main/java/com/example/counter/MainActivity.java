package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView output = findViewById(R.id.textView);
        Button start = findViewById(R.id.btnstart);
        Button stop = findViewById(R.id.btnstop);

        start.setOnClickListener( v -> {
            start.setEnabled(false);
            stop.setEnabled(true);
            String temp = output.getText().toString();
            if(temp.isEmpty())
                counter = 0;
            else
                counter = Integer.valueOf(temp) ;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop.isPressed()){
                        try{
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            return;
                        }
                        output.post(new Runnable() {
                            @Override
                            public void run() {
                                counter++;
                                output.setText(""+counter);
                            }
                        });
                    }
                }
            }).start();
        });

        stop.setOnClickListener( v -> {
            try{
                Thread.sleep(500);
                Thread.interrupted();
            }catch (Exception e){
                e.printStackTrace();
            }
            stop.setEnabled(false);
            start.setEnabled(true);
        });
    }
}