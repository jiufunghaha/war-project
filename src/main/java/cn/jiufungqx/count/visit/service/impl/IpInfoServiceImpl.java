package cn.jiufungqx.count.visit.service.impl;

import cn.jiufungqx.count.visit.service.IpInfoService;
import cn.jiufungqx.count.visit.vo.IpInfoVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: jiufung
 * @create: 2019/7/20 21:51
 */
@Log4j2
@Service
public class IpInfoServiceImpl implements IpInfoService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public IpInfoVo getIpInfo(String ipv4) {
        IpInfoVo ipInfo = null;
        try {
            RestTemplate build = restTemplateBuilder.build();
            String url = "http://ip.aliyun.com/service/getIpInfo.php?ip=" + ipv4;
            String result = build.getForObject(url, String.class);
            JSONObject obj = JSONObject.parseObject(result);
            ipInfo = JSONObject.parseObject(obj.get("data").toString(), IpInfoVo.class);
            log.info("IP信息--------" + result);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return ipInfo;
    }
}
