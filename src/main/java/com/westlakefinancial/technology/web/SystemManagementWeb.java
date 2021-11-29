package com.westlakefinancial.technology.web;

import com.westlakefinancial.technology.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @InterfaceName SystemManagementWeb
 * @Description TODO
 * @Author james
 * @Date 2021/11/26 13:55
 * @Version 1.0
 **/
@FeignClient(value = "${feign.myAccountSalesService-name}", url = "${feign.myAccountSalesService-url:http:\\localhost:8080}")//开发调试使用
public interface SystemManagementWeb {
    @PostMapping("/systemManagement/addUser")
    String addUser(@RequestBody UserInfo user);
}
