package com.intendentapp.configuration;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class OpenXlsx {
	
    private FileInputStream file;   //strumień do pliku
    private XSSFWorkbook workbook;  //instacja skoroszytu
    private XSSFSheet sheet;        //instancja arkuszu w skoroszycie
    private Cell cell;              //komórka w skoroszycie
    private String filename;        //nazwa pliku
    private String outFilename;        //nazwa pliku do zapisania

    public OpenXlsx(String filename, String outFilename) {  //konstruktor
        this.filename=filename;
        try{
            file = new FileInputStream(new File(filename));
            this.outFilename = outFilename;
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheetAt(0);
            cell = null;
        }catch (FileNotFoundException e){
            System.out.println("Nie ma pliku o podanej nazwie!");
        }catch (IOException e){
            System.out.println("Błąd wejścia/wyjścia!");
        }
    }

    //metoda modyfikująca komórkę w wierszu rowNumber, kolumnie cellNumber o wartość value
    public void updateStringCell(Integer rowNumber, Integer cellNumber, String value){
        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellValue(value);
    }

    //metoda modyfikująca komórkę w wierszu rowNumber, kolumnie cellNumber o wartość value
    public void updateIntegerCell(Integer rowNumber, Integer cellNumber, Integer value){
        cell = sheet.getRow(rowNumber).getCell(cellNumber);
        cell.setCellValue(value);
    }
    //metoda modyfikująca komórkę w wierszu rowNumber, kolumnie cellNumber o wartość value
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
            return 1;
        }catch (FileNotFoundException e){
            return 0;
        }catch (IOException e){
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
            System.out.println("Błąd wejścia/wyjścia!");
        }
    }
}
