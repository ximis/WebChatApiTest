package com.wechat.testcase;

import com.wechat.apiobject.UserApiObject;
import com.wechat.task.EvnTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

/**
*由于时间关系，补充以下操作
 * 1、用户创建，更新，删除
 * 2、对创建用户的用户名称长度进行长度参数化测试
 * 3、添加一个多线程的测试使用
* */


public class TestUser {
    UserApiObject user = new UserApiObject();

    @Test
    public void getUser(){
        user.getUser("SuPinRong").then().log().all().body("name", equalTo("粟品容"));
    }

    @Test
    public void update(){
        String userid = "SuPinRong";
        String address = "address for testing";

        HashMap<String, Object> data = new HashMap<>();
        data.put("address", address);

        user.update(userid, data).then().log().all().body("errcode", equalTo(0));
        ;
    }

    @BeforeEach
    public void clear(){
        EvnTask.clearUser("1");
    }

    @CsvFileSource(resources = "/data/createUser.csv", numLinesToSkip = 1)
    @ParameterizedTest
    public void create(String name, int errcode){
        String userid = "SuPinRong_" + System.currentTimeMillis();
        String address = "address for testing";
//        String nameNew = "name for test";
        String nameNew = name;

        HashMap<String, Object> data = new HashMap<>();
        data.put("address", address);
        data.put("name", nameNew);
        data.put("department", new int[]{1});
        data.put("email","xxxxx@qq.com");
        data.put("mobile", String.valueOf(System.currentTimeMillis()).substring(0,11));

        user.create(userid, data).then().body("errcode", equalTo(errcode));
        if(errcode == 0){
            user.getUser(userid).then().body("name", equalTo(nameNew));
        }

    }
}
