package com.mxj.gulimall.member.feign;

import com.mxj.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: CouponFeignService
 * @Author mi xiu jin
 * @Package com.mxj.gulimall.member.feign
 * @Date 2024/4/14 1:16
 * @description: 远程调用优惠券服务
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupon();
}
