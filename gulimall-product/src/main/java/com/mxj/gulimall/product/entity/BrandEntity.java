package com.mxj.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.mxj.common.validator.ListValue;
import com.mxj.common.validator.group.UpdateStatusGroup;
import com.mxj.common.validator.group.AddGroup;
import com.mxj.common.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-12 20:48:45
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId
    @Null(message = "新增不能指定id", groups = AddGroup.class)
    @NotNull(message = "修改必须指定id", groups = UpdateGroup.class)
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "logo不能为空", groups = {AddGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListValue(vals = {0, 1},groups = {AddGroup.class, UpdateStatusGroup.class, UpdateGroup.class})
    @NotNull(message = "显示状态不能为空", groups = {AddGroup.class, UpdateStatusGroup.class, UpdateGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @Pattern(regexp = "/^[a-zA-Z]$/", message = "检索首字母必须是一个字母")
    @NotBlank(message = "检索首字母不能为空")
    private String firstLetter;
    /**
     * 排序
     */
    @Min(value = 0, message = "排序必须大于等于0")
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
