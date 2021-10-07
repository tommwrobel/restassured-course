package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.User;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {
        User user = new User();
        user.setId(445);
        user.setUsername("testuser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        given()
            .body(user).contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
        .then()
            .statusCode(200);

        given()
            .contentType("application/json")
            .pathParam("username", "testuser")
        .when()
            .get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
        .then()
            .log().all()
            .statusCode(200);
    }
}
