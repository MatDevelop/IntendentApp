package com.intendentapp.generator;

import com.intendentapp.model.Menu;
import com.intendentapp.staticclasses.FilePaths;
import com.intendentapp.configuration.OpenXlsx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateMenu {
	
	private static final Logger log = LogManager.getLogger(GenerateMenu.class);
	
    private final String MENU_TEMPLATE_FILE_PATH = "src/main/webapp/static/xlsx/menu.xlsx";

    private int dateExcelRowNumber;               //numer wiersza w arkuszu kalkulacyjnym do wprowadzenia z datą
    private int mealExcelRowNumber;               //numer wiersza w arkuszu kalkulacyjnym do wprowadzenia z posiłkiem
    private List<String> dates;     //daty
    private List<String> meals;     //posiłki
    private OpenXlsx openXlsx;      //instancja pliku excel
    private Integer message;        //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private Date dateFromString;    //data przekonwertowana ze Stringa

    public GenerateMenu(Menu menu) {
        dates = Arrays.asList(menu.getDate().split(","));   //rozdzielenie tekstu składającego się z dat oddzielonego przecinkami
        meals = Arrays.asList(menu.getMeal().split(","));   //rozdzielenie tekstu składającego się z posiłków oddzielonego przecinkami
        openXlsx = new OpenXlsx(MENU_TEMPLATE_FILE_PATH, FilePaths.MENUS_FOLDER_PATH + "/" + menu.getFilename()+"menu.xlsx");     //utworzenie obiektu pliku excelowego
        this.dateExcelRowNumber = 4;                                             //pierwszy wiersz gdzie należy wprowadzić daty w pliku excel
        this.mealExcelRowNumber = 6;                                              //pierwszy wiersz gdzie należy wprowadzić posiłki w pliku excel
    }

    //edytowanie komórek. date+5 dlatego, ponieważ w pliku excel co 5 komórek jest wprowadzana wartość daty oraz posiłków
    public void generate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");      //formatter ustawiony na pokazywanie nazwy dnia
        
        for(String date : dates){
            try {
                dateFromString = formatter.parse(date);         //konwersja ze String na Date
            } catch (ParseException e) {
            	log.error("Błąd konwersji przy zmianie String na Date.");
                log.error(e);
            }
            openXlsx.updateStringCell(this.dateExcelRowNumber, 0, dayFormatter.format(dateFromString) + " - " + date.toUpperCase());
            this.dateExcelRowNumber = this.dateExcelRowNumber + 5;
        }
        
        for(String meal : meals){
            openXlsx.updateStringCell(this.mealExcelRowNumber, 0, meal);
            this.mealExcelRowNumber = this.mealExcelRowNumber + 5;
        }
        
        message = openXlsx.save();
    }

    public Integer getMessage() {
        return message;
    }
}
