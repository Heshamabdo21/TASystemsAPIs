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


public class Reservations_API {

    String BaseURL = "https://api-demo.np.transporticonline.com/steeringcompanies/v1" ;

    public void GET_Reservations_by_Reservations_id_Rq(String TokenValue,String ReservationsID) {        
        String Reservations_Path = "/reservations/"+ReservationsID;
    	SHAFT.API Reservations_api = new SHAFT.API(BaseURL);

    
    	Reservations_api.get(Reservations_Path).
    	setAuthentication("", "", AuthenticationType.NONE).
    	addHeader("Authorization", "Bearer " + TokenValue).perform();

        
        Response Reservations_Response = Reservations_api.getResponse();
        
        SHAFT.Validations.assertThat().number(Reservations_Response.getStatusCode()).isEqualTo(200).perform();
        SHAFT.Validations.verifyThat().number(Reservations_Response.getTime()).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(Reservations_Response.getTime()).isLessThanOrEquals(10000).perform();
        String Reservations_ResponseBody = Reservations_Response.getBody().asString();
        SHAFT.Validations.assertThat().object(Reservations_ResponseBody).contains("vehicleTypes").perform();
       
        Reservations_api.assertThatResponse().extractedJsonValue("vehicleTypes").isNotNull().withCustomReportMessage("Check that cancellationPolicies object is not null.").perform();
        
        Reservations_api.assertThatResponse().matchesSchema("Reservations_schema.json").perform();
        
        System.out.println("Reservations Response Body  : - "+Reservations_ResponseBody+"   -E");

        long responseTime = Reservations_api.getResponse().getTime();
        SHAFT.Validations.verifyThat().number(responseTime).isGreaterThanOrEquals(1.1).perform();
        SHAFT.Validations.verifyThat().number(responseTime).isLessThanOrEquals(10000).perform(); 
    }
 
 
}