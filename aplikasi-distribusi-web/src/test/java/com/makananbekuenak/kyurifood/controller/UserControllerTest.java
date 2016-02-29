package com.makananbekuenak.kyurifood.controller;


import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.User;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author gilang
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-user.sql"})
@WebIntegrationTest(randomPort = true)
public class UserControllerTest {

    private static final String BASE_URL = "/api/user";
    @Value("${local.server.port}")
    int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }
    
    @Test
    public void testFindAll() {
        get(BASE_URL+"/")
                .then()
                .body("totalElements", equalTo(1))
                .body("content.id", hasItems("001"));
    }

    @Test
    public void testSave() throws Exception {

        User u = new User();
        u.setUsername("artivisi");
        u.setEmail("artivisi01@gmail.com");
        u.setFullname("artivisi intermedia");

        given()
                .body(u)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log()
                .headers();

        // email tidak diisi
        User ux = new User();
        ux.setUsername("artivisi");
        
        given()
                .body(ux)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        User ux1 = new User();
        ux1.setUsername("arti");
        ux1.setEmail("artivisi@gmail");
        u.setFullname("artivisi interm");

        given()
                .body(ux1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        User ux2 = new User();
        ux2.setUsername("kyurifood");
        ux2.setEmail("kyurifood@gmail.com");
        u.setFullname("Kyurifood");
        
        given()
                .body(ux1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindById() {
        get(BASE_URL+"/001")
                .then()
                .statusCode(200)
                .body("id", equalTo("001"))
                .body("username", equalTo("artivisi"));

        get(BASE_URL+"/202")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        User u = new User();
        u.setUsername("makananenak");
        u.setEmail("makanan@gmail.com");
        u.setFullname("makanan beku enak");

        given()
                .body(u)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/001")
                .then()
                .statusCode(200);

        get(BASE_URL+"/001")
                .then()
                .statusCode(200)
                .body("id", equalTo("001"))
                .body("username", equalTo("makananenak"))
                .body("fullname", equalTo("makanan beku enak"));
        
        // test id salah
        given()
                .body(u)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testDelete() {
        delete(BASE_URL+"/001")
                .then()
                .statusCode(200);

        get(BASE_URL+"/001")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
}
