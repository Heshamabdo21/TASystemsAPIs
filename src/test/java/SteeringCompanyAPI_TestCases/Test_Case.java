package SteeringCompanyAPI_TestCases;

import org.testng.annotations.Test;

import SteeringCompanyAPIs.CreationalPeriods_API;
import SteeringCompanyAPIs.Lookups_API;
import SteeringCompanyAPIs.PeriodProgramTemplates_API;
import SteeringCompanyAPIs.Token_API;
import SteeringCompanyAPIs.Policies_API;
import SteeringCompanyAPIs.Reservations_API;

public class Test_Case {

    @Test
    public void Valid_Token_RQ_TC() {
       
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Token_TC.CheckTokenExpiration(Token);
    	Token_TC.CheckTokenISS(Token);
        Token_TC.CheckTokentcId(Token);
        Token_TC.CheckTokenpreferred_username(Token);

    }
    /*
    @Test
    public void Valid_GET_all_cities_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_cities_Lookups_Rq(Token);
    	Lookups_TC.GET_all_cities_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }
   
    @Test
    public void Valid_GET_all_hotels_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_hotels_Lookups_Rq(Token);
    	Lookups_TC.GET_all_hotels_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }
    
    @Test
    public void Valid_GET_all_routes_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_routes_Lookups_Rq(Token);
    	Lookups_TC.GET_all_routes_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_sectors_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_sectors_Lookups_Rq(Token);
    	Lookups_TC.GET_all_sectors_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_ports_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_ports_Lookups_Rq(Token);
    	Lookups_TC.GET_all_ports_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_terminals_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_terminals_Lookups_Rq(Token);
    	Lookups_TC.GET_all_terminals_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_vehicleTypes_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_vehicleTypes_Lookups_Rq(Token);
    	Lookups_TC.GET_all_vehicleTypes_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_vehicleCategories_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    	Lookups_TC.GET_all_vehicleCategories_Lookups_Rq(Token);
    	Lookups_TC.GET_all_vehicleCategories_Lookups_by_parameter_Query_Rq(Token,"3","0");
    }

    @Test
    public void Valid_GET_all_policies_Lookups_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Policies_API Policies_TC=new Policies_API();
    	Policies_TC.GET_all_Lookups_policies_Rq(Token);
    	Policies_TC.GET_all_Lookups_policies_by_parameter_Query_Rq(Token,"3","0");
    }
    
    @Test
    public void Valid_GET_all_CreationalPeriods_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	CreationalPeriods_API CreationalPeriods_TC=new CreationalPeriods_API();
    	CreationalPeriods_TC.GET_all_active_CreationalPeriods_Rq(Token);
    	CreationalPeriods_TC.GET_CreationalPeriods_by_CreationalPeriods_id_Rq(Token,"150");
    }
    
    @Test
    public void Valid_GET_all_PeriodProgramTemplates_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	PeriodProgramTemplates_API PeriodProgramTemplates_TC=new PeriodProgramTemplates_API();
    	PeriodProgramTemplates_TC.GET_all_active_PeriodProgramTemplates_Rq(Token);
    	PeriodProgramTemplates_TC.GET_PeriodProgramTemplates_by_PeriodProgramTemplates_id_Rq(Token,"251");
    }
    
    @Test
    public void Valid_GET_all_Reservations_Rq_TC() {
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Reservations_API Reservations_TC=new Reservations_API();
    	Reservations_TC.GET_Reservations_by_Reservations_id_Rq(Token,"6106938");
    }
	*/
}