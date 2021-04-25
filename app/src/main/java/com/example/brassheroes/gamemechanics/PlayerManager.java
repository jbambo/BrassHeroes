package com.example.brassheroes.gamemechanics;

import android.content.Context;

import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;

public class PlayerManager {
    private GameEntity mPlayer;
    private Persistence persistence;

    public PlayerManager(Context context) {
        persistence = new Persistence(context);
        this.mPlayer = persistence.getSavedGame();
    }

    public void equip(Equipment equipment) {
        String s = equipment.getType();
        //case armor was clicked
        if (s.equals("armor")) {
            if (mPlayer.isArmorEquipped()) {
                mPlayer.setEquippedArmor(null);
            }
            mPlayer.setEquippedArmor(equipment);
            persistence.saveData(mPlayer);
        }
        //case weapon was clicked
        if (s.equals("weapon")) {
            if (mPlayer.isWeaponEquipped()) {
                mPlayer.setEquippedWeapon(null);
            }
            mPlayer.setEquippedWeapon(equipment);
            persistence.saveData(mPlayer);

        }

    }

}
