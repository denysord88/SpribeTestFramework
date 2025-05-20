package controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import java.util.concurrent.TimeUnit;
import static conf.Configuration.*;

public class BaseController {
    public RequestSpecification requestSpecification;

    public BaseController() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("http://" + HOST + BASE_PATH)
                .setContentType(ContentType.JSON)
                .setConfig(RestAssuredConfig.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", CONNECTION_TIMEOUT_MILLIS)));
        if (ENABLE_LOGGING)
            requestSpecBuilder.addFilter(new RequestLoggingFilter()).addFilter(new ResponseLoggingFilter());
        requestSpecification = requestSpecBuilder.build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(MAX_RESPONSE_TIME_SECONDS), TimeUnit.SECONDS)
                .build();
    }
}
