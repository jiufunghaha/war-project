package cn.jiufungqx.count.visit.service.impl;

import cn.jiufungqx.common.pojo.VisitRecode;
import cn.jiufungqx.common.utils.StringUtils;
import cn.jiufungqx.count.visit.service.IpInfoService;
import cn.jiufungqx.count.visit.service.VisitCountService;
import cn.jiufungqx.count.visit.vo.IpInfoVo;
import cn.jiufungqx.mapper.VisitRecodeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jiufung
 * @create: 2019/7/20 17:42
 */
@Log4j2
@Service
public class VisitCountServiceImpl extends ServiceImpl<VisitRecodeMapper, VisitRecode> implements VisitCountService {

    @Autowired
    private VisitRecodeMapper visitRecodeMapper;

    @Override
    public void insetVisitRecode(String ipv4) {
        VisitRecode visitRecode = new VisitRecode();
        visitRecode.setId(StringUtils.getUUID());
        visitRecode.setIpv4(ipv4);
        visitRecode.setVisitTime(new Date());
        int result = visitRecodeMapper.insert(visitRecode);
        if(result > 0){
            log.info("访问记录插入数据库成功，访问ip----" + ipv4);
        }else {
            log.error("访问记录插入数据库失败，访问ip----" + ipv4);
        }
    }

    @Override
    public List<VisitRecode> getNullInfoRecode() {
        Map<String, Object> map = new HashMap<>();
        map.put("country", null);
        map.put("city", null);
        map.put("region", null);
        map.put("isp", null);
        List<VisitRecode> recodeList = visitRecodeMapper.selectByMap(map);
        return recodeList;
    }
}
