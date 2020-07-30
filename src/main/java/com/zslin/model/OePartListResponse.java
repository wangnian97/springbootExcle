package com.zslin.model;


import java.util.List;

/**
 * @Author wangnian23
 * @Date 2020/7/28 11:57
 * @Version 1.0
 * 根据OE配件号搜索出对应的配件基础信息
 */
public class OePartListResponse extends ResBasic{
    List<JsOePartVo> data;

    public List<JsOePartVo> getData() {
        return data;
    }

    public void setData(List<JsOePartVo> data) {
        this.data = data;
    }
}
