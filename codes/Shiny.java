import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Shiny extends Pokemon
{
  //fields:
  public static final int FRAME = 400;

  //constructors:
  public Shiny()
  {
    myImage = (new ImageIcon("shiny.png")).getImage();
  }
  
  public boolean addBullet(int mx, int my)
  {
   return addBullet(mx, my, 0);
  }
}
