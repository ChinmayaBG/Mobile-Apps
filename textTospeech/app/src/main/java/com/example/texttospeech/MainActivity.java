package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    TextToSpeech speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText ed1 =  findViewById(R.id.textView2);
        Button b1 =  findViewById(R.id.button);
        speaker = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status)
                    {
                        speaker.setLanguage(Locale.US);
                    }
                }
        );
        b1.setOnClickListener(v -> {
            String SpeakText = ed1.getText().toString();
            speaker.speak(SpeakText, TextToSpeech.QUEUE_FLUSH, new Bundle(), null);
        });

    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
        speaker.stop();
        speaker.shutdown();
    }
}