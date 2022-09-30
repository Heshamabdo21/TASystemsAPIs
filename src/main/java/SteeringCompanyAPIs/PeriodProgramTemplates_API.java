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


public class PeriodProgramTemplates_API {


    String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;
   
    public void GET_all_active_PeriodProgramTemplates_Rq(String TokenValue) {
        String PeriodProgramTemplates_Path = "/periodProgramTemplates";

    	SHAFT.API PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
    
    	PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgramTemplates_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgramTemplates_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgramTemplates_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgramTemplates_ResponseBody = PeriodProgramTemplates_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgramTemplates_ResponseBody).contains("content").perform();
       
        PeriodProgramTemplates_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema("PeriodProgramTemplates_schema.json").perform();
        
        System.out.println("PeriodProgramTemplates Response Body  : - "+PeriodProgramTemplates_ResponseBody+"   -E");

        long responseTime = PeriodProgramTemplates_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
   
    public void GET_PeriodProgramTemplates_by_PeriodProgramTemplates_id_Rq(String TokenValue,String PeriodProgramTemplatesID) {        
        String PeriodProgramTemplates_Path = "/periodProgramTemplates/"+PeriodProgramTemplatesID;
    	SHAFT.API PeriodProgramTemplates_api = new SHAFT.API(BaseURL);

    
    	PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgramTemplates_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgramTemplates_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgramTemplates_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgramTemplates_ResponseBody = PeriodProgramTemplates_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgramTemplates_ResponseBody).contains("programMechanism").perform();
       
        PeriodProgramTemplates_api.assertThatResponse().extractedJsonValue("programMechanism").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema("PeriodProgramTemplatesByID_schema.json").perform();
        
        System.out.println("PeriodProgramTemplates Response Body  : - "+PeriodProgramTemplates_ResponseBody+"   -E");

        long responseTime = PeriodProgramTemplates_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
 
}