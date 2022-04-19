package com.jxyj.delivery;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

// @SpringBootTest
class DeliveryApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(DigestUtil.md5Hex(DigestUtil.md5Hex("111111")));
    }
    @Test
    void contextLoads1() {
        System.out.println(DigestUtil.md5Hex("111111"));
    }

}
