package EvilNerd;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;

public class Lesson30_WritetoExcel_ApachePOI {

    public static void writeToExcel (String write){

        try{
            Workbook workbook = new XSSFWorkbook();
            FileOutputStream fileOutputStream = new FileOutputStream(write);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
      System.out.println("File create ");

        }
        catch (Exception e){
      System.out.println("Что то пошло не так!!!");
        }

    }

    public static Workbook createWorkbook (int sheets, int row, int cell ){
        Workbook workbook = new XSSFWorkbook();
        try {
            if(sheets > 1){
                    Sheet sheet = workbook.createSheet();
                }
            }
        catch (Exception e){
            System.out.println("Введите положительное число sheets");
        }
        return workbook;
    }

    public static void createWorkbook1 (int rowIndex){

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();


        for(int i = 0; i < 5; i++){
            Row row = sheet.createRow(i);
          /*  row.createCell(0).setCellValue("Guano Apes");
            row.createCell(1).setCellValue("1999");
            row1.createCell(0).setCellValue("BLue");
            row1.createCell(1).setCellValue("1997");
            row2.createCell(0).setCellValue("Nirvana");
            row2.createCell(1).setCellValue("1988");
*/
        }
        sheet.getRow(5).createCell(5);


/*
        row.createCell(6);
        
        row.createCell(0).setCellValue("Guano Apes");
        row.createCell(1).setCellValue("1999");

*/

    }

}
