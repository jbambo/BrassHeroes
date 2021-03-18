package com.example.brassheroes.characters;

public class Paladin extends GameEntity {


    public Paladin(String profession) {
        super(profession);
        setMaxHealth(125);
        setHealthInc(25);
        setArmor(15);
        setArmorInc(9);
        setDamageType("mental");

    }
}
