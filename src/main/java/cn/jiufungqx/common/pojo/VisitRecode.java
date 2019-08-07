package cn.jiufungqx.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: jiufung
 * @create: 2019/7/20 14:56
 */
@Data
@TableName(value = "visit_recode")
public class VisitRecode {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String ipv4;
    private String ipv6;
    private String country;
    private String region;
    private String city;
    private String isp;
    private Date visitTime;

}
