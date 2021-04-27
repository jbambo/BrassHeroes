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
import java.util.Arrays;

public class FightEngine {
    private GameEntity mPlayer;
    private ArrayList<Equipment> mInventory;
    private Enemy mEnemy;
    private Context mContext;
    private Persistence persistence;
    Equipment[] drops;

    private final double WEAPON_DROP_CHANCE = 0.15;
    private final double ARMOR_DROP_CHANCE = 0.15;
    private final int LOW_BOUND_EXP = 20;
    private final int HIGH_BOUND_EXP = 60;
    private final int GAME_PROGRESS_AMOUNT = 15;
    private final int BOSS_LEVEL_ADVANTAGE = 2;
    private int bossLevel;


    public FightEngine(Context context) {
        //1. get context
        this.mContext = context;
        //2.create persistence object
        persistence = new Persistence(mContext);
        //3. get saved data
        this.mPlayer = persistence.getSavedGame();
        this.mInventory = persistence.getSavedInventory();
        this.bossLevel = mPlayer.getLevel() + BOSS_LEVEL_ADVANTAGE;
        //4. spawn the enemy
        spawnEnemy();

        //5. initialize the health to max values before fight
        mPlayer.setHealth(mPlayer.getTotalHealth());
        mEnemy.setHealth(mEnemy.getTotalHealth());
    }

    private void spawnEnemy() {
        if (mPlayer.getGameProgress() >= 100) {
            mPlayer.setGameProgress(0);
            this.mEnemy = new Boss(bossLevel);
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
        int expGained = RNG.randomNumber(HIGH_BOUND_EXP, LOW_BOUND_EXP);
        mPlayer.gainExp(expGained);
        //restore health to full after the fight
        mPlayer.setHealth(mPlayer.getTotalHealth());
        //roll item drop
        int drop = rollItemDrop();
        System.out.println("drops" + Arrays.toString(drops));
        //set story progress
        mPlayer.setGameProgress(mPlayer.getGameProgress() + GAME_PROGRESS_AMOUNT);
        //save the data
        persistence.saveData(mPlayer);
        persistence.saveData(mInventory);

        Toast.makeText(mContext, mContext.getString(R.string.win_message_player), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, StoryActivity.class);
        if (drop == 2) {
            intent.putExtra("drop", 2);
            intent.putExtra("drop1", drops[0]);
            intent.putExtra("drop2", drops[1]);
        } else if (drop == 1) {
            intent.putExtra("drop", 1);
            intent.putExtra("drop1", drops[0]);
        } else intent.putExtra("drop", 0);

        intent.putExtra("won", true);
        intent.putExtra("expGained", expGained);


        mContext.startActivity(intent);
    }

    public int rollItemDrop() {
        drops = new Equipment[2];
        boolean first = rollWeapon();
        boolean second = rollArmor();
        if (first && second) {
            drops[0] = mInventory.get(mInventory.size() - 1);
            drops[1] = mInventory.get(mInventory.size() - 2);
            return 2;
        } else if (first || second) {
            drops[0] = mInventory.get(mInventory.size() - 1);
            return 1;
        } else return 0;
    }

    private boolean rollWeapon() {
        if (RNG.randomNumber() <= WEAPON_DROP_CHANCE) {
            Equipment temp = RNG.randomWeapon(mPlayer.getLevel());
            mInventory.add(temp);
            return true;
        }
        return false;
    }

    private boolean rollArmor() {
        if (RNG.randomNumber() <= ARMOR_DROP_CHANCE) {
            mInventory.add(RNG.randomArmor(mPlayer.getLevel()));
            Equipment temp = RNG.randomArmor(mPlayer.getLevel());
            mInventory.add(temp);
            return true;
        }
        return false;
    }

    public GameEntity getPlayer() {
        return mPlayer;
    }

    public Enemy getEnemy() {
        return mEnemy;
    }

    //calculate the damage taken
    private int damageCalculation(int enemyDamage, int targetArmor) {
        //if damage - armor would be  lower or equal  10, damage will be 70% effective
        // eg. damage: 100, armor 90, health 100
        //100-90<= 10 so --> 100*0.7=70 ---> 70-90=20 >10 so  damage dealt will be 70 instead of 100
        if ((enemyDamage - targetArmor <= 10)) {
            int damageResult = (int) (enemyDamage * 0.7);
            return damageResult;
        } else {
            //if damage-armor> 10
            int damageResult = (enemyDamage - targetArmor);
            return damageResult;
        }

    }

    public void receiveDamage(GameEntity target, GameEntity attacker) {
        //case attacker has weapon target has armor equipped
        if (target.isArmorEquipped() && attacker.isWeaponEquipped()) {
            //calculate total values
            int totalDamage = attacker.getCurrentDamage() + attacker.getEquippedWeapon().getDamageStat();
            int totalArmor = target.getTotalArmor();
            int totalHealth = target.getHealth();
            System.out.println("damage target has armor and attacker has weapon: " + damageCalculation(totalDamage, totalArmor));
            target.setHealth(totalHealth - damageCalculation(totalDamage, totalArmor));
        } else
            //case attacker has weapon
            if (attacker.isWeaponEquipped()) {
                int totalDamage = attacker.getCurrentDamage() + attacker.getEquippedWeapon().getDamageStat();
                int totalArmor = target.getTotalArmor();
                int totalHealth = target.getHealth();
                target.setHealth(totalHealth - damageCalculation(totalDamage, totalArmor));
                System.out.println("damage attacker weapon equipped: " + damageCalculation(totalDamage, totalArmor));
            } else {
                //other cases
                int totalDamage = attacker.getCurrentDamage();
                int totalArmor = target.getTotalArmor();
                int totalHealth = target.getHealth();
                target.setHealth(totalHealth - damageCalculation(totalDamage, totalArmor));
                System.out.println("damage none equipped: " + damageCalculation(totalDamage, totalArmor));
            }
    }
}
