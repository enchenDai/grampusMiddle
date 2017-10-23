package com.deepblue.middleware.service.enums;

import com.alibaba.common.lang.StringUtil;

/**
 * palmset 批量追加/删除 人员信息 类型
 */
public enum PalmsetNotifyEnum {
	ADD_TYPE("1", "增加Person"), 	//palmset 批量追加 人员信息
	REMOVE_TYPE("2", "删除Person");	//palmset 批量删除 人员信息

	private final String typeCode;

	private final String typeName;

	/**
	 * @param typeCode
	 * @param typeName
	 */
	private PalmsetNotifyEnum(String typeCode, String typeName) {
		this.typeCode = typeCode;
		this.typeName = typeName;
	}

	/**
	 * <pre>
	 * 通过typeCode取PalmTypeEnum
	 * </pre>
	 *
	 * @param typeCode
	 * @return
	 */
	public static PalmsetNotifyEnum getEnumByCode(String typeCode) {
		PalmsetNotifyEnum[] enums = PalmsetNotifyEnum.values();
		for (PalmsetNotifyEnum codeEnum : enums) {
			if (StringUtil.equals(codeEnum.getTypeCode(), typeCode)) {
				return codeEnum;
			}
		}
		return null;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getTypeName() {
		return typeName;
	}
}