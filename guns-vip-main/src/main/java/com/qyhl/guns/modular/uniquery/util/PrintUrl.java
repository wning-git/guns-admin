package com.qyhl.guns.modular.uniquery.util;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.sys.core.util.ReflectUtil;
import com.qyhl.guns.config.uniquery.Link;
import com.qyhl.guns.config.uniquery.Param;
import lombok.SneakyThrows;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.util.List;

public class PrintUrl implements Function {
    @SneakyThrows
    @Override
    public Object call(Object[] objects, Context context) {
        String urlStr = "";
        Link link = (Link) objects[0];// 获取link对象
        Object obj = objects[1];// 获取数据
        urlStr = link.getHref();
        List<Param> paramList = link.getParamList();
        for (Param param : paramList) {
            if (!StrUtil.contains(urlStr, '?')) {
                urlStr += "?";
            } else {
                urlStr += "&";
            }
            urlStr = urlStr + param.getName() + "=" + ReflectUtil.invokeGetMethod(obj, param.getResultRef());
        }
        return urlStr;
    }
}
