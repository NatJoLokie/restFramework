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
        userPayLoad.setLastName(fakeThe.name().lastName());
        userPayLoad.setEmail(fakeThe.internet().safeEmailAddress());
        userPayLoad.setPassword(fakeThe.internet().password(5,10));
        userPayLoad.setPhone(fakeThe.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void test_PostUser(){

//        System.out.println(userPayLoad);

        Response response = UserEndPoints.createUser(userPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void test_GetUser(){

        Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void test_UpdateUserByName(){

        userPayLoad.setFirstName(fakeThe.name().firstName());
        userPayLoad.setLastName(fakeThe.name().lastName());
        userPayLoad.setEmail(fakeThe.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayLoad.getUsername(),userPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 4)
    public void test_deleteUser(){

        Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());

        Assert.assertEquals(response.getStatusCode(),200);
    }




}
