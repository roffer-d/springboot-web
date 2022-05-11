package com.roffer.web.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 滑块验证工具类
 * VerifyImageUtil
 */
public class VerifyImageUtils {

    /** 源文件宽度 */
    private static final int ORI_WIDTH = 400;
    /** 源文件高度 */
    private static final int ORI_HEIGHT = 200;
    /** 抠图坐标x */
    private static int X;
    /** 抠图坐标y */
    private static int Y;
    /** 模板图宽度 */
    private static int WIDTH;
    /** 模板图高度 */
    private static int HEIGHT;

    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    /**
     * 根据模板切图
     * @param templateFile 模板文件
     * @param targetFile 目标文件
     * @param templateType 模板文件类型
     * @param targetType 目标文件类型
     * @return 切图map集合
     * @throws Exception 异常
     */
    public static Map<String, Object> pictureTemplatesCut(File templateFile, File targetFile, String templateType, String targetType) throws Exception {
        Map<String, Object> pictureMap = new HashMap<>(2);

        InputStream targetIs = new FileInputStream(targetFile);
        // 模板图
        BufferedImage imageTemplate = ImageIO.read(templateFile);
        WIDTH = imageTemplate.getWidth();
        HEIGHT = imageTemplate.getHeight();
        // 随机生成抠图坐标
        generateCutoutCoordinates();
        // 最终图像
        BufferedImage newImage = new BufferedImage(WIDTH, HEIGHT, imageTemplate.getType());
        Graphics2D graphics = newImage.createGraphics();
        graphics.setBackground(Color.white);

        int bold = 5;
        // 获取感兴趣的目标区域
        BufferedImage targetImageNoDeal = getTargetArea(X, Y, WIDTH, HEIGHT, targetIs, targetType);

        // 根据模板图片抠图
        newImage = dealCutPictureByTemplate(targetImageNoDeal, imageTemplate, newImage);

        // 设置“抗锯齿”的属性
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();

        //模板图像矩阵 加阴影边框
        //boundaryShadow(newImage);

        //新建流。
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
        ImageIO.write(newImage, "png", os);
        byte[] newImages = os.toByteArray();
        pictureMap.put("slideImage", newImages);

        // 源图生成遮罩
        BufferedImage oriImage = ImageIO.read(targetFile);
        byte[] oriCopyImages = dealOriPictureByTemplate(oriImage, imageTemplate, X, Y);
        pictureMap.put("validateImage", oriCopyImages);

        pictureMap.put("x", X);
        pictureMap.put("y", Y);
        System.out.println("X="+X+";y="+Y);

        return pictureMap;
    }

    /**
     * 抠图后原图生成
     * @param oriImage 原始图片
     * @param templateImage 模板图片
     * @param x 坐标X
     * @param y 坐标Y
     * @return 添加遮罩层后的原始图片
     * @throws Exception 异常
     */
    private static byte[] dealOriPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage, int x,
                                                   int y) throws Exception {
        // 源文件备份图像矩阵 支持alpha通道的rgb图像
        BufferedImage oriCopyImage = new BufferedImage(oriImage.getWidth(), oriImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);
        // 模板图像矩阵
        int[][] templateImageData = getData(templateImage);

        //copy 源图做不透明处理
        for (int i = 0; i < oriImageData.length; i++) {
            for (int j = 0; j < oriImageData[0].length; j++) {
                int rgb = oriImage.getRGB(i, j);
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                //无透明处理
                rgb = r + (g << 8) + (b << 16) + (240 << 24);
                oriCopyImage.setRGB(i, j, rgb);
            }
        }

        for (int i = 0; i < templateImageData.length; i++) {
            for (int j = 0; j < templateImageData[0].length - 5; j++) {
                int rgb = templateImage.getRGB(i, j);
                //对源文件备份图像(x+i,y+j)坐标点进行透明处理(被抠图的阴影部分)
                if (rgb != 16777215 && rgb <= 0) {
                    int rgb_ori = oriCopyImage.getRGB(x + i, y + j);
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    rgb_ori = r + (g << 8) + (b << 16) + (70 << 24);
                    oriCopyImage.setRGB(x + i, y + j, rgb_ori);
                } else {
                    //do nothing
                }
            }
        }
        //新建流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流
        ImageIO.write(oriCopyImage, "png", os);
        //从流中获取数据数组
        return os.toByteArray();
    }

    /**
     * 根据模板图片抠图
     * @param oriImage 原始图片
     * @param templateImage 模板图片
     * @return 扣了图片之后的原始图片
     */
    private static BufferedImage dealCutPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage,
                                                          BufferedImage targetImage) throws Exception {
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);
        // 模板图像矩阵
        int[][] templateImageData = getData(templateImage);

        System.out.println("shi：" + templateImageData.length);
        // 模板图像宽度
        for (int i = 0; i < templateImageData.length; i++) {
            // 模板图片高度
            for (int j = 0; j < templateImageData[0].length; j++) {
                // 如果模板图像当前像素点不是白色 copy源文件信息到目标图片中
                int rgb = templateImageData[i][j];
                if (rgb != 16777215 && rgb <= 0) {
                    targetImage.setRGB(i, j, oriImageData[i][j]);
                }
            }
        }
        return targetImage;
    }

    /**
     * 获取目标区域
     * @param x            随机切图坐标x轴位置
     * @param y            随机切图坐标y轴位置
     * @param targetWidth  切图后目标宽度
     * @param targetHeight 切图后目标高度
     * @param ois          源文件输入流
     * @return 返回目标区域
     * @throws Exception 异常
     */
    private static BufferedImage getTargetArea(int x, int y, int targetWidth, int targetHeight, InputStream ois,
                                               String fileType) throws Exception {
        Iterator<ImageReader> imageReaderList = ImageIO.getImageReadersByFormatName(fileType);
        ImageReader imageReader = imageReaderList.next();
        // 获取图片流
        ImageInputStream iis = ImageIO.createImageInputStream(ois);
        // 输入源中的图像将只按顺序读取
        imageReader.setInput(iis, true);

        ImageReadParam param = imageReader.getDefaultReadParam();
        Rectangle rec = new Rectangle(x, y, targetWidth, targetHeight);
        param.setSourceRegion(rec);
        return imageReader.read(0, param);
    }

    /**
     * 生成图像矩阵
     * @param bufferedImage 图片流
     * @return 图像矩阵
     */
    private static int[][] getData(BufferedImage bufferedImage){
        int[][] data = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                data[i][j] = bufferedImage.getRGB(i, j);
            }
        }
        return data;
    }

    /**
     * 随机生成抠图坐标
     */
    private static void generateCutoutCoordinates() {
        Random random = new Random();
        int widthDifference = ORI_WIDTH - WIDTH;
        int heightDifference = ORI_HEIGHT - HEIGHT;

        if (widthDifference <= 0) {
            X = 5;
        } else {
            X = random.nextInt(ORI_WIDTH - 3*WIDTH) + 2*WIDTH + 5;
        }

        if (heightDifference <= 0) {
            Y = 5;
        } else {
            Y = random.nextInt(ORI_HEIGHT - HEIGHT ) + 5;
        }

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
    }
}




