import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class ApiResourceTest {
    private val payload = """{
  "data": "SomeData"
}"""

    @Test
    fun testApiEndpoint() {
        given()
                .`when`()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/api")
                .then()
                .statusCode(200)
                .body( equalTo("1"))

        given()
                .`when`().get("/api/SomeData")
                .then()
                .statusCode(200)
                .body("data", equalTo("SomeData"))
    }

}
