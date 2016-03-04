/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.RestAssured.*;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Distributor;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-distributor.sql"})
@WebIntegrationTest(randomPort = true)
public class DistributorControllerTest {
    
    private static final String BASE_URL = "/api/distributor";
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
                .body("content.id", hasItems("sss"));
    }
    
    @Test
    public void testSave() throws Exception {

        Distributor di = new Distributor();
        di.setKode("0014");
        di.setNama("imin");
        di.setHp("085298182755");
        di.setPinBB("234567");
        di.setEmail("reca@gmail.com");
        di.setRekening("987654321");
        di.setAlamat("jalan pasar rebo");
        
        given()
                .body(di)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log()
                .headers();

        // email tidak diisi
        Distributor dix = new Distributor();
        di.setKode("0014");
        
        
        given()
                .body(dix)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Distributor dix1 = new Distributor();
        dix1.setKode("0");
        
        di.setNama("i");
        di.setHp("085298182");
        di.setPinBB("234");
        di.setEmail("reca@gmail.com");
        di.setRekening("987654");
        di.setAlamat("jalan pasar r");

        given()
                .body(dix1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        Distributor dix2 = new Distributor();
        dix2.setKode("123456");
        
        di.setNama("imin");
        di.setHp("085298182755");
        di.setPinBB("234567");
        di.setEmail("reca@gmail.com");
        di.setRekening("987654321");
        di.setAlamat("jalan pasar rebo");
        
        given()
                .body(dix1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindById() {
        get(BASE_URL+"/sss")
                .then()
                .statusCode(200)
                .body("id", equalTo("sss"))
                .body("kode", equalTo("1234"));

        get(BASE_URL+"/202")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Distributor di = new Distributor();
        di.setKode("0015");
        di.setNama("suparmin");
        di.setHp("085298182766");
        di.setPinBB("234599");
        di.setEmail("recanansyah@gmail.com");
        di.setRekening("987654322");
        di.setAlamat("jalan pasar kamis");

        given()
                .body(di)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/sss")
                .then()
                .statusCode(200);

        get(BASE_URL+"/sss")
                .then()
                .statusCode(200)
                .body("id", equalTo("sss"))
                .body("kode", equalTo("0015"))
                .body("nama", equalTo("suparmin"))
                .body("hp", equalTo("085298182766"))
                .body("pinBB", equalTo("234599"))
                .body("email", equalTo("recanansyah@gmail.com"))
                .body("rekening", equalTo("987654322"))
                .body("alamat", equalTo("jalan pasar kamis"));
        
        // test id salah
        given()
                .body(di)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testDelete() {
        delete(BASE_URL+"/sss")
                .then()
                .statusCode(200);

        get(BASE_URL+"/sss")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
}
