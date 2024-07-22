package co.edu.cue.escolarvote.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;

public class ImageUtil {
    public static byte[] fileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Image byteArrayToImage(byte[] byteArray){
        return new Image(new ByteArrayInputStream(byteArray));
    }

    public static byte[] imageToBytes(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        ByteBuffer buffer = ByteBuffer.allocate(width * height * 4); // 4 bytes per pixel (BGRA)

        pixelReader.getPixels(0, 0, width, height, format, buffer, width * 4);

        return buffer.array();
    }

    public static byte[] convertFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return data;
    }
}
