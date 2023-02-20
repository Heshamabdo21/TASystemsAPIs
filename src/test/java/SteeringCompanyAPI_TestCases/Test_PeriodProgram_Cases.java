/*
 * Copyright (c) 2023.
 */

package SteeringCompanyAPI_TestCases;

import PostgresqlUtils.SteeringCompanyQry;
import SteeringCompanyAPIs.CreationalPeriods_API;
import SteeringCompanyAPIs.PeriodProgramTemplates_API;
import SteeringCompanyAPIs.Token_API;
import Utils.ExtraExcelFun;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.GJChronology;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
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

    @Test(description = "PeriodProgram- Get Valid Today Creational Period")
    @Story("Perpare Date for PeriodProgram")
    @Severity(SeverityLevel.CRITICAL)
    public void Get_Valid_Today_Creational_TC() throws JSONException {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_Valid_all_CreationalPeriods_Rq(Token);
        Object[] FirstValidCreationData=GetAllCreationalPeriod_TC.Get_Valid_Today_CreationalPeriods();
        Object[] FirstValidPeriodProgramTemplatesData;
        if(FirstValidCreationData!=null) {
            PeriodProgramTemplates_API GetAllPeriodProgramTemplate_TC = new PeriodProgramTemplates_API();
            GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id_Rq(Token, FirstValidCreationData[2].toString());
            FirstValidPeriodProgramTemplatesData=GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id();
        }else{
            DateTimeZone zone = org.joda.time.DateTimeZone.forID("Asia/Riyadh");
            Chronology GJChronologydate = GJChronology.getInstance(zone);
            LocalDate Today = new LocalDate(GJChronologydate);
            SteeringCompanyQry.UpdateLastCreationalPeriod(Today,Today.plusDays(1));
        }
    }



    /////////////////////// Test Case for Add PeriodPrograms //////////////////////////////////////////

}
