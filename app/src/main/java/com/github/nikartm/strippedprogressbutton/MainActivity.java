package com.github.nikartm.strippedprogressbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.nikartm.support.StripedProgressButton;


public class MainActivity extends AppCompatActivity {

    private StripedProgressButton btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(v -> btnStart.start());
        btnStop.setOnClickListener(v -> btnStart.stop());
    }
}
