package com.intendentapp.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class OpenXlsx {
	
	private final Logger log = LogManager.getLogger(OpenXlsx.class);
	
    private FileInputStream file;   //strumień do pliku
    private XSSFWorkbook workbook;  //instacja skoroszytu
    private XSSFSheet sheet;        //instancja arkuszu w skoroszycie
    private Cell cell;              //komórka w skoroszycie
    private String outFilename;        //nazwa pliku do zapisania

    public OpenXlsx(String filename, String outFilename) {  //konstruktor
        try{
            file = new FileInputStream(new File(filename));
            this.outFilename = outFilename;
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheetAt(0);
            cell = null;
        }catch (FileNotFoundException e){
            log.error("Nie ma pliku o ścieżce: " + filename);
            log.error(e);
        }catch (IOException e){
            log.error("Błąd wejścia/wyjścia przy czytaniu pliku Excel");
            log.error(e);
        }
    }

    //metoda modyfikująca komórkę w wierszu rowNumber, kolumnie cellNumber o wartość value typu String
    public void updateStringCell(Integer rowNumber, Integer cellNumber, String value){
        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellValue(value);
    }

    //metoda modyfikująca komórkę w wierszu rowNumber, kolumnie cellNumber o wartość value typu Integer
    public void updateIntegerCell(Integer rowNumber, Integer cellNumber, Integer value){
        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellValue(value);
    }
    
    //metoda modyfikująca komórkę w wierszu rowNumber, kolumnie cellNumber o wartość value typu Double
    public void updateDoubleCell(Integer rowNumber, Integer cellNumber, Double value){
        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellValue(value);
    }
    
    //zapis wprowadzonych zmian
    public Integer save(){
        try {
            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(outFilename));
            XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
            workbook.write(outFile);
            outFile.close();
            log.info("Dodano poprawnie plik Excel o scieżce: " + outFilename);
            return 1;
        }catch (FileNotFoundException e){
        	log.error("Nie ma pliku o ścieżce: " + outFilename);
        	log.error(e);
            return 0;
        }catch (IOException e){
        	log.error("Błąd wejścia/wyjścia przy zapisie pliku Excel. Ścieżka: " + outFilename);
        	log.error(e);
            return 0;
        }
    }
    public String readOneCell(Integer rowNumber, Integer cellNumber){
        return sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    //zamknięcie pliku
    public void close(){
        try {
            file.close();
        }catch (IOException e){
        	log.error("Błąd wejścia/wyjścia przy zamknięciu pliku Excel. Ścieżka: " + outFilename);
        	log.error(e);
        }
    }
}
