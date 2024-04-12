package com.mxj.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxj.common.utils.PageUtils;
import com.mxj.gulimall.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-12 20:48:45
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

