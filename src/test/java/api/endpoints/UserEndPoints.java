package api.endpoints;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    public static Response createUser(UserPojo payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Urls.POST_URL);

        return response;

    }

    public static Response readUser(String userName) {
        Response response = given()
                .pathParam("userName", "user_Name")
                .when()
                .get(Urls.GET_URL);

        return response;

    }

    public static Response updateUser(String userName, String payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", "user_Name")
                .body(payload)
                .when()
                .put(Urls.UPDATE_URL);

        return response;

    }


    public static Response deleteUser(String userName) {
        Response response = given()
                .pathParam("userName", "user_Name")
                .when()
                .delete(Urls.GET_URL);

        return response;

    }

}
