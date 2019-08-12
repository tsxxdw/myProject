package cn.tsxxdw.service.mypic;

import cn.tsxxdw.mybean.vo.ResultVo;
import cn.tsxxdw.service.mylog.MyLogUtil;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class MyPicUtils {
    public static ResultVo compressPic(String source, String target) {
        try {
            Objects.requireNonNull(source);
            File file = null;
            BufferedImage src = null;
            FileOutputStream out = null;
            ImageWriter imgWrier;
            ImageWriteParam imgWriteParams;

            String suffix = getSuffix_notDots(source).getData();

            // 指定写图片的方式为 png
            imgWrier = ImageIO.getImageWritersByFormatName(suffix).next();
            imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                    null);
            // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
            imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
            // 这里指定压缩的程度，参数qality是取值0~1范围内，
            imgWriteParams.setCompressionQuality((float) 0.1);
            imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
            ColorModel colorModel = ImageIO.read(new File(source)).getColorModel();// ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
//        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
//                colorModel, colorModel.createCompatibleSampleModel(16, 16)));
            imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                    colorModel, colorModel.createCompatibleSampleModel(16, 16)));


            file = new File(source);
            System.out.println(file.length());
            src = ImageIO.read(file);
            out = new FileOutputStream(target);

            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
            // OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null),
                    imgWriteParams);
            out.flush();
            out.close();
            return ResultVo.createSimpleSuccessResult();


        } catch (Exception e) {
            log.error("MyPicUtils.compressPic.error:{}", e);
            return ResultVo.createSystemErrorResult();
        }

    }

    /**
     * 没有小数点
     *
     * @param originFileName
     * @return
     */
    public static ResultVo<String> getSuffix_notDots(String originFileName) {
        int index = originFileName.lastIndexOf(".");
        if (index < 0) { //判断是否有“.”
            return new ResultVo().setFail("Suffix dont have");
        }
        int leng = originFileName.length();
        if (leng - index <= 1) {//判断是否有后缀
            return new ResultVo().setFail("Dont have suffix");
        }
        String suffix = originFileName.substring(index + 1, leng);
        return new ResultVo().setSuccess(suffix);
    }

    public static ResultVo<String> getPrefix(String originFileName) {
        int index = originFileName.lastIndexOf(".");
        if (index < 0) { //判断是否有“.”
            return new ResultVo().setFail("Suffix dont have");
        }
        int leng = originFileName.length();
        if (leng - index <= 1) {//判断是否有后缀
            return new ResultVo().setFail("Dont have suffix");
        }
        String suffix = originFileName.substring(0, index);
        return new ResultVo().setSuccess(suffix);
    }


}