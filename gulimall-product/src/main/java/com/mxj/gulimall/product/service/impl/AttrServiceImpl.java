package com.mxj.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mxj.common.constant.ProductConstant;
import com.mxj.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.mxj.gulimall.product.dao.AttrGroupDao;
import com.mxj.gulimall.product.dao.CategoryDao;
import com.mxj.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.mxj.gulimall.product.entity.AttrGroupEntity;
import com.mxj.gulimall.product.entity.CategoryEntity;
import com.mxj.gulimall.product.service.CategoryService;
import com.mxj.gulimall.product.vo.AttrResponseVo;
import com.mxj.gulimall.product.vo.AttrVo;
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

import com.mxj.gulimall.product.dao.AttrDao;
import com.mxj.gulimall.product.entity.AttrEntity;
import com.mxj.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        //保存基本数据
        this.save(attrEntity);
        //保存关联关系
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        }

        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        LambdaQueryWrapper<AttrEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttrEntity::getAttrType, "base".equalsIgnoreCase(type) ? 1 : 0);

        if (catelogId != 0) {
            queryWrapper.eq(AttrEntity::getCatelogId, catelogId);
        }
        String key = (String) params.get("key");
        if (!key.isEmpty()) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq(AttrEntity::getAttrId, key).or().like(AttrEntity::getAttrName, key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrResponseVo> collect = records.stream()
                .map(record -> {
                    AttrResponseVo attrResponseVo = new AttrResponseVo();
                    BeanUtils.copyProperties(record, attrResponseVo);
                    AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                            new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                                    .eq(AttrAttrgroupRelationEntity::getAttrId, record.getAttrId()));
                    if ("base".equalsIgnoreCase(type)) {
                        if (attrAttrgroupRelationEntity != null) {
                            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                            attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                        }
                    }
                    CategoryEntity categoryEntity = categoryDao.selectById(record.getCatelogId());
                    if (categoryEntity != null) {
                        attrResponseVo.setCatelogName(categoryEntity.getName());
                    }
                    return attrResponseVo;
                })
                .collect(Collectors.toList());
        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public AttrResponseVo getAttrInfo(Long attrId) {
        AttrEntity attrEntity = this.getById(attrId);
        AttrResponseVo attrResponseVo = new AttrResponseVo();
        BeanUtils.copyProperties(attrEntity, attrResponseVo);
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            //查询分组信息
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao
                    .selectOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                            .eq(AttrAttrgroupRelationEntity::getAttrId, attrId));
            if (attrAttrgroupRelationEntity != null) {
                attrResponseVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if (attrGroupEntity != null) {
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }
        Long[] catelogPath = categoryService.findCatelogPath(attrEntity.getCatelogId());
        attrResponseVo.setCatelogPath(catelogPath);
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
        if (categoryEntity != null) {
            attrResponseVo.setCatelogName(categoryEntity.getName());
        }
        return attrResponseVo;
    }

    @Override
    @Transactional
    public void updateAttr(AttrVo attr) {
        //修改基本数据
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            //修改关联关系
            Integer count = attrAttrgroupRelationDao.selectCount(new LambdaUpdateWrapper<AttrAttrgroupRelationEntity>()
                    .set(AttrAttrgroupRelationEntity::getAttrGroupId, attr.getAttrGroupId())
                    .set(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId())
                    .eq(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId()));
            if (count > 0) {
                attrAttrgroupRelationDao.update(null, new LambdaUpdateWrapper<AttrAttrgroupRelationEntity>()
                        .set(AttrAttrgroupRelationEntity::getAttrGroupId, attr.getAttrGroupId())
                        .set(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId())
                        .eq(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId()));
            } else {
                AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
                attrAttrgroupRelationEntity.setAttrId(attr.getAttrId());
                attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
            }
        }
    }

    /**
     * 根据属性组ID获取关联属性列表。
     *
     * @param attrGroupId 属性组的ID，用于查询关联的属性。
     * @return 返回一个空的属性列表。
     */
    @Override
    public List<AttrVo> getRelationAttr(Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> relationEntities = attrAttrgroupRelationDao.selectList(
                new LambdaQueryWrapper<AttrAttrgroupRelationEntity>()
                        .eq(AttrAttrgroupRelationEntity::getAttrGroupId, attrGroupId));
        if (relationEntities != null && !relationEntities.isEmpty()) {
            List<Long> attrIds = relationEntities.stream()
                    .map(AttrAttrgroupRelationEntity::getAttrId)
                    .collect(Collectors.toList());
            if (!attrIds.isEmpty()) {
                List<AttrEntity> attrEntities = (List<AttrEntity>)this.listByIds(attrIds);
                if (attrEntities != null && !attrEntities.isEmpty()) {
                    return attrEntities.stream().map(attrEntity -> {
                        AttrVo attrVo = new AttrVo();
                        BeanUtils.copyProperties(attrEntity, attrVo);
                        return attrVo;
                    }).collect(Collectors.toList());
                }
            }
        }

        return null;
    }
}