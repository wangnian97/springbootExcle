package com.zslin.model;

/**
 * @Author wangnian23
 * @Date 2020/7/28 11:48
 * @Version 1.0
 * 根据OE配件号搜索出对应的配件基础信息
 */
public class EpcOePartVo {
    private String groupId;

    private String epcZh;

    private String partNameZh;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEpcZh() {
        return epcZh;
    }

    public void setEpcZh(String epcZh) {
        this.epcZh = epcZh;
    }

    public String getPartNameZh() {
        return partNameZh;
    }

    public void setPartNameZh(String partNameZh) {
        this.partNameZh = partNameZh;
    }
}
