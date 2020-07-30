package com.zslin.model.util;

/**
 * 精时通用返回错误码枚举
 * @author wangyanqing16
 * @date 2019-06-14 11:29 2019-06-17 15:20
 */

public enum JsResponseStatusEnum {
    SUCCESS(1, "成功"),
    NO_DATA(0, "无数据"),
    NOT_SUPPORT(1003, "不支持的品牌接口"),
    NOT_ENOUTH(1005, "无权限,查询次数耗尽或到期"),
    ;

    private Integer code;
    private String desc;

    JsResponseStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static JsResponseStatusEnum getEnum(Integer code) {
        for(JsResponseStatusEnum item : JsResponseStatusEnum.values()) {
            if(item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
