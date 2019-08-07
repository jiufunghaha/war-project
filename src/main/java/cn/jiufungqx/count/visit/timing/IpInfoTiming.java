package cn.jiufungqx.count.visit.timing;

import cn.jiufungqx.common.pojo.VisitRecode;
import cn.jiufungqx.count.visit.service.IpInfoService;
import cn.jiufungqx.count.visit.service.VisitCountService;
import cn.jiufungqx.count.visit.vo.IpInfoVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: jiufung
 * @create: 2019/7/27 23:16
 */
@Log4j2
@Component
@Configuration
@EnableScheduling
public class IpInfoTiming {

    @Autowired
    private VisitCountService visitCountService;

    @Autowired
    private IpInfoService ipInfoService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void addIpInfo(){
        List<VisitRecode> recodeList = visitCountService.getNullInfoRecode();
        for(VisitRecode recode : recodeList){
            String ipv4 = recode.getIpv4();
            IpInfoVo ipInfo = ipInfoService.getIpInfo(ipv4);
            if(ipInfo != null){
                recode.setCountry(ipInfo.getCountry());
                recode.setIsp(ipInfo.getIsp());
                recode.setCity(ipInfo.getCity());
                recode.setRegion(ipInfo.getRegion());
                visitCountService.updateById(recode);
            }
        }
        log.info("定时任务");

    }

}
