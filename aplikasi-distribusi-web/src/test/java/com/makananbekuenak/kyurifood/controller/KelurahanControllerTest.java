package com.makananbekuenak.kyurifood.controller;


import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kelurahan;
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
 * @author fidya
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kelurahan.sql"})
@WebIntegrationTest(randomPort = true)
public class KelurahanControllerTest {

    private static final String BASE_URL = "/api/kelurahan";
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

        Kelurahan k = new Kelurahan();
        k.setKode("0013");
        k.setNama("padurenan");

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

        // nama tidak diisi
        Kelurahan kx = new Kelurahan();
        kx.setKode("0013");
        
        given()
                .body(kx)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Kelurahan kx1 = new Kelurahan();
        kx1.setKode("0");
        k.setNama("padure");

        given()
                .body(kx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        Kelurahan kx2 = new Kelurahan();
        kx2.setKode("12345");
        k.setNama("padurenan01");
        
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
                .body("kode", equalTo("001"));

        get(BASE_URL+"/202")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Kelurahan k = new Kelurahan();
        k.setKode("456");
        k.setNama("pabuaran");

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
                .body("nama", equalTo("pabuaran"));
        
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