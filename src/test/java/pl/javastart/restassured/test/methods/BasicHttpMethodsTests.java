package pl.javastart.restassured.test.methods;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import static java.util.Collections.singletonList;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setPhotoUrls(singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(singletonList(tag));
        pet.setStatus("available");

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

    @Test
    public void givenExistingPetWhenUpdatePetNameThenPetIsChangedTest() {
        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setName("Burek");
        pet.setPhotoUrls(singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(singletonList(tag));
        pet.setStatus("available");

        given()
            .log().all()
            .body(pet)
            .contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
        .then()
            .log().all()
            .statusCode(200);

        pet.setName("Reksio");

        given()
            .log().all()
            .body(pet)
                .contentType("application/json")
        .when()
            .put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest() {
        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(345);
        pet.setCategory(category);
        pet.setName("Czarek");
        pet.setPhotoUrls(singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(singletonList(tag));
        pet.setStatus("available");

        given()
            .log().all()
            .body(pet)
            .contentType("application/json")
        .when()
            .post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
        .then()
            .log().all()
            .statusCode(200);

        given()
            .log().all()
            .contentType("application/json")
            .pathParam("petId", 345)
        .when()
            .delete("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
        .then()
            .log().all()
            .statusCode(200);
    }

}
