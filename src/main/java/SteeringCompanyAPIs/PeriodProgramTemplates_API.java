package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.API;
import com.shaft.api.*;
import com.shaft.api.RequestBuilder.AuthenticationType;

import com.shaft.tools.io.ExcelFileManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class PeriodProgramTemplates_API {
    ExcelFileManager testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
    String BaseURL = testDataReader.getCellData("API_Data","Steering_Base_URL","URL");
    String PeriodProgramTemplates_Path = testDataReader.getCellData("API_Data","periodProgramTemplates","URL");
    Response PeriodProgramTemplates_Response;
    SHAFT.API PeriodProgramTemplates_api;
    public void Get_Valid_all_PeriodProgramTemplates_Rq(String TokenValue) {
        //  String PeriodProgramTemplates_Path = "/PeriodProgramTemplates";
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(200).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();

    }
    public void Get_Valid_all_PeriodProgramTemplates_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {
        //  String PeriodProgramTemplates_Path = "/PeriodProgramTemplates";
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        List<List<Object>> parameters = Arrays.asList(Arrays.asList("size", PageSize),
                Arrays.asList("page",PageNumber));
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path).
                setParameters(parameters, RestActions.ParametersType.QUERY).
                setTargetStatusCode(200).
                setAuthentication("","", AuthenticationType.NONE).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();

    }
    public void Get_Valid_PeriodProgramTemplates_by_PeriodProgramTemplates_id_Rq(String TokenValue,String PeriodProgramTemplatesID) {
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path+"/"+PeriodProgramTemplatesID).
                setAuthentication("", "", AuthenticationType.NONE).
                setTargetStatusCode(200).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    public void Get_PeriodProgramTemplates_With_NotFound_PeriodProgramTemplates_id_Rq(String TokenValue,String PeriodProgramTemplatesID) {
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path+"/"+PeriodProgramTemplatesID).
                setAuthentication("", "", AuthenticationType.NONE).
                setTargetStatusCode(404).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    public void Get_PeriodProgramTemplates_With_BadRequest_PeriodProgramTemplates_id_Rq(String TokenValue,String PeriodProgramTemplatesID) {
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path+"/"+PeriodProgramTemplatesID).
                setAuthentication("", "", AuthenticationType.NONE).
                setTargetStatusCode(400).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    public void Get_all_PeriodProgramTemplates_With_Missing_Token_Rq() {
        // String Lookup_cities_Path = "/lookups/cities";
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                perform();
        //  addHeader("Authorization", "Bearer " ).perform();

        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    public void Get_all_PeriodProgramTemplates_With_InValid_Token_Rq(String TokenValue) {
        // String Lookup_cities_Path = "/lookups/cities";

        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                addHeader("Authorization", "Bearer "+ TokenValue ).
                perform();

        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    public void Get_PeriodProgramTemplatesby_PeriodProgramTemplates_id_With_Missing_Token_Rq(String PeriodProgramTemplatesID) {
        // String Lookup_cities_Path = "/lookups/cities";
        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path+"/"+ PeriodProgramTemplatesID).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                perform();
        //  addHeader("Authorization", "Bearer " ).perform();

        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    public void Get_PeriodProgramTemplatesby_PeriodProgramTemplates_id_With_InValid_Token_Rq(String TokenValue,String PeriodProgramTemplatesID) {
        // String Lookup_cities_Path = "/lookups/cities";

        PeriodProgramTemplates_api = new SHAFT.API(BaseURL);
        PeriodProgramTemplates_api.get(PeriodProgramTemplates_Path+"/"+PeriodProgramTemplatesID).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                addHeader("Authorization", "Bearer "+ TokenValue ).
                perform();

        PeriodProgramTemplates_Response = PeriodProgramTemplates_api.getResponse();
    }
    /////////////////////////////////////////////Status Code////////////////////////////////////
    public void Check_Valid_PeriodProgramTemplates_status_Code_Response(){
        SHAFT.Validations.assertThat().number(PeriodProgramTemplates_Response.getStatusCode()).isEqualTo(200).perform();
    }
    public void Check_Unauthorized_PeriodProgramTemplates_status_Code_Response(){
        SHAFT.Validations.assertThat().number(PeriodProgramTemplates_Response.getStatusCode()).isEqualTo(401).perform();
    }
    public void Check_Validation_NotFound_PeriodProgramTemplates_status_Code_Response() {
        SHAFT.Validations.assertThat().number(PeriodProgramTemplates_Response.getStatusCode()).isEqualTo(404).perform();
    }
    public void Check_Validation_BadRequest_PeriodProgramTemplates_status_Code_Response() {
        SHAFT.Validations.assertThat().number(PeriodProgramTemplates_Response.getStatusCode()).isEqualTo(400).perform();

    }
    ////////////////////////////////////////////////////////////////////////////////////////
    public void Check_PeriodProgramTemplates_Response_Time() {
        SHAFT.Validations.verifyThat().number(PeriodProgramTemplates_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(PeriodProgramTemplates_Response.getTime()).isLessThanOrEquals(10000).perform();
    }
    public void Check_all_PeriodProgramTemplates_Valid_Content() {
        String PeriodProgramTemplates_ResponseBody = PeriodProgramTemplates_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(PeriodProgramTemplates_ResponseBody).contains("content").perform();
        PeriodProgramTemplates_api.assertThatResponse().extractedJsonValue("content").isNotNull().withCustomReportMessage("Check that content object is not null.").perform();
    }
    //////////////////////////////////////////////////Schema////////////////////////////
    public void Check_all_PeriodProgramTemplates_Response_Valid_Schema() {
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","periodProgramTemplates","Valid_Schema")).perform();
    }
    public void Check_PeriodProgramTemplates_by_PeriodProgramTemplates_id_Response_Valid_Schema() {
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","periodProgramTemplatesByID","Valid_Schema")).perform();
    }
    public void Check_all_PeriodProgramTemplates_Response_Unauthorized_Schema() {
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","UnAuthorized","URL")).perform();
    }
    public void Check_PeriodProgramTemplates_Response_NotFound_Error_Schema() {
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data", "Not Found", "URL")).perform();
    }
    public void Check_PeriodProgramTemplates_Response_BadRequest_Error_Schema() {
        PeriodProgramTemplates_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data", "Bad Request", "URL")).perform();
    }
}