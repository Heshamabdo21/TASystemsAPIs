package Utils;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.tools.io.ReportManager;
import com.shaft.tools.io.ReportManagerHelper;
import com.shaft.tools.support.JavaHelper;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExtraExcelFun extends ExcelFileManager {

    /**
     * Creates a new instance of the test data Excel reader using the target Excel
     * file path
     */
    private void initializeVariables() {
        fis = null;
        workbook = null;
        sheet = null;
        row = null;
        cell = null;
        excelFilePath = "";
        testDataColumnNamePrefix = System.getProperty("testDataColumnNamePrefix");
    }

    /**
     * Creates a new instance of the test data Excel reader using the target Excel
     * file path
     *
     * @param excelFilePath target test data Excel file path
     */
    public ExtraExcelFun(String excelFilePath) {
        super(excelFilePath);
        excelFilePath = JavaHelper.appendTestDataToRelativePath(excelFilePath);
        initializeVariables();
        this.excelFilePath = excelFilePath;
        try {
            fis = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(fis);
            fis.close();
//            ReportManager.logDiscrete("Reading test data from the following file [" + excelFilePath + "].");
        } catch (IOException e) {
            ReportManagerHelper.log(e);
            ReportManager.log("Couldn't find the desired file. [" + excelFilePath + "].");
            Assert.fail("Couldn't find the desired file. [" + excelFilePath + "].");
        } catch (OutOfMemoryError e) {
//	    ReportManagerHelper.log(e); override function to be able to log errors
            ReportManager.log("Couldn't open the desired file. [" + excelFilePath + "].");
            Assert.fail("Couldn't open the desired file. [" + excelFilePath + "].");
        } catch (EmptyFileException e) {
            ReportManagerHelper.log(e);
            ReportManager.log("Please check the target file, as it may be corrupted. [" + excelFilePath + "].");
            Assert.fail("Please check the target file, as it may be corrupted. [" + excelFilePath + "].");
        }

        List<List<Object>> attachments = new ArrayList<>();
        List<Object> testDataFileAttachment = null;
        try {
            testDataFileAttachment = Arrays.asList("Test Data", "Excel",
                    new FileInputStream(excelFilePath));
        } catch (FileNotFoundException e) {
            //unreachable code because if the file was not found then the reader would have failed at a previous step
        }
        attachments.add(testDataFileAttachment);
        ReportManagerHelper.log("Loaded Test Data: \"" + excelFilePath + "\".", attachments);
    }


    private FileInputStream fis;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private String excelFilePath;
    private String testDataColumnNamePrefix;

    public int CountRowsHasSpecificText(String sheetName, String rowName) {
        //     try {
        // get the row number that corresponds to the desired rowName within the first
        // column [0]
        sheet = workbook.getSheet(sheetName);
        int count = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            // get the first cell of each row, and compare it to rowName
            // if they match then that's the row we want

            if (row != null && row.getCell(0).getStringCellValue().contains(rowName)) {
                count++;
            }
            // in certain cases if the row is empty, its value is set to null, and hence a
            // null pointer exception is thrown when
            // you try to get the cell from it.
            // we can skip this exception by checking if row != null.
        }
        return count;


    }

}
