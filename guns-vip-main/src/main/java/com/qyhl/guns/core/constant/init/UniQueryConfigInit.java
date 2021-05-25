package com.qyhl.guns.core.constant.init;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.sys.modular.consts.entity.SysConfig;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import com.qyhl.guns.config.uniquery.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 参数配置 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-20
 */
@Component
@Slf4j
public class UniQueryConfigInit implements CommandLineRunner {

    @Autowired
    private UniQueryConfig uniQueryConfig;
    @Autowired
    private DictService dictService;

    @Override
    public void run(String... args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        log.info("通用查询配置加载开始。");
        Map<String, List<Option>> selectMap = new HashMap<>();
        Map<String, Map<String, String>> resultMap = new HashMap<>();
        Map<String, ParentTable> parentTableMap = new HashMap<>();
        List<Select> selectList = uniQueryConfig.getSelectList();
        for (Select select : selectList) {
            String selectMapKey = select.getSelectMapKey();
            String resultMapKey = StringUtils.isEmpty(select.getResultMapKey())?selectMapKey:select.getResultMapKey();

            List<Option> options = new ArrayList<>();// 下拉选项值的列表
            Map<String, String> results = new HashMap<String, String>();//显示结果
            options.add(new Option("", "全部"));

            if ("0".equals(select.getSelectSourceType())) {
                // 枚举类型
                if (!StrUtil.isEmpty(select.getSelectSource())) {
                    // 1.得到枚举类对象
                    Class clz = Class.forName(select.getSelectSource());
                    // 2.得到所有枚举常量
                    Object[] objects = clz.getEnumConstants();
                    Method getIndex = clz.getMethod("getCode");
                    Method getDesc = clz.getMethod("getMessage");
                    for (Object obj : objects){
                        Option option = new Option();
                        String code = String.valueOf(getIndex.invoke(obj));
                        String message = String.valueOf(getDesc.invoke(obj));
                        option.setValue(code);
                        option.setShow(message);
                        String defaultValue = select.getDefaultValue();
                        if (!StrUtil.isEmpty(defaultValue)) {
                            if (defaultValue.equals(option.getValue())) {
                                option.setSelect(true);
                            }
                        }
                        options.add(option);
                        results.put(code, message);
                    }

                }
            } else if ("1".equals(select.getSelectSourceType())) {
                // 字典表
                if (!StrUtil.isEmpty(select.getSelectSource())) {
                    List<Dict> dictList = dictService.listDicts(Long.valueOf(select.getSelectSource()));
                    for (Dict dict : dictList) {
                        Option option = new Option();
                        String code = dict.getCode();
                        String message = dict.getName();
                        option.setValue(code);
                        option.setShow(message);
                        String defaultValue = select.getDefaultValue();
                        if (!StrUtil.isEmpty(defaultValue)) {
                            if (defaultValue.equals(option.getValue())) {
                                option.setSelect(true);
                            }
                        }
                        options.add(option);
                        results.put(code, message);
                    }
                }

            } else {
                // 其他
            }
            selectMap.put(selectMapKey, options);
            resultMap.put(resultMapKey, results);


        }
        UniQueryConstant.getSelectMap().putAll(selectMap);
        UniQueryConstant.getResultMap().putAll(resultMap);

        // 设置parentTableMap，用于根据id获取parentTable对象，用于查询条件和查询结构的显示
        List<ParentTable> parentTableList = uniQueryConfig.getParentTableList();
        for (ParentTable parentTable : parentTableList) {
            parentTableMap.put(parentTable.getId(), parentTable);
            List<Link> linkList = parentTable.getLinkList();
            if (!linkList.isEmpty()) {
                Map<String, Link> linkMap = linkList.stream().collect(Collectors.toMap(Link::getLocation, a -> a, (k1, k2) -> k1));
                parentTable.setLinkMap(linkMap);
            }
        }
        UniQueryConstant.setParentTableMap(parentTableMap);
        log.info("通用查询配置加载结束。");

    }
}
