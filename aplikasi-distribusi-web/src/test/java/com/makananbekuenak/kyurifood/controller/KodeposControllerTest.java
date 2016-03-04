package com.makananbekuenak.kyurifood.controller;
 

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kodepos;
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
 * @author 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kodepos.sql"})
@WebIntegrationTest(randomPort = true)
public class KodeposControllerTest {

    private static final String BASE_URL = "/api/kodepos";
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
                .body("content.id", hasItems("abcd"));
    }

    @Test
    public void testSave() throws Exception {

        Kodepos u = new Kodepos();
        u.setKode("1233");
        u.setNama("artivisi");
        
       
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

        // nama tidak diisi
        Kodepos ux = new Kodepos();
        ux.setKode("1233");
        
        given()
                .body(ux)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Kodepos ux1 = new Kodepos();
        ux1.setKode("12");
        u.setNama("artivisi");
        
        given()
                .body(ux1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        Kodepos ux2 = new Kodepos();
        ux2.setKode("1232");
        u.setNama("artivisi");
        
        
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
        get(BASE_URL+"/abcd")
                .then()
                .statusCode(200)
                .body("id", equalTo("abcd"))
                .body("kode", equalTo("1234"));

        get(BASE_URL+"/202")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Kodepos u = new Kodepos();
        u.setKode("12345");
        u.setNama("artivisi");
        

        given()
                .body(u)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/abcd")
                .then()
                .statusCode(200);

        get(BASE_URL+"/abcd")
                .then()
                .statusCode(200)
                .body("id", equalTo("abcd"))
                .body("kode", equalTo("12345"))
                .body("nama", equalTo("artivisi"));
        
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
        delete(BASE_URL+"/abcd")
                .then()
                .statusCode(200);

        get(BASE_URL+"/abcd")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/xyza")
                .then()
                .statusCode(404);
    }
}
