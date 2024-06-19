package com.mxj.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.google.gson.Gson;
import com.mxj.gulimall.product.config.QiNiuYunConfig;
import com.mxj.gulimall.product.entity.BrandEntity;
import com.mxj.gulimall.product.service.BrandService;
//import com.qiniu.common.QiniuException;
//import com.qiniu.common.Zone;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
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
//        LambdaQueryWrapper<BrandEntity> brandEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        brandEntityLambdaQueryWrapper.eq(BrandEntity::getBrandId, 1L);
//        BrandEntity entity = brandService.getOne(brandEntityLambdaQueryWrapper);
//        System.out.println(entity);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("成都");
        stringBuilder.append(" / ");
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        System.out.println(stringBuilder);
    }
}

//    @Autowired
//    QiNiuYunConfig qiNiuYunConfig;

//测试上传本地文件到七牛云
//    @Test
//    public void upload() {
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Zone.zone1());
//        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//        //...其他参数参考类注释
//
//        UploadManager uploadManager = new UploadManager(cfg);
//        //...生成上传凭证，然后准备上传
//        String accessKey = qiNiuYunConfig.getAccessKey();
//        String secretKey = qiNiuYunConfig.getSecretKey();
//        String bucket = qiNiuYunConfig.getBucketName();
//        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "C:\\Users\\24237\\Desktop\\code(1)\\20240428004251.png";
//        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "2024/20240428004251.png";
//
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//
//        try {
//            Response response = uploadManager.put(localFilePath, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
//        } catch (QiniuException ex) {
//            ex.printStackTrace();
//            if (ex.response != null) {
//                System.err.println(ex.response);
//
//                try {
//                    String body = ex.response.toString();
//                    System.err.println(body);
//                } catch (Exception ignored) {
//                }
//            }
//        }

//    }
//}
