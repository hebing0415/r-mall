package com.robot.api.util;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class QRCodeUtil {

    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;

    private static final int length = 160;

    /*二维码生成*/
    public static String codeCreate(String text, String accessKey,
                                    String secretKey, String bucket, String resourceHost) {
        Map<EncodeHintType, Object> his = new HashMap<EncodeHintType, Object>();
        his.put(EncodeHintType.MARGIN, 0);
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, length, length, his);
            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();
            //3、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //4、循环将二维码内容定入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            // 创建一个输出流
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //将图片写出到指定位置（复制图片）
            ImageIO.write(image, "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            // 定义一个 Map 集合 存放返回值
            return upload(is, accessKey, secretKey, bucket, resourceHost);
        } catch (WriterException e) {
            log.error("二维码生成失败", e);
        } catch (IOException e) {
            log.error("生成二维码图片失败", e);
        }
        return null;
    }


    /*七牛云上传*/

    public static String upload(InputStream stream, String accessKey,
                                String secretKey, String bucket, String resourceHost) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        long key = new Date().getTime();
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Response response = uploadManager.put(stream, String.valueOf(key), upToken, null, null);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            /*retMap.put("url", "上传的连接" + putRet.key)*/
            ;
            return resourceHost + putRet.key;
        } catch (Exception ex) {
            log.error("upload error", ex);
        }
        return null;
    }
}


