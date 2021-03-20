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

public class PlayerActivity extends AppCompatActivity {
    TextView playerInfo;

    GameEntity player;

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

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void showPlayerInfo() {
        File[] files = getFilesDir().listFiles();
        File file = new File(getFilesDir(), files[0].getName());
        player = Persistence.getData(player, file);
        playerInfo.setText(player.toString());
    }

    private void goBack() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
