package com.example.brassheroes.characters;

public class Paladin extends Entity {

    String damageType = "mental";

    public Paladin(String profession) {
        super(profession);
        setMaxHealth(125);
        setHealthInc(25);
        setArmor(15);
        setArmorInc(9);
    }

    public String getDamageType() {
        return damageType;
    }


}
