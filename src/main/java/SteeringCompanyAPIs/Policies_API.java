package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.API;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.api.*;
import com.shaft.api.RequestBuilder.AuthenticationType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Policies_API {
    ExcelFileManager testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
    String BaseURL = testDataReader.getCellData("API_Data","Steering_Base_URL","Path");

    //String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;
   
    Response All_Policies_Response;
    SHAFT.API All_Policies_api;
   // String All_Policies_Path = "/policies";
    String All_Policies_Path = testDataReader.getCellData("API_Data","GetAllPolicies","Path");
  
    public void GET_All_Policies_Rq(String TokenValue) {
     //   String Lookup_policies_Path = "/policies";
    	 All_Policies_api = new SHAFT.API(BaseURL); 
    	 All_Policies_api.get(All_Policies_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();     
         All_Policies_Response = All_Policies_api.getResponse();
    }
    
    public void Check_Valid_policies_status_Code_Response(){
        SHAFT.Validations.assertThat().number(All_Policies_Response.getStatusCode()).isEqualTo(200).perform();
    }
    
    public void Check_Valid_policies_Response_Time() {
        SHAFT.Validations.verifyThat().number(All_Policies_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(All_Policies_Response.getTime()).isLessThanOrEquals(10000).perform();
    }
    
    public void Check_policies_Valid_Content() {
        String Lookups_policies_ResponseBody = All_Policies_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_policies_ResponseBody).contains("cancellationPolicies").perform();
        All_Policies_api.assertThatResponse().extractedJsonValue("cancellationPolicies").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
    }
    
    public void Check_policies_Response_Valid_Schema() {
        All_Policies_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","GetAllPolicies","Schema")).perform();
     
    }
   
    public void GET_All_Policies_Path_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {        
      //  String Lookup_policies_Path = "/policies";
        All_Policies_api = new SHAFT.API(BaseURL);
    	List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize), 
    			Arrays.asList("page",PageNumber));
    	All_Policies_api.get(All_Policies_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	setParameters(parameters, RestActions.ParametersType.QUERY).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();
    	All_Policies_Response = All_Policies_api.getResponse();
        
    }
 
}