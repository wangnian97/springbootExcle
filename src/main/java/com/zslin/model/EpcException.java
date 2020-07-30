package com.zslin.model;

import com.zslin.model.util.ReturnStatusEnum;

/**
 * @author liqiuping7
 * @className EpcException
 * @Description
 * @date 2019-10-08 15:58
 */
public class EpcException extends RuntimeException {
    private static final long serialVersionUID = -1786392894227554119L;
    private int code;

    public EpcException(String message) {
        super(message);
        this.code = ReturnStatusEnum.SERVER_ERROR.getValue();
    }

    public EpcException(String message, Throwable e) {
        super(message, e);
    }

    public EpcException(int code, String message) {
        super(message);
        this.code = code;
    }

    public EpcException(ReturnStatusEnum errorStatusEnum) {
        super(errorStatusEnum.getDesc());
        this.code = errorStatusEnum.getValue();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
