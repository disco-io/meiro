import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class PokeBallAbility extends Ability
{
   private Image pkb;
   public PokeBallAbility(int sideX, int sideY, int direc)
   {
      super(sideX, sideY, 0, 0, 0, 0, direc, 1);
      pkb = (new ImageIcon("pokeball.png")).getImage();
      mySize = 20;
   }
   
   public void draw(Graphics myBuffer)
   {
      myBuffer.drawImage(pkb, myX, myY, mySize, mySize, null, null);
   }
}