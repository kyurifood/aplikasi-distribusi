/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Permission;
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
 * @author satria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-permission.sql"})
@WebIntegrationTest(randomPort = true)
public class PermissionControllerTest {
    
         private static final String BASE_URL = "/api/permission";
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
                .body("content.id", hasItems("123"));
    }

   @Test
    public void testSave() throws Exception {

        Permission p = new Permission();
        p.setKode("12345");
        p.setNama("city mall");
        
       
        given()
                .body(p)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log()
                .headers();

        // nama tidak diisi
        Permission px = new Permission();
        px.setKode("1234");
        
        given()
                .body(px)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Permission px1 = new Permission();
        px1.setKode("1");
        p.setNama("mall");
        
        given()
                .body(px1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        Permission px2 = new Permission();
        px2.setKode("1232");
        p.setNama("city mall");
        
        
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
        get(BASE_URL+"/123")
                .then()
                .statusCode(200)
                .body("id", equalTo("123"))
                .body("kode", equalTo("1234"));

        get(BASE_URL+"/990")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Permission p = new Permission();
        p.setKode("567");
        p.setNama("Cilandak");
        

        given()
                .body(p)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/123")
                .then()
                .statusCode(200);

        get(BASE_URL+"/123")
                .then()
                .statusCode(200)
                .body("id", equalTo("123"))
                .body("kode", equalTo("567"))
                .body("nama", equalTo("Cilandak"));
        
        // test id salah
        given()
                .body(p)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
    @Test
    public void testDelete() {
        delete(BASE_URL+"/123")
                .then()
                .statusCode(200);

        get(BASE_URL+"/123")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/xyza")
                .then()
                .statusCode(404);
    }
    
}
