package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;

import java.io.File;
import java.lang.reflect.Method;

import static android.view.View.inflate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button contGameBtn, newGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the immersive mode(complete fullscreen)
        setContentView(R.layout.activity_main);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
//      Intent svc=new Intent(this, BackgroundSoundService.class);
//      startService(svc);
        initControls();
    }

    public void initControls() {
        contGameBtn = findViewById(R.id.continueBtn);
        newGameBtn = findViewById(R.id.newGameBtn);

        contGameBtn.setOnClickListener(this);
        newGameBtn.setOnClickListener(this);
    }

    public void startGame() {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void continueGame() {
        if (this.getFilesDir().list().length != 0) {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        } else Toast.makeText(MainActivity.this, "no saved game", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGameBtn:
                if (this.getFilesDir().list().length != 0) {
                    for (File tempFile : getFilesDir().listFiles()) {
                        tempFile.delete();
                    }
                }
                startGame();
                break;
            case R.id.continueBtn:
                continueGame();
                break;
        }
    }
}
