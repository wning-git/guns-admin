package cn.stylefeng.guns.sys.modular.rest.controller;

import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.rest.entity.RestDict;
import cn.stylefeng.guns.sys.modular.rest.service.RestDictService;
import cn.stylefeng.guns.sys.modular.system.model.params.DictParam;
import cn.stylefeng.guns.sys.modular.system.model.result.DictResult;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 基础字典控制器
 *
 * @author stylefeng
 * @Date 2019-03-13 13:53:53
 */
@RestController
@RequestMapping("/rest/dict")
@Tag(name = "RestDictController", description = "字典词义API接口")
public class RestDictController extends BaseController {

    @Autowired
    private RestDictService restDictService;

    /**
     * 新增接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/addItem", method = RequestMethod.POST)
    public ResponseData addItem(@RequestBody DictParam dictParam) {
        this.restDictService.add(dictParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/editItem",method = RequestMethod.PUT)
    public ResponseData editItem(@RequestBody DictParam dictParam) {
        this.restDictService.update(dictParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public ResponseData delete(@RequestBody DictParam dictParam) {
        this.restDictService.delete(dictParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/detail",method = RequestMethod.GET)
    public ResponseData detail(@RequestBody DictParam dictParam) {
        DictResult dictResult = this.restDictService.dictDetail(dictParam.getDictId());
        return ResponseData.success(dictResult);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public LayuiPageInfo list(@RequestBody DictParam dictParam) {
        return this.restDictService.findPageBySpec(dictParam);
    }

    /**
     * 获取某个字典类型下的所有字典
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/listDicts",method = RequestMethod.GET)
    public ResponseData listDicts(@RequestParam("dictTypeId") Long dictTypeId) {
        List<RestDict> dicts = this.restDictService.listDicts(dictTypeId);
        return new SuccessResponseData(dicts);
    }

    /**
     * 获取某个字典类型下的所有字典
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping(path = "/listDictsByCode",method = RequestMethod.GET)
    public ResponseData listDictsByCode(@RequestParam("dictTypeCode") String dictTypeCode) {
        List<RestDict> dicts = this.restDictService.listDictsByCode(dictTypeCode);
        return new SuccessResponseData(dicts);
    }

    /**
     * 获取某个类型下字典树的列表，ztree格式
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping(value = "/ztree",method = RequestMethod.GET)
    public List<ZTreeNode> ztree(@RequestParam("dictTypeId") Long dictTypeId,
                                 @RequestParam(value = "dictId", required = false) Long dictId) {
        return this.restDictService.dictTreeList(dictTypeId, dictId);
    }

}


