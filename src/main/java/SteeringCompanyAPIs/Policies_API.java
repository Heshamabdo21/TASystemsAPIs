package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.API;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.api.*;
import com.shaft.api.RequestBuilder.AuthenticationType;

import com.sun.net.httpserver.Authenticator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Policies_API {
    ExcelFileManager testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
    String BaseURL = testDataReader.getCellData("API_Data","Steering_Base_URL","URL");

    //String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;

    Response All_Policies_Response;
    SHAFT.API All_Policies_api;
   // String All_Policies_Path = "/policies";
    String All_Policies_Path = testDataReader.getCellData("API_Data","GetAllPolicies","URL");
  /////////////////////////// Methods for Get All Policies ///////////////////////
    public void GET_all_Policies_Rq(String TokenValue) {
     //   String Lookup_policies_Path = "/policies";
    	 All_Policies_api = new SHAFT.API(BaseURL); 
    	 All_Policies_api.get(All_Policies_Path).
    	setAuthentication("","", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();     
         All_Policies_Response = All_Policies_api.getResponse();
    }

    public void GET_all_Policies_Path_by_parameter_Query_Rq(String TokenValue,String PageSize,String PageNumber) {
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

    public void GET_all_Policies_With_Missing_Token_Rq() {
        // String Lookup_cities_Path = "/lookups/cities";
        All_Policies_api = new SHAFT.API(BaseURL);
        All_Policies_api.get(All_Policies_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).perform();
              //  addHeader("Authorization", "Bearer " ).perform();

        All_Policies_Response = All_Policies_api.getResponse();
    }

    public void GET_all_Policies_With_InValid_Token_Rq(String TokenValue) {
        // String Lookup_cities_Path = "/lookups/cities";

        All_Policies_api = new SHAFT.API(BaseURL);
        All_Policies_api.get(All_Policies_Path).
                setAuthentication("","", AuthenticationType.NONE).
                setTargetStatusCode(401).
                addHeader("Authorization", "Bearer "+ TokenValue ).perform();

        All_Policies_Response = All_Policies_api.getResponse();
    }

    public void Check_Valid_policies_status_Code_Response(){
        SHAFT.Validations.assertThat().number(All_Policies_Response.getStatusCode()).isEqualTo(200).perform();
    }

    public void Check_Policies_status_Code_Unauthorized_Response(){
        SHAFT.Validations.assertThat().number(All_Policies_Response.getStatusCode()).isEqualTo(401).perform();
    }

    public void Check_policies_Response_Time() {
        SHAFT.Validations.verifyThat().number(All_Policies_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(All_Policies_Response.getTime()).isLessThanOrEquals(10000).perform();
    }
    
    public void Check_policies_Valid_Content() {
        String Lookups_policies_ResponseBody = All_Policies_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Lookups_policies_ResponseBody).contains("cancellationPolicies").perform();
        All_Policies_api.assertThatResponse().extractedJsonValue("cancellationPolicies").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
    }
    
    public void Check_policies_Response_Valid_Schema() {
        All_Policies_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","GetAllPolicies","Valid_Schema")).perform();
    }

    public void Check_policies_Response_Unauthorized_Schema() {
        All_Policies_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","UnAuthorized","URL")).perform();
    }

    /////////////////////// Add Cancel Policy Methods ///////////////////
    String Cancel_Policy_Path = testDataReader.getCellData("API_Data","AddCancellationPolicies","URL");
    String Tax_Policy_Path = testDataReader.getCellData("API_Data","AddTaxPolicies","URL");
    String Payment_Policy_Path = testDataReader.getCellData("API_Data","AddPaymentPolicies","URL");
    String General_Policy_Path = testDataReader.getCellData("API_Data","AddGeneralPolicies","URL");
    String Usage_Policy_Path = testDataReader.getCellData("API_Data","AddUsagePolicies","URL");

    Response Policy_Response;
    SHAFT.API Policy_api;
    public void Add_Policy_Rq(String TokenValue, @NotNull  Object data[]) {
        Random random=new Random();
        int x=random.nextInt(10000);
        Policy_api = new SHAFT.API(BaseURL);
        if(data[1].equals("Cancel"))
        {
        String Add_Cancel_PolicyBody="{\n"+
                "    \"nameArabic\": \""+data[2]+x+"\",\n" +
                "    \"nameEnglish\": \""+data[3]+x+"\",\n" +
                "    \"descriptionArabic\": \""+data[4]+x+"\",\n" +
                "    \"descriptionEnglish\": \""+data[5]+x+"\",\n" +
                "    \"chargeUnit\": \""+data[6]+"\",\n" +
                "    \"deadline\": \""+data[7]+"\",\n" +
                "    \"chargeType\": \""+data[8]+"\",\n" +
                "    \"chargeValue\": \""+data[9]+"\",\n" +
                "    \"id\": \""+data[10]+"\"\n"+
                "}";
        Policy_api.post(Cancel_Policy_Path).
                setRequestBody(Add_Cancel_PolicyBody).
                setTargetStatusCode(201).
                setContentType(ContentType.JSON).
                setAuthentication("","", AuthenticationType.NONE).
                addHeader("Authorization", "Bearer " + TokenValue).perform();
        }
        else if (data[1].equals("Payment"))
        {
            String Add_Payment_PolicyBody="{\n"+
                    "    \"nameArabic\": \""+data[2]+x+"\",\n" +
                    "    \"nameEnglish\": \""+data[3]+x+"\",\n" +
                    "    \"descriptionArabic\": \""+data[4]+x+"\",\n" +
                    "    \"descriptionEnglish\": \""+data[5]+x+"\",\n" +
                    "    \"refundType\": \""+data[6]+"\",\n" +
                    "    \"cancellationPolicyId\": \""+data[7]+"\",\n" +
                    "    \"id\": \""+data[8]+"\"\n"+
                    "}";
            Policy_api.post(Payment_Policy_Path).
                    setRequestBody(Add_Payment_PolicyBody).
                    setTargetStatusCode(201).
                    setContentType(ContentType.JSON).
                    setAuthentication("","", AuthenticationType.NONE).
                    addHeader("Authorization", "Bearer " + TokenValue).perform();
        }
        else if (data[1].equals("Tax"))
        {
            String Add_Tax_PolicyBody="{\n"+
                    "    \"nameArabic\": \""+data[2]+x+"\",\n" +
                    "    \"nameEnglish\": \""+data[3]+x+"\",\n" +
                    "    \"descriptionArabic\": \""+data[4]+x+"\",\n" +
                    "    \"descriptionEnglish\": \""+data[5]+x+"\",\n" +
                    "    \"chargeType\": \""+data[6]+"\",\n" +
                    "    \"chargeValue\": \""+data[7]+"\",\n" +
                    "    \"id\": \""+data[8]+"\"\n"+
                    "}";
            Policy_api.post(Tax_Policy_Path).
                    setRequestBody(Add_Tax_PolicyBody).
                    setTargetStatusCode(201).
                    setContentType(ContentType.JSON).
                    setAuthentication("","", AuthenticationType.NONE).
                    addHeader("Authorization", "Bearer " + TokenValue).perform();
        }
        else if (data[1].equals("Usage"))
        {
            String Add_Usage_PolicyBody="{\n"+
                    "    \"nameArabic\": \""+data[2]+x+"\",\n" +
                    "    \"nameEnglish\": \""+data[3]+x+"\",\n" +
                    "    \"descriptionArabic\": \""+data[4]+x+"\",\n" +
                    "    \"descriptionEnglish\": \""+data[5]+x+"\",\n" +
                    "    \"id\": \""+data[6]+"\"\n"+
                    "}";
            Policy_api.post(Usage_Policy_Path).
                    setRequestBody(Add_Usage_PolicyBody).
                    setTargetStatusCode(201).
                    setContentType(ContentType.JSON).
                    setAuthentication("","", AuthenticationType.NONE).
                    addHeader("Authorization", "Bearer " + TokenValue).perform();
        }
        else if (data[1].equals("General"))
        {
            String Add_General_PolicyBody="{\n"+
                    "    \"nameArabic\": \""+data[2]+x+"\",\n" +
                    "    \"nameEnglish\": \""+data[3]+x+"\",\n" +
                    "    \"descriptionArabic\": \""+data[4]+x+"\",\n" +
                    "    \"descriptionEnglish\": \""+data[5]+x+"\",\n" +
                    "    \"id\": \""+data[6]+"\"\n"+
                    "}";
            Policy_api.post(General_Policy_Path).
                    setRequestBody(Add_General_PolicyBody).
                    setTargetStatusCode(201).
                    setContentType(ContentType.JSON).
                    setAuthentication("","", AuthenticationType.NONE).
                    addHeader("Authorization", "Bearer " + TokenValue).perform();
        }
        Policy_Response = Policy_api.getResponse();
    }

    public void Check_Valid_Add_policies_status_Code_Response(){
        SHAFT.Validations.assertThat().number(Policy_Response.getStatusCode()).isEqualTo(201).perform();
    }
    public void Check_Add_policy_Response_Valid_Schema(String PolicyType) {
        if(PolicyType.equals("Cancel"))
            Policy_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","AddCancellationPolicies","Valid_Schema")).perform();

        else if (PolicyType.equals("Payment"))
            Policy_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","AddPaymentPolicies","Valid_Schema")).perform();

        else if (PolicyType.equals("Tax"))
            Policy_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","AddTaxPolicies","Valid_Schema")).perform();

        else if (PolicyType.equals("Usage"))
            Policy_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","AddUsagePolicies","Valid_Schema")).perform();

        else if (PolicyType.equals("General"))
            Policy_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","AddGeneralPolicies","Valid_Schema")).perform();

    }

    public void Check_Add_Policy_Response_Time() {
        SHAFT.Validations.verifyThat().number(Policy_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Policy_Response.getTime()).isLessThanOrEquals(10000).perform();
    }

    public void Check_Add_policy_Response_Unauthorized_Schema() {
        Policy_api.assertThatResponse().matchesSchema(testDataReader.getCellData("API_Data","UnAuthorized","URL")).perform();
    }


}