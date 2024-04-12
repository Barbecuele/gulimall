package com.mxj.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mxj.gulimall.product.entity.BrandEntity;
import com.mxj.gulimall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("华为");
//        brandEntity.setDescript("华为");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");
//        brandEntity.setBrandId(1L);
//        brandEntity.setDescript("遥遥领先");
//        brandEntity.setName("华为p50");
//        brandService.updateById(brandEntity);
        //分页查询
        LambdaQueryWrapper<BrandEntity> brandEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        brandEntityLambdaQueryWrapper.eq(BrandEntity::getBrandId,1L);
        BrandEntity entity = brandService.getOne(brandEntityLambdaQueryWrapper);
        System.out.println(entity);

    }

}
