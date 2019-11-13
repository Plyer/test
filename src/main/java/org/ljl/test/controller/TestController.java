package org.ljl.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.ljl.test.domain.component.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvjinglu
 * created at 2019/11/13
 */
@Slf4j
@RestController
@RequestMapping("/testCenter")
public class TestController {

    public TestController() {
        log.info("-----创建成功-----");
    }

    @GetMapping("/test/{id}")
    public Response test(@PathVariable Integer id) {
        return Response.success(id + "你好");
    }
}
