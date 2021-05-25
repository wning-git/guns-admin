package com.qyhl.guns.modular.uniquery.util;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.sys.core.util.ReflectUtil;
import com.qyhl.guns.config.uniquery.Link;
import com.qyhl.guns.config.uniquery.Param;
import lombok.SneakyThrows;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.util.List;

public class QueryRowsHeight implements Function {
    @SneakyThrows
    @Override
    public Object call(Object[] objects, Context context) {
        Integer height = 0;
        Integer queryNums = (Integer) objects[0];// 获取link对象
        Double rows = Math.ceil(queryNums/3);
        height = rows.intValue()*48 + 105;
        return height;
    }
}
