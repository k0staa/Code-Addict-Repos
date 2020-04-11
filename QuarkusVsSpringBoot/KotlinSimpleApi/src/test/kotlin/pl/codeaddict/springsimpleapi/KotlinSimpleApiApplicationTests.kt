package pl.codeaddict.springsimpleapi

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class KotlinSimpleApiApplicationTests {
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
