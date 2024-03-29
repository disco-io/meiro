import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.Math;

public class Mudkip extends Pokemon
{
  //fields:
  public static final int FRAME = 400;
 
  private Image mdkp;
  private BufferedImage myImage;
  private int my;
  private int mx;
  private int size;
  private boolean alive;
  private PokeBallAbility pokeball;
  private int[][] PlayArea = {{10, 5},{20, 5},{30, 5},{40, 5},{50, 5},{60, 5},{70, 5},{80, 5},{130, 5},{140, 5},{150, 5},{160, 5},{170, 5},{180, 5},{190, 5},{200, 5},{210, 5},{220, 5},{230, 5},{240, 5},{250, 5},{260, 5},{270, 5},{280, 5},{320, 5},{330, 5},{340, 5},{350, 5},{360, 5}, {10, 15}, {10, 25}, {10, 35}, {10, 45}, {10, 55}, {10, 65}, {10, 75}, {10, 85}
  ,{40, 45},{50, 45},{60, 45},{70, 45},{80, 45},{120, 45},{130, 45},{140, 45},{150, 45},{160, 45},{170, 45},{180, 45},{190, 45},{200, 45},{240, 45},{250, 45},{260, 45},{270, 45},{280, 45},{290, 45},{300, 45},{310, 45},{320, 45}
  ,{80, 85},{90, 85},{100, 85},{110, 85},{120, 85},{130, 85},{140, 85},{150, 85},{160, 85},{210, 85},{220, 85},{230, 85},{240, 85},{250, 85},{260, 85},{270, 85},{280, 85},{290, 85},{300, 85},{310, 85},{320, 85},{330, 85},{340, 85},{350, 85},{360, 85}
  ,{120, 125},{130, 125},{140, 125},{150, 125},{160, 125},{200, 125},{210, 125},{220, 125},{230, 125},{240, 125},{250, 125},{260, 125},{270, 125},{280, 125},{290, 125},{300, 125},{310, 125},{320, 125},{50, 165},{60, 165},{70, 165},{80, 165},{90, 165},{100, 165},{110, 165},{120, 165}
  ,{160, 165},{170, 165},{180, 165},{190, 165},{200, 165},{210, 165},{220, 165},{230, 165},{240, 165},{280, 165},{290, 165},{300, 165},{310, 165},{320, 165}
  ,{0, 205},{10, 205},{20, 205},{30, 205},{40, 205},{50, 205},{60, 205},{70, 205},{80, 205},{90, 205},{100, 205},{110, 205},{120, 205},{160, 205},{170, 205},{180, 205},{190, 205},{200, 205},{240, 205},{250, 205},{260, 205},{270, 205},{280, 205},{320, 205},{330, 205},{340, 205},{350, 205},{360, 205}, {360, 245}, {360, 235}
  ,{20, 245},{30, 245},{40, 245},{80, 245},{90, 245},{100, 245},{110, 245},{120, 245},{130, 245},{140, 245},{150, 245},{160, 245},{280, 245},{290, 245},{300, 245},{310, 245},{320, 245}
  ,{0, 285},{10, 285},{20, 285},{30, 285},{40, 285},{120, 285},{130, 285},{140, 285},{150, 285},{160, 285},{240, 285},{250, 285},{260, 285},{270, 285},{280, 285},{320, 285},{330, 285},{340, 285},{350, 285},{360, 285}
  ,{40, 325},{50, 325},{60, 325},{70, 325},{80, 325},{90, 325},{100, 325},{110, 325},{120, 325},{160, 325},{170, 325},{180, 325},{190, 325},{200, 325},{210, 325},{220, 325},{230, 325},{240, 325},{250, 325},{260, 325},{270, 325},{280, 325},{320, 325},{330, 325},{340, 325},{350, 325},{360, 325}
  ,{10, 365},{20, 365},{30, 365},{40, 365},{50, 365},{60, 365},{70, 365},{80, 365},{90, 365},{100, 365},{110, 365},{120, 365},{130, 365},{140, 365},{150, 365},{160, 365},{170, 365},{180, 365},{190, 365},{200, 365},{240, 365},{250, 365},{260, 365},{270, 365},{280, 365},{290, 365},{300, 365},{310, 365},{320, 365},{330, 365},{340, 365},{350, 365},{360, 365}
  ,{0, 5},{0, 15},{0, 25},{0, 35},{0, 45},{0, 55},{0, 65},{0, 75},{0, 85},{0, 125},{0, 135},{0, 145},{0, 155},{0, 165},{0, 175},{0, 185},{0, 195},{0, 205},{0, 215},{0, 225},{0, 235},{0, 245},{0, 285},{0, 295},{0, 305},{0, 315},{0, 325},{0, 335},{0, 345},{0, 355},{0, 365}
  ,{40, 55},{40, 65},{40, 75},{40, 85},{40, 95},{40, 105},{40, 115},{40, 125},{40, 135},{40, 145},{40, 155},{40, 165},{40, 255},{40, 265},{40, 275},{40, 285},{40, 335},{40, 345},{40, 355},{40, 365}
  ,{80, 15},{80, 25},{80, 35},{80, 45},{80, 95},{80, 105},{80, 115},{80, 125},{80, 255},{80, 265},{80, 275},{80, 285}
  ,{120, 55},{120, 65},{120, 75},{120, 85},{120, 135},{120, 145},{120, 155},{120, 165},{120, 215},{120, 225},{120, 235},{120, 245},{120, 295},{120, 305},{120, 315},{120, 325}
  ,{160, 95},{160, 105},{160, 115},{160, 125},{160, 215},{160, 225},{160, 235},{160, 245},{160, 295},{160, 305},{160, 315},{160, 325}
  ,{200, 55},{200, 65},{200, 75},{200, 85},{200, 165},{200, 175},{200, 185},{200, 195},{200, 205},{200, 215},{200, 225},{200, 235},{200, 245},{200, 255},{200, 265},{200, 275},{200, 285}
  ,{240, 15},{240, 25},{240, 35},{240, 45},{240, 135},{240, 145},{240, 155},{240, 165},{240, 215},{240, 225},{240, 235},{240, 245},{240, 255},{240, 265},{240, 275},{240, 285},{240, 325},{240, 335},{240, 345},{240, 355},{240, 365}
  ,{280, 175},{280, 185},{280, 195},{280, 205},{280, 255},{280, 265},{280, 275},{280, 285}
  ,{320, 15},{320, 25},{320, 35},{320, 45},{320, 175},{320, 185},{320, 195},{320, 205},{320, 255},{320, 265},{320, 275},{320, 285},{320, 335},{320, 345},{320, 355},{320, 365}
  ,{360, 15},{360, 25},{360, 35},{360, 45},{360, 55},{360, 65},{360, 75},{360, 85},{360, 95},{360, 105},{360, 115},{360, 125},{360, 135},{360, 145},{360, 155},{360, 165},{360, 175},{360, 185},{360, 195},{360, 205},{360, 255},{360, 265},{360, 275},{360, 285},{360, 295},{360, 305},{360, 315},{360, 325}
  ,{120, 5},{350, 255},{0, 245},{10, 245}};
 
