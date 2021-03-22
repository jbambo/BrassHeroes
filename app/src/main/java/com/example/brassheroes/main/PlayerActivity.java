package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.Persistence;
import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerActivity extends AppCompatActivity {
    TextView playerInfo;

    ListView listView;

    GameEntity player;

    ArrayList<Equipment> inventory;

    private Button btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
        showPlayerInfo();
    }

    private void initControls() {
        playerInfo = findViewById(R.id.playerStats);
        btnGoBack = findViewById(R.id.btnGoBack);

        listView = findViewById(R.id.playerItemList);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void showPlayerInfo() {
        File gamesDir = new File(getFilesDir(), "savedGames");
        File inventoryDir = new File(getFilesDir(), "savedInventory");

        File[] files = gamesDir.listFiles();
        File file = new File(gamesDir, files[0].getName());
        player = Persistence.getData(player, file);
        playerInfo.setText(player.toString());

        File[] files2 = inventoryDir.listFiles();
        File file2 = new File(inventoryDir, files2[0].getName());
        inventory = Persistence.getData(inventory, file2);
        System.out.println("file succesful: " + inventory.toString());


        ArrayAdapter<Equipment> adapter = new ArrayAdapter<>(this, R.layout.list_layout, R.id.inventoryListDescription, inventory);
        listView.setAdapter(adapter);
    }


    private void goBack() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


}





