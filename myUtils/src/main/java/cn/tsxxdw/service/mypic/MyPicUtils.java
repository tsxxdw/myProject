package cn.tsxxdw.service.mypic;

import cn.tsxxdw.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    /**
     *
     *
     * @param originFileName
     * @return
     */
    public static ResultVo<String> getSuffix(String originFileName) {
        int index = originFileName.lastIndexOf(".");
        if (index < 0) { //判断是否有“.”
            return new ResultVo().setFail("Suffix dont have");
        }
        int leng = originFileName.length();
        if (leng - index <= 1) {//判断是否有后缀
            return new ResultVo().setFail("Dont have suffix");
        }
        String suffix = originFileName.substring(index, leng);
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


    /**
     * 制作水印
     */
    //  markImageByText("hello world","D:/1.jpg","D:2.jpg",45,new Color(0,0,0),"JPG");
    public static ResultVo<Void> watermark_1(String centerLogoText, String source,String target,Integer degree,Color color,String picSuffix){
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            java.awt.Image srcImg = ImageIO.read(new File(source));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),  buffImg.getWidth()/2,buffImg.getHeight() /2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(new java.awt.Font("宋体", java.awt.Font.BOLD, buffImg.getHeight() /2));
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.15f));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(centerLogoText,  buffImg.getWidth()/2 , buffImg.getHeight()/2);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(target);
            ImageIO.write(buffImg, picSuffix, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultVo.createSimpleSuccessResult();
    }

    public static ResultVo<Void> watermark_2(String centerLogoText,String rightAndrigthLogoText, String source,String target,Color color,String picSuffix){
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            java.awt.Image srcImg = ImageIO.read(new File(source));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转

             //   g.rotate(Math.toRadians(degree),  buffImg.getWidth()/2,buffImg.getHeight() /2);

            // 5、设置水印文字颜色
            g.setColor(color);


            g.rotate(Math.toRadians(0),  0,0 );
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));
            g.setFont(new java.awt.Font("宋体", Font.LAYOUT_NO_START_CONTEXT, buffImg.getHeight() /30));
           // g.drawString(rightAndrigthLogoText,  50 , buffImg.getHeight()-50);




           int size= (int)Math.sqrt(buffImg.getWidth()*buffImg.getHeight());
            g.setFont(new java.awt.Font("宋体", java.awt.Font.BOLD,  size/10));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));
            g.rotate(Math.toRadians(45),  buffImg.getWidth()/2,buffImg.getHeight() /2);
            g.drawString(centerLogoText,  buffImg.getWidth()/3 , buffImg.getHeight()/2);




            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(target);
            ImageIO.write(buffImg, picSuffix, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultVo.createSimpleSuccessResult();
    }

    public static void main(String[] args) {
        String source="D:\\duan\\file\\mct\\中式菜系\\中式菜系\\【黄金满地蒜香蟹】粤菜经典勾魂滋味\\c667d89c269b43a5b643fa015913a6f2.jpeg";
        String target="D:\\duan\\file\\mct\\中式菜系\\中式菜系\\【黄金满地蒜香蟹】粤菜经典勾魂滋味\\c667d89c269b43a5b643fa015913a6f2_detail.jpeg";
        MyPicUtils.watermark_2("美菜图","图片来源：外卖名堂美菜图",source,target,new Color(255,255,255),"JPG");

    }

}
