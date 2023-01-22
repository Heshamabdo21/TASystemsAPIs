package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.api.*;
import com.shaft.api.RequestBuilder.AuthenticationType;

import com.shaft.tools.io.ExcelFileManager;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;


public class CreationalPeriods_API {
    ExcelFileManager testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
    String BaseURL = testDataReader.getCellData("API_Data","Steering_Base_URL","URL");

    String CreationalPeriods_Path = testDataReader.getCellData("API_Data","CreationalPeriods","URL");
    Response CreationalPeriods_Response;
    SHAFT.API CreationalPeriods_api;
    public void Get_Valid_all_CreationalPeriods_Rq(String TokenValue) {
      //  String CreationalPeriods_Path = "/creationalPeriods";
    	CreationalPeriods_api = new SHAFT.API(BaseURL);
    	CreationalPeriods_api.get(CreationalPeriods_Path).
    	setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(200).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        CreationalPeriods_Response = CreationalPeriods_api.getResponse();

    }
    public void Get_Valid_all_CreationalPeriods_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {
        //  String CreationalPeriods_Path = "/creationalPeriods";
        CreationalPeriods_api = new SHAFT.API(BaseURL);
        List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize),
                Arrays.asList("page",PageNumber));
        CreationalPeriods_api.get(CreationalPeriods_Path).
                setParameters(parameters, RestActions.ParametersType.QUERY).
                setTargetStatusCode(200).
                setAuthentication("","", AuthenticationType.NONE).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        CreationalPeriods_Response = CreationalPeriods_api.getResponse();

    }
    public void Get_InValid_all_CreationalPeriods_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {
        //  String CreationalPeriods_Path = "/creationalPeriods";
        CreationalPeriods_api = new SHAFT.API(BaseURL);
        List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize),
                Arrays.asList("page",PageNumber));
        CreationalPeriods_api.get(CreationalPeriods_Path).
                setParameters(parameters, RestActions.ParametersType.QUERY).
                setTargetStatusCode(422).
                setAuthentication("","", AuthenticationType.NONE).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        CreationalPeriods_Response = CreationalPeriods_api.getResponse();

    }

    public void Get_Valid_CreationalPeriods_by_id_Rq(String TokenValue, String CreationalPeriodsID) {
    	CreationalPeriods_api = new SHAFT.API(BaseURL);
    	CreationalPeriods_api.get(CreationalPeriods_Path+"/"+CreationalPeriodsID).
    	setAuthentication("", "", AuthenticationType.NONE).
                setTargetStatusCode(200).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
    //////////////////////////////////////////////////////////////////////////////////
    public void Get_CreationalPeriods_With_NotFound_by_id_Rq(String TokenValue,String CreationalPeriodsID) {
        CreationalPeriods_api = new SHAFT.API(BaseURL);
        CreationalPeriods_api.get(CreationalPeriods_Path+"/"+CreationalPeriodsID).
                setAuthentication("", "", AuthenticationType.NONE).
                setTargetStatusCode(404).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
    public void Get_CreationalPeriods_With_BadRequest_by_id_Rq(String TokenValue,String CreationalPeriodsID) {
        CreationalPeriods_api = new SHAFT.API(BaseURL);
        if(CreationalPeriodsID.contains(" ")) {
            CreationalPeriodsID = String.valueOf('\u2001');
        }
        CreationalPeriods_api.get(CreationalPeriods_Path+"/"+CreationalPeriodsID).
                setAuthentication("", "", AuthenticationType.NONE).
                setTargetStatusCode(400).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
   ///////////////////////////////////////////////////////////////////
    public void Get_all_CreationalPeriods_With_Missing_Token_Rq() {
        // String Lookup_cities_Path = "/lookups/cities";
        CreationalPeriods_api = new SHAFT.API(BaseURL);
        CreationalPeriods_api.get(CreationalPeriods_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                perform();
        //  addHeader("Authorization", "Bearer " ).perform();

        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
    public void Get_all_CreationalPeriods_With_InValid_Token_Rq(String TokenValue) {
        // String Lookup_cities_Path = "/lookups/cities";

        CreationalPeriods_api = new SHAFT.API(BaseURL);
        CreationalPeriods_api.get(CreationalPeriods_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                addHeader("Authorization", "Bearer "+ TokenValue ).
                perform();

        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
    //////////////////////////////////////////////////////////////////////////////////////
    public void Get_CreationalPeriods_by_id_With_Missing_Token_Rq(String CreationalPeriodsID) {
        // String Lookup_cities_Path = "/lookups/cities";
        CreationalPeriods_api = new SHAFT.API(BaseURL);
        CreationalPeriods_api.get(CreationalPeriods_Path+"/"+ CreationalPeriodsID).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                perform();
        //  addHeader("Authorization", "Bearer " ).perform();

        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
    public void Get_CreationalPeriods_by_id_With_InValid_Token_Rq(String TokenValue, String CreationalPeriodsID) {
        // String Lookup_cities_Path = "/lookups/cities";

        CreationalPeriods_api = new SHAFT.API(BaseURL);
        CreationalPeriods_api.get(CreationalPeriods_Path+"/"+CreationalPeriodsID).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                addHeader("Authorization", "Bearer "+ TokenValue ).
                perform();

        CreationalPeriods_Response = CreationalPeriods_api.getResponse();
    }
    /////////////////////////////////////////////Status Code////////////////////////////////////
    public void Check_Valid_CreationalPeriods_status_Code_Response(){
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(200).perform();
    }
    public void Check_Validation_Error_CreationalPeriods_status_Code_Response(){
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(422).perform();
    }
    public void Check_Unauthorized_CreationalPeriods_status_Code_Response(){
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(401).perform();
    }

    public void Check_Validation_NotFound_CreationalPeriods_status_Code_Response() {
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(404).perform();
    }
    public void Check_Validation_BadRequest_CreationalPeriods_status_Code_Response() {
        SHAFT.Validations.assertThat().number(CreationalPeriods_Response.getStatusCode()).isEqualTo(400).perform();

    }
    ////////////////////////////////////////////////////////////////////////////////////////
    public void Check_CreationalPeriods_Response_Time() {
        SHAFT.Validations.verifyThat().number(CreationalPeriods_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(CreationalPeriods_Response.getTime()).isLessThanOrEquals(10000).perform();
    }
    /////////////////////////////////////////////////////////////////////////////
    public void Check_all_CreationalPeriods_Valid_Content() {
        String CreationalPeriods_ResponseBody = CreationalPeriods_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(CreationalPeriods_ResponseBody).contains("content").perform();
        CreationalPeriods_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
    }
    //////////////////////////////////////////////////Schema////////////////////////////
    public void Check_all_CreationalPeriods_Response_Valid_Schema() {
        CreationalPeriods_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","CreationalPeriods","Valid_Schema")).perform();
    }
    public void Check_CreationalPeriods_by_id_Response_Valid_Schema() {
        CreationalPeriods_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","CreationalPeriodsByID","Valid_Schema")).perform();
    }
    public void Check_CreationalPeriods_Response_Validation_Error_Schema() {
        CreationalPeriods_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","Validation Error","URL")).perform();
    }
    public void Check_all_CreationalPeriods_Response_Unauthorized_Schema() {
        CreationalPeriods_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","UnAuthorized","URL")).perform();
    }
    public void Check_CreationalPeriods_Response_NotFound_Error_Schema() {
        CreationalPeriods_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data", "Not Found", "URL")).perform();
    }
    public void Check_CreationalPeriods_Response_BadRequest_Error_Schema() {
        CreationalPeriods_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data", "Bad Request", "URL")).perform();
    }

}