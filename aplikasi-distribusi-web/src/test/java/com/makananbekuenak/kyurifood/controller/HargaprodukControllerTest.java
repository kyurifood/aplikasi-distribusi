package com.makananbekuenak.kyurifood.controller;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Hargaproduk;
import java.math.BigDecimal;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-hargaproduk.sql"})
@WebIntegrationTest(randomPort = true)
public class HargaprodukControllerTest {
    
    private static final String BASE_URL = "/api/hargaproduk";
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
                .body("content.id", hasItems("abc123"));
    }
    
    @Test
    public void testSave() throws Exception {

        Hargaproduk h = new Hargaproduk();
        h.setProduk("test 002");
        h.setRegional("test abcd");
        h.setHarga(BigDecimal.valueOf(102000.02));
        h.setBerlakumulai("2016-02-01");
        h.setBerlakusampai("2016-03-02");

        given()
                .body(h)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(201)
                .header("Location", containsString(BASE_URL+"/"))
                .log()
                .headers();

        
        Hargaproduk hx = new Hargaproduk();
        hx.setProduk("test 002");
        
        given()
                .body(hx)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);


        Hargaproduk hx1 = new Hargaproduk();
        hx1.setProduk("03");
        hx1.setRegional("abcd");
        hx1.setBerlakumulai("2016-02-02");
        hx1.setBerlakusampai("2016-03-03");
        h.setHarga(BigDecimal.valueOf(100));
        
        

        given()
                .body(hx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);


        Hargaproduk hx2 = new Hargaproduk();
        hx2.setProduk("003");
        hx2.setRegional("tes baca");
        hx2.setBerlakumulai("2016-04-02");
        hx2.setBerlakusampai("2016-06-02");
        h.setHarga(BigDecimal.valueOf(-100));
        
        given()
                .body(hx1)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindById() {
        get(BASE_URL+"/abc123")
                .then()
                .statusCode(200)
                .body("id", equalTo("abc123"))
                .body("produk", equalTo("001"));

        get(BASE_URL+"/990")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testUpdate() {
        Hargaproduk h = new Hargaproduk();
        h.setProduk("333");
        h.setRegional("Reg-22");
        h.setHarga(BigDecimal.valueOf(102000.03));
        h.setBerlakumulai("2018-01-01");
        h.setBerlakusampai("2018-02-02");

        given()
                .body(h)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/abc123")
                .then()
                .statusCode(200);

        get(BASE_URL+"/abc123")
                .then()
                .statusCode(200)
                .body("id", equalTo("abc123"))
                .body("produk", equalTo("333"))
                .body("regional", equalTo("Reg-22"));
        
        // test id salah
        given()
                .body(h)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+"/xyz456")
                .then()
                .statusCode(404);
    }
    
    @Test
    public void testDelete() {
        delete(BASE_URL+"/abc123")
                .then()
                .statusCode(200);

        get(BASE_URL+"/abc123")
                .then()
                .statusCode(404);
        
        // test id salah
        delete(BASE_URL+"/xyz123")
                .then()
                .statusCode(404);
    }
    
}
