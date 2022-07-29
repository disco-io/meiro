import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class GUIPanel extends JPanel
{

   //fields
   JLabel pkbCount;
   int pkbNum;
   JLabel friendCount;
   int friendNum;
   JLabel hpCount;
   int hpNum;
   MazePanel gfx;
   
   private static int[][] PokeBall = {{6, 80},{46, 45},{46, 165},{122, 165},{85, 85},{285, 85},{325, 85},{365, 85},{325, 6},{184, 6},{285, 325},{362, 125},{362, 165},{263, 285}, {85, 285}, {200, 322},{124, 365},{6, 322},{45, 242},{203, 282},{203, 245}, {361, 243}, {7, 163}, {7, 243}};

   private static int[][] OranBerry = {{46, 105}, {123, 85}, {244, 363}, {284, 363}, {324, 363}, {324, 323}, {128, 6}, {324, 283}, {362, 323}, {362, 283}, {362, 45}, {243, 245}, {45, 323}, {124, 323}, {7, 132}, {120, 245}, {262, 124}};

   public GUIPanel() throws Exception
   {
      setLayout(new BorderLayout());
   //north
      JLabel title = new JLabel("meiro");
      title.setFont(new Font("Serif", Font.BOLD, 40));
      title.setHorizontalAlignment(SwingConstants.CENTER);
      add(title, BorderLayout.NORTH);
   //west
      JPanel subpanel = new JPanel();
      subpanel.setLayout(new GridLayout(3, 1));
      JLabel scoreboard = new JLabel("scoreboard!");
      scoreboard.setFont(new Font("Serif", Font.BOLD, 23));
      scoreboard.setHorizontalAlignment(SwingConstants.CENTER);
      subpanel.add(scoreboard);
      JPanel statspanel = new JPanel();
      statspanel.setLayout(new GridLayout(3, 2));
      JLabel pkbLabel = new JLabel("pokeballs: ");
      pkbLabel.setFont(new Font("Serif", Font.BOLD, 16));
      pkbLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      statspanel.add(pkbLabel);
   //----------------------------------------------
      pkbNum = 0;
      pkbCount = new JLabel("" + pkbNum);
      pkbCount.setFont(new Font("Serif", Font.BOLD, 30));
      pkbCount.setHorizontalAlignment(SwingConstants.CENTER);
      statspanel.add(pkbCount);
      JLabel friendLabel = new JLabel("friends: ");
      friendLabel.setFont(new Font("Serif", Font.BOLD, 16));
      friendLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      statspanel.add(friendLabel);
   //----------------------------------------------
      friendNum = 0;
      friendCount = new JLabel("" + friendNum);
      friendCount.setFont(new Font("Serif", Font.BOLD, 30));
      friendCount.setHorizontalAlignment(SwingConstants.CENTER);
      statspanel.add(friendCount);
      JLabel hpLabel = new JLabel("your hp: ");
      hpLabel.setFont(new Font("Serif", Font.BOLD, 16));
      hpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      statspanel.add(hpLabel);
   //----------------------------------------------
      hpNum = 100;
      hpCount = new JLabel("" + hpNum);
      hpCount.setFont(new Font("Serif", Font.BOLD, 25));
      hpCount.setHorizontalAlignment(SwingConstants.CENTER);
      statspanel.add(hpCount);
      subpanel.add(statspanel);
      JButton scoresheet = new JButton("end and save scores");
      scoresheet.addActionListener(new SaveListener());
      scoresheet.setFont(new Font("Serif", Font.BOLD, 15));
      scoresheet.setHorizontalAlignment(SwingConstants.CENTER);
      subpanel.add(scoresheet);
      add(subpanel, BorderLayout.WEST);
   //south
      JPanel southpanel = new JPanel();
      southpanel.setLayout(new GridLayout(2, 1));
   
      JLabel empty = new JLabel("");
      empty.setHorizontalAlignment(SwingConstants.RIGHT);
      southpanel.add(empty);
   //----------------------------------------------
      JLabel controls = new JLabel("W-up A-left S-down D-right Q-pick E-throw Click-attack!");
      controls.setFont(new Font("Serif", Font.BOLD, 15));
      controls.setHorizontalAlignment(SwingConstants.CENTER);
      southpanel.add(controls);
      add(southpanel, BorderLayout.SOUTH);
   //center
      gfx = new MazePanel();
      add(gfx, BorderLayout.CENTER);
      scoresheet.addKeyListener(gfx);
     
      addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent me) {
             gfx.shoot();
          }
      });    
   }
 
  //instance methods
   public static int[][] getOran()
   {
     return OranBerry;
   }

   public static int[][] getBall()
   {
     return PokeBall;
   }
   
   public static boolean onScreen(int x, int y)
   {
      return (x >= 0 && x <= MazePanel.FRAME) && (y >= 0 && y <= MazePanel.FRAME);
   }
 
   class SaveListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {  //file i/o
            File f = new File("hs.txt");
            FileWriter fw = new FileWriter("hs.txt");
            fw.write("pokeballs: " + pkbNum + ", hp: " + hpNum + ", friends: " + friendNum + "\n");
            fw.close();
         }
         catch (Exception exp)
         {
            System.out.println("error");
         }
      }
   }
 
   class MazePanel extends JPanel implements KeyListener, ActionListener
   {
   //fields:
      public static final int FRAME = 400;
      public final Color BACKGROUND = new Color (204, 204, 204);
      private Image maze;
      private BufferedImage myImage;
      private Graphics myBuffer;
      private Mudkip mudkip;
      private Pokeball pokeball;
      private Pikachu pikachu;
      private Bulbasaur bulbasaur;
      private Torchic torchic;
      private Whismur whismur;
      private Shiny shiny;
      private Oranberry oran;
      private Ability ability;
      private Timer t, timer, t2;
     
      private int[][] Pikachus = {{83, 5, 30, 1}, {242, 85, 30, 1}, {324, 202, 30, 0}, {124, 285, 30, 0}, {78, 203, 30, 1}};
      private int[][] Bulbasaurs = {{83, 165, 20, 1}, {276, 6, 20, 1}, {162, 45, 20, 1}, {284, 165, 20, 0}, {322, 243, 20, 1}, {200, 362, 20, 1}, {6, 362, 20, 0}, {162, 244, 20, 1}};
      private int[][] Torchics = {{162, 119, 40, 1}, {245, 45, 40, 0}, {242, 202, 40, 0}, {84, 321, 40, 0}, {45, 280, 40, 1}};
      private int[][] Whismurs = {{164, 162, 50, 0}, {241, 162, 50, 1}, {204, 122, 50, 0}, {320, 122, 50, 1}};
      private int[][] Shinies = {{360, 361, 70, 1}};
     
      private int[] ogDmg = {30, 20, 40, 50, 70};
     
      private Pokemon[] pikachus, bulbasaurs, torchics, whismurs, shinies;
      private Pokemon[][] myPokemons;
   
   //constructors
      public MazePanel()
      {
         maze = (new ImageIcon("meiro.jpg")).getImage();
         myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         myBuffer = myImage.getGraphics();
         myBuffer.drawImage(maze, 0, 0, FRAME, FRAME, null, null);
         mudkip = new Mudkip();
         pikachus = new Pikachu[Pikachus.length];
         for (int i = 0; i < Pikachus.length; i++)
         {
            pikachus[i] = new Pikachu();
         }
         
         bulbasaurs = new Bulbasaur[Bulbasaurs.length];
         for (int i = 0; i < Bulbasaurs.length; i++)
         {
            bulbasaurs[i] = new Bulbasaur();
         }
         
         torchics = new Torchic[Torchics.length];
         for (int i = 0; i < Torchics.length; i++)
         {
            torchics[i] = new Torchic();
         }
         
         whismurs = new Whismur[Whismurs.length];
         for (int i = 0; i < Whismurs.length; i++)
         {
            whismurs[i] = new Whismur();
         }
         
         shinies = new Shiny[Shinies.length];
         for (int i = 0; i < Shinies.length; i++)
         {
            shinies[i] = new Shiny();
         }
         
         myPokemons = new Pokemon[][]{pikachus, bulbasaurs, torchics, whismurs, shinies};
         
         MoveAction(true);
         timer = new Timer(50, new DistanceChecker());
         timer.start();
      }
     
     public void drawAll()
     {
         pokeball = new Pokeball();
         for (int i = 0; i < PokeBall.length; i++)
         {
            if(PokeBall[i][0] > 0 && PokeBall[i][1] >0)
            {
               pokeball.draw(myBuffer, PokeBall[i][0], PokeBall[i][1], 32);
            }
         }
         oran = new Oranberry();
         for (int i = 0; i < OranBerry.length; i++)
         {
            if(OranBerry[i][0] > 0 && OranBerry[i][1] >0)
            {
               oran.draw(myBuffer, OranBerry[i][0], OranBerry[i][1], 32);
            }
         }
         
         for (int i = 0; i < pikachus.length; i++)
         {
            if (Pikachus[i][2] > 0)
            {
               pikachus[i].draw(myBuffer, Pikachus[i][0], Pikachus[i][1], 32, Pikachus[i][3] == 1);
            }
         }
         
         for (int i = 0; i < bulbasaurs.length; i++)
         {
            if (Bulbasaurs[i][2] > 0)
            {
               bulbasaurs[i].draw(myBuffer, Bulbasaurs[i][0], Bulbasaurs[i][1], 32, Bulbasaurs[i][3] == 1);
            }
         }
         
         for (int i = 0; i < torchics.length; i++)
         {
            if (Torchics[i][2] > 0)
            {
               torchics[i].draw(myBuffer, Torchics[i][0], Torchics[i][1], 32, Torchics[i][3] == 1);
            }
         }
         
         for (int i = 0; i < whismurs.length; i++)
         {
            if (Whismurs[i][2] > 0)
            {
               whismurs[i].draw(myBuffer, Whismurs[i][0], Whismurs[i][1], 32, Whismurs[i][3] == 1);
            }
         }
         
         for (int i = 0; i < shinies.length; i++)
         {
            if (Shinies[i][2] > 0)
            {
               shinies[i].draw(myBuffer, Shinies[i][0], Shinies[i][1], 32, Shinies[i][3] == 1);
            }
         }
      }

      //instance methods:
      public void paintComponent(Graphics g)
      {
         super.paintComponent(g);
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   
      private void MoveAction(boolean right)
     {
         myBuffer = myImage.getGraphics();
         myBuffer.drawImage(maze, 0, 0, FRAME, FRAME, null, null);
         drawAll();
         if (right)
         {
           mudkip.draw(myBuffer, true);
         }
         else
         {
           mudkip.draw(myBuffer, false);
         }
         repaint();
      }
   
      public void shoot()
      {
         if (mudkip.addBullet())
         {
            t = new Timer(50, new GameCycle());
            t.start();
         }
      }
     
      public void throwBall()
      {
         if (pkbNum > 0)
         {
            pkbNum--;
            pkbCount.setText(""+pkbNum);
            if (mudkip.addBall())
            {
               t2 = new Timer(50, new BallCycle());
               t2.start();
            }
         }
      }
     
      class BallCycle implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if (!onScreen(mudkip.getBall().getX(), mudkip.getBall().getY()))
            {
               t2.stop();
               mudkip.nullBall();
               return;
            }
            mudkip.moveBall();
            MoveAction(mudkip.getRight());
         }
      }
     
      class GameCycle implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if (!onScreen(mudkip.getBullet().getX(), mudkip.getBullet().getY()))
            {
               t.stop();
               mudkip.nullBullet();
               return;
            }
            mudkip.moveBullet();
            MoveAction(mudkip.getRight());

         }
      }

      class DistanceChecker implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if (hpNum <= 0)
            {
               mudkip.kill();
               MoveAction(true);
               return;
            }
            int count = 0;
            for (int[][] pokemons: new int[][][]{Pikachus, Bulbasaurs, Torchics, Whismurs, Shinies})
            {
               for (int i = 0; i < pokemons.length; i++)
               {
                  if (pokemons[i][2] > 0)
                  {
                     if (mudkip.distance(pokemons[i][0], pokemons[i][1]) < 60)
                     {
                        myPokemons[count][i].addBullet(pokemons[i][0], pokemons[i][1]);
                     }
                     myPokemons[count][i].moveBullet();
                     if (myPokemons[count][i].getBullet() != null)
                     {
                        if (!onScreen(myPokemons[count][i].getBullet().getX(), myPokemons[count][i].getBullet().getY()))
                        {
                           myPokemons[count][i].nullBullet();
                        }
                     }
                 
                     if (myPokemons[count][i].getBullet() != null)
                     {
                        if (myPokemons[count][i].getBullet().collide(mudkip.GetX(), mudkip.GetY(), mudkip.getSize()))
                        {
                           hpNum -= myPokemons[count][i].getBullet().getDamage();
                           hpCount.setText("" + hpNum);
                           myPokemons[count][i].nullBullet();
                        }
                     }
                     if (mudkip.getBullet() != null)
                     {
                        if (mudkip.getBullet().collide(pokemons[i][0], pokemons[i][1], 35))
                        {
                           pokemons[i][2] -= mudkip.getBullet().getDamage();
                           mudkip.nullBullet();
                           t.stop();
                        }
                     }
                     if (mudkip.getBall() != null)
                     {
                        if (mudkip.getBall().collide(pokemons[i][0], pokemons[i][1], 35))
                        {
                           if (pokemons[i][2] * 2 <= ogDmg[count])
                           {
                              pokemons[i][2] = 0;
                              friendNum++;
                              friendCount.setText("" + friendNum);
                           }
                           mudkip.nullBall();
                           t2.stop();
                        }
                     }
                  }    
               }
               count++;
            }
            MoveAction(mudkip.getRight());
         }
      }
   
      public void PickBall(int x,int y)
     {
         for (int i = 0; i < PokeBall.length; i++)
         {
            if( PokeBall[i][0]>=x-10 && PokeBall[i][0]<=x+10 && PokeBall[i][1]>=y-10 && PokeBall[i][1]<=y+10)
            {
               pkbNum ++;
               pkbCount.setText(pkbNum + "");
               PokeBall[i][0]=-100;
               PokeBall[i][1]=-100;
               MoveAction(true);
               break; //exit once found :)
            }
         }                            
     }

      public void pickBerry(int x,int y)
     {
         for (int i = 0; i < OranBerry.length; i++)
         {
            if( OranBerry[i][0]>=x-10 && OranBerry[i][0]<=x+10 && OranBerry[i][1]>=y-10 && OranBerry[i][1]<=y+10)
            {
                if (hpNum < 90)
                {
                  hpNum += 10;
                }
                else
                {
                  while (hpNum < 100)
                    {
                      hpNum++;
                    }
                }
                hpCount.setText(hpNum + "");
                OranBerry[i][0]=-100;
                OranBerry[i][1]=-100;
                MoveAction(true);
                break;
            }
         }
      }

      public void keyPressed(KeyEvent e)
      {
        if(e.getKeyChar() == 'Q' || e.getKeyChar() == 'q')
         {
            if (mudkip.onBall(mudkip.GetX(), mudkip.GetY()))
            {
              PickBall(mudkip.GetX(), mudkip.GetY());
            }
            else if (mudkip.onBerry(mudkip.GetX(), mudkip.GetY()))
            {
              pickBerry(mudkip.GetX(), mudkip.GetY());
            }
         }
         else if(e.getKeyChar() == 'D' || e.getKeyChar() == 'd')
         {            
            if(mudkip.CanMove(10,  0))
            {
               mudkip.move(10,0);
               MoveAction(true);  
            }    
         }
         else if(e.getKeyChar() == 'A' || e.getKeyChar() == 'a')
         {            
            if(mudkip.CanMove(-10,0))
            {
               mudkip.move(-10,0);
               MoveAction(false);  
            }
         }
         else if(e.getKeyChar() == 'W' || e.getKeyChar() == 'w')
         {
            if(mudkip.CanMove(0,-10))
            {
               mudkip.move(0,-10);  
               MoveAction(true);
            }
         }
         else if(e.getKeyChar() == 'S' || e.getKeyChar() == 's')
         {
            if(mudkip.CanMove(0,10))
            {
               mudkip.move(0,10);  
               MoveAction(true);
            }
         }
         
         else if(e.getKeyChar() == 'E' || e.getKeyChar() == 'e')
         {
            throwBall();
         }
      }

      public void keyReleased(KeyEvent e)
      {
     
      }
   
      public void keyTyped(KeyEvent e)
      {
     
      }
   
      public void actionPerformed(ActionEvent e)      
      {
       
      }
   
   }
}
