package com.qyhl.guns.modular.uniquery.util;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.sys.core.util.ReflectUtil;
import com.qyhl.guns.config.uniquery.Link;
import com.qyhl.guns.config.uniquery.Param;
import lombok.SneakyThrows;
import org.beetl.core.Context;
import org.beetl.core.Function;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ParameterValue implements Function {
    @SneakyThrows
    @Override
    public Object call(Object[] objects, Context context) {
        String paramValue = "";
        String param = (String) objects[0];// 获取变量名
        HttpServletRequest request = (HttpServletRequest )context.getGlobal("request");
        paramValue = request.getParameter(param);
        return paramValue;
    }
}
