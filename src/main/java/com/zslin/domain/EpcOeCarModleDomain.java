package com.zslin.domain;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.zslin.model.*;
import com.zslin.model.util.ReturnStatusEnum;
import com.zslin.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangnian23
 * @Date 2020/7/28 13:02
 * @Version 1.0
 * 通过OE号获取适用车型
 */
@Component
public class EpcOeCarModleDomain {
    /**
     * log
     */
    private final static Logger logger = LoggerFactory.getLogger(EpcOeCarModleDomain.class);

    @Autowired
    private EpcBaseJsDomain epcBaseJsDomain;

    @Value("${epc.js.baseUrl}")
    private String baseUrl;

    /**
     * 从精时获取数据
     * @return
     */
    public List<JsModelListStd> searchCarModel(String part_number, String group_id) {
        List<JsModelListStd> jsModelListStds = new ArrayList<>();
        JsPartInfoVo jsPartInfoVo = new JsPartInfoVo();
        if (StringUtils.isEmpty(part_number)) {
            throw new EpcException(ReturnStatusEnum.PARAM_ERROR.getValue(), "part_number参数不能为空");
        }
        if (StringUtils.isEmpty(group_id)) {
            throw new EpcException(ReturnStatusEnum.PARAM_ERROR.getValue(), "group_id参数不能为空");
        }
        try {
            Map<String, String> body = new LinkedHashMap<String, String>(3);
            body.put("action", "get_modellist_from_part_number_and_group_id");
            body.put("part_number", part_number);
            body.put("group_id",group_id);

            String basePath = "/";
            String url = baseUrl + basePath;
            String resBody = HttpClientUtils.httpGet(url, epcBaseJsDomain.getParamMap(body, basePath));
            if (StringUtils.isBlank(resBody)) {
                throw new EpcException(ReturnStatusEnum.DATA_FAIL);
            }
            JSONArray array = JSONObject.parseObject(resBody).getJSONObject("data").getJSONArray("ModelListStd");
            jsModelListStds = JSONArray.parseArray(array.toJSONString(),JsModelListStd.class);
//            OeCarModelResponse response = JSON.parseObject(array.toJSONString(),OeCarModelResponse.class);
//            jsModelListStds = response.getData();

       //    jsModelListStds  =  JSONObject.parseArray(array.toJSONString(),JsModelListStd.class);
            //epcOeCarModelVos = transfor(jsModelListStds);

//            String response = JSONObject.parseObject(resBody).getJSONObject("data").getJSONObject("PartInfo").toJSONString();
//            jsPartInfoVo =  JSON.parseObject(response, JsPartInfoVo.class);

//             jsPartInfoVo.setBrand_name_zh(response.getJSONObject("Brand_name_zh").toJSONString());
//             jsPartInfoVo.setPart_id(response.getJSONObject("Part_id").toJSONString());
//            OeCarModelResponse response = JSON.parseObject(resBody, OeCarModelResponse.class);
//            if (response == null || response.getCode() == null) {
//                throw new EpcException(ReturnStatusEnum.DATA_FAIL);
//            }
//            if (JsResponseStatusEnum.SUCCESS.getCode().equals(response.getCode())) {
//                List<JsModelListStd> jsModelListStds  = response.getData().getJsModelListStd();
//                epcOeCarModelVos = transfor(jsModelListStds);
//            }
        } catch (Exception e) {
            logger.error("EpcOeCarModleDomain#searchCarModel fail:" + e.getMessage(), e);
        }
        return jsModelListStds;
    }
//    private List<EpcOeCarModelVo> transfor(List<JsModelListStd> JsCarModelVos){
//        List<EpcOeCarModelVo> epcOeCarModelVos = new ArrayList<>();
//        for(JsModelListStd vo:JsCarModelVos){
//            EpcOeCarModelVo epcVo = new EpcOeCarModelVo();
//            epcVo.setBrand(vo.getBrand());
//            epcVo.setDetail(vo.getDetail());
//            epcVo.setEpc(vo.getEpc());
//            epcVo.setFactory(vo.getFactory());
//            epcVo.setId(vo.getId());
//            epcVo.setModel(vo.getModel());
//            epcVo.setModelYear(vo.getModel_year());
//            epcVo.setSeries(vo.getSeries());
//            epcOeCarModelVos.add(epcVo);
//        }
//        return epcOeCarModelVos;
//    }
}
