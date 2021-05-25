package com.qyhl.guns.modular.uniquery.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.sys.core.auth.LoginContextSpringSecutiryImpl;
import cn.stylefeng.guns.sys.core.util.ReflectUtil;
import cn.stylefeng.guns.sys.modular.consts.service.impl.SysConfigServiceImpl;
import com.qyhl.guns.config.uniquery.ParentTable;
import com.qyhl.guns.config.uniquery.Result;
import com.qyhl.guns.config.uniquery.UniQueryConstant;
import com.qyhl.guns.modular.uniquery.dao.UniQueryDao;
import com.qyhl.guns.modular.uniquery.util.SqlBuilder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UniQueryService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UniQueryDao uniQueryDao;

    @Resource
    private LoginContextSpringSecutiryImpl loginContextSpringSecutiryImpl;

    @Resource
    private SysConfigServiceImpl sysConfigServiceImpl;

    /**
     * 统一查询(多表)
     */
    @SuppressWarnings("unchecked")
    public String queryBySql(HttpServletRequest req, HttpServletResponse resp, ParentTable parentTable) throws Exception {
        // 获取查询的parentID
        // String parentTid = (String) req.getAttribute("parent");
        // ParentTable pt = UniCasQueryConstant.getCasSysConfigMap().get(parentTid);

        // String parentTid = req.getParameter("parent");
        // ParentTable parentTable = uniqueryConfigCacheService.getUniqueryConfigMap().get(parentTid);

        String sql = SqlBuilder.getSqlFromReq(req, parentTable);
        String sql1 = SqlBuilder.getCountSqlFromReq(sql, parentTable);
        logger.debug("\n*** 统一查询条数sql ***\n" + sql1);
        logger.debug("\n*** 统一查询sql ***\n" + sql);
        int pageNum = 1;
        int size = 10;// 标准是20条
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        if (StrUtil.isNotEmpty(pageNo)) {
            pageNum = Convert.toInt(pageNo);
        }
        if (StrUtil.isNotEmpty(pageSize)) {
            size = Convert.toInt(pageSize);
        }

        int count = this.uniQueryDao.queryForInt(sql1);// ??
        // int count = this.uniQueryJdbcDao.queryList(sql1,Class.forName(parentTable.getClazz()).newInstance().getClass());
//        PageTool tool = new PageTool(pageNum, count, size);
//        String url = HqlBuilder.getUrlFromReq(req, parentTable);
//        tool.setUrl(url);
        List<Object> list1 = this.uniQueryDao.queryList(sql, (pageNum-1)*size, size, Class.forName(parentTable.getClazz()).newInstance().getClass());
        req.setAttribute("queryResult", list1);
        req.setAttribute("count", count);
        req.setAttribute("pageSize", size);
        req.setAttribute("pageNo", pageNum);

//        req.setAttribute("tool", tool);
        return null;
    }

    public SXSSFWorkbook exportExcl(HttpServletRequest req, HttpServletResponse resp, ParentTable parentTable) throws Exception {

        String sql = SqlBuilder.getSqlFromReq(req, parentTable);
        String sql1 = SqlBuilder.getCountSqlFromReq(sql, parentTable);
        logger.debug("\n*** 统一查询条数sql ***\n" + sql1);
        logger.debug("\n*** 统一查询sql ***\n" + sql);

        int count = this.uniQueryDao.queryForInt(sql1);// ??
        // int count = this.uniQueryJdbcDao.queryList(sql1,Class.forName(parentTable.getClazz()).newInstance().getClass());
        LoginUser user = loginContextSpringSecutiryImpl.getUser();
//        CustomerUser user = SessionUtil.getUser(req.getSession());
        SXSSFWorkbook wb =  outToExcel(sql,count,parentTable,user);
        return wb;
    }

    /**
     * @throws Exception
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @描述:
     * @方法名: outToExcel
     * @param sql
     * @param count
     * @param parentTable
     * @返回类型 void
     * @创建人 weiqh	@创建时间 2017年5月26日 下午4:33:51
     * @修改人 weiqh	@修改时间 2017年5月26日 下午4:33:51
     * @修改备注
     * @since
     * @throws
     */
    private SXSSFWorkbook outToExcel(String sql, int count, ParentTable parentTable,LoginUser user) throws Exception {


        int reportRveryTimeQueryNumber = 2000;
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        wb.setCompressTempFiles(true);
        Sheet sheet = wb.createSheet("Sheet1");
        Font font = wb.createFont();

        font.setFontName("宋体");
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
/*		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);*/

        Row rowHeaderTitle = sheet.createRow(0);
        Cell headerCell = rowHeaderTitle.createCell((short) 0);
        headerCell.setCellValue("名称");
        headerCell.setCellStyle(style);
        Cell headerCellname = rowHeaderTitle.createCell((short) 1);
        headerCellname.setCellValue(parentTable.getName());
        headerCellname.setCellStyle(style);

        Cell headerCelltime = rowHeaderTitle.createCell((short) 2);
        headerCelltime.setCellValue("创建时间");
        headerCelltime.setCellStyle(style);
        Cell headerCelltimes = rowHeaderTitle.createCell((short) 3);
        Cell headerCelltime1 = rowHeaderTitle.createCell((short) 4);
        SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        headerCelltimes.setCellValue(datetemp.format(new Date()));
        headerCelltimes.setCellStyle(style);
        CellRangeAddress address = new CellRangeAddress(0, 0, 3, 4);
        sheet.addMergedRegion(address);


        Cell headerCelluser = rowHeaderTitle.createCell((short) 5);
        headerCelluser.setCellValue("创建人");
        headerCelluser.setCellStyle(style);
        Cell headerCellusers = rowHeaderTitle.createCell((short) 6);
        Cell headerCelluser1 = rowHeaderTitle.createCell((short) 7);
        headerCellusers.setCellValue(user.getName());
        headerCellusers.setCellStyle(style);
        CellRangeAddress addres = new CellRangeAddress(0, 0, 6, 7);
        sheet.addMergedRegion(addres);
        // 表头
        List<Result> results =  parentTable.getResultList();
        Row rowTitle = sheet.createRow(2);
        int t = 0;
        for(int j = 0; j < results.size(); j++){
            Result result = results.get(j);
            if(result.getIsshow() == 1 ){
                Cell cell = rowTitle.createCell((short)t);
                cell.setCellValue(result.getLabel());
                cell.setCellStyle(style);
                t++;
            }
        }


        // 查询次数
        int queryTimes = Double.valueOf(Math.ceil(Double.valueOf(count) / reportRveryTimeQueryNumber))
                .intValue();
        logger.info("报表数据条数为:" + count + "; 分为" + queryTimes + "次查询;");
        int eachNum = 0;
        int querySumNum = 0;

        for (int i = 0; i < queryTimes; i++) {
            int begin = i * reportRveryTimeQueryNumber;
            int end = (i + 1) * reportRveryTimeQueryNumber;
            if (end > count) {
                end = count;
            }
            List<Object> list1 = this.uniQueryDao.queryList(sql,begin,reportRveryTimeQueryNumber,Class.forName(parentTable.getClazz()).newInstance().getClass());
            if (CollUtil.isNotEmpty(list1)) {
                //查询要列的列头】
                for(Object object:list1){
                    querySumNum++;
                    int k = 0;
                    Row row = sheet.createRow(eachNum + 3);
                    for(Result result : results){
                        if(result.getIsshow() == 1){
                            Cell cell = row.createCell((short) k);
                            String value = "";
                            Object obj = ReflectUtil.invokeGetMethod(object, result.getAliasName());
                            if(StrUtil.isNotEmpty(result.getResultMapKey())){
                                value = UniQueryConstant.getResultMap().get(result.getResultMapKey()).get(obj.toString());
                            }else{
                                value = ObjectUtil.isNotNull(obj)?obj.toString():"";
                            }

                            if ("double".equals(result.getColumntype())) {
                                if (StrUtil.isNotEmpty(value)) {
                                    cell.setCellValue(Convert.toDouble(value, 0.0));

                                } else {
                                    cell.setCellValue(0D);
                                }
                            }else{
                                cell.setCellValue(value);
                            }

                            k ++;
                        }
                    }
                    // 每当行数达到设置的值就刷新数据到硬盘,以清理内存
                    if (querySumNum % reportRveryTimeQueryNumber == 0) {
                        ((SXSSFSheet) sheet).flushRows();
                        querySumNum = 0;
                    }
                    eachNum++;
                }
            }else{

            }
        }
        // 报表路径，名称
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String filePath = configCacheService.getConfigValueByKey("uploadFilePath");// 相对路径
//        filePath += "exportExcl/";
//        String filename = user.getId() + sf.format(new Date()) + ".xlsx";// 类型/用户ID;
//		saveXls(wb, filePath+filename);
        return wb;
    }

    public void saveXls(Workbook wb, String target) {
        FileOutputStream fOut = null;
        try {
            // 新建一输出文件流
            fOut = new FileOutputStream(FileUtil.touch(target));
            // 把相应的Excel 工作簿存盘
            wb.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
        } catch (Exception e) {
            logger.error("保存excel出错|", e);
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    logger.error("保存excel关闭流错误|", e);
                }
            }
        }
    }
}
