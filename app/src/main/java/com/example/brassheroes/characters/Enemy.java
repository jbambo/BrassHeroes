package com.example.brassheroes.characters;

import com.example.brassheroes.fightmechanics.RNG;

public class Enemy extends GameEntity {
    public Enemy() {
        setName(RNG.randomName());
        setArmor(4);
        setBaseDamage(10);
    }
}
