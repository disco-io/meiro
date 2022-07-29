import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Torchic
{
  //fields:
  public static final int FRAME = 400;
  
  private Image trch;
  private BufferedImage myImage;

  //constructors:
  public Torchic()
  {
    trch = (new ImageIcon("torchic.png")).getImage();
  }

  //instance methods:
  public void draw(Graphics myBuffer, int x, int y, int size, boolean right)
  {
    if (right)
    {
      myBuffer.drawImage(trch, x, y, size, size, null, null);  
    }
    else
    {
       myBuffer.drawImage(trch, x+size, y-size, size, size, null, null); 
    }
  }
}