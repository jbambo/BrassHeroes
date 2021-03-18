package com.example.brassheroes.characters;

public class Knight extends GameEntity {

    public Knight(String profession) {
        super(profession);
        setDamageInc(14);
        setArmor(10);
        setArmorInc(7);
        setMaxHealth(115);
        setDamageType("physical");
    }
}
