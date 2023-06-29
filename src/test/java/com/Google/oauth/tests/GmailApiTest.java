package com.Google.oauth.tests;

import com.Google.oauth.applicationapi.GmailApi;
import com.Google.oauth.pojo.ErrorRoot;
import com.Google.oauth.pojo.ProfileUserPojo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Google Oauth 2.0")
@Feature("GmailApi Test")
public class GmailApiTest {

    @Description("Get user profile information ")
    @Test
    public void getuserprofile(){

          Response response =GmailApi.Get();
          assertThat(response.statusCode(),equalTo(200));
          ProfileUserPojo responseProfileUserPojo = response.as(ProfileUserPojo.class);
          assertThat(responseProfileUserPojo.getEmailAddress(),equalTo("youssefelshemy99@gmail.com"));

    }
    @Description("Send Message from Resource owner to client  ")
    @Test
    public void sendmessage(){


         String msg="From: youssefelshmey99@gmail.com\n" +
                 "To: elshemym551@gmail.com\n" +
                 "Subject:  Rest assured Test Email\n" +
                 "\n" +
                 "Sending From Gmail Api Using Rest assured By Youssef Elshmey ";

         String base64 = Base64.getUrlEncoder().encodeToString(msg.getBytes());

         HashMap<String,String> payload = new HashMap<>();
         payload.put("raw",base64);

         Response response =GmailApi.post(payload);
         assertThat(response.statusCode(),equalTo(200));


    }

    // negative scenario
    @Test
    public void getuserprofilewithexpiredtoken (){
    String invalid_token="123";

        Response response =GmailApi.Get(invalid_token);
        assertThat(response.statusCode(),equalTo(401));
        ErrorRoot errorrr = response.as(ErrorRoot.class);

        assertThat(errorrr.getError().getCode(),equalTo(401));
        assertThat(errorrr.getError().getStatus(),equalTo("UNAUTHENTICATED"));


    }









}
