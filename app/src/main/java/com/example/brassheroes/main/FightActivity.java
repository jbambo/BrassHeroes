package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.Persistence;
import com.example.brassheroes.R;
import com.example.brassheroes.characters.Enemy;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.gamemechanics.RNG;

import java.io.File;

public class FightActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAttack, btnFlee;

    Enemy enemy;

    GameEntity player;

    TextView playerMessage, enemyName, enemyClass;

    ProgressBar enemyHealth, playerHealth;

    ImageView enemyPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initPlayers();
        initControls();
    }

    private void attackMove() {
        //System.out.println(enemy.getLevel()+"- lv, damage: "+enemy.getCurrentDamage());

        System.out.println("enemy hp before: " + enemy.getHealth());
        enemy.receiveDamage(player.getCurrentDamage(),player.getDamageType());
        enemyHealth.setProgress(enemy.getHealth(), true);
        System.out.println("enemy hp after: " + enemy.getHealth());

        //check win condition
        if (enemy.getHealth() <= 0) {

            player.gainExp(60);
            player.setHealth(player.getMaxHealth());

            File file = new File(getFilesDir(), "Saved-" + player.getName());

            Persistence.saveData(player, file);

            Toast.makeText(this, "you won!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("won", true);
            startActivity(intent);
        }

        System.out.println("player hp before: " + player.getHealth());

        player.receiveDamage(enemy.getCurrentDamage(), enemy.getDamageType());
        playerHealth.setProgress(player.getHealth(), true);

        System.out.println("player hp after: " + player.getHealth());


        if (player.getHealth() <= 0) {
            Toast.makeText(this, "you lost!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("won", false);
            startActivity(intent);
        }


    }

    private void initPlayers() {
        enemy = new Enemy();

        File gamesDir = new File(getFilesDir(), "savedGames");

        File[] files = gamesDir.listFiles();

        File file = new File(gamesDir, files[0].getName());
        player = Persistence.getData(player, file);

        while (player.getLevel() > enemy.getLevel()) {
            enemy.gainExp(enemy.getExpNeeded());
        }

    }

    private void initControls() {

        btnAttack = findViewById(R.id.attackBtn);
        btnFlee = findViewById(R.id.runBtn);
        btnFlee.setOnClickListener(this);
        btnAttack.setOnClickListener(this);

        enemyHealth = findViewById(R.id.enemyHealth);
        enemyHealth.setMax(enemy.getMaxHealth());
        enemyHealth.setProgress(enemy.getMaxHealth());

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setMax(player.getMaxHealth());
        playerHealth.setProgress(player.getMaxHealth());

        playerMessage = findViewById(R.id.playerMessage);
        playerMessage.setText("Make your move, " + player.getName() + " !");

        enemyPortrait = findViewById(R.id.enemyPortrait);
        enemyPortrait.setImageResource(RNG.randomEnemyPortrait());

        enemyName = findViewById(R.id.enemyName);
        enemyName.setText(enemy.getName());

        enemyClass = findViewById(R.id.enemyClass);
        enemyClass.setText("the fallen "+enemy.getProfession());
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