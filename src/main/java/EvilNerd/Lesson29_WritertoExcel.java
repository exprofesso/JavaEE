package EvilNerd;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class Lesson29_WritertoExcel {
  public static void WritertoExcel (String agressa) {
      Workbook wb = new XSSFWorkbook();
      Sheet sheet = wb.createSheet();

      Row row = sheet.createRow(0);
      row.createCell(0).setCellValue("Guano Apes");
      row.createCell(1).setCellValue("1999");

      Row row1 = sheet.createRow(1);
      row1.createCell(0).setCellValue("BLue");
      row1.createCell(1).setCellValue("1997");

      try{

          FileOutputStream fileOutputStream = new FileOutputStream("/Users/sergeypodkolzin/Downloads/" + agressa +".xlsx");
          wb.write(fileOutputStream);
          wb.close();
      System.out.println("Фаил создан ");

      }
      catch (Exception e){
      System.out.println("что то пошло не так и не записалось");
      }

    //
  }
}
