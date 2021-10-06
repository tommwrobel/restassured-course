package pl.javastart.restassured.test.http.methods;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
        String pet = """
                {
                    "id": 123,
                    "category": {
                        "id": 1,
                        "name": "dogs"
                    },
                    "name": "Burek",
                    "photoUrls": [
                        "http://photos.com/dog1.jpg"
                    ],
                    "tags": [
                        {
                            "id": 1,
                            "name": "dogs-category"
                        }
                    ],
                    "status": "available"      
                }
                """;

        given()
            .log().all()
            .body(pet).contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
        .then()
            .log().all()
            .statusCode(200);
    }

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