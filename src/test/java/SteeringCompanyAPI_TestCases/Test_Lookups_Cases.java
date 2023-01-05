package SteeringCompanyAPI_TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.tools.io.ExcelFileManager;

import SteeringCompanyAPIs.Lookups_API;
import SteeringCompanyAPIs.Token_API;


public class Test_Lookups_Cases {
    
    ExcelFileManager testDataReader ;
    String UserName,Password,InValidUserName,InValidPassword;
       @BeforeClass
     public void Setup_data() {
     testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     UserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data1");
     Password=testDataReader.getCellData("TokenAPI_TestData","Password","Data1");
     InValidUserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data2");
     InValidPassword=testDataReader.getCellData("TokenAPI_TestData","Password","Data2");  
     }
    
   
    
   
@Test(description = "TC001 - Perform Get all cities API with valid user name and password")
    public void Valid_GET_all_cities_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
        Token_TC.Check_Token_Valid_status_Code_Response();
    	String Token =Token_TC.Get_Valid_Access_Token();
    	
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_Valid_all_cities_Lookups_Rq(Token);
    	Lookups_TC.Check_Valid_cities_Lookups_status_Code_Response();
    	Lookups_TC.Check_Valid_cities_Lookups_Response_Time();
    	Lookups_TC.Check_cities_Lookups_Valid_Content();
    	Lookups_TC.Check_cities_Lookups_Response_Valid_Schema();
    	    }

    @Test(description = "TC002 - Perform Get all cities API by parameters Qry with valid user name and password and return with token")
    public void Valid_GET_all_cities_Lookups_by_Qry_Rq_TC() {
    Token_API Token_TC=new Token_API();
    Token_TC.POST_Valid_TOKEN_Rq(UserName,Password);
    Token_TC.Check_Token_Valid_status_Code_Response();
    String Token =Token_TC.Get_Valid_Access_Token();
    
    Lookups_API Lookups_TC=new Lookups_API();
    Lookups_TC.GET_Valid_all_cities_Lookups_by_parameter_Query_Rq(Token,"3","0");
    Lookups_TC.Check_Valid_cities_Lookups_status_Code_Response();
    Lookups_TC.Check_Valid_cities_Lookups_Response_Time();
    Lookups_TC.Check_cities_Lookups_Valid_Content();
    Lookups_TC.Check_cities_Lookups_Response_Valid_Schema();
    
}

}