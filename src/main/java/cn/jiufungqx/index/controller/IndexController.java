package cn.jiufungqx.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: jiufung
 * @create: 2019/7/12 21:12
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "login";
    }

}
