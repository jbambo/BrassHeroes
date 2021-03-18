package com.example.brassheroes;

public class Entity {
    private String name;
    private Profession entityProfession;
    private int health=100;
    private int level=1;


    public Entity(String name, Profession profession, int health, int level) {
        this.name = name;
        this.entityProfession = profession;
        this.health = health;
        this.level = level;
    }

    public Entity(String name, Profession profession) {
        this.entityProfession = profession;
        this.name = name;
    }

    public Profession getProfession() {
        return entityProfession;
    }

    public void setProfession(Profession profession) {
        this.entityProfession = profession;
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

    public void levelUp(){
        setLevel(getLevel()+1);
    }
}
