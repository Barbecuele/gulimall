package com.mxj.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxj.common.utils.PageUtils;
import com.mxj.gulimall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-13 02:23:40
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

