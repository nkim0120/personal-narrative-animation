import org.code.theater.*;
import org.code.media.*;

public class WorldCup extends Scene {

  /** Instance Variables */
  private ImageFilter[][] teams;    //2D array of images of the top three teams of the top three continents in the upcoming 2026 World Cup
  
  /** Constructor */
  public WorldCup() {
    teams = new ImageFilter[][] { {new ImageFilter("portugalLogocropped.png"), new ImageFilter("englandLogocropped.png"), new ImageFilter("franceLogocropped.png")},
                                  {new ImageFilter("argentinaLogocropped.png"), new ImageFilter("ecuadorLogocropped.png"), new ImageFilter("colombiaLogocropped.png")},
                                  {new ImageFilter("moroccoLogocropped.png"), new ImageFilter("nigeriaLogocropped.png"), new ImageFilter("senegalLogocropped.png")} };
  }
  
  /**
   * Top-level drawScene method which will draw the whole animation
   * Precondition: All helper methods work as intended
   * Postcondition: The 2026 World Cup teams are draw correctly after their continent
   */
  public void drawScene() {
    //Creates the introduction text for the European teams and plays sound
    playSound("ole.wav");
    clear("WHITE");
    setTextHeight(60);
    setTextColor("BLACK");
    setTextStyle(Font.MONO, FontStyle.BOLD);
    drawText("EUROPE", 90, 200);
    pause(1);

    //Calls the helper method to draw the teams
    clear("BLACK");
    drawEUTeams();

    //Creates the introduction text for the South American teams and plays sound
    playSound("ole.wav");
    clear("WHITE");
    setTextHeight(60);
    setTextColor("BLACK");
    setTextStyle(Font.MONO, FontStyle.BOLD);
    drawText("SOUTH", 100, 200);
    drawText("AMERICA", 70, 250);
    pause(1);

    //Calls the helper method to draw the teams
    clear("BLACK");
    drawSATeams();

    //Creates the introduction text for the African teams and plays sound
    playSound("ole.wav");
    clear("WHITE");
    setTextHeight(60);
    setTextColor("BLACK");
    setTextStyle(Font.MONO, FontStyle.BOLD);
    drawText("AFRICA", 90, 200);
    pause(1);

    //Calls the helper method to draw the teams
    clear("BLACK");
    drawAFRTeams();
  }

  // other methods

  /*
   * Draws the logos the top three teams in Europe
   * Precondition: teams array must be a 2D array of ImageFilter objects, with the first row as the European teams
   * Postcondition: European teams are drawn and applied with the adjustContrast() filter
   */
  public void drawEUTeams() {
    for(int i = 0; i < teams[0].length; i++) {
      drawImage(teams[0][i], 20, 20, 360, 0.0);
      pause(1);
      //Applys the contrast filter with findContrast() as the argument
      teams[0][i].adjustContrast(findContrast(teams[0][i]));
      drawImage(teams[0][i], 20, 20, 360, 0.0);
      pause(1);
      clear("BLACK");
    }
  }

  /*
   * Draws the logos the top three teams in Africa
   * Precondition: teams array must be a 2D array of ImageFilter objects, with the second row as the African teams
   * Postcondition: African teams are drawn and applied with the random() filter
   */
  public void drawAFRTeams() {
    for(int i = 0; i < teams[1].length; i++) {
      drawImage(teams[1][i], 20, 20, 360, 0.0);
      pause(1);
      //Randomizes the pixel RGB values of the image
      teams[1][i].random();
      drawImage(teams[1][i], 20, 20, 360, 0.0);
      pause(1);
      clear("BLACK");
    }
  }

  /*
   * Draws the logos the top three teams in South America
   * Precondition: teams array must be a 2D array of ImageFilter objects, with the third row as the South American teams
   * Postcondition: South American teams are drawn and applied with the applySepia() filter
   */
  public void drawSATeams() {
    for(int i = 0; i < teams[1].length; i++) {
      drawImage(teams[2][i], 20, 20, 360, 0.0);
      pause(1);
      //Applies applySepia() filter and is drawn again
      teams[2][i].applySepia();
      drawImage(teams[2][i], 20, 20, 360, 0.0);
      pause(1);
      clear("BLACK");
    }
  }

  /*
   * Returns the appropriate contrast mulitplier based on the image's average RGB value
   * Precondition: PremierLeague class must be imported and contain the getAveragePixelRGB()
   * Postcondition: 5, 4, 3, or 2 is returned
   */
  public int findContrast(ImageFilter image) {
    //Creates a PremierLeague object so that the getAveragePixelRGB() method can be used
    PremierLeague object = new PremierLeague();
    if (object.getAveragePixelRGB(image) < 80) {
      return 5;
    } else if (object.getAveragePixelRGB(image) < 130) {
      return 4;
    } else if (object.getAveragePixelRGB(image) < 180) {
      return 3;
    } else {
      return 2;
    }
  }

}