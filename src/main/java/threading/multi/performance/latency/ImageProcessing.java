package threading.multi.performance.latency;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO study the codes
public class ImageProcessing {

    public static final String FILE_PATH = "/Users/administrator/Documents/Workspace/Practice/Practice/resource/threading/multi/camera.jpeg";
    public static final String DESTINATION_PATH = "/Users/administrator/Documents/Workspace/Practice/Practice/resource/out/camera.jpeg";

    public static void main(String[] args) throws IOException {

        BufferedImage originalImage = ImageIO.read(new File(FILE_PATH));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();

//        recolorSingleThreaded(originalImage, resultImage); // time spent: 897
        recorlorMultiThreaded(originalImage, resultImage, 2); // time spent depending on number of threads: 905(1), 592(2), 387(3)

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_PATH);
        ImageIO.write(resultImage, "jpeg", outputFile);

        System.err.println("Duration: " + duration);
    }

    public static void recorlorMultiThreaded(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) {
        final List<Thread> threads = new ArrayList<>(numberOfThreads);
        final int width = originalImage.getWidth();
        final int height = originalImage.getHeight() / numberOfThreads;

        for (int i = 0 ; i < numberOfThreads ; i++) {
            final int threadMultiplier = i;

            Thread thread = new Thread(() -> {
               int leftCorner = 0;
               int topCorner = height * threadMultiplier;

               recolorImage(originalImage, resultImage, leftCorner, topCorner, width, height);
            });

            threads.add(thread);
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void recolorSingleThreaded(BufferedImage originalImage, BufferedImage resultImage) {
        recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
    }

    public static void recolorImage(
            BufferedImage originalImage,
            BufferedImage resultImage,
            int leftCorner, int topCorner, int width, int height) {
        for (int x = leftCorner ; x < leftCorner + width && x < originalImage.getWidth() ; x++) {
            for (int y = topCorner ; y < topCorner + height && y <originalImage.getHeight() ; y++) {
                recolorPixel(originalImage, resultImage, x, y);
            }
        }
    }

    public static void recolorPixel(
            BufferedImage originalImage,
            BufferedImage resultImage,
            int x,
            int y) {

        int rgb = originalImage.getRGB(x, y);

        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        int newRed;
        int newGreen;
        int newBlue;

        if (isShadeOfGray(red, green, blue)) {
            newRed = Math.min(255, red + 10);
            newGreen = Math.max(0, green - 80);
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
        setRGB(resultImage, x, y, newRGB);
    }

    public static void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster()
                .setDataElements(
                        x, y, image.getColorModel().getDataElements(rgb, null)
                );
    }

    public static boolean isShadeOfGray(int red, int green, int blue) {
        return Math.abs(red - green) < 30
                && Math.abs(red - blue) < 30
                && Math.abs(green - blue) < 30;
    }

    public static int createRGBFromColors(int red, int green, int blue) {
        int rgb = 0;

        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;

        rgb |= 0xFF000000;

        return rgb;
    }

    public static int getRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    public static int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    public static int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }
}
