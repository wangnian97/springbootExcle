package com.zslin.model;



import java.util.List;

/**
 * @Author wangnian23
 * @Date 2020/7/28 14:12
 * @Version 1.0
 */
public class OeCarModelResponse extends ResBasic{
    List<JsModelListStd> data;

    public List<JsModelListStd> getData() {
        return data;
    }

    public void setData(List<JsModelListStd> data) {
        this.data = data;
    }
}
