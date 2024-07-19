package com.chou.uc_module.controller;

import com.chou.common_module.context.ResponseData;
import com.chou.common_module.context.ResponseDataBuilder;
import com.chou.common_module.context.enums.CommonEnum;
import com.chou.common_module.exception.BizException;
import com.chou.uc_module.service.ITxCompanyOrgService;
import com.chou.uc_module.inf.utils.MergeCellUtils;
import com.chou.uc_module.domain.vo.TxCompanyOrgVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Axel
 * @version 1.0
 * @className TxCompanyOrgController
 * @description TODO
 * @date 2022/11/13 14:04
 */

@Slf4j
@RestController
@RequestMapping("/org")
@RefreshScope
public class TxCompanyOrgController {

    public final static String templatePath = "E:\\jast\\组织导入模板.xlsx";

    @Resource
    private ITxCompanyOrgService iTxCompanyOrgService;

    @GetMapping("/page")
    public ResponseData<TxCompanyOrgVO> pageOrg(@RequestBody TxCompanyOrgVO vo) {
        return null;
    }

    @PostMapping("/add")
    public ResponseData<Boolean> addOrg(@RequestBody TxCompanyOrgVO vo) {
        Integer integer = iTxCompanyOrgService.addOrg(vo);
        return ResponseDataBuilder.buildSuccessData(true);

    }

    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        File file = new File(templatePath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!file.exists()) {
            throw new BizException(CommonEnum.SERVER_BUSY.getCode(), CommonEnum.SERVER_BUSY.getMsg());
        }
        List<TxCompanyOrgVO> vos = iTxCompanyOrgService.getListOrg();
        List<TxCompanyOrgVO> rootVOS = vos.stream().filter(e -> {
            return e.getParentId() == -1L;
        }).collect(Collectors.toList());
        Map<Long, List<TxCompanyOrgVO>> orgMaps = vos.stream().collect(Collectors.groupingBy(TxCompanyOrgVO::getParentId));
        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workBook = getWorkBook(fis, suffix);
            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);//水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中*/
            Sheet sheetAt = workBook.getSheetAt(1);
            int startRowIndex = 1;
            int colNum = 3;
            for (int i = 0; i < rootVOS.size(); i++) {
                Row row = sheetAt.createRow(startRowIndex);
                TxCompanyOrgVO vo = rootVOS.get(i);
                List<TxCompanyOrgVO> orgVOS = orgMaps.get(vo.getId());
                for (int j = 0; j < colNum; j++) {
                    if (j == 0) {
                        if (orgVOS == null || orgVOS.isEmpty()) {
                            Cell cell = row.createCell(j);
                            cell.setCellValue(vo.getName());
                            cell.setCellStyle(cellStyle);
                            break;
                        }else if(orgVOS.size() == 1){
                            Cell cell = row.createCell(j);
                            cell.setCellValue(vo.getName());
                            cell.setCellStyle(cellStyle);
                            continue;
                        }
                        // 合并单元格
                        sheetAt.addMergedRegion(new CellRangeAddress(startRowIndex, orgVOS.size() + startRowIndex - 1, 0, 0));
                        Cell cell = row.createCell(j);
                        cell.setCellValue(vo.getName());
                        cell.setCellStyle(cellStyle);
                        continue;
                    }
                    for (int k = 0; k < orgVOS.size(); k++) {
                        if (j == 1) {
                            Cell rowCell = row.createCell(j, CellType.STRING);
                            rowCell.setCellValue(orgVOS.get(k).getName());
                            rowCell.setCellStyle(cellStyle);
                        }
                        if (j == 2) {
                            Cell rowCell = row.createCell(j, CellType.STRING);
                            rowCell.setCellValue(orgVOS.get(k).getOrgCode());
                            rowCell.setCellStyle(cellStyle);
                        }
                    }
                }

                if(orgVOS == null){
                    startRowIndex = startRowIndex + 1;
                }else if(orgVOS.size() == 1){
                    startRowIndex = startRowIndex + 1;
                }else {
                    startRowIndex = orgVOS.size() + 1;
                }
                //startRowIndex = orgVOS == null ? startRowIndex + 1 : orgVOS.size() + 1;
            }


            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            ServletOutputStream out = response.getOutputStream();
            workBook.write(out);
            out.flush();
            out.close();
            workBook.close();
            // 从数据库查数据
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>>", e);
        }
    }

    public static Workbook getWorkBook(FileInputStream fis, String fileSuffix) {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            if (fileSuffix.endsWith("xls")) {
                //2003
                workbook = new HSSFWorkbook(fis);
            } else if (fileSuffix.endsWith("xlsx")) {
                //2007
                workbook = new XSSFWorkbook(fis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    @GetMapping("/downloadTemplateVersion")
    public void downloadTemplateVersion1(HttpServletRequest request, HttpServletResponse response) {
        File file = new File(templatePath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!file.exists()) {
            throw new BizException(CommonEnum.SERVER_BUSY.getCode(), CommonEnum.SERVER_BUSY.getMsg());
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workBook = getWorkBook(fis, suffix);
            String[] title = {"一级组织名称", "二级组织名称", "二级组织编码"};
            String[] field = { "level1_name", "level2_name", "level2_code"};
            List<Map<String, String>> list = Lists.newArrayList();


            Map<String, String> stringMap1 = new HashMap<>();
            stringMap1.put("level1_name", "根节点");
            stringMap1.put("level2_name", "根节点的下级节点");
            stringMap1.put("level2_code", "BM_1");
            list.add(stringMap1);

            Map<String, String> stringMap2 = new HashMap<>();
            stringMap2.put("level1_name", "根节点");
            stringMap2.put("level2_name", "根节点的下级节点啊啊啊111");
            stringMap2.put("level2_code", "BM_2");
            list.add(stringMap2);

            Map<String, String> stringMap3 = new HashMap<>();
            stringMap3.put("level1_name", "根节点");
            stringMap3.put("level2_name", "根节点的下级节点啊啊啊");
            stringMap3.put("level2_code", "BM_3");
            list.add(stringMap3);

            Map<String, String> stringMap4 = new HashMap<>();
            stringMap4.put("level1_name", "顶级父类");
            stringMap4.put("level2_name", "这里是一级");
            stringMap4.put("level2_code", "BM_1");
            list.add(stringMap4);


            Map<String, String> stringMap5 = new HashMap<>();
            stringMap5.put("level1_name", "顶级父类");
            stringMap5.put("level2_name", "这里是另一个级");
            stringMap5.put("level2_code", "BM_2");
            list.add(stringMap5);

            Map<String, String> stringMap6 = new HashMap<>();
            stringMap6.put("level1_name", "顶级父类111");
            stringMap6.put("level2_name", "");
            stringMap6.put("level2_code", "");
            list.add(stringMap6);
            Map<String, String> stringMap7 = new HashMap<>();
            stringMap7.put("level1_name", "顶级父类112");
            stringMap7.put("level2_name", "");
            stringMap7.put("level2_code", "");
            list.add(stringMap7);

            Map<String, String> stringMap8 = new HashMap<>();
            stringMap8.put("level1_name", "顶级父类113");
            stringMap8.put("level2_name", "顶级父类113_1");
            stringMap8.put("level2_code", "BM_1");
            list.add(stringMap8);

            Map<String, String> stringMap9 = new HashMap<>();
            stringMap9.put("level1_name", "顶级父类113");
            stringMap9.put("level2_name", "顶级父类113_2");
            stringMap9.put("level2_code", "BM_2");
            list.add(stringMap9);



            MergeCellUtils.createExcel(field, title, list, new int[]{0},workBook,1);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            ServletOutputStream out = response.getOutputStream();
            workBook.write(out);
            out.flush();
            out.close();
            workBook.close();
        }catch (Exception e){
            log.error(">>>>>>>>>>>>>", e);
        }


    }

}
