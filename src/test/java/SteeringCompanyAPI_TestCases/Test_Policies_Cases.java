package SteeringCompanyAPI_TestCases;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.shaft.tools.io.ExcelFileManager;
import Utils.ExtraExcelFun;
import SteeringCompanyAPIs.Policies_API;
import SteeringCompanyAPIs.Token_API;
import org.testng.collections.Lists;

import java.util.Arrays;
import java.util.List;

public class Test_Policies_Cases {
    
    ExcelFileManager testDataReader ;
    ExtraExcelFun testDataReader2;

    String UserName,Password;
     @BeforeClass
     ///////// Read Data for Token API ///////////////////////////////
     public void Setup_data() {
     testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     testDataReader2 = new ExtraExcelFun("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     UserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data1");
     Password=testDataReader.getCellData("TokenAPI_TestData","Password","Data1");
     }

     //////////////////Test Cases for Get All Active Policies API ////////////////////////////

    @Test(description = "TC001 - Perform Get all Policies API with valid user name and password")
    @Story("Retrieving All Active Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_GET_all_Policies_Rq_TC() {
         Token_API Token_TC=new Token_API();
         Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
         Token_TC.Check_Token_Valid_status_Code_Response();
         String Token =Token_TC.Get_Valid_Access_Token();

         Policies_API GetAllPolicies_TC=new Policies_API();
         GetAllPolicies_TC.GET_All_Policies_Rq(Token);
         GetAllPolicies_TC.Check_Valid_policies_status_Code_Response();
         GetAllPolicies_TC.Check_policies_Response_Time();
         GetAllPolicies_TC.Check_All_policies_Valid_Content();
         GetAllPolicies_TC.Check_All_policies_Response_Valid_Schema();
     }

