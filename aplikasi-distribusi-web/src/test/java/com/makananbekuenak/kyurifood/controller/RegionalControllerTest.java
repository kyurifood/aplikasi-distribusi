/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Regional;
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
 * @author rinaldy
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-regional.sql"})
@WebIntegrationTest(randomPort = true)
public class RegionalControllerTest {
    private static final String BASE_URL = "/api/regional";
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

        Regional r = new Regional();
        r.setKode("253");
        r.setNama("attivisi");

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
        Regional px = new Regional();
        px.setKode("253");
        given()
                .body(px)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Regional px1 = new Regional();
        px1.setKode("25");
        r.setNama("artivisi");

        given()
                .body(px1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        // Harga negatif
        Regional px2 = new Regional();
        px2.setKode("257");
        r.setNama("artivisi");
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
                .body("kode", equalTo("250"));

        get(BASE_URL+"/990")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Regional r = new Regional();
        r.setKode("261");
        r.setNama("artivisi intermedia");

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
                .body("kode", equalTo("261"))
                .body("nama", equalTo("artivisi intermedia"));
        
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
