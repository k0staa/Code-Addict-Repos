package pl.codeaddict.spockapidocs

import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.junit.Rule
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.springframework.restdocs.headers.HeaderDocumentation.*
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.*
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogApiDocumentationSpec extends Specification {
    @Value('${local.server.port}')
    private Integer serverPort

    @Rule
    private JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation()

    private RequestSpecification requestSpec

    void setup() {
        this.requestSpec = new RequestSpecBuilder()
                .addFilter(
                        documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(
                                        modifyUris().host('api.example.com')
                                                .removePort())
                                .withResponseDefaults(prettyPrint()))
                .build()
    }

    void "login to admin panel"() {
        given:
        final request =
                given(this.requestSpec)
                        .port(serverPort)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON.toString())
                        .body("{\"username\": \"john\", \"password\": \"pass\"}")
                        .filter(
                                document(
                                        "login",
                                        responseHeaders(
                                                headerWithName("Set-Auth-Token").description(
                                                        "Token authorizing user in admin panel")),
                                ))

        when:
        final response = request.post("/api/post")

        then:
        response.statusCode() == HttpStatus.OK.value()
        response.getHeaders().get("Set-Auth-Token") != null
    }

    void "add blog post"() {
        given:
        final request =
                given(this.requestSpec)
                        .port(serverPort)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON.toString())
                        .header("Authorization", "Bearer TOKEN")
                        .body("{\"text\": \"Blog post text\"}")
                        .filter(
                                document(
                                        "add_post",
                                        responseFields(
                                                fieldWithPath("id")
                                                        .description("Created post ID")),
                                        requestFields(
                                                fieldWithPath("text").description("Blog post text")),
                                        requestHeaders(
                                                headerWithName("Authorization").description(
                                                        "Token authorizing user")),
                                ))

        when:
        final response = request.post('/api//post')

        then:
        response.statusCode() == HttpStatus.OK.value()
    }

    void "modify blog post"() {
        given:
        final request =
                given(this.requestSpec)
                        .port(serverPort)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON.toString())
                        .header("Authorization", "Bearer TOKEN")
                        .body("{\"text\": \"Modified blog post text\"}")
                        .filter(
                                document(
                                        "modify_post",
                                        pathParameters(
                                                parameterWithName("id")
                                                        .description("Post unique ID")),
                                        requestFields(
                                                fieldWithPath("text").description("Blog post text")),
                                        requestHeaders(
                                                headerWithName("Authorization").description(
                                                        "Token authorizing user")),
                                ))

        when:
        final response = request.put('/api/post/{id}', [id: "123-5556"])

        then:
        response.statusCode() == HttpStatus.OK.value()
    }

    void "get post details"() {
        given:
        final request =
                given(this.requestSpec)
                        .port(serverPort)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON.toString())
                        .header("Authorization", "Bearer TOKEN")
                        .filter(
                                document(
                                        "get_post",
                                        pathParameters(
                                                parameterWithName("id")
                                                        .description("Blog post unique ID")),
                                        responseFields(
                                                fieldWithPath("id")
                                                        .description("Blog post ID"),
                                                fieldWithPath("text")
                                                        .description("Blog post text"),
                                        ),
                                        requestHeaders(
                                                headerWithName("Authorization").description(
                                                        "Token authorizing user"))
                                ))

        when:
        final response = request.get('/api/post/{id}', [id: "123-4234"])

        then:
        response.statusCode() == HttpStatus.OK.value()
    }
}