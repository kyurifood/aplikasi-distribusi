package com.makananbekuenak.kyurifood.controller;


import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Customer;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-customer.sql"})
@WebIntegrationTest(randomPort = true)
public class CustomerControllerTest {

    private static final String BASE_URL = "/api/customer";
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
                .body("content.id", hasItems("ac"));
    }
    
    @Test
    public void testSave() throws Exception {

        Customer c = new Customer();
        c.setKode("1323");
        c.setNama("cibinong");
        c.setHp("085240");
        c.setAlamat("bogorr");

        given()
                .body(c)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log()
                .headers();

        // hp tidak diisi
        Customer cx = new Customer();
        cx.setKode("1323");
        cx.setNama("cibinong");
        cx.setAlamat("bogorr");
        
        given()
                .body(cx)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // kode kurang dari 3 huruf
        Customer cx1 = new Customer();
        cx1.setKode("1");
        cx1.setNama("cibin");
        cx1.setHp("085");
        c.setAlamat("bog");

        given()
                .body(cx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);

        // Harga negatif
        Customer cx2 = new Customer();
        cx2.setKode("13236");
        cx2.setNama("cibinong1");
        cx2.setHp("0852402");
        c.setAlamat("bog0rr8");
        
        given()
                .body(cx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindById() {
        get(BASE_URL+"/ac")
                .then()
                .statusCode(200)
                .body("id", equalTo("ac"))
                .body("kode", equalTo("001"));

        get(BASE_URL+"/202")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Customer c = new Customer();
        c.setKode("2801");
        c.setNama("kyurifood");
        c.setHp("083828");
        c.setAlamat("cikaret");

        given()
                .body(c)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/ac")
                .then()
                .statusCode(200);

        get(BASE_URL+"/ac")
                .then()
                .statusCode(200)
                .body("id", equalTo("ac"))
                .body("kode", equalTo("2801"))
                .body("nama", equalTo("kyurifood"))
                .body("hp", equalTo("083828"))
                .body("alamat", equalTo("cikaret"));
        
        // test id salah
        given()
                .body(c)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testDelete() {
        delete(BASE_URL+"/ac")
                .then()
                .statusCode(200);

        get(BASE_URL+"/ac")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/012")
                .then()
                .statusCode(404);
    }
}
