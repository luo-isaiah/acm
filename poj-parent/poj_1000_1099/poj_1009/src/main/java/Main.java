import java.util.Scanner;

/**
 * IONU Satellite Imaging, Inc. records and stores very large images using run length encoding.
 * You are to write a program that reads a compressed image, finds the edges in the image,
 * as described below, and outputs another compressed image of the detected edges.
 * <p>
 * A simple edge detection algorithm sets an output pixel's value to be the maximum absolute value of the differences
 * between it and all its surrounding pixels in the input image.
 * Consider the input image below:
 * <p>
 * <img src="http://poj.org/images/1009_1.jpg"/>
 * The upper left pixel in the output image is the maximum of the values |15-15|,|15-100|, and |15-100|, which is 85.
 * The pixel in the 4th row, 2nd column is computed as the maximum of |175-100|, |175-100|, |175-100|,
 * |175-175|, |175-25|, |175-175|,|175-175|, and |175-25|, which is 150.
 * Images contain 2 to 1,000,000,000 (109) pixels.
 * All images are encoded using run length encoding (RLE).
 * This is a sequence of pairs, containing pixel value (0-255) and run length (1-109).
 * Input images have at most 1,000 of these pairs.
 * Successive pairs have different pixel values.
 * All lines in an image contain the same number of pixels.
 *
 * @author Luo Yinzhuo
 * @date 2014-3-24
 * @see <a href="http://poj.org/problem?id=1009">Edge Detection</a>
 */
public class Main {
    /**
     * The program entrance.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the first line.
        System.out.println(scanner.nextLine());

        while (scanner.hasNextLine()) {
            System.out.println(haab2Tzolkin(scanner.nextLine()));
        }
        scanner.close();
    }


}
