package cn.stylefeng.guns.sys.modular.system.model.result;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统登录日志
 * </p>
 *
 * @author ningsw
 * @since 2020-12-29
 */
@Data
public class LoginLogResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long loginLogId;

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 管理员id
     */
    private Long userId;

    /**
     * 管理员名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否执行成功
     */
    private String succeed;

    /**
     * 具体消息
     */
    private String message;

    /**
     * 登录ip
     */
    private String ipAddress;

}
