package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.User;

import static io.restassured.RestAssured.given;

public class UserUpdateTests {

    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest() {
        User user = new User();
        user.setId(445);
        user.setUsername("firstuser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        given()
            .log().all()
            .body(user).contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/user/")
        .then()
            .log().all()
            .statusCode(200);

        user.setFirstName("Adrian");
        user.setLastName("Nowak");

        given()
            .log().all()
            .body(user).contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/user/")
        .then()
            .log().all()
            .statusCode(200);
    }
}
