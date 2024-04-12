package com.mxj.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxj.common.utils.PageUtils;
import com.mxj.gulimall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-13 01:57:08
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

