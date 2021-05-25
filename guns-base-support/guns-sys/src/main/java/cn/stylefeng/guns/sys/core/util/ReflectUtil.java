package cn.stylefeng.guns.sys.core.util;

import cn.stylefeng.guns.sys.modular.system.model.result.DictTypeResult;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射工具
 */
public class ReflectUtil {

    public ReflectUtil() {
    }

    /**
     * 使用反射，获取对象指定属性的值
     * @param c 对象所属的类
     * @param fieldName 对象的属性名
     * @param obj 对象本身
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object invokeGetMethod(Object obj, String fieldName) throws InvocationTargetException, IllegalAccessException {
        return cn.hutool.core.util.ReflectUtil.getMethodByNameIgnoreCase(obj.getClass(),"get"+fieldName).invoke(obj);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        DictTypeResult result = new DictTypeResult();
        result.setCode("aaaa");
        System.out.println(invokeGetMethod(result,"code"));
    }
}
