package com.intendentapp.model;

//klasa służąca do pobierania wartości z formularza tworzenia jadłospisu
public class Menu {
    private String amount;  //liczba dni w jadłospisie
    private String date;    //data posiłku
    private String meal;       //posiłek
    private String filename; //nazwa pliku z jadłospisem

    public Menu(String amount, String date, String meal, String filename) {
        this.amount = amount;
        this.date = date;
        this.meal = meal;
        this.filename = filename;
    }

    public Menu(){}

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
