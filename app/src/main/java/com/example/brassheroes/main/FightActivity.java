package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.Enemy;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.gamemechanics.FightEngine;
import com.example.brassheroes.gamemechanics.Persistence;

public class FightActivity extends AppCompatActivity implements View.OnClickListener {

    final int ATTACK_BUTTON_ID = R.id.attackBtn;
    final int REST_BUTTON_ID = R.id.restBtn;
    final int RUN_BUTTON_ID = R.id.runBtn;

    Button btnAttack, btnFlee, btnRest;

    Enemy enemy;

    GameEntity player;

    TextView playerMessage, enemyName, enemyClass;

    ProgressBar enemyHealth, playerHealth;

    ImageView enemyPortrait;

    FightEngine fightEngine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initPlayer();
        initControls();
    }

    private void attackMove() {
        //players turn
        fightEngine.attackMove(true);
        enemy = fightEngine.getEnemy();
        enemyHealth.setProgress(enemy.getHealth(), true);
        //enemys turn
        fightEngine.attackMove(false);
        player = fightEngine.getPlayer();
        playerHealth.setProgress(player.getHealth(), true);
    }

    private void rest() {
        fightEngine.attackMove(false);
        player = fightEngine.getPlayer();
        playerHealth.setProgress(player.getHealth(),true);
    }

    private void initPlayer() {
        //create fight engine object
        fightEngine = new FightEngine(this);
        //init player
        player = fightEngine.getPlayer();

        //get the spawned enemy
        enemy = fightEngine.getEnemy();
    }

    //all the model fields
    private void initControls() {
        btnRest = findViewById(REST_BUTTON_ID);
        btnAttack = findViewById(ATTACK_BUTTON_ID);
        btnFlee = findViewById(RUN_BUTTON_ID);

        btnRest.setOnClickListener(this);
        btnFlee.setOnClickListener(this);
        btnAttack.setOnClickListener(this);

        enemyHealth = findViewById(R.id.enemyHealth);
        enemyHealth.setMax(enemy.getTotalHealth());
        enemyHealth.setProgress(enemy.getTotalHealth());

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setMax(player.getTotalHealth());
        playerHealth.setProgress(player.getTotalHealth());

        playerMessage = findViewById(R.id.playerMessage);
        playerMessage.setText(getString(R.string.fight_player_move, player.getName()));

        enemyPortrait = findViewById(R.id.enemyPortrait);
        enemyPortrait.setImageResource(enemy.getPortrait());

        enemyName = findViewById(R.id.enemyName);
        enemyName.setText(enemy.getName());

        enemyClass = findViewById(R.id.enemyClass);
        enemyClass.setText(enemy.printClass());
    }

    //leave the fight
    private void run() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case ATTACK_BUTTON_ID:
                attackMove();
                break;
            case RUN_BUTTON_ID:
                run();
                break;
            case REST_BUTTON_ID:
                rest();
                break;
        }
    }
}