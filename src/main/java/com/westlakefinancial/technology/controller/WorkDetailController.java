package com.westlakefinancial.technology.controller;

import com.westlakefinancial.technology.entity.WorkInfo;
import com.westlakefinancial.technology.service.WorkDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Business Controller
 *
 * @author jiapeng.wu
 */
@Slf4j
@RestController
@RequestMapping("/workDetail")
public class WorkDetailController {

    @Autowired
    private WorkDetailService workDetailService;

    @PostMapping(value = "/addWork")
    public String addWork(@RequestBody WorkInfo workInfo) {
        if (workDetailService.addWork(workInfo)) {
            return "Add work info success";
        }
        return "Add work info failed";
    }

    @PostMapping(value = "/modifyWork")
    public String modifyWork(@RequestBody WorkInfo workInfo) {
        if (workDetailService.modifyWork(workInfo)) {
            return "Modify work info success";
        }
        return "Modify work info failed";
    }
}
