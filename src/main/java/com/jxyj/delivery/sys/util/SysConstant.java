package com.jxyj.delivery.sys.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xieDan
 * @Classname SysConstant
 * @Description
 * @Date 2022/4/7 22:43
 * @Created by mr_xie
 */
@Component
public class SysConstant {
    /**
     * 用户token
     */
    public static final String USER_TOKEN = "sysUserToken";
    /**
     * 用户ip
     */
    public static String GET_USER_IP(Long userId) {
        return "sysUserIp" + userId;
    }


    /**
     * 获取被挤下去的人的token
     *
     * @param token
     * @return
     */
    public static String GET_SQUEEZED_TOKEN(String token) {
        return "sysSqueezedToken" + token;
    }

    /**
     * 需要压缩的图片阈值 (KB = 1024 * 100)
     */
    public static final int NEED_COMPRESS_THRESHOLD_VALUE = 102400;

    /**
     * 替换掉真实的路径
     */
    public final static String REPLACE_UP_LOAD = "download";

    @PostConstruct
    private void init() {
        PART_POSITION = partPosition;
        FILE_PREFIX = filePrefix;
        SERVER_IP = serverIp;
    }
    /**
     * 零配件上传位置
     */
    public static String PART_POSITION;
    /**
     * 服务器ip
     */
    public static String SERVER_IP;
    /**
     * 文件前缀
     */
    public static String FILE_PREFIX;
    @Value("${file.upload.serverIp}")
    private String serverIp;
    @Value("${file.upload.prefix}")
    private String filePrefix;
    @Value("${file.upload.part.position}")
    private String partPosition;

}
