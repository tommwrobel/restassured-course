package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {
        String user = """
                {
                  "id": 445,
                  "username": "firstuser",
                  "firstName": "Krzysztof",
                  "lastName": "Kowalski",
                  "email": "krzysztof@test.com",
                  "password": "password",
                  "phone": "+123456789",
                  "userStatus": 1
                }
                """;

        given()
            .body(user).contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
        .then()
            .statusCode(200);
    }
}