  //constructors:
  public Mudkip()
  {
    mdkp = (new ImageIcon("mudkip.png")).getImage();
    right = true;
    my = 5;
    mx = 0;
    size = 35;
    alive = true;
  }

  //instance methods:
  public void draw(Graphics myBuffer, boolean right)
  {
    if (!alive) return;
    this.right = right;
    if (right)
    {
      myBuffer.drawImage(mdkp, mx, my, size, size, null, null);  
    }
    else
    {
      myBuffer.drawImage(mdkp, mx+size , my, -size, size, null, null);
    }
    if (bullet != null) bullet.draw(myBuffer);
    if (pokeball != null) pokeball.draw(myBuffer);
  }

  public void move(int dx, int dy)
  {
    if (!alive) return;
    my += dy;
    mx += dx;
  }
  public int GetX()
  {
   return mx;
  }
  public int GetY()
  {
   return my;
  }
 
  public int getSize()
  {
   return size;
  }

  public boolean CanMove(int x, int y)
  {
     if (!alive) return false;
     for (int i = 0; i < PlayArea.length; i++)
     {
        if(mx+x==PlayArea[i][0] && my+y == PlayArea[i][1])
        {
          return true;
        }
     }
     return false;
  }

  public boolean onBall(int x, int y)
  {
      int[][] PokeBall = GUIPanel.getBall();
      for (int i = 0; i < PokeBall.length; i++)
      {
        if( PokeBall[i][0]>=x-10 && PokeBall[i][0]<=x+10 && PokeBall[i][1]>=y-10 && PokeBall[i][1]<=y+10)
        {
          return true;
        }
      }
      return false;
  }

  public boolean onBerry(int x, int y)
  {
   if (!alive) return false;
   int[][] OranBerry = GUIPanel.getOran();
   for (int i = 0; i < OranBerry.length; i++)
   {
     if( OranBerry[i][0]>=x-10 && OranBerry[i][0]<=x+10 && OranBerry[i][1]>=y-10 && OranBerry[i][1]<=y+10)
     {
       return true;
     }
   }
   return false;
  }
 
  public boolean addBullet()
  {
   if (!alive) return false;
   return addBullet(mx, my, 1);
  }

  public boolean getRight()
  {
   return right;
  }
 
  public double distance(int x, int y)
  {
   return Math.pow(Math.pow(mx-x, 2) + Math.pow(my-y, 2)*2, 0.5);
  }
 
  public void kill()
  {
   alive = false;
  }
 
  public boolean addBall()
  {
   if (pokeball == null)
   {
      pokeball = new PokeBallAbility(mx + ((right)?size/2:0), my + size/2-10, ((right)?1:-1));
      return true;
   }
   return false;
  }
 
  public PokeBallAbility getBall()
  {
   return pokeball;
  }
 
  public void moveBall()
  {
   if (pokeball != null)
   {
      pokeball.move();
   }
  }
 
  public void nullBall()
  {
   pokeball = null;
  }
}
