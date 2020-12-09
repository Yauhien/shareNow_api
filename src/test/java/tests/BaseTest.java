package tests;

import com.core.ApiHelper;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseTest extends Assert {

    @Autowired
    ApiHelper apiHelper;

    @Value("${application.testVehicle}")
    protected String testVehicle;
}
