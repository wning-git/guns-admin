package cn.stylefeng.guns.sys.modular.system.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author stylefeng
 * @since 2019-03-13
 */
@Data
@Schema(name = "DictTypeParam", description = "字典类型请求参数封装类")
public class DictTypeParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 字典类型id
     */
    @Schema(description = "字典类型id", type = "long")
    private Long dictTypeId;

    /**
     * 是否是系统字典，Y-是，N-否
     */
    @Schema(description = "是否是系统字典，Y-是，N-否", type = "string", allowableValues = {"Y", "N"})
    private String systemFlag;

    /**
     * 字典类型编码
     */
    @Schema(type = "string", description = "字典类型编码")
    private String code;

    /**
     * 字典类型名称
     */
    @Schema(type = "string", description = "字典类型名称")
    private String name;

    /**
     * 字典描述
     */
    @Schema(type = "string", description = "字典描述", defaultValue = "")
    private String description;

    /**
     * 状态(字典)
     */
    @Schema(type = "string", description = "状态(字典)", allowableValues = {"ENABLE","DISABLE"},defaultValue = "ENABLE")
    private String status;

    /**
     * 查询条件
     */
    @Schema(type = "string", description = "查询条件", defaultValue = "")
    private String condition;

    /**
     * 排序
     */
    @Schema(type = "int", description = "排序")
    private Integer sort;

    /**
     * 字典类型编码
     */
    @Schema(type = "string", description = "字典类型编码")
    private String dictTypeCode;

    @Override
    public String checkParam() {
        return null;
    }

}
