package com.mxj.common.constant;

/**
 * @Title: ProductConstant
 * @Author mi xiu jin
 * @Package com.mxj.common.constant
 * @Date 2024/6/30 8:42
 * @description: 常量
 */
public class ProductConstant {
    public enum AttrEnum {
        ATTR_TYPE_BASE(1, "基本属性"),
        ATTR_TYPE_SALE(0, "销售属性");
        private int code;
        private String msg;

        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}