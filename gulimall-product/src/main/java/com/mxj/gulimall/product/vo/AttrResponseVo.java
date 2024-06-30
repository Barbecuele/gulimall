package com.mxj.gulimall.product.vo;

import lombok.Data;

/**
 * @Title: AttrResponseVo
 * @Author mi xiu jin
 * @Package com.mxj.gulimall.product.vo
 * @Date 2024/6/25 22:19
 * @description: 属性响应
 */
@Data
public class AttrResponseVo extends AttrVo {
    // 分类名称
    private String catelogName;

    // 分组名称
    private String groupName;

    private Long[] catelogPath;
}
