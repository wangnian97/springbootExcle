package com.zslin.model.util;


import java.util.HashSet;

/**
 * @ClassName: ReturnStatusEnum
 * @Description:返回状态枚举类
 * @author liguoqing6@jd.com
 * @date 2018年7月10日
 */
public enum ReturnStatusEnum {

	/**
	 * 调用成功
	 */
	SERVICE_SUCCESS(200, "成功"),

	/**
	 * 参数错误
	 */
	PARAM_ERROR(3000, "参数错误,%s"),
	/**
	 * vin码对应多个车型
	 */
	CAR_MODEL_NOT_UNIQUE_ERROR(3001, "vin码对应多个原厂车型"),
	/**
	 * vin码无对应车型
	 */
	CAR_MODEL_NOT_EXISTS_ERROR(3002, "vin码无对应原厂车型"),
	/**
	 * 原厂车型不存在
	 */
	ORIGINAL_CAR_MODEL_NOT_EXISTS_ERROR(3003, "原厂车型不存在"),
	/**
	 * 京东车型不存在
	 */
	JD_CAR_MODEL_NOT_EXISTS_ERROR(3004, "京东车型不存在"),

	/**
	 * 多车型暂不支持
	 */
	CAR_MODEL_NOT_SUPPORT_ERROR(3005, "暂不支持vin码对应多原厂车型！"),

	/**数据部分****/
	VIN_ERROR(3000,"参数错误,vin不符合规则!"),
	/**OE输入长度限制提示*/
	OE_ERROR(3006, "参数错误,至少输入5个字符!"),
	SERVER_ERROR(4000, "服务器错误"),
	GATEWAY_ERROR(4001, "调用数据网关错误"),
	INDEX_CREATE_ERROR(4002, "创建索引错误, 原因: "),
    DATA_ERROR(4003, "输入数据有问题或者服务暂没进行支持。"),
    DATA_GET_FAIL(4004, "数据获取异常。"),
	DATA_FAIL(4005,"数据获取失败!"),
	DATA_OVERFLOW(4006, "查询数据量不能超过10000条哟"),
	NO_DATA(6000,"无数据!"),
	DATA_EXCEPTION(4010,"数据库异常。"),
	MIDDLEWARE_ERROR(4100, "中间件错误,%s"),
	/**系统部分****/

	NO_PERMISSION(5000, "请开通接口访问权限"),

	/**
	 * 未知错误
	 */
	UNKNOWN_ERROR(5200, "未知错误"),
	/**
	 * indexKey不存在
	 */
	INDEX_KEY_NOT_EXIST(7000,"indexKey不存在"),
	/**
	 * 索引已存在
	 */
	INDEX_ALREADY_EXIST(7008,"索引已存在"),
	;


	private final int value;
	private final String desc;

	private ReturnStatusEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
	/**
	 * Gets the value.
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	public String getDesc4Log() {
		return "ERROR_CODE:" + value + "\t" + desc + "\t";
	}

	private static HashSet<Integer> hashSet;

	static {
		hashSet = new HashSet<Integer>();
		hashSet.clear();
		for (ReturnStatusEnum returnStatus : ReturnStatusEnum.values()) {
			hashSet.add(returnStatus.getValue());
		}
	}

	public static boolean isDefined(int value) {
		if (hashSet.contains(value)) {
			return true;
		}
		return false;
	}

	public static ReturnStatusEnum get(int value) {
		for (ReturnStatusEnum o : ReturnStatusEnum.values()) {
			if (value == o.getValue()) {
				return o;
			}
		}
		return null;
	}
}
