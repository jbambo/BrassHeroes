package com.example.brassheroes.items;

public class Equipment {
    private String name ;
    private String type;
    private int damageStat;
    private int healthStat;
    private int armorStat;
    private int levelRequirement;
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
        return "" + name +
                "\nDamage: " + damageStat +
                " Health: " + healthStat +
                "\nArmor: " + armorStat +
                " Req. level: " + levelRequirement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
