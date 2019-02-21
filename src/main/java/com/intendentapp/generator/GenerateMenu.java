package com.intendentapp.generator;

import com.intendentapp.model.Menu;
import com.intendentapp.configuration.OpenXlsx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GenerateMenu {

    private final String FILENAME="src/main/webapp/static/xlsx/menu.xlsx";

    private int date;               //numer wiersza w arkuszu kalkulacyjnym do wprowadzenia z datą
    private int meal;               //numer wiersza w arkuszu kalkulacyjnym do wprowadzenia z posiłkiem
    private List<String> dates;     //daty
    private List<String> meals;     //posiłki
    private OpenXlsx openXlsx;      //instancja pliku excel
    private Integer message;        //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private Date dateFromString;    //data przekonwertowana ze Stringa

    //kontruktor
    public GenerateMenu(Menu menu) {
        dates = Arrays.asList(menu.getDate().split(","));   //rozdzielenie tekstu składającego się z dat oddzielonego przecinkami
        meals = Arrays.asList(menu.getMeal().split(","));   //rozdzielenie tekstu składającego się z posiłków oddzielonego przecinkami
        openXlsx = new OpenXlsx(FILENAME, "src/main/webapp/static/menus/"+menu.getFilename()+"menu.xlsx");     //utworzenie obiektu pliku excelowego
        this.date = 4;                                             //pierwszy wiersz gdzie należy wprowadzić daty w pliku excel
        this.meal = 6;                                              //pierwszy wiersz gdzie należy wprowadzić posiłki w pliku excel
    }

    //edytowanie komórek. date+5 dlatego, ponieważ w pliku excel co 5 komórek jest wprowadzana wartość daty oraz posiłków
    public void generate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");      //formatter ustawiony na pokazywanie nazwy dnia
        for(String date : dates){
            try {
                dateFromString = formatter.parse(date);         //konwersja ze String na Date
            } catch (ParseException e) {
                e.printStackTrace();
            }
            openXlsx.updateStringCell(this.date,0,new String(dayFormatter.format(dateFromString)+" - "+date).toUpperCase());
            this.date=this.date+5;
        }
        for(String meal : meals){
            openXlsx.updateStringCell(this.meal,0,meal);
            this.meal=this.meal+5;
        }
        message=openXlsx.save();
    }

    public Integer getMessage() {
        return message;
    }
}
