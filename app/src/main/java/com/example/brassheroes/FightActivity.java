package com.example.brassheroes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FightActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAttack, btnFlee;

    int enemyHP = 100;

    ProgressBar enemyHealth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
    }

    private void stateWatcher() {
        if (enemyHealth.getProgress() == 0) {
            Intent intent = new Intent(this, StoryActivity.class);
            startActivity(intent);
        }
    }

    private void attackMove() {
        Toast.makeText(this, "Damage over 9000!", Toast.LENGTH_SHORT).show();
        enemyHP = enemyHP - 25;
        enemyHealth.setProgress(enemyHP);
        stateWatcher();
    }

    private void initControls() {
        btnAttack = findViewById(R.id.attackBtn);
        btnFlee = findViewById(R.id.runBtn);

        enemyHealth = findViewById(R.id.enemyHealth);

        btnFlee.setOnClickListener(this);
        btnAttack.setOnClickListener(this);
    }

    private void run() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attackBtn:
                attackMove();
                break;
            case R.id.runBtn:
                run();
                break;
        }
    }
}