package com.github.nikartm.stripedprocessbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.nikartm.support.StripedProcessButton;


public class MainActivity extends AppCompatActivity {

    private StripedProcessButton stripedButton;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stripedButton = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);

        stripedButton.setOnClickListener(v -> stripedButton.start());
        btnStop.setOnClickListener(v -> stripedButton.stop());
    }
}
