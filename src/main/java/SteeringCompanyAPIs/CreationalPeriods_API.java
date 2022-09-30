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


public class CreationalPeriods_API {

    String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;
   
    public void GET_all_active_CreationalPeriods_Rq(String TokenValue) {
        String CreationalPeriods_Path = "/creationalPeriods";

    	SHAFT.API CreationalPeriods_api = new SHAFT.API(BaseURL);
    
    	CreationalPeriods_api.get(CreationalPeriods_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response CreationalPeriods_Response = CreationalPeriods_api.getResponse();
        
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(CreationalPeriods_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(CreationalPeriods_Response.getTime()).isLessThanOrEquals(10000).perform();
        String CreationalPeriods_ResponseBody = CreationalPeriods_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(CreationalPeriods_ResponseBody).contains("content").perform();
       
        CreationalPeriods_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        CreationalPeriods_api.assertThatResponse().matchesSchema("CreationalPeriods_schema.json").perform();
        
        System.out.println("CreationalPeriods Response Body  : - "+CreationalPeriods_ResponseBody+"   -E");

        long responseTime = CreationalPeriods_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
   
    public void GET_CreationalPeriods_by_CreationalPeriods_id_Rq(String TokenValue,String CreationalPeriodsID) {        
        String CreationalPeriods_Path = "/creationalPeriods/"+CreationalPeriodsID;
    	SHAFT.API CreationalPeriods_api = new SHAFT.API(BaseURL);

    
    	CreationalPeriods_api.get(CreationalPeriods_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response CreationalPeriods_Response = CreationalPeriods_api.getResponse();
        
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(CreationalPeriods_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(CreationalPeriods_Response.getTime()).isLessThanOrEquals(10000).perform();
        String CreationalPeriods_ResponseBody = CreationalPeriods_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(CreationalPeriods_ResponseBody).contains("periodProgramTemplates").perform();
       
        CreationalPeriods_api.assertThatResponse().extractedJsonValue("periodProgramTemplates").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        CreationalPeriods_api.assertThatResponse().matchesSchema("CreationalPeriodsByID_schema.json").perform();
        
        System.out.println("CreationalPeriods Response Body  : - "+CreationalPeriods_ResponseBody+"   -E");

        long responseTime = CreationalPeriods_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
 
}