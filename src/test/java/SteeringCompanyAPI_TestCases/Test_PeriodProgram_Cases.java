/*
 * Copyright (c) 2023.
 */

package SteeringCompanyAPI_TestCases;

import SteeringCompanyAPIs.PeriodPrograms_API;
import SteeringCompanyAPIs.Token_API;
import Utils.ExtraExcelFun;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("ALL")
public class Test_PeriodProgram_Cases {

    ExtraExcelFun testDataReader ;
    ExtraExcelFun testDataReader2;

    String UserName,Password;
    ///////// Read Data for Token API ///////////////////////////////
    @BeforeClass
     public void Setup_data() {
    // testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     UserName=testDataReader2.getCellData("TokenAPI_TestData","UserName","Data1");
     Password=testDataReader2.getCellData("TokenAPI_TestData","Password","Data1");
     }


    /////////////////////// Test Case for Add Cancel PeriodPrograms //////////////////////////////////////////
    @DataProvider (name = "Valid_data_add_PeriodPrograms")
    public Object[][] Valid_Add_PeriodPrograms(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData","Add_PeriodProgram_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"TC_Type");
            data[i][1]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"APIName");
            data[i][2]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"creationPeriodId");
            data[i][3]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"templateId");
            data[i][4]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"periodMaxQuotaPerPeriod");
            data[i][5]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"minimumModelYear");
            data[i][6]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"maximumModelYear");
            data[i][7]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"minimumSeat");
            data[i][8]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"maximumSeat");
            data[i][9]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"vehiclePricePer");
            data[i][10]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"isActive");
            data[i][11]= testDataReader2.getCellData("PeriodProgram_TestData","Add_PeriodProgram_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @SuppressWarnings("TestDataSupplier")
  //  @Test(description = "TC001 -PeriodProgram- Perform Add Valid PeriodPrograms",dataProvider = "Valid_data_add_PeriodPrograms")
    @Story("Adding PeriodPrograms")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_PeriodPrograms_TC(Object[] data) {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        PeriodPrograms_API AddPeriodPrograms_TC=new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_Rq(Token,data);
        AddPeriodPrograms_TC.Check_Valid_Add_PeriodPrograms_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodPrograms_Response_Time();
        AddPeriodPrograms_TC.Check_All_PeriodPrograms_Valid_Content();
       // testDataReader = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
      //  String ExpectedResult=testDataReader.getCellData("PeriodProgram_TestData",data[0].toString(),"ExpectedResult");
        AddPeriodPrograms_TC.Check_PeriodProgram_Content(data[data.length-1].toString());
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Valid_Schema(data[1].toString());
    }

}
