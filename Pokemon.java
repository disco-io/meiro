import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public abstract class Pokemon
{
   //fields:
   public static final int FRAME = 400;
   
   protected Ability bullet;
   protected Timer t;
   protected Image myImage;
   protected boolean right;
   
   public void draw(Graphics myBuffer, int x, int y, int size, boolean left)
   {
    right = !left;
    if (left)
    {
      myBuffer.drawImage(myImage, x, y, size, size, null, null);  
    }
    else
    {
       myBuffer.drawImage(myImage, x+size, y, -size, size, null, null);
    }
    if (bullet != null) bullet.draw(myBuffer);
   }
 
  public boolean addBullet(int mx, int my)
  {
   return addBullet(mx, my, -1);
  }
 
  public boolean addBullet(int mx, int my, int id)
  {
   if (bullet==null)
   {
      //wild? red bullet! mudkip? blue bullet!
      bullet = new Ability(mx+((right)?35:0), my + 35/2, ((id == -1)?255:0), 0, ((id == -1)?0:255), ((id == -1)?1:(id == 0)?5:10), ((right)? 1: -1), id);

      return true;
   }
   return false;
  }
 
  public void moveBullet()
  {
   if (bullet!=null)
   {
      bullet.move();
   }
  }
 
  public Ability getBullet()
  {
   return bullet;
  }
 
  public void nullBullet()
  {
   bullet = null;
  }
 
}