    @Test(description = "TC002 - Perform Get all Policies API with pagination with valid user name and password")
    @Story("Retrieving All Active Policy with Pagination")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_GET_all_Policies_by_Qry_Rq_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetAllPolicies_TC=new Policies_API();
        GetAllPolicies_TC.GET_All_Policies_Path_by_parameter_Query_Rq(Token,"3","0");
        GetAllPolicies_TC.Check_Valid_policies_status_Code_Response();
        GetAllPolicies_TC.Check_policies_Response_Time();
        GetAllPolicies_TC.Check_All_policies_Valid_Content();
        GetAllPolicies_TC.Check_All_policies_Response_Valid_Schema();
    }

    @Test(description = "TC003 - Perform Get all Policies API with missing Token")
    @Story("Retrieving All Active Policy with missing Token")
    @Severity(SeverityLevel.MINOR)
    public void Invalid_GET_all_Policies_with_missing_Token_TC() {
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        //String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetAllPolicies_TC=new Policies_API();
        GetAllPolicies_TC.GET_All_Policies_With_Missing_Token_Rq();
        GetAllPolicies_TC.Check_Unauthorized_Policies_status_Code_Response();
        GetAllPolicies_TC.Check_policies_Response_Time();
        GetAllPolicies_TC.Check_All_policies_Response_Unauthorized_Schema();
    }

    @Test(description = "TC004 - Perform Get all Policies API with invalid or expired Token")
    @Story("Retrieving All Active Policy with invalid/expired Token")
    @Severity(SeverityLevel.MINOR)
    public void Invalid_GET_all_Policies_with_invalid_Token_TC() {
         Token_API Token_TC=new Token_API();
         Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
         Token_TC.Check_Token_Valid_status_Code_Response();
         //String Token =Token_TC.Get_Valid_Access_Token();
        Policies_API GetAllPolicies_TC=new Policies_API();
        GetAllPolicies_TC.GET_All_Policies_With_InValid_Token_Rq("123");
        GetAllPolicies_TC.Check_Unauthorized_Policies_status_Code_Response();
        GetAllPolicies_TC.Check_policies_Response_Time();
        //GetAllPolicies_TC.Check_All_policies_Response_Unauthorized_Schema(); // There is no response content
    }

    /////////////////////// Test Case for Add Cancel Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_add_Cancel_Policy")
    public Object[][] Valid_Add_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Cancel_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC005 - Perform Add Valid Cancel Policy",dataProvider = "Valid_data_add_Cancel_Policy")
    @Story("Adding Cancel Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_Cancel_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddCancelPolicies_TC=new Policies_API();
        AddCancelPolicies_TC.Add_Policy_Rq(Token,data);
        AddCancelPolicies_TC.Check_Valid_Add_policies_status_Code_Response();
        AddCancelPolicies_TC.Check_Policy_Response_Time();
       // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddCancelPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC020 - Perform Add Cancel Policy With Missing Token",dataProvider = "Valid_data_add_Cancel_Policy")
    @Story("Adding Cancel Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_Cancel_Policy_With_Missing_Token_TC(Object[] data){
      //  Token_API Token_TC=new Token_API();
      //  Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
     //   Token_TC.Check_Token_Valid_status_Code_Response();
     //   String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddCancelPolicies_TC=new Policies_API();
        AddCancelPolicies_TC.Add_Policy_With_Missing_Token_Rq(data);
        AddCancelPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddCancelPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddCancelPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC021 - Perform Add Cancel Policy With Invalid/Expired Token",dataProvider = "Valid_data_add_Cancel_Policy")
    @Story("Adding Cancel Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_Cancel_Policy_With_Invalid_Token_TC(Object[] data){
        //  Token_API Token_TC=new Token_API();
        //  Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        //   Token_TC.Check_Token_Valid_status_Code_Response();
        //   String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddCancelPolicies_TC=new Policies_API();
        AddCancelPolicies_TC.Add_Policy_With_InValid_Token_Rq("123",data);
        AddCancelPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddCancelPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
      //  AddCancelPolicies_TC.Check_All_policies_Response_Unauthorized_Schema();
    }
    /////////////////////// Test Case for Add Tax Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_add_Tax_Policy")
    public Object[][] Valid_Add_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Tax_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Tax_Valid_"+(i+1),"ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC006 - Perform Add Valid Tax Policy",dataProvider = "Valid_data_add_Tax_Policy")
    @Story("Adding Tax Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_Tax_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddTaxPolicies_TC=new Policies_API();
        AddTaxPolicies_TC.Add_Policy_Rq(Token,data);
        AddTaxPolicies_TC.Check_Valid_Add_policies_status_Code_Response();
        AddTaxPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddTaxPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC022 - Perform Add Valid Tax Policy With Missing Token",dataProvider = "Valid_data_add_Tax_Policy")
    @Story("Adding Tax Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_Tax_Policy_With_Missing_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddTaxPolicies_TC=new Policies_API();
        AddTaxPolicies_TC.Add_Policy_With_Missing_Token_Rq(data);
        AddTaxPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddTaxPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddTaxPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC023 - Perform Add Valid Tax Policy With Invalid/Expired Token",dataProvider = "Valid_data_add_Tax_Policy")
    @Story("Adding Tax Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_Tax_Policy_With_Invalid_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddTaxPolicies_TC=new Policies_API();
        AddTaxPolicies_TC.Add_Policy_With_InValid_Token_Rq("123",data);
        AddTaxPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddTaxPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
       // AddTaxPolicies_TC.Check_All_policies_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Add Usage Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_add_Usage_Policy")
    public Object[][] Valid_Add_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Usage_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Usage_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC007 - Perform Add Valid Usage Policy",dataProvider = "Valid_data_add_Usage_Policy")
    @Story("Adding Usage Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_Usage_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
    //    ++x;
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddUsagePolicies_TC=new Policies_API();
        AddUsagePolicies_TC.Add_Policy_Rq(Token,data);
        AddUsagePolicies_TC.Check_Valid_Add_policies_status_Code_Response();
        AddUsagePolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddUsagePolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC024 - Perform Add Usage Policy With Missing Token",dataProvider = "Valid_data_add_Usage_Policy")
    @Story("Adding Usage Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_Usage_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        //    ++x;
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddUsagePolicies_TC=new Policies_API();
        AddUsagePolicies_TC.Add_Policy_With_Missing_Token_Rq(data);
        AddUsagePolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddUsagePolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddUsagePolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC025 - Perform Add Usage Policy With Invalid/Expired Token",dataProvider = "Valid_data_add_Usage_Policy")
    @Story("Adding Usage Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_Usage_Policy_With_Invalid_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        //    ++x;
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddUsagePolicies_TC=new Policies_API();
        AddUsagePolicies_TC.Add_Policy_With_InValid_Token_Rq("123",data);
        AddUsagePolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddUsagePolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        //AddUsagePolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Add Payment Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_add_Payment_Policy")
    public Object[][] Valid_Add_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Payment_Valid_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Payment_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC008 - Perform Add Valid Payment Policy",dataProvider = "Valid_data_add_Payment_Policy")
    @Story("Adding Payment Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_Payment_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddPaymentPolicies_TC=new Policies_API();
        AddPaymentPolicies_TC.Add_Policy_Rq(Token,data);
        AddPaymentPolicies_TC.Check_Valid_Add_policies_status_Code_Response();
        AddPaymentPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddPaymentPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC026 - Perform Add Payment Policy With Missing Token",dataProvider = "Valid_data_add_Payment_Policy")
    @Story("Adding Payment Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void InValid_Add_Payment_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddPaymentPolicies_TC=new Policies_API();
        AddPaymentPolicies_TC.Add_Policy_With_Missing_Token_Rq(data);
        AddPaymentPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddPaymentPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddPaymentPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC027 - Perform Add Payment Policy With Invalid/Expired Token",dataProvider = "Valid_data_add_Payment_Policy")
    @Story("Adding Payment Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void InValid_Add_Payment_Policy_With_InValid_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddPaymentPolicies_TC=new Policies_API();
        AddPaymentPolicies_TC.Add_Policy_With_InValid_Token_Rq("123",data);
        AddPaymentPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddPaymentPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
       // AddPaymentPolicies_TC.Check_All_policies_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Add General Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_add_General_Policy")
    public Object[][] Valid_Add_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_General_Valid_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_General_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC009 - Perform Add Valid General Policy ",dataProvider = "Valid_data_add_General_Policy")
    @Story("Adding General Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Add_General_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API AddGeneralPolicies_TC=new Policies_API();
        AddGeneralPolicies_TC.Add_Policy_Rq(Token,data);
        AddGeneralPolicies_TC.Check_Valid_Add_policies_status_Code_Response();
        AddGeneralPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddGeneralPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC028 - Perform Add General Policy With Missing Token",dataProvider = "Valid_data_add_General_Policy")
    @Story("Adding General Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_General_Policy_With_Missing_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddGeneralPolicies_TC=new Policies_API();
        AddGeneralPolicies_TC.Add_Policy_With_Missing_Token_Rq(data);
        AddGeneralPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddGeneralPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        AddGeneralPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC029 - Perform Add General Policy With Invalid/Expired Token",dataProvider = "Valid_data_add_General_Policy")
    @Story("Adding General Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Add_General_Policy_With_Invalid_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API AddGeneralPolicies_TC=new Policies_API();
        AddGeneralPolicies_TC.Add_Policy_With_InValid_Token_Rq("123",data);
        AddGeneralPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        AddGeneralPolicies_TC.Check_Policy_Response_Time();
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
      //  AddGeneralPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Update Cancel Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Update_Cancel_Policy")
    public Object[][] Valid_Update_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Cancel_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC010 - Perform Update Valid Cancel Policy",dataProvider = "Valid_data_Update_Cancel_Policy")
    @Story("Updating Cancel Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Update_Cancel_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API UpdateCancelPolicies_TC=new Policies_API();
        UpdateCancelPolicies_TC.Update_Policy_Rq(Token,data);
        UpdateCancelPolicies_TC.Check_Valid_Update_policies_status_Code_Response();
        UpdateCancelPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateCancelPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC030 - Perform Update Cancel Policy with missing Token",dataProvider = "Valid_data_Update_Cancel_Policy")
    @Story("Updating Cancel Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Cancel_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateCancelPolicies_TC=new Policies_API();
        UpdateCancelPolicies_TC.Update_Policy_With_Missing_Token_Rq(data);
        UpdateCancelPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateCancelPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateCancelPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC031 - Perform Update Cancel Policy with Invalid/Expired Token",dataProvider = "Valid_data_Update_Cancel_Policy")
    @Story("Updating Cancel Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Cancel_Policy_With_Invalid_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateCancelPolicies_TC=new Policies_API();
        UpdateCancelPolicies_TC.Update_Policy_With_InValid_Token_Rq("123",data);
        UpdateCancelPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateCancelPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
       // UpdateCancelPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Update Tax Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Update_Tax_Policy")
    public Object[][] Valid_Update_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Tax_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Tax_Valid_"+(i+1),"ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC011 - Perform Update Valid Tax Policy",dataProvider = "Valid_data_Update_Tax_Policy")
    @Story("Updating Tax Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Update_Tax_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API UpdateTaxPolicies_TC=new Policies_API();
        UpdateTaxPolicies_TC.Update_Policy_Rq(Token,data);
        UpdateTaxPolicies_TC.Check_Valid_Update_policies_status_Code_Response();
        UpdateTaxPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateTaxPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC032 - Perform Update Tax Policy With Missing Token",dataProvider = "Valid_data_Update_Tax_Policy")
    @Story("Updating Tax Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Tax_Policy_With_Missing_Token_TC(Object[] data){
    /*    Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateTaxPolicies_TC=new Policies_API();
        UpdateTaxPolicies_TC.Update_Policy_With_Missing_Token_Rq(data);
        UpdateTaxPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateTaxPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateTaxPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC033 - Perform Update Tax Policy With Invalid/Expired Token",dataProvider = "Valid_data_Update_Tax_Policy")
    @Story("Updating Tax Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Tax_Policy_With_InValid_Token_TC(Object[] data){
    /*    Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateTaxPolicies_TC=new Policies_API();
        UpdateTaxPolicies_TC.Update_Policy_With_InValid_Token_Rq("123",data);
        UpdateTaxPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateTaxPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
       // UpdateTaxPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Update Usage Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Update_Usage_Policy")
    public Object[][] Valid_Update_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Usage_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Usage_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC012 - Perform Update Valid Usage Policy",dataProvider = "Valid_data_Update_Usage_Policy")
    @Story("Updating Usage Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Update_Usage_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API UpdateUsagePolicies_TC=new Policies_API();
        UpdateUsagePolicies_TC.Update_Policy_Rq(Token,data);
        UpdateUsagePolicies_TC.Check_Valid_Update_policies_status_Code_Response();
        UpdateUsagePolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateUsagePolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC034 - Perform Update Usage Policy With Missing Token",dataProvider = "Valid_data_Update_Usage_Policy")
    @Story("Updating Usage Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Usage_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateUsagePolicies_TC=new Policies_API();
        UpdateUsagePolicies_TC.Update_Policy_With_Missing_Token_Rq(data);
        UpdateUsagePolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateUsagePolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateUsagePolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC035 - Perform Update Usage Policy With Invalid/Expired Token",dataProvider = "Valid_data_Update_Usage_Policy")
    @Story("Updating Usage Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Usage_Policy_With_Invalid_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateUsagePolicies_TC=new Policies_API();
        UpdateUsagePolicies_TC.Update_Policy_With_InValid_Token_Rq("123",data);
        UpdateUsagePolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateUsagePolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
       // UpdateUsagePolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Update Payment Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Update_Payment_Policy")
    public Object[][] Valid_Update_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Payment_Valid_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Payment_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC013 - Perform Update Valid Payment Policy",dataProvider = "Valid_data_Update_Payment_Policy")
    @Story("Updating Payment Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Update_Payment_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API UpdatePaymentPolicies_TC=new Policies_API();
        UpdatePaymentPolicies_TC.Update_Policy_Rq(Token,data);
        UpdatePaymentPolicies_TC.Check_Valid_Update_policies_status_Code_Response();
        UpdatePaymentPolicies_TC.Check_Policy_Response_Time();
        // UpdatePaymentPolicies_TC.Check_All_policies_Valid_Content();
        UpdatePaymentPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC036 - Perform Update Payment Policy With Missing Token",dataProvider = "Valid_data_Update_Payment_Policy")
    @Story("Updating Payment Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Payment_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdatePaymentPolicies_TC=new Policies_API();
        UpdatePaymentPolicies_TC.Update_Policy_With_Missing_Token_Rq(data);
        UpdatePaymentPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdatePaymentPolicies_TC.Check_Policy_Response_Time();
        // UpdatePaymentPolicies_TC.Check_All_policies_Valid_Content();
        UpdatePaymentPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC037 - Perform Update Payment Policy With Invalid/Expired Token",dataProvider = "Valid_data_Update_Payment_Policy")
    @Story("Updating Payment Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_Payment_Policy_With_InValid_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdatePaymentPolicies_TC=new Policies_API();
        UpdatePaymentPolicies_TC.Update_Policy_With_InValid_Token_Rq("123",data);
        UpdatePaymentPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdatePaymentPolicies_TC.Check_Policy_Response_Time();
        // UpdatePaymentPolicies_TC.Check_All_policies_Valid_Content();
       // UpdatePaymentPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Update General Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Update_General_Policy")
    public Object[][] Valid_Update_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_General_Valid_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_General_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC014 - Perform Update Valid General Policy",dataProvider = "Valid_data_Update_General_Policy")
    @Story("Updating General Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Update_General_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API UpdateGeneralPolicies_TC=new Policies_API();
        UpdateGeneralPolicies_TC.Update_Policy_Rq(Token,data);
        UpdateGeneralPolicies_TC.Check_Valid_Update_policies_status_Code_Response();
        UpdateGeneralPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateGeneralPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC038 - Perform Update General Policy With Missing Token",dataProvider = "Valid_data_Update_General_Policy")
    @Story("Updating General Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_General_Policy_With_Missing_Token_TC(Object[] data){
       /* Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateGeneralPolicies_TC=new Policies_API();
        UpdateGeneralPolicies_TC.Update_Policy_With_Missing_Token_Rq(data);
        UpdateGeneralPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateGeneralPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
        UpdateGeneralPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC039 - Perform Update General Policy With InValid/Expired Token",dataProvider = "Valid_data_Update_General_Policy")
    @Story("Updating General Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Update_General_Policy_With_InValid_Token_TC(Object[] data){
      /* Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API UpdateGeneralPolicies_TC=new Policies_API();
        UpdateGeneralPolicies_TC.Update_Policy_With_InValid_Token_Rq("123",data);
        UpdateGeneralPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        UpdateGeneralPolicies_TC.Check_Policy_Response_Time();
        // UpdateCancelPolicies_TC.Check_All_policies_Valid_Content();
     //   UpdateGeneralPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Get Cancel Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Get_Cancel_Policy")
    public Object[][] Valid_Get_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Get_Cancel_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Get_Cancel_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC015 - Perform Get Valid Cancel Policy",dataProvider = "Valid_data_Get_Cancel_Policy")
    @Story("Retrieving Cancel Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Get_Cancel_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetCancelPolicies_TC=new Policies_API();
        GetCancelPolicies_TC.Get_Policy_Rq(Token,data);
        GetCancelPolicies_TC.Check_Valid_Get_policies_status_Code_Response();
        GetCancelPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetCancelPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC040 - Perform Get Cancel Policy With Missing Token",dataProvider = "Valid_data_Get_Cancel_Policy")
    @Story("Retrieving Cancel Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Cancel_Policy_With_Missing_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetCancelPolicies_TC=new Policies_API();
        GetCancelPolicies_TC.Get_Policy_With_Missing_Token_Rq(data);
        GetCancelPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetCancelPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetCancelPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC041 - Perform Get Cancel Policy With InValid/Expired Token",dataProvider = "Valid_data_Get_Cancel_Policy")
    @Story("Retrieving Cancel Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Cancel_Policy_With_InValid_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetCancelPolicies_TC=new Policies_API();
        GetCancelPolicies_TC.Get_Policy_With_InValid_Token_Rq("123",data);
        GetCancelPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetCancelPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
      //  GetCancelPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Get Tax Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Get_Tax_Policy")
    public Object[][] Valid_Get_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Get_Tax_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Get_Tax_Valid_"+(i+1),"ExpectedResult");
        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC016 - Perform Get Valid Tax Policy",dataProvider = "Valid_data_Get_Tax_Policy")
    @Story("Retrieving Tax Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Get_Tax_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetTaxPolicies_TC=new Policies_API();
        GetTaxPolicies_TC.Get_Policy_Rq(Token,data);
        GetTaxPolicies_TC.Check_Valid_Get_policies_status_Code_Response();
        GetTaxPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetTaxPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC042 - Perform Get Tax Policy With Missing Token",dataProvider = "Valid_data_Get_Tax_Policy")
    @Story("Retrieving Tax Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Tax_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetTaxPolicies_TC=new Policies_API();
        GetTaxPolicies_TC.Get_Policy_With_Missing_Token_Rq(data);
        GetTaxPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetTaxPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetTaxPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC043 - Perform Get Tax Policy With InValid/Expired Token",dataProvider = "Valid_data_Get_Tax_Policy")
    @Story("Retrieving Tax Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Tax_Policy_With_InValid_Token_TC(Object[] data){
    /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetTaxPolicies_TC=new Policies_API();
        GetTaxPolicies_TC.Get_Policy_With_InValid_Token_Rq("123",data);
        GetTaxPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetTaxPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
       // GetTaxPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Get Usage Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Get_Usage_Policy")
    public Object[][] Valid_Get_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Get_Usage_Valid_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Get_Usage_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC017 - Perform Get Valid Usage Policy",dataProvider = "Valid_data_Get_Usage_Policy")
    @Story("Retrieving Usage Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Get_Usage_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetUsagePolicies_TC=new Policies_API();
        GetUsagePolicies_TC.Get_Policy_Rq(Token,data);
        GetUsagePolicies_TC.Check_Valid_Get_policies_status_Code_Response();
        GetUsagePolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetUsagePolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC044 - Perform Get Usage Policy With Missing Token",dataProvider = "Valid_data_Get_Usage_Policy")
    @Story("Retrieving Usage Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Usage_Policy_With_Missing_Token_TC(Object[] data){
    /*    Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetUsagePolicies_TC=new Policies_API();
        GetUsagePolicies_TC.Get_Policy_With_Missing_Token_Rq(data);
        GetUsagePolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetUsagePolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetUsagePolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC045 - Perform Get Usage Policy With InValid/Expired Token",dataProvider = "Valid_data_Get_Usage_Policy")
    @Story("Retrieving Usage Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Usage_Policy_With_InValid_Token_TC(Object[] data){
    /*    Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetUsagePolicies_TC=new Policies_API();
        GetUsagePolicies_TC.Get_Policy_With_InValid_Token_Rq("123",data);
        GetUsagePolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetUsagePolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
       // GetUsagePolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Get Payment Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Get_Payment_Policy")
    public Object[][] Valid_Get_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Get_Payment_Valid_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Get_Payment_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC018 - Perform Get Valid Payment Policy",dataProvider = "Valid_data_Get_Payment_Policy")
    @Story("Retrieving Payment Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Get_Payment_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetPaymentPolicies_TC=new Policies_API();
        GetPaymentPolicies_TC.Get_Policy_Rq(Token,data);
        GetPaymentPolicies_TC.Check_Valid_Get_policies_status_Code_Response();
        GetPaymentPolicies_TC.Check_Policy_Response_Time();
        // GetPaymentPolicies_TC.Check_All_policies_Valid_Content();
        GetPaymentPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC046 - Perform Get Payment Policy With Missing Token",dataProvider = "Valid_data_Get_Payment_Policy")
    @Story("Retrieving Payment Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Payment_Policy_With_Missing_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetPaymentPolicies_TC=new Policies_API();
        GetPaymentPolicies_TC.Get_Policy_With_Missing_Token_Rq(data);
        GetPaymentPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetPaymentPolicies_TC.Check_Policy_Response_Time();
        // GetPaymentPolicies_TC.Check_All_policies_Valid_Content();
        GetPaymentPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC047 - Perform Get Payment Policy With Invalid/Expired Token",dataProvider = "Valid_data_Get_Payment_Policy")
    @Story("Retrieving Payment Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_Payment_Policy_With_InValid_Token_TC(Object[] data){
      /*  Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetPaymentPolicies_TC=new Policies_API();
        GetPaymentPolicies_TC.Get_Policy_With_InValid_Token_Rq("123",data);
        GetPaymentPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetPaymentPolicies_TC.Check_Policy_Response_Time();
        // GetPaymentPolicies_TC.Check_All_policies_Valid_Content();
       // GetPaymentPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    /////////////////////// Test Case for Get General Policies //////////////////////////////////////////
    @DataProvider (name = "Valid_data_Get_General_Policy")
    public Object[][] Valid_Get_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Get_General_Valid_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Get_General_Valid_"+(i+1),"ExpectedResult");

        }
        return data;
    }
    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC019 - Perform Get Valid General Policy",dataProvider = "Valid_data_Get_General_Policy")
    @Story("Retrieving General Policy")
    @Severity(SeverityLevel.CRITICAL)
    public void Valid_Get_General_Policy_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API GetGeneralPolicies_TC=new Policies_API();
        GetGeneralPolicies_TC.Get_Policy_Rq(Token,data);
        GetGeneralPolicies_TC.Check_Valid_Get_policies_status_Code_Response();
        GetGeneralPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetGeneralPolicies_TC.Check_Policy_Response_Valid_Schema(data[1].toString());
    }
////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC048 - Perform Get General Policy With Missing Token",dataProvider = "Valid_data_Get_General_Policy")
    @Story("Retrieving General Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_General_Policy_With_Missing_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetGeneralPolicies_TC=new Policies_API();
        GetGeneralPolicies_TC.Get_Policy_With_Missing_Token_Rq(data);
        GetGeneralPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetGeneralPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
        GetGeneralPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC049 - Perform Get General Policy With InValid/Expired Token",dataProvider = "Valid_data_Get_General_Policy")
    @Story("Retrieving General Policy")
    @Severity(SeverityLevel.NORMAL)
    public void InValid_Get_General_Policy_With_InValid_Token_TC(Object[] data){
     /*   Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();
*/
        Policies_API GetGeneralPolicies_TC=new Policies_API();
        GetGeneralPolicies_TC.Get_Policy_With_InValid_Token_Rq("123",data);
        GetGeneralPolicies_TC.Check_Unauthorized_policy_status_Code_Response();
        GetGeneralPolicies_TC.Check_Policy_Response_Time();
        // GetCancelPolicies_TC.Check_All_policies_Valid_Content();
       // GetGeneralPolicies_TC.Check_policy_Response_Unauthorized_Schema();
    }

    
    ///////////////////////////////////////// Missing Keys Test Cases for Add Policy /////////////////////////////////////
    public Object[][] Missing_Add_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Cancel_Missing_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Add_Cancel_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] Missing_Add_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Tax_Missing_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Tax_Missing_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] Missing_Add_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Usage_Missing_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Usage_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] Missing_Add_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Payment_Missing_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Payment_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] Missing_Add_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_General_Missing_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_General_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "Missing_data_add_Policy")
    public Object[][] Missing_data_add_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(Missing_Add_Cancel_Policy()));
        result.addAll(Arrays.asList(Missing_Add_Tax_Policy()));
        result.addAll(Arrays.asList(Missing_Add_Usage_Policy()));
        result.addAll(Arrays.asList(Missing_Add_Payment_Policy()));
        result.addAll(Arrays.asList(Missing_Add_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC050 - Perform Add Policy with Missing Keys",dataProvider = "Missing_data_add_Policy")
    @Story("Adding Policy with Missing keys")
    @Severity(SeverityLevel.CRITICAL)
    public void Add_Policy_With_Missing_Keys_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Add_Policy_With_Invalid_Input_Rq(Token,data);
        Policies_TC.Check_Validation_Error_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_Validation_Error_Schema();
    }

//////////////////////////////Missing keys Test Cases for Update Policy///////////////////

    public Object[][] Missing_Update_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Cancel_Missing_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Update_Cancel_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] Missing_Update_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Tax_Missing_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Tax_Missing_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] Missing_Update_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Usage_Missing_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Usage_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] Missing_Update_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Payment_Missing_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Payment_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] Missing_Update_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_General_Missing_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_General_Missing_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "Missing_data_Update_Policy")
    public Object[][] Missing_data_Update_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(Missing_Update_Cancel_Policy()));
        result.addAll(Arrays.asList(Missing_Update_Tax_Policy()));
        result.addAll(Arrays.asList(Missing_Update_Usage_Policy()));
        result.addAll(Arrays.asList(Missing_Update_Payment_Policy()));
        result.addAll(Arrays.asList(Missing_Update_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC051 - Perform Update Policy with Missing Keys",dataProvider = "Missing_data_Update_Policy")
    @Story("Updating Policy with Missing keys")
    @Severity(SeverityLevel.CRITICAL)
    public void Update_Policy_With_Missing_Keys_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Update_Policy_With_Invalid_Input_Rq(Token,data);
        Policies_TC.Check_Validation_Error_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        Policies_TC.Check_policy_Content(data[data.length-1].toString());
        Policies_TC.Check_policy_Response_Validation_Error_Schema();
    }


    ///////////////////////////////////////// InValid Data Test Cases for Add Policy/////////////////////////////////////
    public Object[][] InValid_Add_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Cancel_InValid_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Add_Cancel_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] InValid_Add_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Tax_InValid_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Tax_InValid_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] InValid_Add_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Usage_InValid_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Usage_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] InValid_Add_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Payment_InValid_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Payment_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] InValid_Add_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_General_InValid_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_General_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "InValid_data_add_Policy")
    public Object[][] InValid_data_add_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(InValid_Add_Cancel_Policy()));
        result.addAll(Arrays.asList(InValid_Add_Tax_Policy()));
        result.addAll(Arrays.asList(InValid_Add_Usage_Policy()));
        result.addAll(Arrays.asList(InValid_Add_Payment_Policy()));
        result.addAll(Arrays.asList(InValid_Add_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC052 - Perform Add Policy with InValid data",dataProvider = "InValid_data_add_Policy")
    @Story("Adding Policy with InValid data")
    @Severity(SeverityLevel.CRITICAL)
    public void Add_Policy_With_InValid_data_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Add_Policy_With_Invalid_Input_Rq(Token,data);
        Policies_TC.Check_Validation_Error_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
      //  Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_Validation_Error_Schema();
    }

    ///////////////////////////////////////// InValid Data Test Cases for Update Policy /////////////////////////////////////
    public Object[][] InValid_Update_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Cancel_InValid_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Update_Cancel_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] InValid_Update_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Tax_InValid_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Tax_InValid_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] InValid_Update_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Usage_InValid_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Usage_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] InValid_Update_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Payment_InValid_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Payment_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] InValid_Update_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_General_InValid_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_General_InValid_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "InValid_data_Update_Policy")
    public Object[][] InValid_data_Update_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(InValid_Update_Cancel_Policy()));
        result.addAll(Arrays.asList(InValid_Update_Tax_Policy()));
        result.addAll(Arrays.asList(InValid_Update_Usage_Policy()));
        result.addAll(Arrays.asList(InValid_Update_Payment_Policy()));
        result.addAll(Arrays.asList(InValid_Update_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC053 - Perform Update Policy with InValid data",dataProvider = "InValid_data_Update_Policy")
    @Story("Updating Policy with InValid data")
    @Severity(SeverityLevel.CRITICAL)
    public void Update_Policy_With_InValid_data_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Update_Policy_With_Invalid_Input_Rq(Token,data);
        Policies_TC.Check_Validation_Error_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        //  Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_Validation_Error_Schema();
    }

    ///////////////////////////////////////// Not Accepted Data Data Test Cases for Add Policy/////////////////////////////////////
    public Object[][] NotAccepted_Add_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Cancel_NotAccepted_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotAccepted_Add_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Tax_NotAccepted_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotAccepted_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] NotAccepted_Add_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Usage_NotAccepted_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotAccepted_Add_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Payment_NotAccepted_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotAccepted_Add_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_General_NotAccepted_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_General_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "NotAccepted_data_add_Policy")
    public Object[][] NotAccepted_data_add_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(NotAccepted_Add_Cancel_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Add_Tax_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Add_Usage_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Add_Payment_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Add_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC054 - Perform Add Policy with NotAccepted data",dataProvider = "NotAccepted_data_add_Policy")
    @Story("Adding Policy with NotAccepted data")
    @Severity(SeverityLevel.CRITICAL)
    public void Add_Policy_With_NotAccepted_data_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Add_Policy_With_NotAccepted_Input_Rq(Token,data);
        Policies_TC.Check_Validation_NotAccepted_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        //  Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_NotAccepted_Error_Schema();
    }

    ///////////////////////////////////////// Not Accepted Data Test Cases for Update Policy /////////////////////////////////////
    public Object[][] NotAccepted_Update_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Cancel_NotAccepted_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotAccepted_Update_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Tax_NotAccepted_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotAccepted_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] NotAccepted_Update_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Usage_NotAccepted_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotAccepted_Update_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Payment_NotAccepted_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotAccepted_Update_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_General_NotAccepted_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_General_NotAccepted_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "NotAccepted_data_Update_Policy")
    public Object[][] NotAccepted_data_Update_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(NotAccepted_Update_Cancel_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Update_Tax_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Update_Usage_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Update_Payment_Policy()));
        result.addAll(Arrays.asList(NotAccepted_Update_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC055 - Perform Update Policy with NotAccepted data",dataProvider = "NotAccepted_data_Update_Policy")
    @Story("Updating Policy with NotAccepted data")
    @Severity(SeverityLevel.CRITICAL)
    public void Update_Policy_With_NotAccepted_data_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Update_Policy_With_NotAccepted_Input_Rq(Token,data);
        Policies_TC.Check_Validation_NotAccepted_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        //  Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_NotAccepted_Error_Schema();
    }



    ///////////////////////////////////////// Not Accepted Data Data Test Cases for Add Policy/////////////////////////////////////
    public Object[][] NotFound_Add_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Cancel_NotFound_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Add_Cancel_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotFound_Add_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Tax_NotFound_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Tax_NotFound_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] NotFound_Add_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Usage_NotFound_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Usage_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotFound_Add_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_Payment_NotFound_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Add_Payment_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotFound_Add_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Add_General_NotFound_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Add_General_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "NotFound_data_add_Policy")
    public Object[][] NotFound_data_add_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(NotFound_Add_Cancel_Policy()));
        result.addAll(Arrays.asList(NotFound_Add_Tax_Policy()));
        result.addAll(Arrays.asList(NotFound_Add_Usage_Policy()));
        result.addAll(Arrays.asList(NotFound_Add_Payment_Policy()));
        result.addAll(Arrays.asList(NotFound_Add_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC056 - Perform Add Policy with NotFound data",dataProvider = "NotFound_data_add_Policy")
    @Story("Adding Policy with NotFound data")
    @Severity(SeverityLevel.CRITICAL)
    public void Add_Policy_With_NotFound_data_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Add_Policy_With_NotFound_Input_Rq(Token,data);
        Policies_TC.Check_Validation_NotFound_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        //  Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_NotFound_Error_Schema();
    }

    ///////////////////////////////////////// Not Accepted Data Test Cases for Update Policy /////////////////////////////////////
    public Object[][] NotFound_Update_Cancel_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Cancel_NotFound_");
        Object[][] data =new Object[dataRowsNumber][ 12];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"TC_Type");
            data[i][1]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"APIName");
            data[i][2]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"nameArabic");
            data[i][3]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"nameEnglish");
            data[i][4]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"chargeUnit");
            data[i][7]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"deadline");
            data[i][8]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"chargeType");
            data[i][9]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"chargeValue");
            data[i][10]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"id");
            data[i][11]= testDataReader.getCellData("Policy_TestData","Update_Cancel_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotFound_Update_Tax_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Tax_NotFound_");
        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"chargeType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"chargeValue");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Tax_NotFound_"+(i+1),"ExpectedResult");
        }
        return data;
    }

    public Object[][] NotFound_Update_Usage_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Usage_NotFound_");
        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Usage_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotFound_Update_Payment_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_Payment_NotFound_");

        Object[][] data =new Object[dataRowsNumber][ 10];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"refundType");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"cancellationPolicyId");
            data[i][8]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"id");
            data[i][9]=testDataReader.getCellData("Policy_TestData","Update_Payment_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    public Object[][] NotFound_Update_General_Policy(){
        int dataRowsNumber = testDataReader2.CountRowsHasSpecificText("Policy_TestData","Update_General_NotFound_");

        Object[][] data =new Object[dataRowsNumber][ 8];
        for (int i=0;i<dataRowsNumber;i++)
        {
            data[i][0]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"TC_Type");
            data[i][1]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"APIName");
            data[i][2]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"nameArabic");
            data[i][3]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"nameEnglish");
            data[i][4]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"descriptionArabic");
            data[i][5]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"descriptionEnglish");
            data[i][6]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"id");
            data[i][7]=testDataReader.getCellData("Policy_TestData","Update_General_NotFound_"+(i+1),"ExpectedResult");

        }
        return data;
    }

    @DataProvider(name = "NotFound_data_Update_Policy")
    public Object[][] NotFound_data_Update_Policy() {
        List<Object[]> result = Lists.newArrayList();
        result.addAll(Arrays.asList(NotFound_Update_Cancel_Policy()));
        result.addAll(Arrays.asList(NotFound_Update_Tax_Policy()));
        result.addAll(Arrays.asList(NotFound_Update_Usage_Policy()));
        result.addAll(Arrays.asList(NotFound_Update_Payment_Policy()));
        result.addAll(Arrays.asList(NotFound_Update_General_Policy()));
        return result.toArray(new Object[result.size()][]);
    }

    @SuppressWarnings("TestDataSupplier")
    @Test(description = "TC057 - Perform Update Policy with NotFound data",dataProvider = "NotFound_data_Update_Policy")
    @Story("Updating Policy with NotFound data")
    @Severity(SeverityLevel.CRITICAL)
    public void Update_Policy_With_NotFound_data_TC(Object[] data){
        Token_API Token_TC=new Token_API();
        Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
        String Token =Token_TC.Get_Valid_Access_Token();

        Policies_API Policies_TC=new Policies_API();
        Policies_TC.Update_Policy_With_NotFound_Input_Rq(Token,data);
        Policies_TC.Check_Validation_NotFound_policy_status_Code_Response();
        Policies_TC.Check_Policy_Response_Time();
        //  Policies_TC.Check_policy_Content(data[data.length-1].toString());
        // AddCancelPolicies_TC.Check_All_policies_Valid_Content();
        Policies_TC.Check_policy_Response_NotFound_Error_Schema();
    }

}
