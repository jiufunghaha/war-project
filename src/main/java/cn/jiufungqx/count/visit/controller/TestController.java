package cn.jiufungqx.count.visit.controller;

import cn.jiufungqx.common.pojo.VisitRecode;
import cn.jiufungqx.count.visit.service.VisitCountService;
import cn.jiufungqx.mapper.VisitRecodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: jiufung
 * @create: 2019/7/27 23:44
 */
@RestController
@RequestMapping("/count/visit/test")
public class TestController {

    @Autowired
    private VisitCountService visitCountService;

    @GetMapping("/null/recode")
    public String getNullRecode(){
        List<VisitRecode> recode = visitCountService.getNullInfoRecode();
        System.out.println(recode);
        return "成功";
    }

}
