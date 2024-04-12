package com.mxj.gulimall.member.dao;

import com.mxj.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-13 02:23:40
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
