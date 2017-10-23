package com.deepblue.middleware.service.enums;


/**
 * Created by enchen on 10/10/17.
 */
public enum PalmTypeEnum {
    ADD_TYPE("1", "新增注册"),
    UPDATE_TYPE("2", "更新注册"),
    DELETE_TYPE("3", "删除会员");

    private final String typeCode;

    private final String typeName;

    /**
     * @param typeCode
     * @param typeName
     */
    private PalmTypeEnum(String typeCode, String typeName) {
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
    public static PalmTypeEnum getEnumByCode(String typeCode) {
        PalmTypeEnum[] enums = PalmTypeEnum.values();
        for (PalmTypeEnum codeEnum : enums) {
            if (codeEnum.getTypeCode().equals(typeCode)) {
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
