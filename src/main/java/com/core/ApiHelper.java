package com.core;

import static org.junit.Assert.*;

import io.restassured.path.json.JsonPath;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Component
public class ApiHelper extends ApiCore {

    public void checkResourcesReturned(String resourceNames) {
        JsonPath jp = new JsonPath(getLastResponseBody());
        List<LinkedHashMap> actualResponse = jp.get("$.");

        List<String> resourcesList = Arrays.stream(resourceNames.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        for (String expectedResource : resourcesList) {
            assertTrue("resource " + expectedResource + " is not found in response: " + resourcesList,
                    actualResponse.stream().anyMatch(a -> a.containsValue(expectedResource)));
        }
    }

    public void checkErrorMessage(String errorId, String errorMessage) {
        JsonPath jp = new JsonPath(getLastResponseBody());
        LinkedHashMap response = jp.get("$.");
        assertTrue(response.containsValue(errorId));
        assertTrue(response.containsValue(errorMessage));
    }
}
