import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PhotoMagic
{
    public static Picture transform(Picture pic, LFSR lfsr){
        //encrypt an image using lfsr

        //each pixel, get RGB values of the pixel,
        // and then XOR RGB, each with 8 randomly generated bits,
        // that color is the pixel's new color
        for (int row = 0; row < pic.width(); row++) {
            for (int col = 0; col < pic.height(); col++) {
                Color pixelColor = pic.get(row, col);

                //change red color
                int redColor = pixelColor.getRed();
                int redXORval = lfsr.generate(8);
                redColor = redColor ^ redXORval;

                //change green color
                int greenColor = pixelColor.getGreen();
                int greenXORval = lfsr.generate(8);
                greenColor = greenXORval ^ greenColor;

                //change blue color
                int blueColor = pixelColor.getBlue();
                int blueXORval = lfsr.generate(8);
                blueColor = blueColor ^ blueXORval;

                pic.set(row, col, new Color(redColor, greenColor, blueColor));
            }
        }

        return pic;
    }

    public static void main(String[] args) {

        Picture original = new Picture("pipe.png");
        original.show();

        Picture encrypted = transform(new Picture("pipe.png"), new LFSR("01101000010100010000", 16));
        encrypted.show();

        File file1 = new File("encryptedPipe.png");
        try {
            file1.createNewFile();
            encrypted.save(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Picture decrypted = transform(new Picture("encryptedPipe.png"), new LFSR("01101000010100010000", 16));
        decrypted.show();

        File file2 = new File("decryptedPipe.png");
        try {
            file2.createNewFile();
            decrypted.save(file2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
