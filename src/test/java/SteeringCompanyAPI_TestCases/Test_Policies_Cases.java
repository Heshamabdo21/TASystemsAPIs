package SteeringCompanyAPI_TestCases;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.shaft.tools.io.ExcelFileManager;

import SteeringCompanyAPIs.Policies_API;
import SteeringCompanyAPIs.Token_API;

public class Test_Policies_Cases {
    
    ExcelFileManager testDataReader ;
    String UserName,Password;
     @BeforeClass
     ///////// Read Data for Token API ///////////////////////////////
     public void Setup_data() {
     testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     UserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data1");
     Password=testDataReader.getCellData("TokenAPI_TestData","Password","Data1");
     }

     //////////////////Test Cases for Get All Active Policies API ////////////////////////////

    @Test(description = "TC001 - Perform Get all Policies API with valid user name and password")
    public void Valid_GET_all_Policies_Rq_TC() {
         Token_API Token_TC=new Token_API();
         Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
         Token_TC.Check_Token_Valid_status_Code_Response();
         String Token =Token_TC.Get_Valid_Access_Token();

         Policies_API GetAllPolicies_TC=new Policies_API();
         GetAllPolicies_TC.GET_all_Policies_Rq(Token);
         GetAllPolicies_TC.Check_Valid_policies_status_Code_Response();
         GetAllPolicies_TC.Check_policies_Response_Time();
         GetAllPolicies_TC.Check_policies_Valid_Content();
         GetAllPolicies_TC.Check_policies_Response_Valid_Schema();
     }

    @Test(description = "TC002 - Perform Get all Policies API with pagination with valid user name and password")
    public void Valid_GET_all_Policies_by_Qry_Rq_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetAllPolicies_TC=new Policies_API();
        GetAllPolicies_TC.GET_all_Policies_Path_by_parameter_Query_Rq(Token,"3","0");
        GetAllPolicies_TC.Check_Valid_policies_status_Code_Response();
        GetAllPolicies_TC.Check_policies_Response_Time();
        GetAllPolicies_TC.Check_policies_Valid_Content();
        GetAllPolicies_TC.Check_policies_Response_Valid_Schema();
    }

    @Test(description = "TC003 - Perform Get all Policies API with missing Token")
    public void Invalid_GET_all_Policies_with_missing_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetAllPolicies_TC=new Policies_API();
        GetAllPolicies_TC.GET_all_Policies_With_Missing_Token_Rq();
        GetAllPolicies_TC.Check_Policies_status_Code_Unauthorized_Response();
        GetAllPolicies_TC.Check_policies_Response_Time();
        GetAllPolicies_TC.Check_policies_Response_Unauthorized_Schema();
    }

    @Test(description = "TC004 - Perform Get all Policies API with invalid or expired Token")
    public void Invalid_GET_all_Policies_with_invalid_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetAllPolicies_TC=new Policies_API();
        GetAllPolicies_TC.GET_all_Policies_With_InValid_Token_Rq("123");
        GetAllPolicies_TC.Check_Policies_status_Code_Unauthorized_Response();
        GetAllPolicies_TC.Check_policies_Response_Time();
        //GetAllPolicies_TC.Check_policies_Response_Unauthorized_Schema(); // There is no response content
    }

    /////////////////////// Test Case for Add Cancel Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_for_Cancel_Policy")
    public Object[][] Valid_Cancel_Policy(){
        Object data[][]=new Object[2][ 11];
        for (int i=0;i<2;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"nameArabic");;
            data[i][2]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"nameEnglish");;
            data[i][3]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"descriptionArabic");;
            data[i][4]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"descriptionEnglish");;
            data[i][5]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"chargeUnit");;
            data[i][6]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"deadline");;
            data[i][7]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"chargeType");;
            data[i][8]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"chargeValue");;
            data[i][9]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"id");;
            data[i][10]=testDataReader.getCellData("Policy_TestData","Cancel_Valid_"+(i+1),"ExpectedResult");;
        }
        return data;
    }

    @Test(description = "TC005 - Perform Add Valid Cancel Policy",dataProvider = "Valid_data_for_Cancel_Policy")
    @Story("CRUD Operations")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_Cancel_Policy_TC(Object data[]){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddCancelPolicies_TC=new Policies_API();
        AddCancelPolicies_TC.Add_Policy_Rq(Token,data);
        AddCancelPolicies_TC.Check_Valid_Add_policies_status_Code_Response();
        AddCancelPolicies_TC.Check_Add_Policy_Response_Time();
       // AddCancelPolicies_TC.Check_policies_Valid_Content();
        AddCancelPolicies_TC.Check_Add_policy_Response_Valid_Schema();
    }


}
