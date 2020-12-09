package tests;

import com.config.BeanConfig;
import com.core.ApiEndpoints;
import com.enums.Resources;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j
@ContextConfiguration(classes = {BeanConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesTests extends BaseTest{

    @Test
    public void getAllVehicleResources(){
        String productsEndpoint = String.format(ApiEndpoints.RESOURCES, testVehicle);
        apiHelper.sendGetRequest(productsEndpoint);
        assertEquals(200, apiHelper.getResponseStatusCode());
        apiHelper.checkResourcesReturned(Resources.ALL_RESOURCES.getResourceNames());
    }

    @Test
    public void checkInvalidAuthorization(){
        String productsEndpoint = String.format(ApiEndpoints.RESOURCES, testVehicle);
        apiHelper.sendGetRequestWinInvalidToken(productsEndpoint);
        assertEquals(401, apiHelper.getResponseStatusCode());
        apiHelper.checkErrorMessage("101", "Unauthorized");
    }
}
