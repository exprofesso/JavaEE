package EvilNerd;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class Lesson28_Excel_ApachePOI {


  protected static void readExcel (String adressage) {
    // /Users/sergeypodkolzin/Downloads/read.xlsx
    try{
      FileInputStream fileInputStream = new FileInputStream(new File(adressage));
      XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
      XSSFSheet sheet = workbook.getSheetAt(0);

      Iterator<Row> rowIterator = sheet.iterator();

      while (rowIterator.hasNext()){
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()){
          Cell cell = cellIterator.next();
          switch (cell.getCellType()){
            case NUMERIC:
              System.out.printf("%.0f", cell.getNumericCellValue());
              break;
            case STRING:
              System.out.print(cell.getStringCellValue() + "\t\t");
              break;
          }
        }
        System.out.println();
      }
      fileInputStream.close();

    }
    catch (Exception e){
      System.out.println("Что то пошло не так");
    }

  }
}
