package com.example.isabelletournamentpocketapp;

public class SpreadsheetParser {
    static String[] parseRawSpreadsheet(String rawSpreadSheet){

        return rawSpreadSheet.substring(1).split("!");
    }

    static String[] parseRawCharacter(String rawCharacterData){


        return rawCharacterData.split(",");
    }

    static String[] parseRawFields(String rawFields){

        return rawFields.split(",");
    }
}
