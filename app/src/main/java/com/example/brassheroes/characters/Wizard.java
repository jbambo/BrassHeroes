package com.example.brassheroes.characters;

public class Wizard extends GameEntity {

    public Wizard(String profession) {
        super(profession);
        setArmor(5);
        setBaseDamage(20);
        setDamageInc(20);
        setHealthInc(15);
        setDamageType("magic");
    }
}
