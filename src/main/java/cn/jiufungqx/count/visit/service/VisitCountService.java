package cn.jiufungqx.count.visit.service;

import cn.jiufungqx.common.pojo.VisitRecode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: jiufung
 * @create: 2019/7/20 17:42
 */
public interface VisitCountService extends IService<VisitRecode> {

    public void insetVisitRecode(String ipv4);

    public List<VisitRecode> getNullInfoRecode();

}
