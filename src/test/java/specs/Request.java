package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.AllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class Request {

    public static RequestSpecification requestSpec = with()
        .filter(withCustomTemplates())
        .contentType(JSON)
        .log().all();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
        .log(STATUS)
        .log(BODY)
        .build();
}