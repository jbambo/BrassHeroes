package com.example.brassheroes;

import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Persistence {

    private static ArrayList<Equipment> equipment;

    public static void saveData(GameEntity player, File file) {

        Gson gson = new Gson();
        String saveJson = gson.toJson(player);
        try {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saveJson);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveData(ArrayList<Equipment> equipment, File file) {
        Gson gson = new Gson();
        String saveJson = gson.toJson(equipment);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saveJson);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Equipment> getData(ArrayList<Equipment> inventory, File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = bufferedReader.readLine();
            //close readers
            bufferedReader.close();
            fileReader.close();

            Gson gson = new Gson();

            Type collectionType = new TypeToken<ArrayList<Equipment>>() {
            }.getType();

            return gson.fromJson(data, collectionType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventory;
    }

    public static GameEntity getData(GameEntity player, File file) {
        //start file and buffered reader
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //data from file to string
            String data = bufferedReader.readLine();
            //close readers
            bufferedReader.close();
            fileReader.close();
            return gson.fromJson(data, GameEntity.class);
            //create json object with the data
            //JSONObject jsonObject = new JSONObject(data);
            //put the data into the game entity object again
//            return new GameEntity(
//                    jsonObject.get("name").toString(),
//                    jsonObject.get("profession").toString(),
//                    jsonObject.get("damageType").toString(),
//                    Integer.parseInt(jsonObject.get("baseDamage").toString()),
//                    Integer.parseInt(jsonObject.get("damageInc").toString()),
//                    Integer.parseInt(jsonObject.get("currentDamage").toString()),
//                    Integer.parseInt(jsonObject.get("armor").toString()),
//                    Integer.parseInt(jsonObject.get("armorInc").toString()),
//                    Integer.parseInt(jsonObject.get("health").toString()),
//                    Integer.parseInt(jsonObject.get("maxHealth").toString()),
//                    Integer.parseInt(jsonObject.get("healthInc").toString()),
//                    Integer.parseInt(jsonObject.get("level").toString()),
//                    Integer.parseInt(jsonObject.get("exp").toString()),
//                    Integer.parseInt(jsonObject.get("expNeeded").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

}
