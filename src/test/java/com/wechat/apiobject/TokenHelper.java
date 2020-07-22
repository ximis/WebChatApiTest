/**
 * projectName: WeChatWorkApiTest
 * fileName: TokenHelper.java
 * packageName: com.wechat.apiobject
 * date: 2020-07-18 5:42 下午
 */
package com.wechat.apiobject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: TokenHelper
 * @packageName: com.wechat.apiobject
 * @description: Token相关工具类
 * @data: 2020-07-18 5:42 下午
 **/
public class TokenHelper {
    public static String getAccessToken() {
        String access_token = given().param("corpid", "wwd182224c9f58fd5c")
                .param("corpsecret","ia7zwzXvGTJENOja0LT0tHSFdVZGQKk5C_sOlrtR26o" )
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .body("errcode", equalTo(0))
                .extract().path("access_token")
                ;
        return access_token;
    }
}