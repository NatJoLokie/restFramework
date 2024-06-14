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
                .body(payload).log().all()
                .when()
                .post(Urls.POST_URL);

        return response;

    }

    public static Response readUser(String user_Name) {
        Response response = given()
                .pathParam("userName", user_Name)
                .log().all()
                .when()
                .get(Urls.GET_URL);

        return response;

    }

    public static Response updateUser(String user_Name, UserPojo payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", user_Name)
                .body(payload)
                .log().all()
                .when()
                .put(Urls.UPDATE_URL);

        return response;

    }


    public static Response deleteUser(String user_Name) {
        Response response = given()
                .pathParam("userName", user_Name)
                .log().all()
                .when()
                .delete(Urls.DELETE_URL);

        return response;

    }

}
