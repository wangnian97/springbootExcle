package com.zslin.service;

import com.zslin.model.JsModelListStd;
import com.zslin.model.JsPartInfoVo;
import com.zslin.model.WebDto;
import com.zslin.utils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangnian23
 * @Date 2020/7/29 9:45
 * @Version 1.0
 */
@Service
public class PoiOe {
    @Autowired
    EpcOeCarModelImpl epcOeCarModel;

    public void read() throws IOException {
        List<String> oeList = new ArrayList<String>();
        List<String> jautoList = new ArrayList<String>();
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:jat4.xlsx")));
        XSSFSheet sheet = book.getSheetAt(0);
        for(int i=1; i<sheet.getLastRowNum()+1; i++) {
            XSSFRow row = sheet.getRow(i);
            String oe = row.getCell(22).getStringCellValue();

            String str[] = oe.split(";");
            for(String s:str){
                oeList.add(s);
                String jauto = row.getCell(2).getStringCellValue();
                jautoList.add(jauto);
            }
        }
        List<WebDto> list = new ArrayList<WebDto>();

        for(int i=0;i<oeList.size();i++){
            List<JsModelListStd> jsModelListStds= epcOeCarModel.searchCarModel(oeList.get(i));
            if(jsModelListStds!=null){
                for(JsModelListStd st:jsModelListStds)
                list.add(new WebDto(jautoList.get(i),oeList.get(i), st.getId(), st.getIsshowdata(),st.getEpc(), st.getDetail(), st.getFactory(),st.getBrand(),st.getModel(),st.getSeries(),st.getModel_year(),st.getSales_version(),st.getCc(),st.getEngine_no(),st.getFuel_type(),st.getAir_intake(),st.getTransmission_detail(),st.getGear_num(),st.getDoor_num(),st.getSeat_num(),st.getBody_type(),st.getDriven_model(),st.getDate_begin(),st.getDate_end(),st.getPrice(),st.getAutohome_id()));
            }else{
                list.add(new WebDto(jautoList.get(i),oeList.get(i), -1,-1,"null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null","null"));
            }
        }
                Map<String, String> map = new HashMap<String, String>();
                map.put("title", "OE信息表");
                map.put("total", list.size()+" 条");
                map.put("date", "getDate()");
                ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "web-info-template.xls", new FileOutputStream("D:/oe_0730.xls"), list, WebDto.class, true);

    }
}
