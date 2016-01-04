   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;

   public class FinalDisplay extends JPanel
   {
      private static final int WIDTH = 1360;	//width of screen
      private static final int HEIGHT = 725;	//height of screen
      private static JLabel instr;				//the instructions label
      private int changeX, changeY;				//enemy dx and dy
      private static int pause = 1, deathpause = 0, dragoncount=0;	//integers controlling the pausing function
      private int count = 0;						//used for flapping the players wings
      private static String prompt = "-";		//updates the prompt in the scoreboard
      private Graphics myBuffer;					//a buffer object
      private PlayerBird player;					
      private ImageIcon background;				//the background's image icon, used for the animation
      private EnemyBird[] enemyArray;			//array of eneies reference
      private BufferedImage myImage;			//also sets the buffer
      private MouseInfo mouseInfo;				//an object used to return the coordinates of the mouse
      private DragonBird dragon;
      private static boolean canEatDrag=false;
      public FinalDisplay()
      {
         setLayout(new BorderLayout());				//Creates a layout with cardinal directions
      	
         myImage =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);	//sets the buffer
         myBuffer = myImage.getGraphics();
         background = new ImageIcon("skyHD4.jpg");				//instantiates an image icon for the background
         myBuffer.drawImage(background.getImage(), 0,0,WIDTH, HEIGHT, null);	//original background drawing/before animation
      
         setFocusable(true);	//allows keyboard and mouse input
      
         player = new PlayerBird((WIDTH / 2), (HEIGHT / 2));	//sends the original coordinates to player constructor
      	
         instr = new JLabel("");		//sets a blank JLabel for the instructions
         instr.setOpaque(false);		//allows the background to show behind the blank JLabel
         
         dragon = new DragonBird(1200, 900);
      		
         enemyArray = new EnemyBird[10];
         for(int a = 0; a < enemyArray.length; a++)                    //makes an array to hold the enemies
         {
            int x = 0;
            int flap = (int)(Math.random() * 20);
            int y = (int)(Math.random() * (HEIGHT - 251)); //starts at random height
            int coin = (int)(Math.random()* 2);
            switch(coin)
            {
               case 0: x = (int)(Math.random()* -100 - 277); //starts from offscreen left
                  break;
               case 1: x = (int)(Math.random()* 100 + (WIDTH + 277)); //starts from offscreen right
                  break;
            }       
            switch(a)				//the switch makes 4 butterflies, 3 condors, and 3 airplanes
            {
               case 0:
               case 1:        
               case 2:
               case 3:
                  changeX = (int)(Math.random() * 5 + 1);	//randomizes dx & dy
                  if(coin == 1)		//determines whether enemy starts offscreen right or left
                     changeX = changeX * -1;        
                  changeY = (int)(Math.random() * 10 - 5);
                  enemyArray[a] = new EnemyBird(x, y, flap, "Butterfly", changeX, changeY, 75, 51); //butterfly instantiation
                  break;      
               case 4:
               case 5:
               case 6:
                  changeX = (int)(Math.random() * 8 + 1);
                  changeY = (int)(Math.random() * 16 - 8);
                  if(coin == 1)
                     changeX = changeX * -1;
                  enemyArray[a] = new EnemyBird(x, y, flap, "Condor",  changeX, changeY, 137, 100);    //Condor instantiation
                  break;
               case 7:
               case 8:
               case 9:
                  changeX = (int)(Math.random() * 10 + 1);
                  changeY = (int)(Math.random() * 20 - 10);
                  if(coin == 1)
                     changeX = changeX * -1;
                  enemyArray[a] = new EnemyBird(x, y, flap, "Airplane", changeX, changeY, 258, 84);//Airplane instantiation
                  break;
            }
         }
      }
      public void paintComponent(Graphics g)            //actually draws the images we've buffered
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   
       
      public void game(int runtime)								//the basic gameplay logic called by Panel's timer
      {
         //redo the baackground to wipe the old images
         myBuffer.drawImage(background.getImage(), 0,0,WIDTH, HEIGHT, null);    //ressets background
         
         dragoncount+=runtime;
         if(dragoncount <= 50000 + runtime && dragoncount>=50000)
         {
            dragon.setIsHere(true);		
         }
         else if(dragoncount <= 60000 +runtime && dragoncount>=60000)
         {
            dragoncount = 0;
            dragon.setIsHere(false);
            dragon.goAway();
         }	
      	
         //only checks for collision between player and all the enemies if the game is unpaused
         if(pause == 0)
         {
            if(dragon.getIsHere() == true)
            {
               collide(player, dragon);
            }
            for(int z=0; z<enemyArray.length; z++)
            {
               collide(player, enemyArray[z]);
            }
         }
      		
      	
         //sets the new player coordinates, 10% closer to the mouse cursor (homing algorithm)
         player.setdx((int)((mouseInfo.getPointerInfo().getLocation().x - (player.getX() + (player.getLength() / 2)))/10));
         player.setdy((int)((mouseInfo.getPointerInfo().getLocation().y - 15 - (player.getY() + (player.getWidth() / 2)))/10));
         player.setX(player.getX()+ player.getdx());
         player.setY(player.getY()+ player.getdy());
         
         //ensures new coordinates are in the frame
         player.move(WIDTH, HEIGHT);
           
         //calculates the new enemy coordinates according to dx and dy if the game is unpaused
         if(pause == 0)
         {
            if(dragon.getIsHere()==true)
               dragon.move(WIDTH, HEIGHT, player);
            for(int x=0; x<enemyArray.length; x++)
            {
               enemyArray[x].move(WIDTH, HEIGHT);
            }
         }
         //ends the deathpause after about 2-3 seconds
         else
         {
            deathpause++;
            if(deathpause == 60)
            {
               pause = 0;
               deathpause = 0;
            }
         }
           
         //flaps the players wings and draws the plaer at new coordinates
         count++;
         if(count == 20)                          //resets count if it is 20  
         {
            player.draw(myBuffer, 1);            
            count = 0;                                
         }            
         else if(count <= 10)							 //the other half, sends 1
         {
            player.draw(myBuffer, 0);
         }
         else
         {
            player.draw(myBuffer, 1);				//half the time, sends 0
         }
         
         if(dragon.getIsHere()==true)
         {
            dragon.setFlapCount(dragon.getFlapCount() + 1);
            if(dragon.getFlapCount() == 30)                            //draws the current enemy at new coordinates
            {
               dragon.draw(myBuffer, 1);            //half the time, sends 0
               dragon.setFlapCount(0);                                //the other half, sends 1
            }            
            else if(dragon.getFlapCount() <= 15)
            {
               dragon.draw(myBuffer, 0);
            }
            else
            {
               dragon.draw(myBuffer, 1);
            }
         }
         //redraws the enemy array
         for(int y = 0; y < enemyArray.length; y++)
         {
            enemyArray[y].setFlapCount(enemyArray[y].getFlapCount() + 1);
            if(enemyArray[y].getFlapCount() == 20)                            //draws the current enemy at new coordinates
            {
               enemyArray[y].draw(myBuffer, 1);            //half the time, sends 0
               enemyArray[y].setFlapCount(0);                                //the other half, sends 1
            }            
            else if(enemyArray[y].getFlapCount() <= 10)
            {
               enemyArray[y].draw(myBuffer, 0);
            }
            else
            {
               enemyArray[y].draw(myBuffer, 1);
            }
         }
         
         //draws all changes from the buffer to the screen
         repaint();
      }  
   
       //called to check contact between the player and all the enemies
      private static void collide(PlayerBird arg1, Bird arg2)    //arg1 = player, arg2 = current enemy in array being checked
      {  
         for(int x = arg2.getX()+5; x <= arg2.getX() + (arg2.getLength()-5); x++)   //starts at upper left corner(x,y)
         {
            if(pause == 1)
               break;
            for(int y = arg2.getY()+5; y <= arg2.getY() + (arg2.getWidth()-5); y++)
               if((arg1.getX() + arg1.getLength() > x && x > arg1.getX()) && (arg1.getY() + arg1.getWidth() > y && y > arg1.getY()))
               {
                  if(arg1.compareTo(arg2)>0)        //if player is bigger
                  {
                     if(!arg2.toString().equals("Boss Dragon"))
                     {
                        arg2.eaten(HEIGHT, WIDTH);    //enemy is wiped off screen
                        if(canEatDrag==false)
                           arg1.calcGrowth(arg2.toString());    //player grows
                        prompt = "You just ate: " + arg2.toString();
                     }
                     else
                        prompt="You Win!";
                     break;                                        //ends loop
                  }
                  else
                  {
                     arg1.eaten(WIDTH/2, HEIGHT/2);    //sends player to the center of the screen
                     prompt = "Eaten by:"+arg2.toString() + " - You Lose a Life!";
                     pause = 1;   //pauses the game for 2-3 seconds
                     if(arg1.getLives()<=0)    //if player is out of lives
                     {
                        prompt = "No Lives - You Lose!";	//sets the prompt label to be win
                     }
                     break;    //ends the loop
                  }
               }     
         }     
      }
      public String[] update(String[] arg)
      {
         arg[1] = "Lives: " + player.getLives();		//gets the player's lives
         if(dragon.getSize() < player.getSize())
         {
            arg[2]="You can eat the dragon!!";
            canEatDrag=true;
            dragoncount = 50000;
            dragon.setIsHere(true);
         }
         else if(enemyArray[7].getSize() < player.getSize()) //airplane
         {
            arg[2] = "Largest Prey: Airplane";						//set prompt label later on
         }
         else if(enemyArray[4].getSize() < player.getSize()) //Condor
            arg[2] = "Largest Prey: Condor"; 
         else
            arg[2] = "Largest Prey: Butterfly"; 	//default biggest food is butterfly
         arg[3] = prompt;	
         return arg;	//returns the array to panel, for scoreboard updating purposes
      }
      
      public String[] reset(String[] arg)
      {
         player.setLives(3);
         player.setWidth(60);
         player.setLength(90);
         arg[0] = "0:0";
         arg[1] = "Lives: " + player.getLives();		//gets the player's lives
         arg[2] = "Largest Prey: Butterfly"; 	//default biggest food is butterfly
         prompt="-";    
         arg[3] = prompt;	
         dragoncount = 0;
         dragon.setIsHere(false);
         return arg;	//returns the array to panel, for scoreboard updating purposes
      }
   }
