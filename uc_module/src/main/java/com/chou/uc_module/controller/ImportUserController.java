package com.chou.uc_module.controller;

import com.chou.common_module.utils.SnowflakeIdWorker;
import com.chou.uc_module.domain.entity.User;
import com.chou.uc_module.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Axel
 * @version 1.0
 * @className ImportUserController
 * @description TODO
 * @date 2022/6/25 12:22
 */

@Slf4j
@RestController
@RequestMapping("/import")
@RefreshScope
public class ImportUserController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/batchImportUser")
    public void batchImportUser(MultipartFile file, HttpServletRequest request) throws IOException {
        List<User> userList = Lists.newArrayList();
        List<String[]> list = parseExcel(file);
        SnowflakeIdWorker worker = new SnowflakeIdWorker(1,1);
        if(CollectionUtils.isNotEmpty(list)){
            for (String[] strings : list) {
                User user = User.builder().id(worker.nextId()).userName(strings[0]).deposit(Integer.valueOf(strings[1])).build();
                userList.add(user);
            }
        }
        // 数据库保存导入的值
        iUserService.saveBatch(userList);

    }

    private List<String[]> parseExcel(MultipartFile file) throws IOException {
        List<String[]> list = Lists.newArrayList();
        Workbook workBook = getWorkBook(file);
        int numberOfSheets = workBook.getNumberOfSheets();
        for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {
            Sheet sheet = workBook.getSheetAt(sheetIndex);
            // 分别获取表格的第一行和最后一行，计算需要导入的行数
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            // 循环表格的每一行
            for (int rowIndex = firstRowNum + 1; rowIndex < lastRowNum; rowIndex++) {
                // 获取每行
                Row sheetRow = sheet.getRow(rowIndex);
                // 获取当前行的第1列
                short firstCellNum = sheetRow.getFirstCellNum();
                // 获取当前行的最后一列
                short lastCellNum = sheetRow.getLastCellNum();
                // 获取实际有数据的列数
                String[] cells = new String[sheetRow.getPhysicalNumberOfCells()];
                // 循环当前行的每一列数据
                for (int cellIndex = firstCellNum; cellIndex < lastCellNum; cellIndex++) {
                    String cellValue;
                    Cell cell = sheetRow.getCell(cellIndex);
                    // 处理特殊数据类型（时间，小数值）
                    String dataFormatString = cell.getCellStyle().getDataFormatString();
                    if ("y/m/d".equals(dataFormatString)) {
                        cellValue = new SimpleDateFormat("yyyy/MM/dd").format(cell.getDateCellValue());
                        cells[cellIndex] = cellValue;
                        continue;
                    }
                    switch (cell.getCellType()) {
                        case STRING:
                            //  文本类型
                            cellValue = cell.getStringCellValue();
                            break;
                        case BOOLEAN:
                            // 布尔值
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case BLANK:
                            cellValue = "";
                            // 空值
                            break;
                        case NUMERIC:
                            // 数字
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case ERROR:
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知数据类型";
                            break;
                    }
                    cells[cellIndex] = cellValue;

                }
                list.add(cells);
            }
        }
        return list;
    }

    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith("xls")){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith("xlsx")){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


}