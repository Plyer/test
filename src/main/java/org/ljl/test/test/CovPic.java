package org.ljl.test.test;

import com.mortennobel.imagescaling.ResampleOp;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author lvjinglu
 * created at 2019/12/16
 */
public class CovPic {

    private String targetDir;

    public CovPic(String targetDir) {
        this.targetDir = targetDir;
        File file = new File(targetDir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void main(String[] args) {
        CovPic covPic = new CovPic(".");
        covPic.cov2(new File("/Users/abc/Documents/WechatIMG64.png"));
    }

    public void cov(File img) {
        try {
            int width = Thumbnails.of(img).scale(1).asBufferedImage().getWidth();
            double scale = 750.0 / width;
            File file = new File(targetDir + "/" + img.getName().trim());
            if (!file.exists()) {
                file.createNewFile();
            }

            Thumbnails.of(img).outputQuality(1).scale(scale).toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cov2(File img) {
        try {
            BufferedImage inputBufImage = ImageIO.read(img);
            int height = (int) (inputBufImage.getHeight() * (750.0 / inputBufImage.getWidth()));
            File file = new File(targetDir + "/2" + img.getName().trim());
            if (!file.exists()) {
                file.createNewFile();
            }

            ResampleOp resampleOp = new ResampleOp(750, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,null);
            ImageIO.write(rescaledTomato, "JPG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
