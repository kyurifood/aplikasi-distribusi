/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.RestAssured.*;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kabupaten;
import org.junit.Before;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author id-oz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kabupaten.sql"})
@WebIntegrationTest(randomPort = true)
public class KabupatenControllerTest {
    
    private static final String BASE_URL = "/api/kabupaten";
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
                .body("content.id", hasItems("abc"));
    }
    
    @Test
    public void testSave() throws Exception {

        Kabupaten k = new Kabupaten();
        k.setKode("0012");
        k.setNama("bogoronta");
        
        given()
                .body(k)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log()
                .headers();

        // email tidak diisi
        Kabupaten kx = new Kabupaten();
        kx.setKode("0012");
        
        given()
                .body(kx)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Kabupaten kx1 = new Kabupaten();
        kx1.setKode("0");
        k.setNama("bogoro");

        given()
                .body(kx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        Kabupaten kx2 = new Kabupaten();
        kx2.setKode("12345");
        k.setNama("bogor001");
        
        given()
                .body(kx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindById() {
        get(BASE_URL+"/abc")
                .then()
                .statusCode(200)
                .body("id", equalTo("abc"))
                .body("kode", equalTo("123"));

        get(BASE_URL+"/202")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Kabupaten k = new Kabupaten();
        k.setKode("456");
        k.setNama("cibinces");

        given()
                .body(k)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/abc")
                .then()
                .statusCode(200);

        get(BASE_URL+"/abc")
                .then()
                .statusCode(200)
                .body("id", equalTo("abc"))
                .body("kode", equalTo("456"))
                .body("nama", equalTo("cibinces"));
        
        // test id salah
        given()
                .body(k)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testDelete() {
        delete(BASE_URL+"/abc")
                .then()
                .statusCode(200);

        get(BASE_URL+"/abc")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
    
    
}
