package com.Google.oauth.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.Google.oauth.api.SpecBuilder.getresponsespec;
import static io.restassured.RestAssured.given;

public class TokenManager {
public static String renewtoken(){

    HashMap<String,String> parameter = new HashMap<String,String>();
    parameter.put("client_id","952914135759-e0v6gtljm14irqlpcrsr3o63fo33q7kl.apps.googleusercontent.com");
    parameter.put("client_secret","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");
    parameter.put("grant_type","refresh_token");
    parameter.put("refresh_token","1//09nb6Xw3aHNDqCgYIARAAGAkSNwF-L9IrSVlVcV2lg22OihDHBUUrSLoUjyL-s6II_cFWZ7CKobmAM3waNeMZgccMoqBozUJrjHc");

  Response response = given().
            baseUri("https://oauth2.googleapis.com").
                   formParams(parameter).
            when().post("/token").
            then().
                   spec(getresponsespec()).
                   extract().
                   response();
    if (response.statusCode() !=200){

        throw new RuntimeException(" !!! renew token failed");
    }
    return response.path("access_token");

}
}
