package com.chou.uc_module.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Axel
 * @version 1.0
 * @className MergeCellUtils
 * @description TODO
 * @date 2022/11/13 22:51
 */

@Slf4j
public class MergeCellUtils {
    /**
     * @param field      字段标题对应
     * @param title      字段
     * @param list   数据
     * @param mergeIndex 需要合并的列
     * @param workbook   工作不
     * @param indexSheet sheet 索引
     */
    public static void createExcel(String[] field,
                                   String[] title,
                                   List<Map<String, String>> list,
                                   int[] mergeIndex,
                                   Workbook workbook, int indexSheet) {
        if (title.length == 0) {
            return;
        }
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中*/
        Sheet sheetAt = null;
        int n = 0;
        //循环sheet页 实例化sheet对象并且设置sheet名称，book对象
        try {
            sheetAt = workbook.createSheet("组织结构列表");
            //workbook.setSheetName(n, "组织结构列表");
            workbook.setSelectedTab(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert sheetAt != null;
        Row row0 = sheetAt.createRow(0);
        for (int i = 0; i < title.length; i++) {
            Cell cell_1 = row0.createCell(i);
            cell_1.setCellValue(title[i]);
        }
        // 数据总数
        int size = list.size();
        List<PoiModel> poiModels = Lists.newArrayList();
        for (Map<String, String> map : list) {
            int index = sheetAt.getLastRowNum() + 1;
            Row row = sheetAt.createRow(index);
            // 循环列 标题
            for (int i = 0; i < title.length; i++) {
                String titleField = field[i];
                String old = null;
                if (index > 1) {
                    old = poiModels.get(i) == null ? null : poiModels.get(i).getContent();
                }
                // 序号合并的列
                for (int k : mergeIndex) {
                    if (index == 1) {
                        PoiModel poiModel = PoiModel.builder().oldContent(map.get(titleField)).content(map.get(titleField)).rowIndex(1).cellIndex(i).build();
                        poiModels.add(poiModel);
                        break;
                    }
                    PoiModel poiModel = poiModels.get(i);
                    String content = map.get(titleField);
                    // 当前行的当前列与上一行的当前列的内容不一致时，则把当前行以上的合并
                    if (i > 0 && k == i) {
                        // 如果不需要考虑当前行与上一行内容相同，但是它们的前一列内容不一样则不合并的情况，把或条件删除
                        if (!poiModel.getContent().equals(content) || poiModel.getContent().equals(content) && !poiModels.get(i - 1).getOldContent().equals(map.get(field[i - 1]))) {
                            get(poiModel, content, index, i, sheetAt);
                        }
                    }
                    // 处理第一列的情况
                    if (k == i && i == 0 && !poiModel.getContent().equals(content)) {
                        get(poiModel, content, index, i, sheetAt);
                    }
                    // 最后一行没有后续的行与之比较，所有当到最后一行时则直接合并对应列的相同内容
                    if (k == i && index == size && poiModels.get(i).getRowIndex() != index) {
                        CellRangeAddress cra = new CellRangeAddress(poiModels.get(i).getRowIndex(), index, poiModels.get(i).getCellIndex(), poiModels.get(i).getCellIndex());
                        sheetAt.addMergedRegion(cra);
                    }
                }
                Cell cell = row.createCell(i);
                cell.setCellValue(map.get(titleField));
                cell.setCellStyle(cellStyle);
                poiModels.get(i).setOldContent(old);
            }
        }

    }

    /**
     * 合并单元格
     *
     * @param poiModel
     * @param content
     * @param index
     * @param i
     * @param sheet
     */
    private static void get(PoiModel poiModel, String content, int index, int i, Sheet sheet) {
        if (poiModel.getRowIndex() != index - 1) {
            CellRangeAddress cra = new CellRangeAddress(poiModel.getRowIndex(), index - 1, poiModel.getCellIndex(), poiModel.getCellIndex());
            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
        }
        /*重新记录该列的内容为当前内容，行标记改为当前行标记，列标记则为当前列*/
        poiModel.setContent(content);
        poiModel.setRowIndex(index);
        poiModel.setCellIndex(i);
    }
}
