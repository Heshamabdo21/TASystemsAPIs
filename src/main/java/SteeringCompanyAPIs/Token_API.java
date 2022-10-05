package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.API;
import com.shaft.tools.io.ExcelFileManager;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shaft.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Token_API {

    ExcelFileManager testDataReader = new ExcelFileManager("SteeringCompanyAPI_TestData/SteeringCompanyAPI_TestData.xlsx");
   // String KEYCLOAK_HOST = "https://auth-demo.np.transporticonline.com" ;
    String KEYCLOAK_HOST=testDataReader.getCellData("API_Data","KEYCLOAK_HOST","Data1");
    //String Token_Path = "/auth/realms/tic/protocol/openid-connect/token";
    String Token_Path = testDataReader.getCellData("API_Data","Token_Path","Data1");
    Response token_Response;
    
    public String POST_Valid_TOKEN_Rq(String TokenUserName,String TokenPassword) {
    	SHAFT.API token_api = new SHAFT.API(KEYCLOAK_HOST);
        String TokenRequestBody="username="+TokenUserName+"&"+"password="+TokenPassword+"&"
        +"grant_type=password"+"&"+"client_id=tic-api";
        token_api.post(Token_Path).
        setRequestBody(TokenRequestBody).
        setTargetStatusCode(200).
        setContentType(ContentType.URLENC).perform();
        
        token_Response = token_api.getResponse();
        
        SHAFT.Validations.assertThat().number(token_Response.getStatusCode()).isEqualTo(200).withCustomReportMessage("Check Response Status Code is 200 and it is valid.").perform();

        String TokenResponseBody = token_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(TokenResponseBody).contains("access_token").withCustomReportMessage("Check Access Token is existed.").perform();
        token_api.assertThatResponse().extractedJsonValue("access_token").isNotNull().withCustomReportMessage("Check Access token is not null.").perform();
        
        SHAFT.Validations.verifyThat().number(token_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(token_Response.getTime()).isLessThanOrEquals(10000).perform();
 
        token_api.assertThatResponse().matchesSchema("Token_schema.json").withCustomReportMessage("Token Schema is matched with valid schema.").perform();
        
        String Token= token_api.getResponse().getBody().jsonPath().getJsonObject("access_token").toString();       

   //     String TokenValue = token_api.getResponse().getBody().jsonPath().getJsonObject("$['access_token']");
        return Token;
    
    }
    

    public String POST_invalid_TOKEN_Rq(String TokenUserName,String TokenPassword) {
        SHAFT.API token_api = new SHAFT.API(KEYCLOAK_HOST);

        String TokenRequestBody="username="+TokenPassword+"&"+"password="+TokenPassword+"&"
        +"grant_type=password"+"&"+"client_id=tic-api";
        token_api.post(Token_Path).
        setRequestBody(TokenRequestBody).
        setTargetStatusCode(200).
        setContentType(ContentType.URLENC).perform();
        
        Response token_Response = token_api.getResponse();
        
        SHAFT.Validations.assertThat().number(token_Response.getStatusCode()).isEqualTo(200).withCustomReportMessage("Check Response Status Code is 200 and it is valid.").perform();

        String TokenResponseBody = token_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(TokenResponseBody).contains("access_token").withCustomReportMessage("Check Access Token is existed.").perform();
        token_api.assertThatResponse().extractedJsonValue("access_token").isNotNull().withCustomReportMessage("Check Access token is not null.").perform();
        
        SHAFT.Validations.verifyThat().number(token_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(token_Response.getTime()).isLessThanOrEquals(10000).perform();
 
        token_api.assertThatResponse().matchesSchema("Token_schema.json").withCustomReportMessage("Token Schema is matched with valid schema.").perform();
        
        String Token= token_api.getResponse().getBody().jsonPath().getJsonObject("access_token").toString();       

   //     String TokenValue = token_api.getResponse().getBody().jsonPath().getJsonObject("$['access_token']");
        return Token;
    
    }
    

    
    
    public void CheckTokenExpiration(String Token) {
        DecodedJWT decodedJWT = JWT.decode(Token);        
        //String ExpirationDatetime = decodedJWT.getExpiresAt().toString();
        Number ValidToken=0;
        if( decodedJWT.getExpiresAt().before(new Date())) {
       //     System.out.println("token is expired");
            ValidToken=0;
            
        }else {
         //   System.out.println("token isnot expired");
            ValidToken=1;
        }
        SHAFT.Validations.verifyThat().number(ValidToken).isEqualTo(1).withCustomReportMessage("Expiration Date Token is Valid").perform();
    }
    
    public void CheckTokenISS(String Token,String  ActualISS) {
       // ActualISS="\"https://auth-demo.np.transporticonline.com/auth/realms/tic\"";
        DecodedJWT decodedJWT = JWT.decode(Token);        
        String ISSString = decodedJWT.getClaim("iss").toString();
       // System.out.println("iss Value : --  "+ISSString+"  ----");
        SHAFT.Validations.verifyThat().object(ISSString).isEqualTo(ActualISS).withCustomReportMessage("ISS is Valid").perform();
    }
       
    public void CheckTokentcId(String Token,String  ActualtcId) {
   //     String  ActualtcId="\"319957\"";
       //   System.out.println("------------ Decode JWT ------------");  
          DecodedJWT decodedJWT = JWT.decode(Token);        
          String tcIdString = decodedJWT.getClaim("tcId").toString();
         // System.out.println("iss Value : --  "+tcIdString+"  ----");
          SHAFT.Validations.verifyThat().object(tcIdString).isEqualTo(ActualtcId).withCustomReportMessage("tcId is Valid").perform();
      }
    
    public void CheckTokenpreferred_username(String Token,String  ActuaLpreferred_username) {
      //  String  ActuaLpreferred_username="\"tokhi\"";
    //      System.out.println("------------ Decode JWT ------------");  
          DecodedJWT decodedJWT = JWT.decode(Token);        
          String preferred_usernameString = decodedJWT.getClaim("preferred_username").toString();
        //  System.out.println("iss Value : --  "+preferred_usernameString+"  ----");
          SHAFT.Validations.verifyThat().object(preferred_usernameString).isEqualTo(ActuaLpreferred_username).withCustomReportMessage("preferred_username is Valid").perform();
      }

}