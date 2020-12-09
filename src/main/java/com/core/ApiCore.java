package com.core;

import static io.restassured.RestAssured.given;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Log4j
@PropertySource("classpath:application.properties")
public class ApiCore {

    @Value("${api.domain}")
    private String apiDomain;
    @Value("${auth.token}")
    private String authToken;
    private String invalidToken = "Bearer 1c4c444c-v123-4123-s123-4c4c444c4c44";

    private ExtractableResponse<Response> lastResponse = null;

    public String buildUrl(String endpoint) {
        return apiDomain + endpoint;
    }

    public String getLastResponseBody() {
        return lastResponse.response().body().asString();
    }

    public int getResponseStatusCode() {
        return lastResponse.statusCode();
    }

    public void sendGetRequest(String endpoint) {
        String url = buildUrl(endpoint);
        try {
            lastResponse = given().header("Authorization", authToken).get(url).then().extract();
        } catch (IllegalArgumentException e) {
            log.error("Trying to send get request to endpoint:  " + url + " Exception occurs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendGetRequestWinInvalidToken(String endpoint) {
        String url = buildUrl(endpoint);
        try {
            lastResponse = given().header("Authorization", authToken + 1).get(url).then().extract();
        } catch (IllegalArgumentException e) {
            log.error("Trying to send get request to endpoint:  " + url + " Exception occurs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendDeleteRequest(String endpoint) {
        String url = buildUrl(endpoint);
        try {
            lastResponse = given().delete(url).then().extract();
        } catch (IllegalArgumentException e) {
            log.error("Trying to send get request to endpoint:  " + url + " Exception occurs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public RequestSpecification buildJavaObjectAsJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            Assert.fail("Unable to serialize JSON");
        }
        String removeEscapeChars = json.replaceAll("\\\\", "");
        String updatedBody = removeEscapeChars.substring(1, removeEscapeChars.length() - 1);

        RequestSpecification given = given();
        given.contentType("application/json");
        given.body(updatedBody);

        return given;
    }

    public void sendPostRequest(String endpoint, Object object) {
        String url = buildUrl(endpoint);
        RequestSpecification given = buildJavaObjectAsJson(object);

        try {
            lastResponse = given.post(url).then().extract();
        } catch (IllegalArgumentException e) {
            log.error("Trying to send get request to endpoint:  " + url + " Exception occurs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendPatchRequest(String endpoint, Object object) {
        String url = buildUrl(endpoint);
        RequestSpecification given = buildJavaObjectAsJson(object);

        try {
            lastResponse = given.patch(url).then().extract();
        } catch (IllegalArgumentException e) {
            log.error("Trying to send get request to endpoint:  " + url + " Exception occurs: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
