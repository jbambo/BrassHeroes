package com.example.brassheroes.characters;

public class Knight extends Entity {

    String damageType = "physical";

    public Knight(String profession) {
        super(profession);
        setDamageInc(14);
        setArmor(10);
        setArmorInc(7);
        setMaxHealth(115);
    }

    public String getDamageType() {
        return damageType;
    }
}
