package com.example.brassheroes.items;

public class Armor extends Equipment {

    public Armor(String name, int damageStat, int healthStat, int armorStat, int levelRequirement) {
        super(name, damageStat, healthStat, armorStat, levelRequirement);
        super.setType("armor");
    }
}
