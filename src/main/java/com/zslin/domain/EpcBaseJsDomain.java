package com.zslin.domain;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liqiuping7
 * @className EpcBaseJsDomain
 * @Description
 * @date 2020-06-17 11:04
 */
@Component
public class EpcBaseJsDomain {
    /**
     * 精时用户名
     */
    @Value("${epc.js.user}")
    private String user;
    /**
     * 精时密码
     */
    @Value("${epc.js.password}")
    private String password;

    /**
     * 拼装参数
     *
     * @param body
     * @return
     */
    protected Map<String, Object> getParamMap(Map<String, String> body, String baseUrlPath) {
        /** token生成规则 MD5(MD5(username)+MD5(password)+url_parameters) **/
        String md5User = DigestUtils.md5Hex(user);
        String md5Password = DigestUtils.md5Hex(password);
        StringBuilder preEncodeStr = new StringBuilder();
        preEncodeStr.append(md5User);
        preEncodeStr.append(md5Password);
        StringBuilder urlParameters = new StringBuilder();
        urlParameters.append(baseUrlPath);
        urlParameters.append("?");
        boolean isStart = true;
        Map<String, Object> paramMap = new LinkedHashMap<>(10);
        for (String key : body.keySet()) {
            paramMap.put(key, body.get(key));
            if (isStart) {
                isStart = false;
            } else {
                urlParameters.append("&");
            }
            urlParameters.append(key);
            urlParameters.append("=");
            urlParameters.append(body.get(key));
        }
        preEncodeStr.append(urlParameters.toString());
        String resultToken = DigestUtils.md5Hex(preEncodeStr.toString());
        paramMap.put("user", user);
        paramMap.put("token", resultToken);
        return paramMap;
    }
}
