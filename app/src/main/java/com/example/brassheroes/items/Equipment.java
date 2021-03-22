package com.example.brassheroes.items;

public class Equipment {
    private String name = "sword of testing";
    private int damageStat = 42;
    private int healthStat = 0;
    private int armorStat = 0;
    private int levelRequirement = 1;
    //later rarity and unique modifiers

    public Equipment(String name, int damageStat, int healthStat, int armorStat, int levelRequirement) {
        this.name = name;
        this.damageStat = damageStat;
        this.healthStat = healthStat;
        this.armorStat = armorStat;
        this.levelRequirement = levelRequirement;
    }

    public Equipment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamageStat() {
        return damageStat;
    }

    public void setDamageStat(int damageStat) {
        this.damageStat = damageStat;
    }

    public int getHealthStat() {
        return healthStat;
    }

    public void setHealthStat(int healthStat) {
        this.healthStat = healthStat;
    }

    public int getArmorStat() {
        return armorStat;
    }

    public void setArmorStat(int armorStat) {
        this.armorStat = armorStat;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    @Override
    public String toString() {
        return "Weapon name: " + name +
                ", Damage: " + damageStat +
                ", Health: " + healthStat +
                ",\nArmor: " + armorStat +
                ", Required level: " + levelRequirement;
    }
}
