package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.gamemechanics.BackgroundSoundService;
import com.example.brassheroes.R;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button contGameBtn, newGameBtn;

    File gamesDir, inventoryDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the immersive mode(complete fullscreen)
        setContentView(R.layout.activity_main);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        //start the app background music
        Intent svc = new Intent(this, BackgroundSoundService.class);
        startService(svc);
        initControls();
    }

    public void initControls() {
        gamesDir = new File(getFilesDir(), "savedGames");
        inventoryDir = new File(getFilesDir(), "savedInventory");

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
        if (gamesDir.isDirectory()&&inventoryDir.isDirectory()){
            if (gamesDir.listFiles().length != 0) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            } else Toast.makeText(MainActivity.this, "no saved game", Toast.LENGTH_LONG).show();
        }else Toast.makeText(MainActivity.this, "never started a game", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGameBtn:
                if (gamesDir.isDirectory()&&inventoryDir.isDirectory()) {
                    if (gamesDir.listFiles().length != 0) {
                        for (File tempFile : gamesDir.listFiles()) {
                            tempFile.delete();
                        }
                        for (File tempFile : inventoryDir.listFiles()) {
                            tempFile.delete();
                        }
                    }
                } else {
                    System.out.println("no dir found, creating");
                    gamesDir.mkdir();
                    inventoryDir.mkdir();
                }
                startGame();
                break;

            case R.id.continueBtn:
                continueGame();
                break;
        }
    }
}

