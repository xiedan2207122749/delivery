package com.jxyj.delivery.common.util;

import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import com.jxyj.delivery.sys.util.SysConstant;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author dell
 * @Classname FileUploadUtil
 * @Description
 * @Date 2020/12/2 10:09
 * @Created by mr_xie
 */
@Slf4j
public class FileUploadUtil {

    private final static HashSet<String> SET = new HashSet<String>(32) {
        private static final long serialVersionUID = 1L;
        {
            add("bmp");
            add("dib");
            add("gif");
            add("jfif");
            add("jpe");
            add("jpeg");
            add("jpg");
            add("png");
            add("tif");
            add("tiff");
            add("ico");
        }
    };

    public static void onlyPicture(MultipartFile multipartFile) {
        String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!FileUploadUtil.SET.contains(suffix)) {
            throw new BaseException(GlobalErrorEnum.MUST_IS_PICTURE);
        }
    }
    /**
     * 获取文件位子
     * @param multipartFile 客户上传的位置
     * @param filePath 文件存放位置
     * @return 生成完整的文件位置
     */
    public static String getFilePath(MultipartFile multipartFile, String filePath) {
        if (multipartFile == null) {
            throw new BaseException(GlobalErrorEnum.FILE_EMPTY);
        }
        LocalDate now = LocalDate.now();
        StringBuilder builder = new StringBuilder(SysConstant.FILE_PREFIX);
        builder.append(filePath).append("/").append(now.getYear()).append("/").append(now.getMonthValue()).append("/").append(now.getDayOfMonth());
        builder.append("/").append(UUID.randomUUID().toString());
        builder.append(".").append(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        return builder.toString();
    }
    
    private static void fileUpload(MultipartFile multipartFile, String filename) {
        File targetFile = new File(filename);
        //保存文件
        try {
            File parentFile = targetFile.getParentFile();
            if (!parentFile.exists()) {
                // 之所以不直接用mkdirs创建这个路径所有文件 是因为  mkdirs创建完 全是目录 只有执行完{targetFile.createNewFile();}
                // 之后才会把最后一个目录改成文件 但是上传了的如果是txt文件 而且txt文件是空的 这样执行{targetFile.createNewFile();}会抛异常 所以用这种创建方式先创建文件所有父级目录
                // 然后再用{targetFile.createNewFile()}创建对应的文件
                parentFile.mkdirs();
            }
            targetFile.createNewFile();
            multipartFile.transferTo(targetFile);
        } catch (IllegalStateException e) {
            log.error("multipartFile.transferTo, IllegalStateException", e);
        } catch (IOException e) {
            log.error("multipartFile.transferTo, IOException", e);
        }
    }
    
    /**
     *
     * @Description 生成缩略图
     * @param imageFile 图片文件
     * @return
     */
    public static String uploadFileAndCreateThumbnail(MultipartFile imageFile) {
        FileUploadUtil.onlyPicture(imageFile);
        String perpetualFilePosition;
        long size = imageFile.getSize();
        if (size > SysConstant.NEED_COMPRESS_THRESHOLD_VALUE) {
            // 永久地址
            perpetualFilePosition = getFilePath(imageFile, SysConstant.PART_POSITION);
            // png换成jpg内存会少很多
            if (perpetualFilePosition.contains(".png")) {
                perpetualFilePosition = perpetualFilePosition.replace(".png", ".jpg");
            }
            System.out.println(perpetualFilePosition);
            File targetFile = new File(perpetualFilePosition);
            File parentFile = targetFile.getParentFile();
            if (!parentFile.exists()) {
                // 之所以不直接用mkdirs创建这个路径所有文件 是因为  mkdirs创建完 全是目录 只有执行完{targetFile.createNewFile();}
                // 之后才会把最后一个目录改成文件 但是上传了的如果是txt文件 而且txt文件是空的 这样执行{targetFile.createNewFile();}会抛异常 所以用这种创建方式先创建文件所有父级目录
                // 然后再用{targetFile.createNewFile()}创建对应的文件
                parentFile.mkdirs();
            }
            try {
                Thumbnails.of(imageFile.getInputStream()).scale(1f).outputQuality(SysConstant.NEED_COMPRESS_THRESHOLD_VALUE * 1.0 / size).outputFormat("jpg").toFile(perpetualFilePosition);
            } catch (Exception e) {
                log.error("multipartFile.transferTo, IllegalStateException", e);
                throw new BaseException(GlobalErrorEnum.ILLEGAL_DATA);
            }
        } else {
            // 如果上传的文件没有超过阈值 就不用压缩了 直接把文件放到指定位置就行了
            perpetualFilePosition = getFilePath(imageFile, SysConstant.PART_POSITION);
            System.out.println(perpetualFilePosition);
            fileUpload(imageFile, perpetualFilePosition);
        }
        try {
            perpetualFilePosition = perpetualFilePosition.replace(SysConstant.FILE_PREFIX, SysConstant.REPLACE_UP_LOAD);
            perpetualFilePosition = SysConstant.SERVER_IP + perpetualFilePosition + "?originalFileName=" +
                URLEncoder.encode(imageFile.getOriginalFilename(), "utf-8").replace("+","%20");
        } catch (Exception e) {
            log.error("multipartFile.transferTo, IllegalStateException", e);
            throw new BaseException(GlobalErrorEnum.ILLEGAL_DATA);
        }
        return perpetualFilePosition;
    }
}
