import org.code.theater.*;
import org.code.media.*;

public class PremierLeague extends Scene {

  /** Instance Variables */
  private ImageFilter[][] players;    //2D array of images of the top three players of the top three teams in the Premier League
  private ImageFilter[] teams;        //1D array of the logos of the top three teams in the Premier League
  
  /** Constructor */
  public PremierLeague() {
    players = new ImageFilter[][] { {new ImageFilter("sakaARScropped.png"), new ImageFilter("salibaARScropped.png"), new ImageFilter("riceARScropped.png")},
                                    {new ImageFilter("haalandMCIcropped.png"), new ImageFilter("rodriMCIcropped.png"), new ImageFilter("fodenMCIcropped.png")},
                                    {new ImageFilter("tielemansAVLcropped.png"), new ImageFilter("watkinsAVLcropped.png"), new ImageFilter("martinezAVLcropped.png")} };
    teams = new ImageFilter[] {new ImageFilter("arsLogocropped.png"), new ImageFilter("mciLogocropped.png"), new ImageFilter("avlLogocropped.png")};
  }

  /**
   * Top-level drawScene method which will draw the whole animation
   * Precondition: drawTeams() method works properly
   * Postcondition: Entire scene is drawn; teams and players are correctly displayed
   */
  public void drawScene() {
    clear("WHITE");
    drawTeams();
  }

  // other methods

  /*
   * Draws the images of the team logos and calls other helper methods to draw the players on each team
   * Precondition: teams array must be a 2D array of ImageFilter objects
   * Postcondition: Each team's logo appears and is applied with a filter then their respective players are drawn
   */
  public void drawTeams(){
    //Draws the Arsenal logo and text and plays sound
    playSound("ole.wav");
    drawImage(teams[0], 100, 20, 200, 0.0);
    setTextHeight(60);
    setTextColor("RED");
    setTextStyle(Font.MONO, FontStyle.NORMAL);
    drawText("Arsenal", 75, 300);
    pause(1);
    //Uses the mirror filter and draws the logo again
    teams[0].mirrorVertical();
    drawImage(teams[0], 100, 20, 200, 0.0);
    pause(1);

    //Calls the helper method to draw the players
    clear("BLACK");
    drawARSPlayers();

    //Draws the Manchester City logo and text and plays sound
    playSound("ole.wav");
    clear("WHITE");
    drawImage(teams[1], 100, 20, 200, 0.0);
    setTextHeight(45);
    setTextColor("TEAL");
    setTextStyle(Font.MONO, FontStyle.NORMAL);
    drawText("Manchester", 70, 300);
    drawText("City", 150, 350);
    pause(1);
    //Uses the threshold filter with the image's average RGB value as the argument for the threshold
    teams[1].threshold(getAveragePixelRGB(teams[1]));
    drawImage(teams[1], 100, 20, 200, 0.0);
    pause(1);

    //Calls the helper method to draw the players
    clear("BLACK");
    drawMCIPlayers();

    //Draws the Aston Villa logo and text and plays sound
    playSound("ole.wav");
    clear("WHITE");
    drawImage(teams[2], 100, 20, 200, 0.0);
    setTextHeight(45);
    setTextColor("MAROON");
    setTextStyle(Font.MONO, FontStyle.NORMAL);
    drawText("Aston", 130, 300);
    drawText("Villa", 130, 350);
    pause(1);
    //Uses the negative filter and draws the logo again
    teams[2].makeNegative();
    drawImage(teams[2], 100, 20, 200, 0.0);
    pause(1);

    //Calls the helper method to draw the players
    clear("BLACK");
    drawAVLPlayers();
  }

  /*
   * Draws the images the players on the team Arsenal
   * Precondition: players array must be a 2D array of ImageFilter objects, with the first row as the Arsenal players
   * Postcondition: Arsenal players are drawn and applied with the makeNegative() filter
   */
  public void drawARSPlayers() {
    for(int i = 0; i < players[0].length; i++) {
      drawImage(players[0][i], 20, 20, 360, 0.0);
      pause(1);
      //Applies makeNegative() filter and is drawn again
      players[0][i].makeNegative();
      drawImage(players[0][i], 20, 20, 360, 0.0);
      pause(1);
      clear("BLACK");
    }
  }

  /*
   * Draws the images the players on the team Manchester City
   * Precondition: players array must be a 2D array of ImageFilter objects, with the second row as the MCI players
   * Postcondition: MCI players are drawn and applied with the colorize() filter
   */
  public void drawMCIPlayers() {
    for(int i = 0; i < players[1].length; i++) {
      drawImage(players[1][i], 20, 20, 360, 0.0);
      pause(1);
      //Applies colorize() filter and is drawn again
      players[1][i].colorize();
      drawImage(players[1][i], 20, 20, 360, 0.0);
      pause(1);
      clear("BLACK");
    }
  }

  /*
   * Draws the images the players on the team Aston Villa
   * Precondition: players array must be a 2D array of ImageFilter objects, with the third row as the AVL players
   * Postcondition: AVL players are drawn and applied with the applySepia() filter
   */
  public void drawAVLPlayers() {
    for(int i = 0; i < players[2].length; i++) {
      drawImage(players[2][i], 20, 20, 360, 0.0);
      pause(1);
      //Applies applySepia() filter and is drawn again
      players[2][i].applySepia();
      drawImage(players[2][i], 20, 20, 360, 0.0);
      pause(1);
      clear("BLACK");
    }
  }

  /*
   * Returns the overall average RGB of the entire image
   * Precondition: ImageFilter class must be imported and contain the getImagePixels() method
   * Postcondition: Every single RGB value in the image is taken into account to return the overall average
   */
  public int getAveragePixelRGB(ImageFilter image) {
    //Creates the 2D array of pixels for the image argument
    Pixel[][] pixels = image.getImagePixels();
    int total = 0;
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        total += pixel.getRed() + pixel.getBlue() + pixel.getGreen();
      }
    }
    //Divides the total by the number of RGB values in the entire image
    return (int)(total / (pixels.length * pixels[0].length * 3.0));
  }
  
}