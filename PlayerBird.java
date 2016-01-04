   //Name:    Date:
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;

    public class PlayerBird extends Bird
   {
      private int myLives=3;
      private static ImageIcon playerBird;
       public PlayerBird(int x, int y)
      {
         super(x,y,1, 1, 0, 90, 60,"PlayerBird");
         playerBird = new ImageIcon("pos_hawk_up.gif");
      }
     
     
       public void draw(Graphics myBuffer, int pos)    //sets the method for drawing the up flap
      {
         if(getdx() < 0)
         {
            if(pos==1)
            {
               playerBird=new ImageIcon("neg_hawk_down.gif");
               myBuffer.drawImage(playerBird.getImage(), getX(), getY(), getLength() , getWidth(), null, null);
            }
            
            else if(pos==0)
            {
               playerBird = new ImageIcon("neg_hawk_up.gif");
               myBuffer.drawImage(playerBird.getImage(), getX(), getY(), getLength(), getWidth() , null, null);
            }
         }
         else if(getdx() > 0)
         {
            if(pos==1)
            {
               playerBird=new ImageIcon("pos_hawk_down.gif");
               myBuffer.drawImage(playerBird.getImage(), getX(), getY(), getLength() , getWidth(), null, null);
            }
            
            else if(pos==0)
            {
               playerBird = new ImageIcon("pos_hawk_up.gif");
               myBuffer.drawImage(playerBird.getImage(), getX(), getY(), getLength(), getWidth() , null, null);
            }
         }
         else
         {
            myBuffer.drawImage(playerBird.getImage(), getX(), getY(), getLength() , getWidth(), null, null);
         }
      }
     
       public String toString()                //this method will return the type of the bird
      {
         return "PlayerBird";
      }
   
       public int getLives()
      {
         return myLives;
      }
   	
       public void setLives(int l)
      {
         myLives = l;
      }
       public void eaten(int middleX, int middleY)
      {
         myLives--;
         setLength(((getLength() - 90) / 2) + 90);
         setWidth(((getWidth() - 60) / 2) + 60);
         setX(middleX);
         setY(middleY);
      }
     
       public void calcGrowth(String s)
      {
         if(s.equals("Butterfly"))
         {
            setLength(getLength() + 1);
            setWidth(getWidth() + 1);
         }
         else if(s.equals("Condor"))
         {
            setLength(getLength() + 2);
            setWidth(getWidth() + 2);
         }
         else if(s.equals("Airplane"))
         {
            setLength(getLength() + 4);
            setWidth(getWidth() + 4);
         }
         // else if(s.equals("Dragon")
          // win;
         setSize(getLength()*getWidth());
      }
   
   
     
       public void move(int rightEdge, int bottomEdge)
      {
      //setX(getX()+ getdx());
      //setY(getY()+ getdy());
      
         if(getY() >=bottomEdge-getWidth())
         {
            setY(bottomEdge-getWidth());
         }
         else if(getY() <=0)
         {
            setY(0);
         }
         else if(getX() >= rightEdge - getLength())
         {
            setX(rightEdge - getLength());
         }
         else if(getX() <=0)
         {
            setX(0);
         }
      
      }
   }