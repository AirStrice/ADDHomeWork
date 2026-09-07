import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    void testGet() {
        given()
                .when()
                .get("/get")
                .then()
                .log().all()                           // ← выводит весь ответ в консоль
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/get"));
    }

    @Test
    void testPost() {
        given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    void testPut() {
        given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .put("/put")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    void testPatch() {
        given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .patch("/patch")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    void testDelete() {
        given()
                .contentType("application/json")
                .body("{\"key\":\"value\"}")
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}