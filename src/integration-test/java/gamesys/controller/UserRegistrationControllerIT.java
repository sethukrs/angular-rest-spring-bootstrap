package gamesys.controller;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import gamesys.factory.UserFactory;
import gamesys.util.AbstractControllerIT;
import gamesys.util.ITTestUtil;
import gamesys.util.ObjectMapperUtil;
import org.apache.http.HttpHeaders;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRegistrationControllerIT extends AbstractControllerIT {

    public static final String PATH = "/CodingExercise/register";

    @Test
    public void test1ToListAllUsers() throws Exception {

        when()
                .get(PATH)
                .then()
                .statusCode(200)
                .body("id.get(0)", equalTo(3))
                .body("id.get(1)", equalTo(1))
                .body("id.get(2)", equalTo(2))
                .body("ssn.get(0)", equalTo("123-11-1232"))
                .body("ssn.get(1)", equalTo("123-11-1234"))
                .body("ssn.get(2)", equalTo("123-11-1233"));
    }

    @Test
    public void test2ToDeregisterUserByID() throws Exception {
        //De register user id : 3 - return 204 , deleted
        when()
                .delete(PATH + "/3")
                .then()
                .statusCode(204);

        //fetch all users will not have user id 3, as it is deleted now
        when()
                .get(PATH)
                .then()
                .statusCode(200)
                .body("id.get(0)", equalTo(1))
                .body("id.get(1)", equalTo(2))
                .body("ssn.get(0)", equalTo("123-11-1234"))
                .body("ssn.get(1)", equalTo("123-11-1233"));

        //Try De register user id : 3 will return 404 - not found
        when()
                .delete(PATH + "/3")
                .then()
                .statusCode(404);

    }

    @Test
    public void test3ToRegisterUser() throws Exception {
        //
        //De register all users - return 204 , deleted
        when()
                .delete(PATH)
                .then()
                .statusCode(204);

        //Register user id : 1
        given()
                .when()
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(user("user1", "User1", "1987-06-22T23:00:00.000Z", "111-11-1111"))
                .post(PATH)
                .then()
                .statusCode(201);

        //fetch all users will show newly added user, as it is deleted now
        when()
                .get(PATH)
                .then()
                .statusCode(200)
                .body("username.get(0)", equalTo("user1"))
                .body("password.get(0)", equalTo("User1"))
                .body("dob.get(0)", equalTo("1987-06-22T23:00:00.000Z"))
                .body("ssn.get(0)", equalTo("111-11-1111"));
    }

    @Test
    public void test4ToThrowDuplicateSSNException() throws Exception {
        //Register already registered user
        given()
                .when()
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(user("user1", "User1", "1987-06-22T23:00:00.000Z", "111-11-1111"))
                .post(PATH)
                .then()
                .statusCode(400)
                .body(equalTo(ErrorCodes.DUPLICATE_SSN_EXCEPTION));
    }

    @Test
    public void test5ToThrowInvalidDobException() throws Exception {
        //Register user with invalid dob - not ISO 8601 format
        given()
                .when()
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(user("user2", "User2", "1987-JUN-22", "111-11-1112"))
                .post(PATH)
                .then()
                .statusCode(400)
                .body(equalTo(ErrorCodes.INVALID_DOB_EXCEPTION));
    }

    @Test
    public void test6ToThrowUserBlackListedException() throws Exception {
        //Register user who is blacklisted
        given()
                .when()
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(user("user6", "User6", "1985-01-01T23:00:00.000Z", "999-11-1111"))
                .post(PATH)
                .then()
                .statusCode(400)
                .body(equalTo(ErrorCodes.USER_BLACK_LISTED_EXCEPTION));
    }


    private String user(String username, String password, String dob, String ssn) {
        return ITTestUtil.readResource("json/user.json", username, password, dob, ssn);
    }

}