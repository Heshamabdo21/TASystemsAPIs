package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.API;
import com.shaft.api.*;
import com.shaft.api.RequestBuilder.AuthenticationType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Lookups_API {

    String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;
   
    public void GET_all_cities_Lookups_Rq(String TokenValue) {
        String Lookup_cities_Path = "/lookups/cities";

    	SHAFT.API Lookups_cities_api = new SHAFT.API(BaseURL);
    
    	Lookups_cities_api.get(Lookup_cities_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_cities_Response = Lookups_cities_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_cities_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_cities_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_cities_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_cities_ResponseBody = Lookups_cities_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_cities_ResponseBody).contains("content").perform();
       
        Lookups_cities_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_cities_api.assertThatResponse().matchesSchema("Lookups_cities_schema.json").perform();
        
        System.out.println("Lookups Cities Response Body  : - "+Lookups_cities_ResponseBody+"   -E");

        long responseTime = Lookups_cities_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
   
    public void GET_all_cities_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
        String Lookup_cities_Path = "/lookups/cities";
    	SHAFT.API Lookups_cities_api = new SHAFT.API(BaseURL);

    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_cities_api.get(Lookup_cities_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_cities_Response = Lookups_cities_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_cities_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_cities_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_cities_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_cities_ResponseBody = Lookups_cities_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_cities_ResponseBody).contains("content").perform();
       
        Lookups_cities_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_cities_api.assertThatResponse().matchesSchema("Lookups_cities_schema.json").perform();
        
        System.out.println("Lookups Cities Response Body  : - "+Lookups_cities_ResponseBody+"   -E");

        long responseTime = Lookups_cities_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_hotels_Lookups_Rq(String TokenValue) {
        String Lookup_hotels_Path = "/lookups/hotels";

    	SHAFT.API Lookups_hotels_api = new SHAFT.API(BaseURL);
    	Lookups_hotels_api.get(Lookup_hotels_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_hotels_Response = Lookups_hotels_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_hotels_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_hotels_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_hotels_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_hotels_ResponseBody = Lookups_hotels_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_hotels_ResponseBody).contains("content").perform();
       
        Lookups_hotels_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_hotels_api.assertThatResponse().matchesSchema("Lookups_hotels_schema.json").perform();
        
        System.out.println("Lookups hotels Response Body  : - "+Lookups_hotels_ResponseBody+"   -E");

        long responseTime = Lookups_hotels_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_hotels_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_hotels_Path = "/lookups/hotels";
    	SHAFT.API Lookups_hotels_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_hotels_api.get(Lookup_hotels_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_hotels_Response = Lookups_hotels_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_hotels_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_hotels_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_hotels_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_hotels_ResponseBody = Lookups_hotels_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_hotels_ResponseBody).contains("content").perform();
        
         Lookups_hotels_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_hotels_api.assertThatResponse().matchesSchema("Lookups_hotels_schema.json").perform();
         
         System.out.println("Lookups hotels Response Body  : - "+Lookups_hotels_ResponseBody+"   -E");

         long responseTime = Lookups_hotels_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_sectors_Lookups_Rq(String TokenValue) {
        String Lookup_sectors_Path = "/lookups/sectors";

    	SHAFT.API Lookups_sectors_api = new SHAFT.API(BaseURL);
    	Lookups_sectors_api.get(Lookup_sectors_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_sectors_Response = Lookups_sectors_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_sectors_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_sectors_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_sectors_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_sectors_ResponseBody = Lookups_sectors_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_sectors_ResponseBody).contains("content").perform();
       
        Lookups_sectors_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_sectors_api.assertThatResponse().matchesSchema("Lookups_sectors_schema.json").perform();
        
        System.out.println("Lookups sectors Response Body  : - "+Lookups_sectors_ResponseBody+"   -E");

        long responseTime = Lookups_sectors_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_sectors_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_sectors_Path = "/lookups/sectors";
    	SHAFT.API Lookups_sectors_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_sectors_api.get(Lookup_sectors_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_sectors_Response = Lookups_sectors_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_sectors_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_sectors_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_sectors_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_sectors_ResponseBody = Lookups_sectors_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_sectors_ResponseBody).contains("content").perform();
        
         Lookups_sectors_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_sectors_api.assertThatResponse().matchesSchema("Lookups_sectors_schema.json").perform();
         
         System.out.println("Lookups sectors Response Body  : - "+Lookups_sectors_ResponseBody+"   -E");

         long responseTime = Lookups_sectors_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_ports_Lookups_Rq(String TokenValue) {
        String Lookup_ports_Path = "/lookups/ports";

    	SHAFT.API Lookups_ports_api = new SHAFT.API(BaseURL);
    	Lookups_ports_api.get(Lookup_ports_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_ports_Response = Lookups_ports_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_ports_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_ports_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_ports_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_ports_ResponseBody = Lookups_ports_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_ports_ResponseBody).contains("content").perform();
       
        Lookups_ports_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_ports_api.assertThatResponse().matchesSchema("Lookups_ports_schema.json").perform();
        
        System.out.println("Lookups ports Response Body  : - "+Lookups_ports_ResponseBody+"   -E");

        long responseTime = Lookups_ports_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_ports_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_ports_Path = "/lookups/ports";
    	SHAFT.API Lookups_ports_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_ports_api.get(Lookup_ports_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_ports_Response = Lookups_ports_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_ports_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_ports_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_ports_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_ports_ResponseBody = Lookups_ports_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_ports_ResponseBody).contains("content").perform();
        
         Lookups_ports_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_ports_api.assertThatResponse().matchesSchema("Lookups_ports_schema.json").perform();
         
         System.out.println("Lookups ports Response Body  : - "+Lookups_ports_ResponseBody+"   -E");

         long responseTime = Lookups_ports_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_terminals_Lookups_Rq(String TokenValue) {
        String Lookup_terminals_Path = "/lookups/terminals";

    	SHAFT.API Lookups_terminals_api = new SHAFT.API(BaseURL);
    	Lookups_terminals_api.get(Lookup_terminals_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_terminals_Response = Lookups_terminals_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_terminals_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_terminals_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_terminals_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_terminals_ResponseBody = Lookups_terminals_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_terminals_ResponseBody).contains("content").perform();
       
        Lookups_terminals_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_terminals_api.assertThatResponse().matchesSchema("Lookups_terminals_schema.json").perform();
        
        System.out.println("Lookups terminals Response Body  : - "+Lookups_terminals_ResponseBody+"   -E");

        long responseTime = Lookups_terminals_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_terminals_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_terminals_Path = "/lookups/terminals";
    	SHAFT.API Lookups_terminals_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_terminals_api.get(Lookup_terminals_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_terminals_Response = Lookups_terminals_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_terminals_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_terminals_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_terminals_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_terminals_ResponseBody = Lookups_terminals_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_terminals_ResponseBody).contains("content").perform();
        
         Lookups_terminals_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_terminals_api.assertThatResponse().matchesSchema("Lookups_terminals_schema.json").perform();
         
         System.out.println("Lookups terminals Response Body  : - "+Lookups_terminals_ResponseBody+"   -E");

         long responseTime = Lookups_terminals_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_routes_Lookups_Rq(String TokenValue) {
        String Lookup_routes_Path = "/lookups/routes";

    	SHAFT.API Lookups_routes_api = new SHAFT.API(BaseURL);
    	Lookups_routes_api.get(Lookup_routes_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_routes_Response = Lookups_routes_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_routes_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_routes_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_routes_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_routes_ResponseBody = Lookups_routes_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_routes_ResponseBody).contains("content").perform();
       
        Lookups_routes_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_routes_api.assertThatResponse().matchesSchema("Lookups_routes_schema.json").perform();
        
        System.out.println("Lookups routes Response Body  : - "+Lookups_routes_ResponseBody+"   -E");

        long responseTime = Lookups_routes_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_routes_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_routes_Path = "/lookups/routes";
    	SHAFT.API Lookups_routes_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_routes_api.get(Lookup_routes_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_routes_Response = Lookups_routes_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_routes_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_routes_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_routes_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_routes_ResponseBody = Lookups_routes_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_routes_ResponseBody).contains("content").perform();
        
         Lookups_routes_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_routes_api.assertThatResponse().matchesSchema("Lookups_routes_schema.json").perform();
         
         System.out.println("Lookups routes Response Body  : - "+Lookups_routes_ResponseBody+"   -E");

         long responseTime = Lookups_routes_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_vehicleTypes_Lookups_Rq(String TokenValue) {
        String Lookup_vehicleTypes_Path = "/lookups/vehicleTypes";

    	SHAFT.API Lookups_vehicleTypes_api = new SHAFT.API(BaseURL);
    	Lookups_vehicleTypes_api.get(Lookup_vehicleTypes_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_vehicleTypes_Response = Lookups_vehicleTypes_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_vehicleTypes_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_vehicleTypes_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_vehicleTypes_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_vehicleTypes_ResponseBody = Lookups_vehicleTypes_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_vehicleTypes_ResponseBody).contains("content").perform();
       
        Lookups_vehicleTypes_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_vehicleTypes_api.assertThatResponse().matchesSchema("Lookups_vehicleTypes_schema.json").perform();
        
        System.out.println("Lookups vehicleTypes Response Body  : - "+Lookups_vehicleTypes_ResponseBody+"   -E");

        long responseTime = Lookups_vehicleTypes_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_vehicleTypes_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_vehicleTypes_Path = "/lookups/vehicleTypes";
    	SHAFT.API Lookups_vehicleTypes_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_vehicleTypes_api.get(Lookup_vehicleTypes_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_vehicleTypes_Response = Lookups_vehicleTypes_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_vehicleTypes_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_vehicleTypes_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_vehicleTypes_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_vehicleTypes_ResponseBody = Lookups_vehicleTypes_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_vehicleTypes_ResponseBody).contains("content").perform();
        
         Lookups_vehicleTypes_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_vehicleTypes_api.assertThatResponse().matchesSchema("Lookups_vehicleTypes_schema.json").perform();
         
         System.out.println("Lookups vehicleTypes Response Body  : - "+Lookups_vehicleTypes_ResponseBody+"   -E");

         long responseTime = Lookups_vehicleTypes_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_all_vehicleCategories_Lookups_Rq(String TokenValue) {
        String Lookup_vehicleCategories_Path = "/lookups/vehicleCategories";

    	SHAFT.API Lookups_vehicleCategories_api = new SHAFT.API(BaseURL);
    	Lookups_vehicleCategories_api.get(Lookup_vehicleCategories_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_vehicleCategories_Response = Lookups_vehicleCategories_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_vehicleCategories_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_vehicleCategories_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_vehicleCategories_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_vehicleCategories_ResponseBody = Lookups_vehicleCategories_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_vehicleCategories_ResponseBody).contains("content").perform();
       
        Lookups_vehicleCategories_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
        
        Lookups_vehicleCategories_api.assertThatResponse().matchesSchema("Lookups_vehicleCategories_schema.json").perform();
        
        System.out.println("Lookups vehicleCategories Response Body  : - "+Lookups_vehicleCategories_ResponseBody+"   -E");

        long responseTime = Lookups_vehicleCategories_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
       
    public void GET_all_vehicleCategories_Lookups_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
    
    	String Lookup_vehicleCategories_Path = "/lookups/vehicleCategories";
    	SHAFT.API Lookups_vehicleCategories_api = new SHAFT.API(BaseURL);
    	
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_vehicleCategories_api.get(Lookup_vehicleCategories_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

    	 Response Lookups_vehicleCategories_Response = Lookups_vehicleCategories_api.getResponse();
         
         SHAFT.Validations.assertThat().number(Lookups_vehicleCategories_Response.getStatusCode()).isEqualTo(200).perform();
         SHAFT.Validations.verifyThat().number(Lookups_vehicleCategories_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(Lookups_vehicleCategories_Response.getTime()).isLessThanOrEquals(10000).perform();
         String Lookups_vehicleCategories_ResponseBody = Lookups_vehicleCategories_Response.getBody().asString();
         SHAFT.Validations.assertThat().object(Lookups_vehicleCategories_ResponseBody).contains("content").perform();
        
         Lookups_vehicleCategories_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
         
         Lookups_vehicleCategories_api.assertThatResponse().matchesSchema("Lookups_vehicleCategories_schema.json").perform();
         
         System.out.println("Lookups vehicleCategories Response Body  : - "+Lookups_vehicleCategories_ResponseBody+"   -E");

         long responseTime = Lookups_vehicleCategories_api.getResponse().getTime();
         SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
         SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

 
}