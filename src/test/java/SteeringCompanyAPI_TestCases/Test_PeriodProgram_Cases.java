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

import java.util.Arrays;
import java.util.Objects;

import static org.testng.Assert.assertNotNull;
@SuppressWarnings("ALL")
public class Test_PeriodProgram_Cases {

    ExtraExcelFun testDataReader;
    ExtraExcelFun testDataReader2;

    String UserName, Password;
    DateTimeZone zone;
    Chronology GJChronologydate;
    LocalDate Today;
    Object[] FirstValidCreationData, FirstValidPeriodProgramTemplatesData;

    ///////// Read Data for Token API ///////////////////////////////
    @BeforeClass
    public void Setup_data() {
        // testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        UserName = testDataReader2.getCellData("TokenAPI_TestData", "UserName", "Data1");
        Password = testDataReader2.getCellData("TokenAPI_TestData", "Password", "Data1");
        zone = org.joda.time.DateTimeZone.forID("Asia/Riyadh");
        GJChronologydate = GJChronology.getInstance(zone);
        Today = new LocalDate(GJChronologydate);
    }

    public String SharedSteps_GetToken() {
        Token_API Token_TC = new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName, Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
       // String Token = Token_TC.Get_Valid_Access_Token();
        return Token_TC.Get_Valid_Access_Token();
    }

    @Test(description = "PeriodProgram- Get Valid Today Creational Period", priority = 0)
    @Story("Perpare Date for PeriodProgram")
    @Severity(SeverityLevel.CRITICAL)
    public void Get_Valid_Today_Creational_TC() throws JSONException {
        String Token = SharedSteps_GetToken().toString();
        CreationalPeriods_API GetAllCreationalPeriod_TC = new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_Valid_all_CreationalPeriods_Rq(Token);
        FirstValidCreationData = GetAllCreationalPeriod_TC.Get_Valid_Today_CreationalPeriods();
        // FirstValidPeriodProgramTemplatesData;
        PeriodProgramTemplates_API GetAllPeriodProgramTemplate_TC = new PeriodProgramTemplates_API();
        if (Objects.nonNull(FirstValidCreationData)) {
            GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id_Rq(Token, FirstValidCreationData[2].toString());
            FirstValidPeriodProgramTemplatesData = GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id();
        } else {
            SteeringCompanyQry.UpdateLastCreationalPeriod(Today, Today.plusDays(1));
            GetAllCreationalPeriod_TC.Get_Valid_all_CreationalPeriods_Rq(Token);
            FirstValidCreationData = GetAllCreationalPeriod_TC.Get_Valid_Today_CreationalPeriods();
            GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id_Rq(Token, FirstValidCreationData[2].toString());
            FirstValidPeriodProgramTemplatesData = GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id();
        }

        Arrays.stream(FirstValidPeriodProgramTemplatesData).forEach(data -> System.out.println("FirstValidPeriodProgramTemplatesData ::: " + data));
        Arrays.stream(FirstValidCreationData).forEach(data -> System.out.println("FirstValidCreationData ::: " + data));
    }

    /////////////////////// Test Case for Add PeriodPrograms //////////////////////////////////////////

}
