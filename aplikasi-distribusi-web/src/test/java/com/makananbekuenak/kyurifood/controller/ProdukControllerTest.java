package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Produk;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-produk.sql"})
@WebIntegrationTest(randomPort = true)
public class ProdukControllerTest {
    
     private static final String BASE_URL = "/api/produk";
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

        Produk p = new Produk();
        p.setKode("1234");
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
        Produk px = new Produk();
        px.setKode("1234");
        
        given()
                .body(px)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Produk px1 = new Produk();
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
        Produk px2 = new Produk();
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
                .body("kode", equalTo("123"));

        get(BASE_URL+"/990")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Produk p = new Produk();
        p.setKode("456");
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
                .body("kode", equalTo("456"))
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
