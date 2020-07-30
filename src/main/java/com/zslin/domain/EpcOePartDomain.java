package com.zslin.domain;

import com.alibaba.fastjson.JSON;

import com.zslin.model.EpcException;
import com.zslin.model.EpcOePartVo;
import com.zslin.model.JsOePartVo;
import com.zslin.model.OePartListResponse;
import com.zslin.model.util.JsResponseStatusEnum;
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
 * @Date 2020/7/28 11:32
 * @Version 1.0    通过OE号获取配件基础信息
 */
@Component
public class EpcOePartDomain {

    /**
     * log
     */
    private final static Logger logger = LoggerFactory.getLogger(EpcOePartDomain.class);

    @Autowired
    private EpcBaseJsDomain epcBaseJsDomain;

    @Value("${epc.js.baseUrl}")
    private String baseUrl;

    /**
     * 从精时获取车系列表数据
     * @return
     */
    public List<EpcOePartVo> searchOePart(String query_part_number) {
        List<EpcOePartVo> epcOePartVos = null;
        if (StringUtils.isEmpty(query_part_number)) {
            throw new EpcException(ReturnStatusEnum.PARAM_ERROR.getValue(), "query_part_number参数不能为空");
        }
        try {
            Map<String, String> body = new LinkedHashMap<String, String>(3);
            body.put("action", "search_epc");
            body.put("query_part_number", query_part_number);
            body.put("query_match_type","exact");

            String basePath = "/";
            String url = baseUrl + basePath;
            String resBody = HttpClientUtils.httpGet(url, epcBaseJsDomain.getParamMap(body, basePath));
            if (StringUtils.isBlank(resBody)) {
                throw new EpcException(ReturnStatusEnum.DATA_FAIL);
            }
           OePartListResponse response = JSON.parseObject(resBody, OePartListResponse.class);
            if (response == null || response.getCode() == null) {
                throw new EpcException(ReturnStatusEnum.DATA_FAIL);
            }
            if (JsResponseStatusEnum.SUCCESS.getCode().equals(response.getCode())) {
                List<JsOePartVo> jsOePartVos  = response.getData();
                epcOePartVos = transfor(jsOePartVos);
            }
        } catch (Exception e) {
            logger.error("EpcOePartDomain# searchOePart fail:" + e.getMessage(), e);
        }
        return epcOePartVos;
    }
    private List<EpcOePartVo> transfor(List<JsOePartVo> jsOePartVos){
        List<EpcOePartVo> epcOePartVos = new ArrayList<>();
        for(JsOePartVo vo:jsOePartVos){
            EpcOePartVo epcVo = new EpcOePartVo();
           epcVo.setGroupId(vo.getGroup_id());
           epcVo.setEpcZh(vo.getEpc_zh());
           epcVo.setPartNameZh(vo.getPart_name_zh());
            epcOePartVos.add(epcVo);
        }
        return epcOePartVos;

    }
}
