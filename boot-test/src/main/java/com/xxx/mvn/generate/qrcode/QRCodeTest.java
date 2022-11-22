package com.xxx.mvn.generate.qrcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author:TuoTuo
 * @createDate:2022/8/16 15:31
 * @description:
 */
@Slf4j
public class QRCodeTest {
    @Test
    public void test() throws IOException {
        BufferedImage image = QRCodeUtil.createImage("UTF-8", "ABAC", 200, 300);
        System.out.println(image);
        // 保存图片到本地
        File outputfile = new File("C:\\Users\\Ging\\Desktop\\java 代码生成的二维码\\qrcode01.jpg");
        ImageIO.write(image, "jpg", outputfile);
    }

    @Test
    public void testCreateImage() throws IOException {
        String content = "https://www.baidu.com/";
//        content = "445";
        BufferedImage image = QRCodeUtil.createImage("UTF-8", content, 200, 300);
        File file = new File("C:\\Users\\Ging\\Desktop\\01.jpg");
        Image img = ImageIO.read(file);
        int wideth = img.getWidth(null); // 得到源图宽
        int height = img.getHeight(null); // 得到源图长
        BufferedImage tag = new BufferedImage(5 * wideth, 3 * height,
                BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(img, 0, 0, 5 * wideth,
                3* height, null); // 绘制后的图
        System.out.println(tag);
         QRCodeUtil.addUpFont(tag,"百度");
         QRCodeUtil.insertLogoImage(image,tag,20,30);
        // 保存图片到本地
        File outputfile = new File("C:\\Users\\Ging\\Desktop\\java 代码生成的二维码\\qrcode09.jpg");
        ImageIO.write(image, "jpg", outputfile);
    }
}
