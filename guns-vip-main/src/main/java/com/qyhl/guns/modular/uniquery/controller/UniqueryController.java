package com.qyhl.guns.modular.uniquery.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.redis.util.RedisUtil;
import com.qyhl.guns.config.uniquery.Option;
import com.qyhl.guns.config.uniquery.ParentTable;
import com.qyhl.guns.config.uniquery.UniQueryConfig;
import com.qyhl.guns.config.uniquery.UniQueryConstant;
import com.qyhl.guns.modular.uniquery.service.UniQueryService;
import org.apache.commons.collections.ListUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/uniQuery")
public class UniqueryController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String PREFIX = "/uniQuery/";

    @Resource
    private UniQueryConfig uniqueryConfig;
    @Resource
    private UniQueryService uniQueryService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 进入统一查询条件页(多表)
     *
     * @param req
     * @param resp
     * @return 跳转路径
     */
    @RequestMapping(value = "/toUniQuery/{parent}", method = RequestMethod.GET)
    public String toCasUniQuery(@PathVariable String parent, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        String  aaa = (String)redisUtil.get("aaa");
        System.out.println(aaa);
        req.setAttribute("parent", parent);
        // TODO 为了方便配置通用查询配置测试，先不读取缓存中的配置
        Map<String, ParentTable> parentTableMap = UniQueryConstant.getParentTableMap();
        Map<String, List<Option>> selectMap = UniQueryConstant.getSelectMap();
        Map<String, Map<String, String>> resultMap = UniQueryConstant.getResultMap();
        ParentTable parentTable = parentTableMap.get(parent);
        req.setAttribute("parentTable", parentTable);
        req.setAttribute("selectMap", selectMap);
        req.setAttribute("resultMap", resultMap);
        if("1".equals(parentTable.getDefaultResult())){  // 直接跳转到有值页面
            return "redirect:/uniQuery/uniQuery?parent="+parent;
        }
        return PREFIX+"queryResult.html";

    }

    /**
     * 统一查询(多表)执行Controller
     *
     * @param req
     * @param resp
     * @return 结果页面
     */
    @RequestMapping(value = "/uniQuery")
    public String casUniQuery(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage = PREFIX + "queryResult.html";
        Map<String, List<Option>> selectMap = UniQueryConstant.getSelectMap();
        Map<String, Map<String, String>> resultMap = UniQueryConstant.getResultMap();
        try {
            String parent = req.getParameter("parent");
            // 从常量类中获取通用查询配置
            // TODO 后续从缓存中获取
            Map<String, ParentTable> parentTableMap = UniQueryConstant.getParentTableMap();
            if (MapUtil.isEmpty(parentTableMap)) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            ParentTable parentTable = parentTableMap.get(parent);
            if (ObjectUtil.isNull(parentTable)) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            resp.setHeader("cache-control","public");// 跳转其他页面后，回退时，拿不到之前列表页面的数据。添加页面缓存

            String showType = parentTable.getShowType();
            req.setAttribute("parentTable", parentTable);
            req.setAttribute("selectMap", selectMap);
            req.setAttribute("resultMap", resultMap);
            this.uniQueryService.queryBySql(req, resp, parentTable);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resultPage;
    }

    @RequestMapping(value = "/exportExcel/{parent}",method = RequestMethod.POST)
    public void exportExcl(@PathVariable String parent, HttpServletRequest req, HttpServletResponse resp){
        String path = "";
        try{
            Map<String, ParentTable> parentTableMap = UniQueryConstant.getParentTableMap();
            if (ObjectUtil.isNull(parentTableMap)) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            ParentTable parentTable = parentTableMap.get(parent);
            if (ObjectUtil.isNull(parentTable)) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            OutputStream os = resp.getOutputStream();
            resp.reset();// 清空输出流
            resp.setHeader("Content-disposition", "attachment; filename="+ URLEncoder.encode(parentTable.getName(),"utf-8")+".xlsx");// 设定输出文件头
            resp.setContentType("application/x-msdownload;charset=UTF-8");
//			resp.setContentType("application/msexcel");// 定义输出类型

            //生成excl 并且返回文件路径
            SXSSFWorkbook wb = uniQueryService.exportExcl(req, resp, parentTable);
            wb.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
