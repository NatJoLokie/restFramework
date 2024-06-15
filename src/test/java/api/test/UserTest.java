package api.test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker fakeThe;
    UserPojo userPayLoad;

    public Logger logger;

    @BeforeClass
    public void setUp(){

        fakeThe = new Faker();
        userPayLoad = new UserPojo();

        userPayLoad.setId(fakeThe.idNumber().hashCode());
        userPayLoad.setUsername(fakeThe.name().username());
        userPayLoad.setFirstName(fakeThe.name().firstName());
        userPayLoad.setLastName(fakeThe.name().lastName());
        userPayLoad.setEmail(fakeThe.internet().safeEmailAddress());
        userPayLoad.setPassword(fakeThe.internet().password(5,10));
        userPayLoad.setPhone(fakeThe.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void test_PostUser(){

        logger.info("------- Creating User -------");

//        System.out.println(userPayLoad);

        Response response = UserEndPoints.createUser(userPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("------- User Created -------");
    }

    @Test(priority = 2)
    public void test_GetUser(){

        logger.info("------- Get User Details -------");

        Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void test_UpdateUserByName(){

        logger.info("------- User Update -------");

        userPayLoad.setFirstName(fakeThe.name().firstName());
        userPayLoad.setLastName(fakeThe.name().lastName());
        userPayLoad.setEmail(fakeThe.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayLoad.getUsername(),userPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("------- User Updated -------");

    }

    @Test(priority = 4)
    public void test_deleteUser(){

        logger.info("------- Delete User -------");

        Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("------- User Deleted -------");
    }




}
