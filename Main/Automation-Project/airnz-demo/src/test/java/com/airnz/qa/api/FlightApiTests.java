package com.airnz.qa.api;

import com.airnz.qa.config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.beans.Transient;

/**
 * Rest Assured实现航班API测试（模拟Air NZ后端接口）
 */
public class FlightApiTests {

    @Test
    public void testGetFlightData() {
        // 配置Rest Assured
        RestAssured.baseURI = TestConfig.FLIGHT_API_URL;
    
        // GET请求：模拟查询航班列表
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/1")
        .then()
            .statusCode(200)  // 验证响应状态码
            .body("id", equalTo(1))  // 验证返回数据
            .body("title", notNullValue())
            .log().all();  // 打印响应日志

        System.out.println("Rest Assured GET API Test Passed!");
    }

    @Test
    public void testCreateFlightBooking() {
        // POST请求：模拟创建航班预订
        String requestBody = "{\"flightNumber\":\"ANZ123\",\"passengerCount\":2,\"destination\":\"Auckland\"}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post()
        .then()
            .statusCode(201)  // 验证创建成功
            .body("id", notNullValue())
            .log().all();

        System.out.println("Rest Assured POST API Test Passed!");
    }

}