package com.example.brassheroes.characters;

public class Wizard extends GameEntity {

    public Wizard() {
        setProfession("wizard");
        setArmor(5);
        setBaseDamage(20);
        setDamageInc(20);
        setHealthInc(15);
        setDamageType("magic");
    }
}
