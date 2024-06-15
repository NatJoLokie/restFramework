package api.test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest {
    UserPojo userPayLoad;

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void test_DD_PostUser(String userId, String userName, String fName, String lName,String userEmail,String pwd, String ph ){

        userPayLoad = new UserPojo();

        userPayLoad.setId(Integer.parseInt(userId));
        userPayLoad.setUsername(userName);
        userPayLoad.setFirstName(fName);
        userPayLoad.setLastName(lName);
        userPayLoad.setEmail(userEmail);
        userPayLoad.setPassword(pwd);
        userPayLoad.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayLoad);

        Assert.assertEquals(response.getStatusCode(),200);



    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void test_DD_deleteUser(String userName ){

        Response response = UserEndPoints.deleteUser(userName);

        Assert.assertEquals(response.getStatusCode(),200);
    }


}
