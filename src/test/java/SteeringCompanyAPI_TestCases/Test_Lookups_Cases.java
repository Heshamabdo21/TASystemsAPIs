package SteeringCompanyAPI_TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.tools.io.ExcelFileManager;

import SteeringCompanyAPIs.CreationalPeriods_API;
import SteeringCompanyAPIs.Lookups_API;
import SteeringCompanyAPIs.PeriodProgramTemplates_API;
import SteeringCompanyAPIs.Token_API;
import SteeringCompanyAPIs.Policies_API;
import SteeringCompanyAPIs.Reservations_API;

public class Test_Lookups_Cases {
    
    ExcelFileManager testDataReader ;
    String KEYCLOAK_HOST,UserName,Password,InValidUserName,InValidPassword;
   //     String EmptyUserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data3");
  //      String EmptyPassword=testDataReader.getCellData("TokenAPI_TestData","Password","Data3");
     @BeforeClass
     public void Setup_data() {
     testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
     //KEYCLOAK_HOST = "https://auth-demo.np.transporticonline.com" ;
     UserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data1");
     Password=testDataReader.getCellData("TokenAPI_TestData","Password","Data1");
     InValidUserName=testDataReader.getCellData("TokenAPI_TestData","UserName","Data2");
     InValidPassword=testDataReader.getCellData("TokenAPI_TestData","Password","Data2");  
     }
    
   
    
   
@Test(description = "TC001 - Peform Get all cities API with valid user name and password")
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

    @Test(description = "TC002 - Peform Get all cities API by parameters Qry with valid user name and password and retun with token")
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

   /*
    @Test
    public void Valid_GET_all_hotels_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_hotels_Lookups_Rq(Token);
    	Lookups_TC.GET_all_hotels_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }
    
    @Test
    public void Valid_GET_all_routes_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_routes_Lookups_Rq(Token);
    	Lookups_TC.GET_all_routes_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_sectors_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_sectors_Lookups_Rq(Token);
    	Lookups_TC.GET_all_sectors_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_ports_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_ports_Lookups_Rq(Token);
    	Lookups_TC.GET_all_ports_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_terminals_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_terminals_Lookups_Rq(Token);
    	Lookups_TC.GET_all_terminals_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_vehicleTypes_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_vehicleTypes_Lookups_Rq(Token);
    	Lookups_TC.GET_all_vehicleTypes_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_vehicleCategories_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_vehicleCategories_Lookups_Rq(Token);
    	Lookups_TC.GET_all_vehicleCategories_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_policies_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Policies_API Policies_TC=new Policies_API();
    	Policies_TC.GET_all_Lookups_policies_Rq(Token);
    	Policies_TC.GET_all_Lookups_policies_by_parameter_Query_Rq(Token,"3","0");
    }
    
    @Test
    public void Valid_GET_all_CreationalPeriods_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	CreationalPeriods_API CreationalPeriods_TC=new CreationalPeriods_API();
    	CreationalPeriods_TC.GET_all_active_CreationalPeriods_Rq(Token);
    	CreationalPeriods_TC.GET_CreationalPeriods_by_CreationalPeriods_id_Rq(Token,"150");
    }
    
    @Test
    public void Valid_GET_all_PeriodProgramTemplates_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	PeriodProgramTemplates_API PeriodProgramTemplates_TC=new PeriodProgramTemplates_API();
    	PeriodProgramTemplates_TC.GET_all_active_PeriodProgramTemplates_Rq(Token);
    	PeriodProgramTemplates_TC.GET_PeriodProgramTemplates_by_PeriodProgramTemplates_id_Rq(Token,"251");
    }
    
    @Test
    public void Valid_GET_all_Reservations_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_Valid_TOKEN_Rq("tokhi","123");
    	Reservations_API Reservations_TC=new Reservations_API();
    	Reservations_TC.GET_Reservations_by_Reservations_id_Rq(Token,"6106938");
    }
	*/
}