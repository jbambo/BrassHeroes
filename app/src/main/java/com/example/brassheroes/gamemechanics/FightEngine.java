package com.example.brassheroes.gamemechanics;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.Boss;
import com.example.brassheroes.characters.Enemy;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;
import com.example.brassheroes.main.StoryActivity;

import java.util.ArrayList;

public class FightEngine {
    private GameEntity mPlayer;
    private ArrayList<Equipment> mInventory;
    private Enemy mEnemy;
    private Context mContext;
    private Persistence persistence;

    private final double WEAPON_DROP_CHANCE = 0.15;
    private final double ARMOR_DROP_CHANCE = 0.15;
    private final int LOW_BOUND_EXP = 20;
    private final int HIGH_BOUND_EXP = 60;
    private final int GAME_PROGRESS_AMOUNT = 15;


    public FightEngine(Context context) {
        //1. get context
        this.mContext = context;
        //2.create persistence object
        persistence = new Persistence(mContext);
        //3. get saved data
        this.mPlayer = persistence.getSavedGame();
        this.mInventory = persistence.getSavedInventory();
        //4. spawn the enemy
        spawnEnemy();
    }

    private void spawnEnemy() {
        if (mPlayer.getGameProgress() >= 100) {
            mPlayer.setGameProgress(0);
            this.mEnemy = new Boss();
        } else {
            this.mEnemy = new Enemy();
            while (mPlayer.getLevel() > mEnemy.getLevel()) {
                mEnemy.gainExp(mEnemy.getExpNeeded());
            }
        }

    }

    public void attackMove(boolean isPlayer) {
        //check who makes the attack move
        if (isPlayer) {
            receiveDamage(mEnemy, mPlayer);
            //check if enemy has 0 health
            if (mEnemy.getHealth() <= 0) {
                winResult();
            }
        }
        if (!isPlayer) {
            receiveDamage(mPlayer, mEnemy);
            if (mPlayer.getHealth() <= 0) {
                Toast.makeText(mContext, "you lost!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, StoryActivity.class);
                intent.putExtra("won", false);
                mContext.startActivity(intent);
            }
        }
    }

    //results of the fight
    public void winResult() {
        //gain experience
        mPlayer.gainExp(RNG.randomNumber(HIGH_BOUND_EXP, LOW_BOUND_EXP));
        //set health to full after the fight
        mPlayer.setHealth(mPlayer.getMaxHealth());
        //roll item drop
        rollItemDrop();
        //set story progress
        mPlayer.setGameProgress(mPlayer.getGameProgress() + GAME_PROGRESS_AMOUNT);
        //save the data
        persistence.saveData(mPlayer);
        persistence.saveData(mInventory);
        Toast.makeText(mContext, mContext.getString(R.string.winMessagePlayer), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, StoryActivity.class);
        intent.putExtra("won", true);
        mContext.startActivity(intent);
    }

    public void rollItemDrop() {
        if (RNG.randomNumber() <= WEAPON_DROP_CHANCE) {
            mInventory.add(RNG.randomWeapon(mPlayer.getLevel()));
        }
        if (RNG.randomNumber() <= ARMOR_DROP_CHANCE) {
            mInventory.add(RNG.randomArmor(mPlayer.getLevel()));
        }
    }

    public GameEntity getPlayer() {
        return mPlayer;
    }

    public Enemy getEnemy() {
        return mEnemy;
    }

    public void setEnemy(Enemy mEnemy) {
        this.mEnemy = mEnemy;
    }

    private int damageCalculation(int enemyDamage, int targetArmor, int targetHealth) {
        if ((enemyDamage - targetArmor <= 5)) {
            return (int) (targetHealth - (enemyDamage * 0.4));
        } else
            return (targetHealth - (enemyDamage - targetArmor));
    }

    public void receiveDamage(GameEntity target, GameEntity attacker) {
        //case both have sth equipped
        if (target.isArmorEquipped() && attacker.isWeaponEquipped()) {
            //calculate total values
            int totalDamage = attacker.getCurrentDamage() + attacker.getEquippedWeapon().getDamageStat();
            int totalArmor = target.getArmor() + target.getEquippedArmor().getArmorStat();
            int totalHealth = target.getHealth() + target.getEquippedArmor().getHealthStat();

            target.setHealth(damageCalculation(totalDamage, totalArmor, totalHealth));
        }
        //case only target
        if (target.isArmorEquipped()) {
            int totalArmor = target.getArmor() + target.getEquippedArmor().getArmorStat();
            int totalHealth = target.getHealth() + target.getEquippedArmor().getHealthStat();
            target.setHealth(damageCalculation(attacker.getCurrentDamage(), totalArmor, totalHealth));
        }
        //case only attacker
        if (attacker.isWeaponEquipped()) {
            int totalDamage = attacker.getCurrentDamage() + attacker.getEquippedWeapon().getDamageStat();
            target.setHealth(damageCalculation(totalDamage, target.getArmor(), target.getHealth()));
        }
        //case none
        if (!attacker.isWeaponEquipped()&&!target.isArmorEquipped()){
            target.setHealth(damageCalculation(attacker.getCurrentDamage(), target.getArmor(), target.getHealth()));
        }

    }
}
