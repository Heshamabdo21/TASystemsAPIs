package SteeringCompanyAPI_TestCases;

import org.testng.annotations.Test;

import SteeringCompanyAPIs.Lookups_API;
import SteeringCompanyAPIs.Token_API;


public class Test_Case {

    @Test
    public void Valid_Token_RQ_TC() {
       
    	Token_API Token_TC=new Token_API();
    	String Token =Token_TC.POST_TOKEN_Rq();
    	Lookups_API Lookups_TC=new Lookups_API();
    }
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


	
}