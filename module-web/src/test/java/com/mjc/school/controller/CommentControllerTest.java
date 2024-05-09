package com.mjc.school.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommentControllerTest {
    String newsExample = "{ \"authorName\": \"Barbara\", \"content\": \"The Populist Wave and Its Discontents\", \"tagNames\": [ \"military\", \"sensory\", \"guard\" ], \"title\": \"The Integrity\" }";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8082/api/v1";
        RestAssured.port = 8082;
    }

    @Test
    public void readAllCommentTest() {
        given()
                .contentType("application/json")
                .param("page", 0)
                .param("size", 5)
                .param("sortBy", "created,desc")
                .when()
                .request("GET", "/comment")
                .then()
                .statusCode(200);
    }

    @Test
    public void readCommentByIdTest() {
        Response newsResp = createNewsExample();
        Integer newsId = newsResp.jsonPath().getInt("id");
        Response commResp = createCommentExample(newsId);

        given()
                .contentType("application/json")
                .body(commResp.jsonPath().getLong("id"))
                .when()
                .request("GET", "/comment/" + commResp.jsonPath().getLong("id"))
                .then()
                .statusCode(200);

        deleteTmpInfo(newsResp);
    }

    @Test
    public void createCommentTest() {
        Response newsResp = createNewsExample();
        Integer newsId = newsResp.jsonPath().getInt("id");

        given()
                .contentType("application/json")
                .body("{ \"content\": \"Awesome!\", \"newsId\":" + newsId + "}")
                .when()
                .request("POST", "/comment")
                .then()
                .statusCode(201)
                .body("newsId", equalTo(newsId));


        Response newsForExtractComment = RestAssured.given()
                .contentType("application/json")
                .body(newsId)
                .when()
                .request("GET", "news/" + newsId)
                .then()
                .statusCode(200)
                .extract().response();

        List<String> comment = newsForExtractComment.jsonPath().getList("commentList.content", String.class);

        given()
                .contentType("application/json")
                .body(newsId)
                .when()
                .request("GET", "/news/" + newsId + "/comment")
                .then()
                .statusCode(200)
                .body("content", equalTo(comment));
        deleteTmpInfo(newsResp);


    }

    @Test
    public void updateCommentTest() {
        Response newsResp = createNewsExample();
        Integer newsId = newsResp.jsonPath().getInt("id");
        Response response = createCommentExample(newsId);

        given()
                .contentType("application/json")
                .param("id", response.jsonPath().getInt("id"))
                .body("{ \"content\": \"NotBad!\", \"newsId\":" + newsId + "}")
                .when()
                .request("PUT", "/comment/" + response.jsonPath().getLong("id"))
                .then()
                .statusCode(200)
                .body("newsId", equalTo(newsId));
        deleteTmpInfo(newsResp);
    }

    @Test
    public void deleteCommentTest() {
        Response newsResp = createNewsExample();
        Integer newsId = newsResp.jsonPath().getInt("id");
        Response response = createCommentExample(newsId);

        given()
                .contentType("application/json")
                .when()
                .request("DELETE", "/comment/" + response.jsonPath().getLong("id"))
                .then()
                .statusCode(204);
        deleteTmpInfo(newsResp);
    }

    public Response createNewsExample() {
        return RestAssured.given()
                .contentType("application/json")
                .body(newsExample)
                .when()
                .request("POST", "/news")
                .then()
                .statusCode(201).extract().response();
    }

    public Response createCommentExample(Integer newsId) {
        return RestAssured.given()
                .contentType("application/json")
                .body("{ \"content\": \"Incredible!\", \"newsId\":" + newsId + "}")
                .when()
                .request("POST", "/comment")
                .then()
                .statusCode(201)
                .extract().response();
    }

    public void deleteTmpInfo(Response response) {
        given()
                .request("DELETE", "/news/" + response.jsonPath().getInt("id"))
                .then()
                .statusCode(204);
        List<Integer> tagIds = response.jsonPath().getList("tagList.id", Integer.class);

        tagIds.forEach(a -> given().contentType("application/json")
                .delete("/tag/" + a)
                .then()
                .statusCode(204));

        given()
                .contentType("application/json")
                .delete("/author/" + response.jsonPath().getInt("authorDtoResponse.id"))
                .then()
                .statusCode(204);
    }
}
