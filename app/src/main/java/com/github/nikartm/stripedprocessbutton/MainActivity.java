package com.github.nikartm.stripedprocessbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.nikartm.support.StripedProcessButton;


public class MainActivity extends AppCompatActivity {

    private StripedProcessButton stripedButton;
    private Button btnStop;
    private StripedProcessButton btn_2;
    private StripedProcessButton btn_3;
    private StripedProcessButton btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stripedButton = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);

        stripedButton.setOnClickListener(v -> stripedButton.start());
        btnStop.setOnClickListener(v -> stripedButton.stop());

//        stripedButton.setStripeWidth(13).setStripeGradient(false);
//        stripedButton.start();

        btn_2.start();
        btn_3.start();
        btn_4.start();
    }
}
