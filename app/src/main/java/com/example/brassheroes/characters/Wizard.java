package com.example.brassheroes.characters;

public class Wizard extends Entity {

    String damageType = "magic";

    public Wizard(String profession) {
        super(profession);
        setArmor(5);
        setBaseDamage(20);
        setDamageInc(20);
        setHealthInc(15);
    }

    public String getDamageType() {
        return damageType;
    }
}
