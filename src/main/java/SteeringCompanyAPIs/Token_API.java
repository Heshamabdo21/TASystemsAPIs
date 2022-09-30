package SteeringCompanyAPIs;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.API;
import com.shaft.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.HashMap;


public class Token_API {

    String KEYCLOAK_HOST = "https://auth-demo.np.transporticonline.com" ;
    String Token_Path = "/auth/realms/tic/protocol/openid-connect/token";
   
    public String POST_TOKEN_Rq() {
    	SHAFT.API token_api = new SHAFT.API(KEYCLOAK_HOST);

        String TokenRequestBody="username=tokhi"+"&"+"password=123"+"&"
        +"grant_type=password"+"&"+"client_id=tic-api";
        token_api.post(Token_Path).
        setRequestBody(TokenRequestBody).
        setTargetStatusCode(200).
        setContentType(ContentType.URLENC).perform();

        
        Response token_Response = token_api.getResponse();
        
        SHAFT.Validations.assertThat().number(token_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(token_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(token_Response.getTime()).isLessThanOrEquals(10000).perform();
        String TokenResponseBody = token_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(TokenResponseBody).contains("access_token").perform();
        token_api.assertThatResponse().extractedJsonValue("access_token").isNotNull().withCustomReportMessage("Check that Access token is not null.").perform();
        
        token_api.assertThatResponse().matchesSchema("Token_schema.json").perform();
        
        String Token= token_api.getResponse().getBody().jsonPath().getJsonObject("access_token").toString();
        System.out.println("Token  : - "+Token+"   -E");

        long responseTime = token_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform();

        String TokenValue = token_api.getResponse().getBody().jsonPath().getJsonObject("$['access_token']");
        return Token;
       // System.out.println("Value : --  "+value+"  ----");
    
    
    }
	
}