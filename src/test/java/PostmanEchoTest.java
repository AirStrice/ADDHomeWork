import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostmanEchoTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    void testGet() {
        Response response = given()
                .when()
                .get("/get")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getMap("args"));
        assertNotNull(response.jsonPath().getMap("headers"));
        assertEquals("https://postman-echo.com/get", response.jsonPath().getString("url"));
    }

    @Test
    void testPost() {
        Response response = given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .post("/post")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getMap("args"));
        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().getMap("files"));
        assertNotNull(response.jsonPath().getMap("form"));
        assertNotNull(response.jsonPath().getMap("headers"));
        assertNotNull(response.jsonPath().getMap("json"));
        assertEquals("https://postman-echo.com/post", response.jsonPath().getString("url"));
    }

    @Test
    void testPut() {
        Response response = given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .put("/put")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getMap("args"));
        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().getMap("files"));
        assertNotNull(response.jsonPath().getMap("form"));
        assertNotNull(response.jsonPath().getMap("headers"));
        assertNotNull(response.jsonPath().getMap("json"));
        assertEquals("https://postman-echo.com/put", response.jsonPath().getString("url"));
    }

    @Test
    void testPatch() {
        Response response = given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .patch("/patch")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getMap("args"));
        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().getMap("files"));
        assertNotNull(response.jsonPath().getMap("form"));
        assertNotNull(response.jsonPath().getMap("headers"));
        assertNotNull(response.jsonPath().getMap("json"));
        assertEquals("https://postman-echo.com/patch", response.jsonPath().getString("url"));
    }

    @Test
    void testDelete() {
        Response response = given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .delete("/delete")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getMap("args"));
        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().getMap("files"));
        assertNotNull(response.jsonPath().getMap("form"));
        assertNotNull(response.jsonPath().getMap("headers"));
        assertNotNull(response.jsonPath().getMap("json"));
        assertEquals("https://postman-echo.com/delete", response.jsonPath().getString("url"));
    }
}