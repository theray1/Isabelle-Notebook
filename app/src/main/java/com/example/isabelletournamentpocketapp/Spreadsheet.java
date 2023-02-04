package com.example.isabelletournamentpocketapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Spreadsheet {
    private String rawSpreadSheet;//The whole data spreadsheet, in one string.

    public Spreadsheet(MainActivity ma){
        String rawFieldsLine;
        String returnString;

        String scriptURL = "https://script.google.com/macros/s/AKfycbzw6tewTJDZkwlpNU6NZVudnu4UjZRIPM6NRANg4A3w5LPtexVqQFv4_WnfYBgiXKJePw/exec";

        try {
            URL url = new URL(scriptURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            rawFieldsLine = reader.readLine();
            returnString = String.valueOf(rawFieldsLine);

            reader.close();

            setRawSpreadSheet(returnString);

            ma.setArrayOfRawCharacterData(SpreadsheetParser.parseRawSpreadsheet(this.getRawSpreadSheet()));




        } catch (Exception e) {
            e.printStackTrace();
            setRawSpreadSheet("");
        }
    }

    public String getRawSpreadSheet() {
        return rawSpreadSheet;
    }

    public void setRawSpreadSheet(String rawSpreadSheet) {
        this.rawSpreadSheet = rawSpreadSheet;
    }
}
