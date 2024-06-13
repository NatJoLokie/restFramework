package api.test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker fakeThe;
    UserPojo userPayLoad;

    @BeforeClass
    public void setUpData(){

        fakeThe = new Faker();
        userPayLoad = new UserPojo();

        userPayLoad.setId(fakeThe.idNumber().hashCode());
        userPayLoad.setUsername(fakeThe.name().username());
        userPayLoad.setFirstName(fakeThe.name().firstName());
        userPayLoad.setLastName(fakeThe.name().firstName());
        userPayLoad.setEmail(fakeThe.internet().emailAddress());
        userPayLoad.setPassword(fakeThe.internet().password(5,10));
        userPayLoad.setPhone(fakeThe.phoneNumber().cellPhone());

    }

    @Test
    public void testPostUser(){

        Response response = UserEndPoints.createUser(userPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

}
