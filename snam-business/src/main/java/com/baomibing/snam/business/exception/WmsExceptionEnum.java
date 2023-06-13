package com.baomibing.snam.business.exception;

import com.baomibing.tool.exception.ExceptionEnumable;

/**
 * WmsExceptionEnum
 *
 * @author frog 2023/6/4 22:11
 * @version 1.0.0
 **/
public enum WmsExceptionEnum implements ExceptionEnumable {

    ITEM_CODE_DUPLICATE(30001, "商品编码{0}重复,无法进行后续操作!"),
    CLASS_NAME_DUPLICATE(30002, "商品分类名称{0}重复,无法进行后续操作!"),
    PROVIDER_CODE_OR_NAME_OR_SNAME_DUPLICATE(30003, "供应商编码/名称/简称中存在重复,无法进行后续操作!"),
    ;

    WmsExceptionEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
    @Override
    public int getExceptionCode() {
        return code;
    }

    @Override
    public String getExceptionMessage() {
        return message;
    }
}
