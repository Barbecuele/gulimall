package com.mxj.gulimall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxj.common.utils.PageUtils;
import com.mxj.common.utils.Query;

import com.mxj.gulimall.product.dao.CategoryDao;
import com.mxj.gulimall.product.entity.CategoryEntity;
import com.mxj.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> entities = categoryDao.selectList(null);
        //2.组装成父子的树形结构
        //2.1找到所有的一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).peek(menu -> menu.setChildren(getChildrens(menu, entities))).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))
        ).collect(Collectors.toList());
        return level1Menus;
    }

    /**
     * 递归查找所有菜单的子菜单
     */
    private List<CategoryEntity> getChildrens(CategoryEntity menu, List<CategoryEntity> entities) {
        List<CategoryEntity> children = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == menu.getCatId()
        ).peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity, entities))).sorted(Comparator.comparingInt(menu1 -> (menu1.getSort() == null ? 0 : menu1.getSort()))
        ).collect(Collectors.toList());
        return children;
    }

}