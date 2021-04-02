package com.example.brassheroes.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.gamemechanics.Persistence;
import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;

import java.io.File;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    GameEntity player;

    private Button btnInventory, btnFight;

    private TextView playerName;

    private ProgressBar playerExpBar;

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
        //file object with saved games dir
        File gamesDir = new File(getFilesDir(), "savedGames");
        //list of files on saved games dir
        File[] files = gamesDir.listFiles();
        //select the file
        File file = new File(gamesDir, files[0].getName());
        player = Persistence.getData(player, file);

//        File inventoryDir = new File(getFilesDir(), "savedInventory");
//        File[] files1 = inventoryDir.listFiles();
//
//        File file1 = new File(inventoryDir, files1[0].getName());

    }

    private void initControls() {

        playerExpBar = findViewById(R.id.mapPlayerExp);

        playerExpBar.setMax(player.getExpNeeded());
        playerExpBar.setProgress(player.getExp());

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

    @SuppressLint("NonConstantResourceId")
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