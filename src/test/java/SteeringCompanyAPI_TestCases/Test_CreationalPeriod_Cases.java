package SteeringCompanyAPI_TestCases;

import SteeringCompanyAPIs.CreationalPeriods_API;
import SteeringCompanyAPIs.Token_API;
import Utils.ExtraExcelFun;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_CreationalPeriod_Cases {
    ExtraExcelFun testDataReader ;
    ExtraExcelFun testDataReader2;

    String UserName,Password;
    @BeforeClass
    ///////// Read Data for Token API ///////////////////////////////
    public void Setup_data() {
        // testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
        UserName=testDataReader2.getCellData("TokenAPI_TestData","UserName","Data1");
        Password=testDataReader2.getCellData("TokenAPI_TestData","Password","Data1");
    }

    //////////////////Test Cases for Get All Active CreationalPeriod API ////////////////////////////

    @Test(description = "TC001  -CreationalPeriod-  Perform Get all CreationalPeriod API with valid user name and password")
    @Story("Retrieving All Active CreationalPeriod")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_GET_all_CreationalPeriod_Rq_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_Valid_all_CreationalPeriods_Rq(Token);
        GetAllCreationalPeriod_TC.Check_Valid_CreationalPeriods_status_Code_Response();
        GetAllCreationalPeriod_TC.Check_CreationalPeriods_Response_Time();
        GetAllCreationalPeriod_TC.Check_all_CreationalPeriods_Response_Valid_Schema();
    }

    @Test(description = "TC002  -CreationalPeriod-   Perform Get all CreationalPeriod API with pagination with valid user name and password")
    @Story("Retrieving All Active CreationalPeriod with Pagination")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_GET_all_CreationalPeriod_by_Qry_Rq_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_Valid_all_CreationalPeriods_by_parameter_Query_Rq(Token,"3","0");
        GetAllCreationalPeriod_TC.Check_Valid_CreationalPeriods_status_Code_Response();
        GetAllCreationalPeriod_TC.Check_CreationalPeriods_Response_Time();
        //  GetAllCreationalPeriod_TC.Check_All_CreationalPeriod_Valid_Content();
        GetAllCreationalPeriod_TC.Check_all_CreationalPeriods_Response_Valid_Schema();
    }

    ///////////////Test cases for missing /invalid/expired Token/////////////////////////////////////////
    @Test(description = "TC003  -CreationalPeriod-   Perform Get all CreationalPeriod API with missing Token")
    @Story("Retrieving All Active CreationalPeriod with missing Token")
    @Severity(SeverityLevel.MINOR)
    public void Invalid_GET_all_CreationalPeriod_with_missing_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();

        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_all_CreationalPeriods_With_Missing_Token_Rq();
        GetAllCreationalPeriod_TC.Check_Unauthorized_CreationalPeriods_status_Code_Response();
        GetAllCreationalPeriod_TC.Check_CreationalPeriods_Response_Time();
        GetAllCreationalPeriod_TC.Check_all_CreationalPeriods_Response_Unauthorized_Schema();
    }

    @Test(description = "TC004  -CreationalPeriod-   Perform Get CreationalPeriod by ID with missing Token")
    @Story("Retrieving Active CreationalPeriod By ID with missing Token")
    @Severity(SeverityLevel.MINOR)
    public void Invalid_GET_CreationalPeriod_ByID_with_missing_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();

        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_CreationalPeriodsby_CreationalPeriods_id_With_Missing_Token_Rq("228");
        GetAllCreationalPeriod_TC.Check_Unauthorized_CreationalPeriods_status_Code_Response();
        GetAllCreationalPeriod_TC.Check_CreationalPeriods_Response_Time();
        GetAllCreationalPeriod_TC.Check_all_CreationalPeriods_Response_Unauthorized_Schema();
    }

    @Test(description = "TC005 -CreationalPeriod-  Perform Get all CreationalPeriod API with invalid or expired Token")
    @Story("Retrieving All Active CreationalPeriod with invalid/expired Token")
    @Severity(SeverityLevel.MINOR)
    public void Invalid_GET_all_CreationalPeriod_with_invalid_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_all_CreationalPeriods_With_InValid_Token_Rq("123");
        GetAllCreationalPeriod_TC.Check_Unauthorized_CreationalPeriods_status_Code_Response();
        GetAllCreationalPeriod_TC.Check_CreationalPeriods_Response_Time();
        //GetAllCreationalPeriod_TC.Check_All_CreationalPeriod_Response_Unauthorized_Schema(); // There is no response content
    }

    @Test(description = "TC006 -CreationalPeriod-  Perform Get all CreationalPeriod API with invalid or expired Token")
    @Story("Retrieving All Active CreationalPeriod with invalid/expired Token")
    @Severity(SeverityLevel.MINOR)
    public void Invalid_GET_CreationalPeriod_ByID_with_invalid_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        CreationalPeriods_API GetAllCreationalPeriod_TC=new CreationalPeriods_API();
        GetAllCreationalPeriod_TC.Get_CreationalPeriodsby_CreationalPeriods_id_With_InValid_Token_Rq("123","228");
        GetAllCreationalPeriod_TC.Check_Unauthorized_CreationalPeriods_status_Code_Response();
        GetAllCreationalPeriod_TC.Check_CreationalPeriods_Response_Time();
        //GetAllCreationalPeriod_TC.Check_All_CreationalPeriod_Response_Unauthorized_Schema(); // There is no response content
    }
//////////////////////////////////////////////////////////////////////////////////////
}
