package cn.jiufungqx.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: jiufung
 * @create: 2019/8/7 22:19
 */
@Data
@TableName(value = "user")
public class User {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String name;
    private String password;
    private String nickName;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String idNumber;
    private String qq;
    private Integer loginCount;
    private Date createTime;
    private Date updateTime;

}
