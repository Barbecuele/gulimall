package com.mxj.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.exceptions.ClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    void contextLoads() throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get("C:\\Users\\24237\\Desktop\\code(1)\\20240428004251.png"));
        ossClient.putObject("gulimall-g1", "20240428004251.png", inputStream);
        ossClient.shutdown();
        System.out.println("上传完成");
    }

}
