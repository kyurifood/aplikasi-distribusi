/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Role;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-role.sql"})
@WebIntegrationTest(randomPort = true)
public class RoleControllerTest {

    private static final String BASE_URL = "/api/role";
    @Value("${local.server.port}")
    int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testFindAll() {
        get(BASE_URL + "/")
                .then()
                .body("totalElements", equalTo(1))
                .body("content.id", hasItems("1"));
    }

    @Test
    public void testSave() {

        Role r = new Role();
        r.setKode("213");
        r.setNama("kyuri");

        given()
                .body(r)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log().headers();

        // nama tidak diisi
        Role px = new Role();
        px.setKode("213");
        given()
                .body(px)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Role px1 = new Role();
        px1.setKode("21");
        r.setNama("kyuri");

        given()
                .body(px1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        // Harga negatif
        Role px2 = new Role();
        px2.setKode("217");
        r.setNama("kyuri");
        given()
          .body(px1)
          .contentType(ContentType.JSON)
          .when()
          .post(BASE_URL+"/")
          .then()
          .statusCode(400);


    }
    
    @Test
    public void testFindById() {
        get(BASE_URL+"/1")
                .then()
                .statusCode(200)
                .body("id", equalTo("1"))
                .body("kode", equalTo("212"));

        get(BASE_URL+"/990")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Role r = new Role();
        r.setKode("231");
        r.setNama("kyurifood");

        given()
                .body(r)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/1")
                .then()
                .statusCode(200);

        get(BASE_URL+"/1")
                .then()
                .statusCode(200)
                .body("id", equalTo("1"))
                .body("kode", equalTo("231"))
                .body("nama", equalTo("kyurifood"));
        
        // test id salah
        given()
                .body(r)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/013")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testDelete() {
        delete(BASE_URL+"/1")
                .then()
                .statusCode(200);

        get(BASE_URL+"/1")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/013")
                .then()
                .statusCode(404);
    }
}