/**
 * projectName: WeChatWorkApiTest
 * fileName: EvnTask.java
 * packageName: com.wechat.task
 * date: 2020-07-18 5:47 下午
 */
package com.wechat.task;

import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.apiobject.UserApiObject;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: EvnTask
 * @packageName: com.wechat.task
 * @description: 环境管理任务
 * @data: 2020-07-18 5:47 下午
 **/
public class EvnTask {
    public static void evnClear(String accessToken) {
        Response listResponse = DepartmentApiObject.listDepartMent("", accessToken);
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for (int departmentId : departmentIdList) {
            if (1 == departmentId) {
                continue;
            }
            DepartmentApiObject.deleteDepartMent(departmentId + "", accessToken);
        }
    }

    public static void clearUser(String departmentid){
        Response listResponse = new UserApiObject().getDepartmentUser(departmentid);
        ArrayList<String> userIdList = listResponse.path("userlist.userid");
        userIdList.stream().forEach(x->{
            if( !x.equals("SuPinRong")){
                new UserApiObject().deleteUser(x);
            }
        });
    }
}