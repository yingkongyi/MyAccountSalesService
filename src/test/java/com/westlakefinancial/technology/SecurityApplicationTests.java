package com.westlakefinancial.technology;

import com.westlakefinancial.technology.entity.UserInfo;
import com.westlakefinancial.technology.web.SystemManagementWeb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private SystemManagementWeb systemManagementWeb;
    @Test
    public void testFeign(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("3");
        userInfo.setUsername("4");
        userInfo.setPassword("5");
        String result = systemManagementWeb.addUser(userInfo);
        System.out.println("testFeign:::::::::::::::::::"+ result);
    }
}
