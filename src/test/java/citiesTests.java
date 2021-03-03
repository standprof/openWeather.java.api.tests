import io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class citiesTests {

    private static String appId = ""; //the id can be received in the Open Weather Map website, after registration
    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://api.openweathermap.org/data/2.5")
                .addParam("appid", appId)
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void collectionResourceOK(){
        String city = "London";
        given()
                .spec(spec)
                .param("q", city)
                .when()
                .get("weather")
                .then()
                .statusCode(200)
                .body("name", equalTo(city))
                .body("id", is(2643743));

        System.out.println("test");
    }
}
