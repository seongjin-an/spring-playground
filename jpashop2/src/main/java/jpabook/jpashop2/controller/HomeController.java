package jpabook.jpashop2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
//    Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/")
    public String home() {
        log.info("HOME CONTROLLER!!!");
        return "home";
    }
}
