package com.definesys.rt.Test;

import javafx.scene.media.VideoTrack;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test1 {

    private String path = "E:\\definesys\\rt\\";

    @Autowired
    private DemoService service;

    @Autowired
    private DemoDao dao;

    private List<String> list = new ArrayList<>();

    public void test(){
        for (int i = 0; i < 3000; i++) {

        }
        dao.save(new Demo());
    }


    @Test
    public void excleTest07() throws IOException {

        Map<String,Object> map = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(path + "分公司全面绩效工号.xlsx");

        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < 3088; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            String code = Test1.getWholeEmployeeCode(cell.getStringCellValue());
            map.put("I_PERNR",code);
//            list.add(code);
        }
//        System.out.println(list.size());
//        list.forEach(System.out::println);
//        String demo = service.demo(map);
//        System.out.println(demo.getE_TELEPHONE());
//        System.out.println(demo.getMESSAGE());
//        System.out.println(demo.getSTATUS());


//        fileInputStream.close();
    }

    /**
     * 获取完整工号
     *
     * @param employeeCode 未处理过的工号
     * @return
     */
    public static String getWholeEmployeeCode(String employeeCode) {
        if(StringUtils.isEmpty(employeeCode)){
            return null;
        }
        employeeCode = employeeCode.replaceAll("[a-zA-Z]", "");
        return String.format("%08d", Long.parseLong(employeeCode));
    }
}
