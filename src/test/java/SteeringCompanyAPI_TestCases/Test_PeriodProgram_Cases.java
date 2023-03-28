/*
 * Copyright (c) 2023.
 */

package SteeringCompanyAPI_TestCases;

import PostgresqlUtils.SteeringCompanyQry;
import SteeringCompanyAPIs.CreationalPeriods_API;
import SteeringCompanyAPIs.PeriodProgramTemplates_API;
import SteeringCompanyAPIs.PeriodPrograms_API;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Objects;

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
            //update DB last creation period with new dates to be valid in case there is no creational period
            SteeringCompanyQry.UpdateLastCreationalPeriod(Today, Today.plusDays(1));
            GetAllCreationalPeriod_TC.Get_Valid_all_CreationalPeriods_Rq(Token);
            FirstValidCreationData = GetAllCreationalPeriod_TC.Get_Valid_Today_CreationalPeriods();
            GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id_Rq(Token, FirstValidCreationData[2].toString());
            FirstValidPeriodProgramTemplatesData = GetAllPeriodProgramTemplate_TC.Get_Valid_PeriodProgramTemplates_by_id();
        }

        try {
            int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_");
            for (int i = 0; i < dataRowsNumber; i++) {
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId", FirstValidCreationData[0].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "templateId", FirstValidPeriodProgramTemplatesData[0].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod", FirstValidCreationData[1].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear", FirstValidPeriodProgramTemplatesData[1].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear", FirstValidPeriodProgramTemplatesData[2].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat", FirstValidPeriodProgramTemplatesData[4].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat", FirstValidPeriodProgramTemplatesData[5].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer", FirstValidPeriodProgramTemplatesData[3].toString());
                testDataReader2.AddExpectedResult("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "isActive", "true");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //  Arrays.stream(FirstValidPeriodProgramTemplatesData).forEach(data -> System.out.println("FirstValidPeriodProgramTemplatesData ::: " + data));
        // Arrays.stream(FirstValidCreationData).forEach(data -> System.out.println("FirstValidCreationData ::: " + data));
    }
    /////////////////////// Test Case for Valid Add PeriodPrograms //////////////////////////////////////////
    @DataProvider(name = "Valid_data_add_PeriodProgram")
    public Object[][] Valid_Add_PeriodProgram() {
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_");
        Object[][] data = new Object[dataRowsNumber][22];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC001 -PeriodProgram- Perform Add Valid PeriodProgram", dataProvider = "Valid_data_add_PeriodProgram")
    @Story("Adding Valid PeriodProgram")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_PeriodProgram_TC(Object[] data) {
        // Token_API Token_TC=new Token_API();
        //Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        //  Token_TC.Check_Token_Valid_status_Code_Response();
        // String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API AddPeriodPrograms_TC = new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_Rq(Token, data);
        AddPeriodPrograms_TC.Check_Valid_Add_PeriodPrograms_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        testDataReader = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        String ExpectedResult = testDataReader.getCellData("PeriodProgram_TestData", data[0].toString(), "ExpectedResult");
        AddPeriodPrograms_TC.Check_PeriodProgram_Content(ExpectedResult);
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////Invalid token for Add Period Program///////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC002 -PeriodProgram- Perform Add PeriodProgram With Missing Token", dataProvider = "Valid_data_add_PeriodProgram")
    @Story("Adding InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_PeriodProgram_With_Missing_Token_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API AddPeriodPrograms_TC = new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_With_Missing_Token_Rq(data);
        AddPeriodPrograms_TC.Check_Unauthorized_PeriodProgram_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Unauthorized_Schema();
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC003 -PeriodProgram- Perform Add PeriodProgram With Invalid/Expired Token", dataProvider = "Valid_data_add_PeriodProgram")
    @Story("Adding InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_PeriodProgram_With_Invalid_Token_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API AddPeriodPrograms_TC = new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_With_InValid_Token_Rq("123", data);
        AddPeriodPrograms_TC.Check_Unauthorized_PeriodProgram_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
//        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Unauthorized_Schema();
    }
    /////////////////////////////////////Invalid data for Add Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "InValid_data_add_PeriodProgram")
    public Object[][] InValid_Add_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_");
        Object[][] data = new Object[dataRowsNumber][22];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC004 -PeriodProgram- Perform Add PeriodProgram With Invalid data", dataProvider = "InValid_data_add_PeriodProgram")
    @Story("Adding InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_PeriodProgram_With_Invalid_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API AddPeriodPrograms_TC = new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_With_Invalid_Input_Rq(Token, data);
        AddPeriodPrograms_TC.Check_Validation_Error_PeriodProgram_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        AddPeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Validation_Error_Schema();
    }
    /////////////////////////////////////NotAccepted data for Add Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "NotAccepted_data_add_PeriodProgram")
    public Object[][] NotAccepted_Add_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_");
        Object[][] data = new Object[dataRowsNumber][22];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC005 -PeriodProgram- Perform Add PeriodProgram With NotAccepted data", dataProvider = "NotAccepted_data_add_PeriodProgram")
    @Story("Adding InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_PeriodProgram_With_NotAccepted_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API AddPeriodPrograms_TC = new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_With_NotAccepted_Input_Rq(Token, data);
        AddPeriodPrograms_TC.Check_Validation_NotAccepted_PeriodProgram_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        AddPeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_NotAccepted_Error_Schema();
    }
    /////////////////////////////////////NotFound data for Add Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "NotFound_data_add_PeriodProgram")
    public Object[][] NotFound_Add_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_");
        Object[][] data = new Object[dataRowsNumber][22];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Add_NotFound_PeriodProgram_" + (i + 1), "ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC006 -PeriodProgram- Perform Add PeriodProgram With NotAccepted data", dataProvider = "NotFound_data_add_PeriodProgram")
    @Story("Adding InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_PeriodProgram_With_NotFound_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API AddPeriodPrograms_TC = new PeriodPrograms_API();
        AddPeriodPrograms_TC.Add_PeriodProgram_With_NotFound_Input_Rq(Token, data);
        AddPeriodPrograms_TC.Check_Validation_NotFound_PeriodProgram_status_Code_Response();
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        AddPeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        AddPeriodPrograms_TC.Check_PeriodProgram_Response_NotFound_Error_Schema();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////// Test Case for Valid Update PeriodPrograms //////////////////////////////////////////
    @DataProvider(name = "Valid_data_Update_PeriodProgram")
    public Object[][] Valid_Update_PeriodProgram() {
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_Valid_PeriodProgram_Valid_Creational_" + (i + 1), "PeriodProgramID");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC001 -PeriodProgram- Perform Update Valid PeriodProgram", dataProvider = "Valid_data_Update_PeriodProgram")
    @Story("Updateing Valid PeriodProgram")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Update_PeriodProgram_TC(Object[] data) {
        // Token_API Token_TC=new Token_API();
        //Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        //  Token_TC.Check_Token_Valid_status_Code_Response();
        // String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API UpdatePeriodPrograms_TC = new PeriodPrograms_API();
        UpdatePeriodPrograms_TC.Update_PeriodProgram_Rq(Token, data);
        UpdatePeriodPrograms_TC.Check_Valid_Update_PeriodPrograms_status_Code_Response();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        testDataReader = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        String ExpectedResult = testDataReader.getCellData("PeriodProgram_TestData", data[0].toString(), "ExpectedResult");
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Content(ExpectedResult);
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////Invalid token for Update Period Program///////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC002 -PeriodProgram- Perform Update PeriodProgram With Missing Token", dataProvider = "Valid_data_Update_PeriodProgram")
    @Story("Updateing InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_PeriodProgram_With_Missing_Token_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API UpdatePeriodPrograms_TC = new PeriodPrograms_API();
        UpdatePeriodPrograms_TC.Update_PeriodProgram_With_Missing_Token_Rq(data);
        UpdatePeriodPrograms_TC.Check_Unauthorized_PeriodProgram_status_Code_Response();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Unauthorized_Schema();
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC003 -PeriodProgram- Perform Update PeriodProgram With Invalid/Expired Token", dataProvider = "Valid_data_Update_PeriodProgram")
    @Story("Updateing InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_PeriodProgram_With_Invalid_Token_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API UpdatePeriodPrograms_TC = new PeriodPrograms_API();
        UpdatePeriodPrograms_TC.Update_PeriodProgram_With_InValid_Token_Rq("123", data);
        UpdatePeriodPrograms_TC.Check_Unauthorized_PeriodProgram_status_Code_Response();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Time();
//        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Unauthorized_Schema();
    }
    /////////////////////////////////////Invalid data for Update Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "InValid_data_Update_PeriodProgram")
    public Object[][] InValid_Update_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_InValid_PeriodProgram_Valid_Creational_" + (i + 1), "PeriodProgramID");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC004 -PeriodProgram- Perform Update PeriodProgram With Invalid data", dataProvider = "InValid_data_Update_PeriodProgram")
    @Story("Updateing InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_PeriodProgram_With_Invalid_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API UpdatePeriodPrograms_TC = new PeriodPrograms_API();
        UpdatePeriodPrograms_TC.Update_PeriodProgram_With_Invalid_Input_Rq(Token, data);
        UpdatePeriodPrograms_TC.Check_Validation_Error_PeriodProgram_status_Code_Response();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Validation_Error_Schema();
    }
    /////////////////////////////////////NotAccepted data for Update Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "NotAccepted_data_Update_PeriodProgram")
    public Object[][] NotAccepted_Update_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotAccepted_PeriodProgram_Valid_Creational_" + (i + 1), "PeriodProgramID");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC005 -PeriodProgram- Perform Update PeriodProgram With NotAccepted data", dataProvider = "NotAccepted_data_Update_PeriodProgram")
    @Story("Updateing InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_PeriodProgram_With_NotAccepted_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API UpdatePeriodPrograms_TC = new PeriodPrograms_API();
        UpdatePeriodPrograms_TC.Update_PeriodProgram_With_NotAccepted_Input_Rq(Token, data);
        UpdatePeriodPrograms_TC.Check_Validation_NotAccepted_PeriodProgram_status_Code_Response();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_NotAccepted_Error_Schema();
    }
    /////////////////////////////////////NotFound data for Update Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "NotFound_data_Update_PeriodProgram")
    public Object[][] NotFound_Update_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Update_NotFound_PeriodProgram_" + (i + 1), "PeriodProgramID");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC006 -PeriodProgram- Perform Update PeriodProgram With NotAccepted data", dataProvider = "NotFound_data_Update_PeriodProgram")
    @Story("Updateing InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_PeriodProgram_With_NotFound_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API UpdatePeriodPrograms_TC = new PeriodPrograms_API();
        UpdatePeriodPrograms_TC.Update_PeriodProgram_With_NotFound_Input_Rq(Token, data);
        UpdatePeriodPrograms_TC.Check_Validation_NotFound_PeriodProgram_status_Code_Response();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        UpdatePeriodPrograms_TC.Check_PeriodProgram_Response_NotFound_Error_Schema();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////// Test Case for Valid Get PeriodPrograms //////////////////////////////////////////
    @DataProvider(name = "Valid_data_Get_PeriodProgram")
    public Object[][] Valid_Get_PeriodProgram() {
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Get_Valid_PeriodProgram_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_Valid_PeriodProgram_" + (i + 1), "PeriodProgramID");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC001 -PeriodProgram- Perform Get Valid PeriodProgram", dataProvider = "Valid_data_Get_PeriodProgram")
    @Story("Geting Valid PeriodProgram")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Get_PeriodProgram_TC(Object[] data) {
        // Token_API Token_TC=new Token_API();
        //Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        //  Token_TC.Check_Token_Valid_status_Code_Response();
        // String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API GetPeriodPrograms_TC = new PeriodPrograms_API();
        GetPeriodPrograms_TC.Get_PeriodProgram_Rq(Token, data);
        GetPeriodPrograms_TC.Check_Valid_Get_PeriodPrograms_status_Code_Response();
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
     //   testDataReader = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
    //    String ExpectedResult = testDataReader.getCellData("PeriodProgram_TestData", data[0].toString(), "ExpectedResult");
        GetPeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////Invalid token for Get Period Program///////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC002 -PeriodProgram- Perform Get PeriodProgram With Missing Token", dataProvider = "Valid_data_Get_PeriodProgram")
    @Story("Geting InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_PeriodProgram_With_Missing_Token_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API GetPeriodPrograms_TC = new PeriodPrograms_API();
        GetPeriodPrograms_TC.Get_PeriodProgram_With_Missing_Token_Rq(data);
        GetPeriodPrograms_TC.Check_Unauthorized_PeriodProgram_status_Code_Response();
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Unauthorized_Schema();
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC003 -PeriodProgram- Perform Get PeriodProgram With Invalid/Expired Token", dataProvider = "Valid_data_Get_PeriodProgram")
    @Story("Geting InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_PeriodProgram_With_Invalid_Token_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();

        PeriodPrograms_API GetPeriodPrograms_TC = new PeriodPrograms_API();
        GetPeriodPrograms_TC.Get_PeriodProgram_With_InValid_Token_Rq("123", data);
        GetPeriodPrograms_TC.Check_Unauthorized_PeriodProgram_status_Code_Response();
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
//        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Unauthorized_Schema();
    }
    /////////////////////////////////////BadRequest data for Get Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "BadRequest_data_Get_PeriodProgram")
    public Object[][] BadRequest_Get_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_BadRequest_PeriodProgram_" + (i + 1), "PeriodProgramID");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC004 -PeriodProgram- Perform Get PeriodProgram With BadRequest data", dataProvider = "BadRequest_data_Get_PeriodProgram")
    @Story("Geting InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_PeriodProgram_With_BadRequest_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API GetPeriodPrograms_TC = new PeriodPrograms_API();
        GetPeriodPrograms_TC.Get_PeriodProgram_With_BadRequest_Input_Rq(Token, data);
        GetPeriodPrograms_TC.Check_Validation_BadRequest_PeriodProgram_status_Code_Response();
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        GetPeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Validation_Error_Schema();
    }

    /////////////////////////////////////NotFound data for Get Period Program///////////////////////////////////////////////////////////
    @DataProvider(name = "NotFound_data_Get_PeriodProgram")
    public Object[][] NotFound_Get_PeriodProgram() {
        ExtraExcelFun testDataReader2;
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_");
        Object[][] data = new Object[dataRowsNumber][23];
        for (int i = 0; i < dataRowsNumber; i++) {
            data[i][0] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "TC_Type");
            data[i][1] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "APIName");
            data[i][2] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "creationPeriodId");
            data[i][3] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "templateId");
            data[i][4] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "periodMaxQuotaPerPeriod");
            data[i][5] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "minimumModelYear");
            data[i][6] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "maximumModelYear");
            data[i][7] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "minimumSeat");
            data[i][8] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "maximumSeat");
            data[i][9] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "vehiclePricePer");
            data[i][10] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "isActive");
            data[i][11] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "TaxName");
            data[i][12] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "TaxID");
            data[i][13] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "PaymentName");
            data[i][14] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "PaymentID");
            data[i][15] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "CancellationName");
            data[i][16] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "CancellationID");
            data[i][17] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "GeneralName");
            data[i][18] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "GeneralID");
            data[i][19] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "UsageName");
            data[i][20] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "UsageID");
            data[i][21] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "ExpectedResult");
            data[i][22] = testDataReader2.getCellData("PeriodProgram_TestData", "Get_NotFound_PeriodProgram_" + (i + 1), "PeriodProgramID");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(dependsOnMethods = "Get_Valid_Today_Creational_TC", description = "TC006 -PeriodProgram- Perform Get PeriodProgram With NotFound data", dataProvider = "NotFound_data_Get_PeriodProgram")
    @Story("Geting InValid PeriodProgram")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_PeriodProgram_With_NotFound_Data_TC(Object[] data) {
//        Token_API Token_TC=new Token_API();
//        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
//        Token_TC.Check_Token_Valid_status_Code_Response();
//        String Token =Token_TC.Get_Valid_Access_Token();
        String Token = SharedSteps_GetToken();
        PeriodPrograms_API GetPeriodPrograms_TC = new PeriodPrograms_API();
        GetPeriodPrograms_TC.Get_PeriodProgram_With_NotFound_Input_Rq(Token, data);
        GetPeriodPrograms_TC.Check_Validation_NotFound_PeriodProgram_status_Code_Response();
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_Time();
        GetPeriodPrograms_TC.Check_PeriodProgram_Content(data[21].toString());
        GetPeriodPrograms_TC.Check_PeriodProgram_Response_NotFound_Error_Schema();
    }

}

