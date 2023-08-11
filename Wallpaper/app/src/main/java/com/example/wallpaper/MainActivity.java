package com.example.wallpaper;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};

        Timer timer = new Timer();
        Random random = new Random();
        View wallView = findViewById(R.id.view);

        findViewById(R.id.button).setOnClickListener( v-> {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            wallView.setBackground(ContextCompat.getDrawable(getApplicationContext(), images[random.nextInt(images.length)]));
                        }
                    });
                }
            },0, 3000);
        });

    }
}
