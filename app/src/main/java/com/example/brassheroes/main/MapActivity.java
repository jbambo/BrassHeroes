package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.Persistence;
import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;

import java.io.File;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private int mapProgress;

    GameEntity player;

    String username;

    private Button btnInventory, btnFight;

    private TextView playerName;

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

        //get the first files name
        File[] files = getFilesDir().listFiles();
        //select this file
        File file = new File(getFilesDir(), files[0].getName());
        try {
            player = Persistence.getData(player, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initControls() {

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
            case R.id.btnInventory:
                openInventory();
                break;
            case R.id.btnFight:
                startFight();
                break;
        }
    }
}