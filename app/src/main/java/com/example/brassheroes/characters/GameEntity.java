package com.example.brassheroes.characters;

import com.example.brassheroes.items.Equipment;

import java.util.ArrayList;

public class GameEntity {

    private String name;
    private String profession;
    private String damageType;
    private int baseDamage = 15;
    private int damageInc = 10;
    private int currentDamage;
    private int armor = 8;
    private int armorInc = 5;
    private int health = 100;
    private int maxHealth = 100;
    private int healthInc = 20;
    private int level = 1;
    private int exp = 0;
    private int expNeeded = 100;
    private ArrayList<Equipment> playersEquipment;

    public GameEntity(
            String name,
            String profession,
            String damageType,
            int baseDamage,
            int damageInc,
            int currentDamage,
            int armor,
            int armorInc,
            int health,
            int maxHealth,
            int healthInc,
            int level,
            int exp,
            int expNeeded) {

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

    public void levelUp() {
        //set damage
        setCurrentDamage(getCurrentDamage() + getDamageInc());
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


    }

    //gain experience
    public void gainExp(int expAmt) {
        if (getExpNeeded() <= getExp()) {
            levelUp();
            setExp(0);
        } else {
            setExp(getExp() + expAmt);
            //check now if level up possible
            if (getExpNeeded() <= getExp()) {
                levelUp();
                setExp(0);
            }
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

    private int damageCalculation(int enemyDamage) {
        if ((enemyDamage - getArmor() <= 5)) {
            return (int) (getHealth() - (enemyDamage * 0.4));
        } else
            return (getHealth() - (enemyDamage - getArmor()));
    }

    private int damageCalculationSEffective(int enemyDamage) {
        double superEffective = 1.5;
        if ((enemyDamage - getArmor() <= 5)) {
            return (int) ((getHealth() - (enemyDamage * 0.4)) * superEffective);
        } else
            return (int) ((getHealth() - (enemyDamage - getArmor())) * superEffective);
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public void receiveDamage(int enemyDamage, String enemyType) {

        setHealth(damageCalculation(enemyDamage));

//        //physical recieves damage
//        switch (enemyType) {
//            case "physical":
//                if (getDamageType().equals("magic")) {
//                    setHealth(damageCalculationSEffective(enemyDamage));
//                } else setHealth(damageCalculation(enemyDamage));
//
//                break;
//
//            //mental recieves damage
//            case "mental":
//                if (enemyType.equals("physical")) {
//                    setHealth(damageCalculationSEffective(enemyDamage));
//
//                } else setHealth(damageCalculation(enemyDamage));
//
//                break;
//
//            //magic recieves damage
//            case "magic":
//                if (enemyType.equals("mental")) {
//                    setHealth(damageCalculationSEffective(enemyDamage));
//
//                } else setHealth(damageCalculation(enemyDamage));
//
//                break;
//
//            default:
//                setHealth(damageCalculation(enemyDamage));
//                break;
//        }

    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nProfession: " + getProfession() +
                "\nDamage Type: " + getDamageType() +
                "\nDamage: " + getCurrentDamage() +
                "\nArmor: " + getArmor() +
                "\nHealth: " + getMaxHealth() +
                "\nLevel: " + getLevel();
    }

    public ArrayList<Equipment> getPlayersEquipment() {
        return playersEquipment;
    }

    public void setPlayersEquipment(ArrayList<Equipment> playersEquipment) {
        this.playersEquipment = playersEquipment;
    }
}
