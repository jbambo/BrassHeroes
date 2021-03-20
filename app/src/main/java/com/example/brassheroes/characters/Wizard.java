package com.example.brassheroes.characters;

public class Wizard extends GameEntity {

    public Wizard() {
        setProfession("wizard");
        setBaseDamage(20);
        setDamageInc(20);
        setHealthInc(15);
        setDamageType("magic");
    }
}
