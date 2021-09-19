package com.example.isabelletournamentpocketapp;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    private int maximumNumberOfEchoes = 2;
    private String[] data;
    //This array contains every field data from the source spreadsheet. Fields can be added and removed at will, but the first and second one must be name and number, in this order.
    private String[] fields;

    public Character(String[] arrayOfRawFieldsAndRawCharacters, String characterId) {

        this.fields = SpreadsheetParser.parseRawFields(arrayOfRawFieldsAndRawCharacters[0]);

        ArrayList<String[]> arrayOfArrayOfCharacterData = new ArrayList<>();
        String[] temp;

        for(int i = 1; i < arrayOfRawFieldsAndRawCharacters.length; i++){

            temp = SpreadsheetParser.parseRawCharacter(arrayOfRawFieldsAndRawCharacters[i]);

            if(temp[1].equals(characterId)){
                System.out.println("temp[1] : " + temp[1] + " / characterId : " + characterId);
                arrayOfArrayOfCharacterData.add(SpreadsheetParser.parseRawCharacter(arrayOfRawFieldsAndRawCharacters[i]));
            }
        }

        if(arrayOfArrayOfCharacterData.size()==1){
            //Id given correspond to a character that has no echo fighter
            this.data = arrayOfArrayOfCharacterData.get(0);
        } else{
            //Id given correspond to a character that has one echo fighter
            this.data = resolveEchoFighters(arrayOfArrayOfCharacterData);
        }

    }

    private String[] resolveEchoFighters(ArrayList<String[]> arrayOfArrayOfCharacterData){
        if(arrayOfArrayOfCharacterData.size() == 2){
            for (int i = 0; i < arrayOfArrayOfCharacterData.get(0).length; i++) {
                if(!arrayOfArrayOfCharacterData.get(0)[i].equals(arrayOfArrayOfCharacterData.get(1)[i])){
                    arrayOfArrayOfCharacterData.get(0)[i] = arrayOfArrayOfCharacterData.get(0)[i] + "||" + arrayOfArrayOfCharacterData.get(1)[i];
                }
            }
        }
        return arrayOfArrayOfCharacterData.get(0);
    }

    public String[] getData() {
        return data;
    }
    public String[] getFields() {
        return fields;
    }
    public String getName(){
        return getData()[0];
    }
    public String getFighterNumber(){
        return getData()[1];
    }
}
