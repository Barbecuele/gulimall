package com.mxj.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mxj.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.mxj.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.mxj.gulimall.product.vo.AttrGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxj.common.utils.PageUtils;
import com.mxj.common.utils.Query;

import com.mxj.gulimall.product.dao.AttrGroupDao;
import com.mxj.gulimall.product.entity.AttrGroupEntity;
import com.mxj.gulimall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        LambdaQueryWrapper<AttrGroupEntity> attrGroupEntityQueryWrapper = new LambdaQueryWrapper<>();
        if (params.get("key") != null) {
            attrGroupEntityQueryWrapper.like(AttrGroupEntity::getAttrGroupName, params.get("key"));
        }
        if (catelogId == 0) {
            //查询全部
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    attrGroupEntityQueryWrapper
            );
            return new PageUtils(page);
        } else {
            //查询指定分类下的
            attrGroupEntityQueryWrapper.eq(AttrGroupEntity::getCatelogId, catelogId);
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    attrGroupEntityQueryWrapper
            );
            return new PageUtils(page);
        }
    }

    @Override
    public void deleteRelation(List<AttrGroupVo> attrGroupIds) {
        List<AttrAttrgroupRelationEntity> relationEntities = attrGroupIds.stream()
                .map(attrGroupVo -> {
                    AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                    BeanUtils.copyProperties(attrGroupVo, attrAttrgroupRelationEntity);
                    return attrAttrgroupRelationEntity;
                }).collect(Collectors.toList());
        attrAttrgroupRelationDao.deleteBatchRelation(relationEntities);
    }
}