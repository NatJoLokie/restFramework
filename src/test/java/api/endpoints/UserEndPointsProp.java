package api.endpoints;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsProp {

//    method for getting URL from property file
    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");// load the properties file

    return routes;
    }
    public static Response createUser(UserPojo payload) {
       String POST_URL =  getURL().getString("POST_URL");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .log().uri()
                .when()
                .post(POST_URL);

        return response;

    }

    public static Response readUser(String user_Name) {
        String GET_URL = getURL().getString("GET_URL");

        Response response = given()
                .pathParam("userName", user_Name)
                .log().uri()
                .when()
                .get(GET_URL);

        return response;

    }

    public static Response updateUser(String user_Name, UserPojo payload) {
        String UPDATE_URL = getURL().getString("UPDATE_URL");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", user_Name)
                .body(payload)
                .log().uri()
                .when()
                .put(UPDATE_URL);

        return response;

    }


    public static Response deleteUser(String user_Name) {
        String DELETE_URL = getURL().getString("DELETE_URL");
        Response response = given()
                .pathParam("userName", user_Name)
                .log().uri()
                .when()
                .delete(DELETE_URL);

        return response;

    }

}
