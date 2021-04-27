package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.gamemechanics.CustomAdapter;
import com.example.brassheroes.gamemechanics.Persistence;
import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    private TextView playerInfo;

    private ListView listView;

    private GameEntity player;

    private ArrayList<Equipment> inventory;

    private Button btnGoBack;

    private ImageView playerPortrait;

    private Persistence persistence;


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
        persistence = new Persistence(this);

        player = persistence.getSavedGame();
        inventory = persistence.getSavedInventory();

        playerPortrait = findViewById(R.id.playerPortrait);

        playerInfo = findViewById(R.id.playerStats);
        btnGoBack = findViewById(R.id.btnGoBack);

        //list view of the inventory
        listView = findViewById(R.id.playerItemList);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void showPlayerInfo() {
        //create an adapter for the list view
        //single row is defined in: list_layout.xml
        //R.id.inventoryListDescription is the text view that will be populated with data
        //ArrayAdapter<Equipment> adapter = new ArrayAdapter<>
        //(this, R.layout.list_layout, R.id.inventoryListDescription, inventory);
        CustomAdapter adapter = new CustomAdapter(inventory,this);
        listView.setAdapter(adapter);
        playerPortrait.setImageResource(player.getPortrait());
        playerInfo.setText(player.toString());
    }
    private void goBack() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}





