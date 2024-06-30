package com.mxj.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.mxj.gulimall.product.service.AttrService;
import com.mxj.gulimall.product.service.CategoryService;
import com.mxj.gulimall.product.vo.AttrGroupVo;
import com.mxj.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mxj.gulimall.product.entity.AttrGroupEntity;
import com.mxj.gulimall.product.service.AttrGroupService;
import com.mxj.common.utils.PageUtils;
import com.mxj.common.utils.R;


/**
 * 属性分组
 *
 * @author mxj
 * @email 2423776460@qq.com
 * @date 2024-04-12 21:13:50
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @RequestMapping("/{attrGroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrGroupId") Long attrGroupId) {
        List<AttrVo> attrVos = attrService.getRelationAttr(attrGroupId);

        return R.ok().put("data", attrVos);
    }

    /**
     * 列表
     */
    @GetMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(catelogPath);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("attr/relation/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody List<AttrGroupVo> attrGroupIds) {
        attrGroupService.deleteRelation(attrGroupIds);
        return R.ok();
    }

}
