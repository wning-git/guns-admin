package cn.stylefeng.guns.sys.modular.rest.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.rest.entity.RestDictType;
import cn.stylefeng.guns.sys.modular.rest.service.RestDictTypeService;
import cn.stylefeng.guns.sys.modular.system.model.params.DictTypeParam;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 字典类型表控制器
 *
 * @author stylefeng
 * @Date 2019-03-13 1:54
 */
@RestController
@RequestMapping("/rest/dictType")
@Tag(name = "RestDictTypeController", description = "字典类型API接口")
public class RestDictTypeController extends BaseController {

    @Autowired
    private RestDictTypeService restDictTypeService;

    /**
     * 新增接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @Operation(summary = "添加字典类型", description = "添加一个字典类型",tags = {"字典类型"})
    @RequestMapping(path = "/addItem", method = RequestMethod.POST)
    public ResponseData addItem(@Parameter(description = "字典类型参数", required = true) @RequestBody DictTypeParam dictTypeParam) {
        this.restDictTypeService.add(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/editItem",method = RequestMethod.PUT)
    public ResponseData editItem(@RequestBody DictTypeParam dictTypeParam) {
        this.restDictTypeService.update(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public ResponseData delete(@RequestBody DictTypeParam dictTypeParam) {
        this.restDictTypeService.delete(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/detail",method = RequestMethod.GET)
    public ResponseData detail(@RequestBody DictTypeParam dictTypeParam) {
        RestDictType detail = this.restDictTypeService.getById(dictTypeParam.getDictTypeId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public LayuiPageInfo list(@RequestBody DictTypeParam dictTypeParam) {
        return this.restDictTypeService.findPageBySpec(dictTypeParam);
    }

    /**
     * 查询所有字典
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/listTypes",method = RequestMethod.GET)
    public ResponseData listTypes() {

        QueryWrapper<RestDictType> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.select("dict_type_id", "code", "name");

        List<RestDictType> list = this.restDictTypeService.list(objectQueryWrapper);
        return new SuccessResponseData(list);
    }

}


