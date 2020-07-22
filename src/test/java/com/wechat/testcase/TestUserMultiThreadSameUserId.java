package com.wechat.testcase;

import com.wechat.apiobject.UserApiObject;
import com.wechat.task.EvnTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

public class TestUserMultiThreadSameUserId {
    UserApiObject user = new UserApiObject();

    @BeforeAll
    static void beforeClass(){
        EvnTask.clearUser("1");
    }

    @RepeatedTest(20)
    @Execution(CONCURRENT)
    public void create() {
        System.out.println("thread id: " + Thread.currentThread().getId());
        String userid = "SuPinRong_4567890-yj";
        String address = "address for testing";
        String nameNew = "name for test";

        HashMap<String, Object> data = new HashMap<>();
        data.put("address", address);
        data.put("name", nameNew);
        data.put("department", new int[]{1});
        data.put("email", "xxxxx@qq.com");
        data.put("mobile", String.valueOf(System.currentTimeMillis()).substring(0, 11));

        user.create(userid, data).then().body("errcode", equalTo(0));
    }
}
