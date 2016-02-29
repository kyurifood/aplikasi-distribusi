/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Marketer;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-marketer.sql"})
@WebIntegrationTest(randomPort = true)
public class MarketerControllerTest {

    private static final String BASE_URL = "/api/marketer";
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
                .body("content.id", hasItems("011"));
    }
    
    @Test
    public void testSave() throws Exception {

        Marketer m = new Marketer();
        m.setKode("120");
        m.setNama("martet");
        m.setHp("085397477853");
        m.setPinbb("abcdef");
        m.setEmail("market@gmail.com");
        m.setRekening("0123456890");
        m.setAlamat("jln.Sapta Marga");

        given()
                .body(m)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL + "/"))
                .log()
                .headers();

        // tidak diisi
        Marketer mx = new Marketer();
        mx.setKode("120");

        given()
                .body(mx)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Marketer mx1 = new Marketer();
        mx1.setKode("1");
        mx1.setNama("mar");
        mx1.setHp("085397477");
        mx1.setPinbb("abc");
        mx1.setEmail("market@gmail");
        mx1.setRekening("012345");
        m.setAlamat("jln.Sapta Ma");

        given()
                .body(mx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        // Harga negatif
        Marketer mx2 = new Marketer();
        mx2.setKode("321");
        mx2.setNama("penyedia");
        mx2.setHp("081244423769");
        mx2.setPinbb("123456");
        mx2.setEmail("penyedia@gmail.com");
        mx2.setRekening("9876543210");
        m.setAlamat("jln.Padurenan");

        given()
                .body(mx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindById() {
        get(BASE_URL + "/011")
                .then()
                .statusCode(200)
                .body("id", equalTo("011"))
                .body("kode", equalTo("123"));

        get(BASE_URL + "/202")
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdate() {
        Marketer m = new Marketer();
        m.setKode("345");
        m.setNama("marketer");
        m.setHp("085256871017");
        m.setPinbb("cdefgh");
        m.setEmail("marketer@gmail.com");
        m.setRekening("abcdfeghij");
        m.setAlamat("jln.Trans Sulawesi");

        given()
                .body(m)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL + "/011")
                .then()
                .statusCode(200);

        get(BASE_URL + "/011")
                .then()
                .statusCode(200)
                .body("id", equalTo("011"))
                .body("kode", equalTo("345"))
                .body("alamat", equalTo("jln.Trans Sulawesi"));
                

        // test id salah
        given()
                .body(m)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL + "/012")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDelete() {
        delete(BASE_URL + "/011")
                .then()
                .statusCode(200);

        get(BASE_URL + "/011")
                .then()
                .statusCode(404);

        // test id salah
        delete(BASE_URL + "/012")
                .then()
                .statusCode(404);
    }
}
