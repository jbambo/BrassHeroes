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
import com.example.brassheroes.fightmechanics.RNG;

import java.io.File;

public class FightActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAttack, btnFlee;

    Enemy enemy;

    GameEntity player;

    TextView playerMessage, enemyName;

    ProgressBar enemyHealth, playerHealth;

    ImageView enemyPortrait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
    }

    private void stateWatcher() {
        if (enemy.getHealth() <= 0) {

            player.gainExp(100);

            File file = new File(getFilesDir(), "Saved-" + player.getName());
            Persistence.saveData(player, file);

            Toast.makeText(this, "you won!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("won", true);
            startActivity(intent);
        }
        if (player.getHealth() <= 0) {
            Toast.makeText(this, "you lost!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("won", false);
            startActivity(intent);
        }
    }

    private void attackMove() {

        enemy.recieveDamage(player.getCurrentDamage());
        enemyHealth.setProgress(enemy.getHealth(), true);

        stateWatcher();

        player.recieveDamage(enemy.getCurrentDamage());
        playerHealth.setProgress(player.getHealth(), true);

        stateWatcher();

    }


    private void initControls() {
        enemy = new Enemy();

        File[] files = getFilesDir().listFiles();

        File file = new File(getFilesDir(), files[0].getName());
        player = Persistence.getData(player, file);

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