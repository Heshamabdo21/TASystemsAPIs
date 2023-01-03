package SteeringCompanyAPI_TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.shaft.tools.io.ExcelFileManager;

import SteeringCompanyAPIs.Policies_API;
import SteeringCompanyAPIs.Token_API;

public class Test_Policies_Cases {
    
    ExcelFileManager testDataReader ;
    String UserName,Password,InValidUserName,InValidPassword;
   //     String EmptyUserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data3");
  //      String EmptyPassword=testDataReader.getCellData("TokenAPI_TestData","Password","Data3");
     @BeforeClass
     public void Setup_data() {
     testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     UserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data1");
     Password=testDataReader.getCellData("TokenAPI_TestData","Password","Data1");
     InValidUserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data2");
     InValidPassword=testDataReader.getCellData("TokenAPI_TestData","Password","Data2");  
     }

     @DataProvider (name = "Valid_data_for_Cancel_Policy")

     public Object[][] Valid_Cancel_Policy(){
        // int Rows=testDataReader.();
         int Columns=testDataReader.getLastColumnNumber("CancelPolicy_TestData");
         Object data[][]=new Object[2][ Columns];
         for (int i=0;i<2;i++)
         {
             data[i][0]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"TC_Type");
             data[i][1]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"nameArabic");;
             data[i][2]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"nameEnglish");;
             data[i][3]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"descriptionArabic");;
             data[i][4]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"descriptionEnglish");;
             data[i][5]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"chargeUnit");;
             data[i][6]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"deadline");;
             data[i][7]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"chargeType");;
             data[i][8]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"chargeValue");;
             data[i][9]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"id");;
             data[i][10]=testDataReader.getCellData("CancelPolicy_TestData","TC_Valid_"+i,"ExpectedResult");;
         }
         return data;
     }
     @Test(description = "TC001 - Perform Get all Policies API with valid user name and password")
     public void Valid_GET_all_Policies_Rq_TC() {
         Token_API Token_TC=new Token_API();
         Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
         Token_TC.Check_Token_Valid_status_Code_Response();
         String Token =Token_TC.Get_Valid_Access_Token();

         Policies_API GetAllPolicies_TC=new Policies_API();
         GetAllPolicies_TC.GET_All_Policies_Rq(Token);
         GetAllPolicies_TC.Check_Valid_policies_status_Code_Response();
        // GetAllPolicies_TC.Check_Valid_policies_Response_Time();
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
        GetAllPolicies_TC.GET_All_Policies_Path_by_parameter_Query_Rq(Token,"3","0");
        GetAllPolicies_TC.Check_Valid_policies_status_Code_Response();
        GetAllPolicies_TC.Check_Valid_policies_Response_Time();
        GetAllPolicies_TC.Check_policies_Valid_Content();
        GetAllPolicies_TC.Check_policies_Response_Valid_Schema();
    }

}
