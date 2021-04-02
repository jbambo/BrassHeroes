package com.example.brassheroes.items;

public class Weapon extends Equipment {
    public Weapon(String name, int damageStat, int healthStat, int armorStat, int levelRequirement) {
        super(name, damageStat, healthStat, armorStat, levelRequirement);
        super.setType("weapon");
    }

}

