package com.example.brassheroes.characters;

public class GameEntity {

    private String name;
    private String profession;
    private String damageType;
    private int baseDamage = 15;
    private int damageInc = 10;
    private int currentDamage;
    private int armor = 8;
    private int armorInc = 5;
    private int health=100;
    private int maxHealth = 100;
    private int healthInc = 20;
    private int level = 1;
    private int exp = 0;
    private int expNeeded = 100;


    public GameEntity(String name, String profession, String damageType, int baseDamage, int damageInc,int currentDamage, int armor, int armorInc, int health, int maxHealth, int healthInc, int level, int exp, int expNeeded) {
        this.name = name;
        this.profession = profession;
        this.damageType = damageType;
        this.baseDamage = baseDamage;
        this.damageInc = damageInc;
        this.currentDamage = currentDamage;
        this.armor = armor;
        this.armorInc = armorInc;
        this.health = health;
        this.maxHealth = maxHealth;
        this.healthInc = healthInc;
        this.level = level;
        this.exp = exp;
        this.expNeeded = expNeeded;
    }


    public GameEntity() {
        this.currentDamage = getBaseDamage();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }


    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealthInc() {
        return healthInc;
    }

    public void setHealthInc(int healthInc) {
        this.healthInc = healthInc;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public void setCurrentDamage(int currentDamage) {
        this.currentDamage = currentDamage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    // stats on level up
    public void levelUp() {
        if (exp == expNeeded) {
            //set damage
            setCurrentDamage(getBaseDamage() + getDamageInc());
            // set exp needed for next level
            setExpNeeded(getExpNeeded() + 20);
            //increase level by 1
            setLevel(getLevel() + 1);
            //set health
            setMaxHealth(getMaxHealth() + getHealthInc());
            //change health/level increase
            setHealthInc(getHealthInc() + 10);
            //increase armor
            setArmor(getArmor() + getArmorInc());
            setExp(0);
        }
    }

    public int getDamageInc() {
        return damageInc;
    }

    public void setDamageInc(int damageInc) {
        this.damageInc = damageInc;
    }

    public int getArmorInc() {
        return armorInc;
    }

    public void setArmorInc(int armorInc) {
        this.armorInc = armorInc;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }


    @Override
    public String toString() {
        return "Name:\t" + getName() +
                "\nProfession:\t" + getProfession() +
                "\nDamage Type:\t" + getDamageType() +
                "\nDamage:\t" + getCurrentDamage() +
                "\nArmor:\t" + getArmor() +
                "\nHealth:\t" + getMaxHealth() +
                "\nLevel:\t" + getLevel();
    }
}
