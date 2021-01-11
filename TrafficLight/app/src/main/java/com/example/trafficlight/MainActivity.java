package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FrameLayout light1, light2, light3;
    private Button buttonStart;
    private int counter = 0;
    private boolean tumbler = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        light1 = findViewById(R.id.Light1);
        light2 = findViewById(R.id.Light2);
        light3 = findViewById(R.id.Light3);
        buttonStart = findViewById(R.id.button);
    }

    public void buttonClick(View view) {
        if(!tumbler) {
            tumbler = true;
            buttonStart.setText("Stop");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (tumbler) {
                        counter++;
                        switch (counter)
                        {
                            case 1:
                                light1.setBackgroundColor(getResources().getColor(R.color.red));
                                light2.setBackgroundColor(getResources().getColor(R.color.grey));
                                light3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 2:
                                light2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                light1.setBackgroundColor(getResources().getColor(R.color.grey));
                                light3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 3:
                                light3.setBackgroundColor(getResources().getColor(R.color.green));
                                light1.setBackgroundColor(getResources().getColor(R.color.grey));
                                light2.setBackgroundColor(getResources().getColor(R.color.grey));
                                counter = 0;
                                break;
                        }
                        try {

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            tumbler = false;
            buttonStart.setText("Start");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tumbler = false;
    }
}