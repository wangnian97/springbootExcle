package com.zslin.model;

/**
 * @Author wangnian23
 * @Date 2020/7/28 15:01
 * @Version 1.0
 * 通过OE号获取适用车型返回数据
 */
public class JsPartInfoVo {

    private String Part_id;

    private String Brand_name_zh;

    private Integer Group_id;

    private String Group_name;

    public Integer getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(Integer group_id) {
        Group_id = group_id;
    }

    public String getGroup_name() {
        return Group_name;
    }

    public void setGroup_name(String group_name) {
        Group_name = group_name;
    }

    public String getPart_id() {
        return Part_id;
    }

    public void setPart_id(String part_id) {
        Part_id = part_id;
    }

    public String getBrand_name_zh() {
        return Brand_name_zh;
    }

    public void setBrand_name_zh(String brand_name_zh) {
        Brand_name_zh = brand_name_zh;
    }
}
