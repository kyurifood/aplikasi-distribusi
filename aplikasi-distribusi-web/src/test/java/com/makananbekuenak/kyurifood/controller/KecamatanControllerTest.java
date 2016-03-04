package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kecamatan;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kecamatan.sql"})
@WebIntegrationTest(randomPort = true)
public class KecamatanControllerTest {

    private static final String BASE_URL = "/api/kecamatan";
    @Value("${local.server.port}")
    int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }
    
    @Test
    public void tesFindAll() {
        get(BASE_URL+"/")
                .then()
                .body("totalElements", equalTo(1))
                .body("content.id", hasItems("kec"));
    }

    @Test
    public void testSave() throws Exception {

        Kecamatan k = new Kecamatan();
        k.setKode("kecamatan 001");
        k.setNama("Cibinong");

        given()
                .body(k)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL + "/"))
                .log().headers();
        
        // nama tidak diisi
        Kecamatan kx = new Kecamatan();
        kx.setKode("kecamatan 001");
        
        given()
                .body(kx)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
        
        // kode kurang dari 3 huruf
        Kecamatan kx1 = new Kecamatan();
        kx1.setKode("12");
        k.setNama("Cibinong Test");
        
        given()
                .body(kx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
        
        // Harga negatif
        Kecamatan kx2 = new Kecamatan();
        kx2.setKode("PT-009");
        kx2.setNama("Test");
        
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
        get(BASE_URL+"/kec")
                .then()
                .statusCode(200)
                .body("id", equalTo("kec"))
                .body("kode", equalTo("001"));

        get(BASE_URL+"/990")
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdate() {
        Kecamatan k = new Kecamatan();
        k.setKode("KX-009");
        k.setNama("Kecamatan 909");

        given()
                .body(k)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/kec")
                .then()
                .statusCode(200);

        get(BASE_URL+"/kec")
                .then()
                .statusCode(200)
                .body("id", equalTo("kec"))
                .body("kode", equalTo("KX-009"))
                .body("nama", equalTo("Kecamatan 909"));
        
        // test id salah
        given()
                .body(k)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/xyz456")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDelete() {
        delete(BASE_URL+"/kec")
                .then()
                .statusCode(200);

        get(BASE_URL+"/kec")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/xyz123")
                .then()
                .statusCode(404);
    }
}
