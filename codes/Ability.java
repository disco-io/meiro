import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*; 

public class Ability
{
  //fields:
  protected int myX;
  protected int myY;
  protected int mydx;
  protected int mySize;
  protected int dmg;
  protected int id;
  protected Color myColor;
 
  //constructors:
  public Ability(int sideX, int sideY, int r, int g, int b, int damage, int direc, int i)
  {
    mySize = 7;
    myColor = new Color(r, g, b);
    myY = sideY;
    myX = sideX;
    mydx = 5 * direc;
    dmg = damage;
    id = i;
  }
 
  //instance methods:
  public void move()
   {
      myX += mydx;
   }
     
   public int getY()
   {
      return myY;
   }  
   
   public int getX()
   {
      return myX;
   }
   
   public int getW()
   {
      return mySize;
   }
   
   public int getId()
   {
      return id;
   }
   
   public int getDamage()
   {
      return dmg;
   }
   
   public void draw(Graphics myBuffer)
   {
      myBuffer.setColor(myColor);
      myBuffer.fillRect(myX, myY, mySize, mySize);
   }
   public boolean collide(int x, int y, int size)
   {
      return ((myX >= x && myX <= x+size) || (myX+mySize >= x && myX+mySize <= x+size))&&((myY >= y && myY <= y+size) || (myY+mySize >= y && myY+mySize <= y+size));
   }
}
