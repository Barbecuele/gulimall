package com.mxj.gulimall.order.dao;

import com.mxj.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-13 11:57:24
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
