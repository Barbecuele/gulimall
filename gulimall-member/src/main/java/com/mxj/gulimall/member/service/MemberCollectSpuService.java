package com.mxj.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxj.common.utils.PageUtils;
import com.mxj.gulimall.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-13 02:23:40
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

