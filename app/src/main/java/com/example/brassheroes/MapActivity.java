package com.example.brassheroes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private int playerLevel, mapProgress, playerClass;

    private Button btnInventory, btnFight;

    private TextView playerName;

    public MapActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        initControls();
    }

    private void initControls() {
        username= getIntent().getStringExtra("username");
        playerClass= getIntent().getIntExtra("playerClass",0);

        playerName = findViewById(R.id.playerNameMap);
        playerName.setText(username);

        btnFight = findViewById(R.id.btnFight);
        btnInventory = findViewById(R.id.btnInventory);

        btnFight.setOnClickListener(this);
        btnInventory.setOnClickListener(this);
    }

    public void openInventory() {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    public void startFight() {
        Intent intent = new Intent(this, FightActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInventory:
                openInventory();
                break;
            case R.id.btnFight:
                startFight();
                break;
        }
    }
}