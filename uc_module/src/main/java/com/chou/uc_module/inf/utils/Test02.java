package com.chou.uc_module.inf.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test02 {

    /**
     * @param field
     * @param title      标题集合 tilte的长度应该与list中的model的属性个数一致
     * @param listList   内容集合
     * @param mergeIndex 合并单元格的列
     */
    @SneakyThrows
    public static String createExcel(String[] field, String[] title, List<List<Map<String, String>>> listList, int[] mergeIndex) {
        long startTime = System.currentTimeMillis();
        if (title.length == 0) {
            return null;
        }
        // 初始化excel模板
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;

        int n = 0;
        //循环sheet页 实例化sheet对象并且设置sheet名称，book对象
        try {
            sheet = workbook.createSheet();
            workbook.setSheetName(n, "sheet1");
            workbook.setSelectedTab(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 数据总数
        int size = listList.stream().mapToInt(List::size).sum();
        assert sheet != null;
        Row row0 = sheet.createRow(0);
        for (int i = 0; i < title.length; i++) {
            Cell cell_1 = row0.createCell(i);
            cell_1.setCellValue(title[i]);
        }

        List<PoiModel> poiModels = Lists.newArrayList();
        for (List<Map<String, String>> list : listList) {
            for (Map<String, String> map : list) {
                int index = sheet.getLastRowNum() + 1;
                Row row = sheet.createRow(index);
                for (int i = 0; i < title.length; i++) {
                    String titleField = field[i];
                    String old = null;
                    if (index > 1) {
                        old = poiModels.get(i) == null ? null : poiModels.get(i).getContent();
                    }
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
                                get(poiModel, content, index, i, sheet);
                            }
                        }
                        // 处理第一列的情况
                        if (k == i && i == 0 && !poiModel.getContent().equals(content)) {
                            get(poiModel, content, index, i, sheet);
                        }
                        // 最后一行没有后续的行与之比较，所有当到最后一行时则直接合并对应列的相同内容
                        if (k == i && index == size && poiModels.get(i).getRowIndex() != index) {
                            CellRangeAddress cra = new CellRangeAddress(poiModels.get(i).getRowIndex(), index, poiModels.get(i).getCellIndex(), poiModels.get(i).getCellIndex());
                            sheet.addMergedRegion(cra);
                        }
                    }
                    Cell cell = row.createCell(i);
                    cell.setCellValue(map.get(titleField));
                    poiModels.get(i).setOldContent(old);
                }
            }
        }

        File file = new File("E:\\jast\\demo.xls");
        FileOutputStream fout = new FileOutputStream(file);
        workbook.write(fout);
        fout.close();

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return file.getAbsolutePath();
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

    public static void main(String[] args) throws IOException {
        // 此处标题的数组则对应excel的标题
        String[] title = {"id", "标题", "描述", "负责人", "开始时间", "名字", "年龄", "性别", "班级"};
        String[] field = {"id", "title", "dec", "manager", "beginTime", "name", "age", "sex", "clazz"};
        List<Map<String, String>> list = Lists.newArrayList();
        // 这边是制造一些数据，注意每个list中map的key要和标题数组中的元素一致
        for (int i = 0; i < 100; i++) {
            HashMap<String, String> map = Maps.newHashMap();
            if (i > 40) {
                if (i < 45) {
                    map.put("id", "333");
                    map.put("title", "美女");
                } else if (i > 50 && i < 55) {
                    map.put("id", "444");
                    map.put("title", "美男");
                } else {
                    map.put("id", "444");
                    map.put("title", "少男");
                }
            } else if (i > 25) {
                map.put("id", "222");
                map.put("title", "少女");
            } else if (i == 5 || i == 8) {
                map.put("id", "222");
                map.put("title", "少年");
            } else {
                map.put("id", "222");
                map.put("title", "青年");
            }
            map.put("dec", "都是有用的人");
            map.put("manager", "管理员");
            map.put("beginTime", "2017-02-27 11:20:26");
            map.put("name", "tsy");
            map.put("age", "28");
            map.put("sex", "男");
            if (i > 80) {
                if (i < 82) {
                    map.put("clazz", "er版");
                } else {
                    map.put("clazz", "");
                }
            } else {
                map.put("clazz", "一版");
            }
            list.add(map);
        }

        Map<String, List<Map<String, String>>> map = Maps.newHashMap();
        map.put("测试合并数据", list);

        // 模拟大数据量情况下，任务中心可分页查询接口，分批返回数据
        List<List<Map<String, String>>> groups = pageByNum(list, 5);
        // 此处数组为需要合并的列，可能有的需求是只需要某些列里面相同内容合并
        System.out.println(createExcel(field, title, groups, new int[]{0, 1, 2, 8}));
    }

    public static <T> List<List<T>> pageByNum(List<T> list, int pageSize) {
        return IntStream.range(0, list.size()).boxed().filter(t -> t % pageSize == 0).map(t -> list.stream().skip(t).limit(pageSize).collect(Collectors.toList())).collect(Collectors.toList());
    }

}
