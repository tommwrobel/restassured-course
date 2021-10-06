package pl.javastart.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SecondRestAssuredAutomatedTest {

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given()
            .log().method()
            .log().uri()
            .pathParam("petId", 1)
        .when()
            .get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
        .then()
            .log().all()
            .statusCode(200);
    }
}
