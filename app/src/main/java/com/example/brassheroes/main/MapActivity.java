package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.gamemechanics.Persistence;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private GameEntity player;

    private Button btnInventory, btnFight;

    private TextView playerName;

    private ProgressBar playerExpBar, playerProgressBar;

    private Persistence persistence;

    private final int INVENTORY_BUTTON_ID = R.id.btnInventory;
    private final int FIGHT_BUTTON_ID = R.id.btnFight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        getData();
        initControls();

    }

    //function to extract saved data
    private void getData() {
        persistence = new Persistence(this);
        player = persistence.getSavedGame();
    }

    private void initControls() {

        playerExpBar = findViewById(R.id.mapPlayerExp);
        playerProgressBar = findViewById(R.id.mapPlayerProgress);

        playerExpBar.setMax(player.getExpNeeded());
        playerExpBar.setProgress(player.getExp());

        playerProgressBar.setProgress(player.getGameProgress());

        playerName = findViewById(R.id.playerNameMap);
        playerName.setText(player.getName());

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
            case INVENTORY_BUTTON_ID:
                openInventory();
                break;
            case FIGHT_BUTTON_ID:
                startFight();
                break;
        }
    }
}