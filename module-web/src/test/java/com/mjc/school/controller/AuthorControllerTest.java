package com.mjc.school.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthorControllerTest {
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8082/api/v1";
        RestAssured.port = 8082;
    }

    @Test
    public void readAllAuthorTest() {
        given()
                .contentType("application/json")
                .param("page", 0)
                .param("size", 5)
                .param("sortBy", "name,dsc")
                .when()
                .request("GET", "/author")
                .then()
                .statusCode(200);
    }

    @Test
    public void readAuthorByIdTest() {
        Response authResponse = createAuthorExample();

        String authorName = authResponse.jsonPath().getString("name");

        given()
                .contentType("application/json")
                .body(authResponse.jsonPath().getLong("id"))
                .when()
                .request("GET", "/author/" + authResponse.jsonPath().getLong("id"))
                .then()
                .statusCode(200)
                .body("name", equalTo(authorName));

        deleteAuthor(authResponse);
    }

    @Test
    public void createAuthorTest() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{ \"name\": \"Roberto\" }")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(201)
                .body("name", equalTo("Roberto"))
                .extract().response();

        deleteAuthor(response);
    }

    @Test
    public void updateAuthorTest() {
        Response response = createAuthorExample();
        Integer authorId = response.jsonPath().getInt("id");

        given()
                .contentType("application/json")
                .body("{ \"name\": \"T.Tvardo\" }")
                .when()
                .request("PUT", "/author/" + authorId)
                .then()
                .statusCode(200)
                .body("name", equalTo("T.Tvardo"))
                .body("id", equalTo(authorId));
        deleteAuthor(response);
    }

    @Test
    public void deleteAuthorTest() {
        Response response = createAuthorExample();
        Long authorId = response.jsonPath().getLong("id");

        given()
                .contentType("application/json")
                .body(authorId)
                .when()
                .request("DELETE", "/author/" + authorId)
                .then()
                .statusCode(204);
    }

    @Test
    public void createdAuthorFailedTest() {
        given()
                .contentType("application/json")
                .body("{ \"name\": \"Ro\" }")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(400);
    }

    public Response createAuthorExample() {
        return RestAssured.given()
                .contentType("application/json")
                .body("{ \"name\": \"R.Stasharko\" }")
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(201)
                .extract().response();
    }
    public void deleteAuthor(Response response){
        given()
                .request("DELETE", "/author/" + response.jsonPath().getLong("id"))
                .then()
                .statusCode(204);
    }
}

