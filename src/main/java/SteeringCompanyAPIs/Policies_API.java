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


public class Policies_API {

    String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;
   
    public void GET_all_Lookups_policies_Rq(String TokenValue) {
        String Lookup_policies_Path = "/policies";

    	SHAFT.API Lookups_policies_api = new SHAFT.API(BaseURL);
    
    	Lookups_policies_api.get(Lookup_policies_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_policies_Response = Lookups_policies_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_policies_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_policies_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_policies_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_policies_ResponseBody = Lookups_policies_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_policies_ResponseBody).contains("cancellationPolicies").perform();
       
        Lookups_policies_api.assertThatResponse().extractedJsonValue("cancellationPolicies").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        Lookups_policies_api.assertThatResponse().matchesSchema("Lookups_policies_schema.json").perform();
     
    }
   
    public void GET_all_Lookups_policies_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
        String Lookup_policies_Path = "/policies";
    	SHAFT.API Lookups_policies_api = new SHAFT.API(BaseURL);

    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	Lookups_policies_api.get(Lookup_policies_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Lookups_policies_Response = Lookups_policies_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Lookups_policies_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Lookups_policies_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Lookups_policies_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Lookups_policies_ResponseBody = Lookups_policies_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_policies_ResponseBody).contains("cancellationPolicies").perform();
       
        Lookups_policies_api.assertThatResponse().extractedJsonValue("cancellationPolicies").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        Lookups_policies_api.assertThatResponse().matchesSchema("Lookups_policies_schema.json").perform();
        
        System.out.println("Lookups policies Response Body  : - "+Lookups_policies_ResponseBody+"   -E");

        long responseTime = Lookups_policies_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
 
}