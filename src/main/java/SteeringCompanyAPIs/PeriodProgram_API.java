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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PeriodProgram_API {


    String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;
   
    public void GET_all_active_PeriodProgram_Rq(String TokenValue) {
        String PeriodProgram_Path = "/getPeriodPrograms";

    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);
    
    	PeriodProgram_api.get(PeriodProgram_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("content").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgram_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
   
    public void GET_all_active_PeriodProgram_by_Query_param_Rq(String TokenValue,String PageSize,String PageNumber) {
        String PeriodProgram_Path = "/getPeriodPrograms";

    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);

    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));

    	PeriodProgram_api.get(PeriodProgram_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("content").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgram_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }

    public void GET_PeriodProgram_by_PeriodProgram_id_Rq(String TokenValue,String PeriodProgramID) {        
        String PeriodProgram_Path = "/periodPrograms/"+PeriodProgramID;
    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);

    
    	PeriodProgram_api.get(PeriodProgram_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("programMechanism").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("programMechanism").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgramByID_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
    
    public void Add_New_PeriodProgram_without_policies_Rq(String TokenValue) throws JSONException {
        String PeriodProgram_Path = "/periodPrograms";

    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);
    

    	JSONObject PeriodProgramRequestBody = new JSONObject();
        		PeriodProgramRequestBody.put("creationPeriodId", 150);
        		PeriodProgramRequestBody.put("templateId", 251);
        		PeriodProgramRequestBody.put("periodMaxQuotaPerPeriod", 10);
        		PeriodProgramRequestBody.put("minimumModelYear", 2019);
        		PeriodProgramRequestBody.put("maximumModelYear", 2022);
        		PeriodProgramRequestBody.put("minimumSeat", 4);
        		PeriodProgramRequestBody.put("maximumSeat", 4);
        		PeriodProgramRequestBody.put("vehiclePricePer", 4);
        		PeriodProgramRequestBody.put("isActive", true);
        		JSONArray policies = new JSONArray("[]");

        		PeriodProgramRequestBody.put("policies", policies);


    	PeriodProgram_api.post(PeriodProgram_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).
        setRequestBody(PeriodProgramRequestBody).
        setTargetStatusCode(201).
        setContentType(ContentType.JSON).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("content").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgram_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
      
    public void Add_New_PeriodProgram_with_policies_Rq(String TokenValue) throws JSONException {
        String PeriodProgram_Path = "/periodPrograms";

    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);
    

    	JSONObject PeriodProgramRequestBody = new JSONObject();
        		PeriodProgramRequestBody.put("creationPeriodId", 150);
        		PeriodProgramRequestBody.put("templateId", 251);
        		PeriodProgramRequestBody.put("periodMaxQuotaPerPeriod", 10);
        		PeriodProgramRequestBody.put("minimumModelYear", 2019);
        		PeriodProgramRequestBody.put("maximumModelYear", 2022);
        		PeriodProgramRequestBody.put("minimumSeat", 4);
        		PeriodProgramRequestBody.put("maximumSeat", 4);
        		PeriodProgramRequestBody.put("vehiclePricePer", 4);
        		PeriodProgramRequestBody.put("isActive", true);
        		JSONArray policies = new JSONArray("[  \"type\": \"Tax\","
        				+ "            \"id\": 3 },"
        				+ "        {"
        				+ "            \"type\": \"Payment\","
        				+ "            \"id\": 3"
        				+ "        },"
        				+ "        {"
        				+ "            \"type\": \"Cancellation\","
        				+ "            \"id\": 3"
        				+ "        },"
        				+ "        {"
        				+ "            \"type\": \"General\","
        				+ "            \"id\": 3"
        				+ "        },"
        				+ "        {"
        				+ "            \"type\": \"Usage\","
        				+ "            \"id\": 3"
        				+ "        }]");

        		PeriodProgramRequestBody.put("policies", policies);


    	PeriodProgram_api.post(PeriodProgram_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).
        setRequestBody(PeriodProgramRequestBody).
        setTargetStatusCode(201).
        setContentType(ContentType.JSON).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("content").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgram_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
      
    public void Edit_PeriodProgram_without_policies_by_PeriodProgramID_Rq(String TokenValue,String PeriodProgramID) throws JSONException {
        String PeriodProgram_Path = "/periodPrograms/"+PeriodProgramID;

    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);
    

    	JSONObject PeriodProgramRequestBody = new JSONObject();
        		PeriodProgramRequestBody.put("creationPeriodId", 150);
        		PeriodProgramRequestBody.put("templateId", 251);
        		PeriodProgramRequestBody.put("periodMaxQuotaPerPeriod", 10);
        		PeriodProgramRequestBody.put("minimumModelYear", 2019);
        		PeriodProgramRequestBody.put("maximumModelYear", 2022);
        		PeriodProgramRequestBody.put("minimumSeat", 4);
        		PeriodProgramRequestBody.put("maximumSeat", 4);
        		PeriodProgramRequestBody.put("vehiclePricePer", 4);
        		PeriodProgramRequestBody.put("isActive", true);
        		JSONArray policies = new JSONArray("[]");

        		PeriodProgramRequestBody.put("policies", policies);


    	PeriodProgram_api.put(PeriodProgram_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).
        setRequestBody(PeriodProgramRequestBody).
        setTargetStatusCode(201).
        setContentType(ContentType.JSON).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("content").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgram_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
     
    public void Edit_PeriodProgram_with_policies_by_PeriodProgramID_Rq(String TokenValue,String PeriodProgramID) throws JSONException {
        String PeriodProgram_Path = "/periodPrograms/"+PeriodProgramID;

    	SHAFT.API PeriodProgram_api = new SHAFT.API(BaseURL);
    

    	JSONObject PeriodProgramRequestBody = new JSONObject();
        		PeriodProgramRequestBody.put("creationPeriodId", 150);
        		PeriodProgramRequestBody.put("templateId", 251);
        		PeriodProgramRequestBody.put("periodMaxQuotaPerPeriod", 10);
        		PeriodProgramRequestBody.put("minimumModelYear", 2019);
        		PeriodProgramRequestBody.put("maximumModelYear", 2022);
        		PeriodProgramRequestBody.put("minimumSeat", 4);
        		PeriodProgramRequestBody.put("maximumSeat", 4);
        		PeriodProgramRequestBody.put("vehiclePricePer", 4);
        		PeriodProgramRequestBody.put("isActive", true);
        		JSONArray policies = new JSONArray("[  \"type\": \"Tax\","
        				+ "            \"id\": 3 },"
        				+ "        {"
        				+ "            \"type\": \"Payment\","
        				+ "            \"id\": 3"
        				+ "        },"
        				+ "        {"
        				+ "            \"type\": \"Cancellation\","
        				+ "            \"id\": 3"
        				+ "        },"
        				+ "        {"
        				+ "            \"type\": \"General\","
        				+ "            \"id\": 3"
        				+ "        },"
        				+ "        {"
        				+ "            \"type\": \"Usage\","
        				+ "            \"id\": 3"
        				+ "        }]");

        		PeriodProgramRequestBody.put("policies", policies);


    	PeriodProgram_api.post(PeriodProgram_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).
        setRequestBody(PeriodProgramRequestBody).
        setTargetStatusCode(201).
        setContentType(ContentType.JSON).perform();

        
        Response PeriodProgram_Response = PeriodProgram_api.getResponse();
        
        SHAFT.Validations.assertThat().number(PeriodProgram_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgram_Response.getTime()).isLessThanOrEquals(10000).perform();
        String PeriodProgram_ResponseBody = PeriodProgram_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgram_ResponseBody).contains("content").perform();
       
        PeriodProgram_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        PeriodProgram_api.assertThatResponse().matchesSchema("PeriodProgram_schema.json").perform();
        
        System.out.println("PeriodProgram Response Body  : - "+PeriodProgram_ResponseBody+"   -E");

        long responseTime = PeriodProgram_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }


}