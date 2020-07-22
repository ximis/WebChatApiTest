package com.wechat.apiobject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserApiObject {
    public Response getUser(String userid) {
        return given()
                .queryParam("access_token", TokenHelper.getAccessToken())
                .queryParam("userid", userid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all()
                .extract().response();
    }

    public Response getDepartmentUser(String departmentid) {
        return given()
                .queryParam("access_token", TokenHelper.getAccessToken())
                .queryParam("department_id", departmentid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all()
                .extract().response();
    }

    public Response update(String userid, HashMap<String, Object> data) {
        data.put("userid", userid);
        return given()
                .queryParam("access_token", TokenHelper.getAccessToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all().extract().response();

    }


    public Response create(String userid, HashMap<String, Object> data) {
        data.put("userid", userid);
        return given()
                .queryParam("access_token", TokenHelper.getAccessToken())
                .contentType(ContentType.JSON)
                .body(data)
                .log().all()
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public Response deleteUser(String userid) {
        return given()
                .queryParam("access_token", TokenHelper.getAccessToken())
                .queryParam("userid", userid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all()
                .extract().response();
    }

}
