import org.code.theater.*;
import org.code.media.*;

/*
 * Class containing multiple filters for images
 */
public class ImageFilter extends ImagePlus {

  /*
   * Uses the constructor from ImagePlus
   */
  public ImageFilter(String filename) {
    super(filename);
  }

  /*
   * Adjusts the contrast of the image by multiplying the
   * red, green, and blue values by the multiplier
   */
  public void adjustContrast(int multiplier) {
    Pixel[][] pixels = getImagePixels();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        pixel.setBlue(pixel.getBlue() * multiplier);
        pixel.setRed(pixel.getRed() * multiplier);
        pixel.setGreen(pixel.getGreen() * multiplier);
        
        if (pixel.getBlue() > 255) {
          pixel.setBlue(255);
        }

        if (pixel.getRed() > 255) {
          pixel.setRed(255);
        }

        if (pixel.getGreen() > 255) {
          pixel.setGreen(255);
        }
      }
    }
  }

  /*
   * Applies a sepia filter by calculating new red, green, and blue color
   * values based on sepia tone formulas and setting the Pixel object to the result
   */
  public void applySepia() {
    Pixel[][] pixels = getImagePixels();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        int newRed = (int)(0.393 * pixel.getRed() + 0.769 * pixel.getGreen() + 0.189 * pixel.getBlue());
        int newGreen = (int)(0.349 * pixel.getRed() + 0.686 * pixel.getGreen() + 0.168 * pixel.getBlue());
        int newBlue = (int)(0.272 * pixel.getRed() + 0.534 * pixel.getGreen() + 0.131 * pixel.getBlue());
        pixel.setRed(newRed);
        pixel.setGreen(newGreen);
        pixel.setBlue(newBlue);
      }
    }
  }

  /*
   * Applies a colorize filter by converting each Pixel to grayscale and applying
   * a color to it based on its grayscale value
   */
  public void colorize() {
    Pixel[][] pixels = getImagePixels();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        int average = (int)((pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3);
        if (average < 85) {
          pixel.setRed(255);
          pixel.setBlue(0);
          pixel.setGreen(0);
        } else if (average < 170) {
          pixel.setGreen(255);
          pixel.setBlue(0);
          pixel.setRed(0);
        } else {
          pixel.setBlue(255);
          pixel.setGreen(0);
          pixel.setRed(0);
        }
      }
    }
  }

  /*
   * Inverts the colors in the image by setting the red,
   * green, and blue color values of each Pixel object to
   * the result of 255 minus their current values
   */
  public void makeNegative() {
    Pixel[][] pixels = getImagePixels();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        pixel.setRed(255 - pixel.getRed());
        pixel.setBlue(255 - pixel.getBlue());
        pixel.setGreen(255 - pixel.getGreen());
      }
    }
  }

  /*
   * Mirrors the image vertically
   */
  public void mirrorVertical() {
    Pixel[][] pixels = getImagePixels();
    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[0].length / 2; c++) {
        pixels[r][c].setColor(pixels[r][pixels[0].length - 1 - c].getColor());
      }
    }
  }

  /*
   * Applies a threshold filter to an image
   */
  public void threshold(int value) {
    Pixel[][] pixels = getImagePixels();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        int average = (int)((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);
        if (average < value) {
          pixel.setRed(0);
          pixel.setGreen(0);
          pixel.setBlue(0);
        } else {
          pixel.setRed(255);
          pixel.setGreen(255);
          pixel.setBlue(255);
        }
      }
    }
  }

  /*
   * Applies a random value to each of the image pixels
   */
  public void random() {
    Pixel[][] pixels = getImagePixels();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        pixel.setBlue((int)(Math.random() * 256));
        pixel.setRed((int)(Math.random() * 256));
        pixel.setGreen((int)(Math.random() * 256));
      }
    }
  }
}