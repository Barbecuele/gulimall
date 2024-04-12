package com.mxj.gulimall.product.dao;

import com.mxj.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-12 20:48:45
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
