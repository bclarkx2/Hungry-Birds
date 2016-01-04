   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;

    public class DragonBird extends Bird
   {
      private static ImageIcon dragon;
      private static boolean isHere=false;
       public DragonBird(int x, int y)
      {
         super(x, y, 0, 0, (int)(Math.random()*30), 300, 150, "Boss Dragon" );
         dragon = new ImageIcon("neg_dragon_down.gif");
      }
     
       public void draw(Graphics myBuffer, int pos)    //sets the method for drawing the up flap
      {
         if(getdx() < 0)
         {    
            if(pos==1)
            {
               dragon=new ImageIcon("neg_dragon_up.gif");
               myBuffer.drawImage(dragon.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
            else if(pos==0)
            {
               dragon = new ImageIcon("neg_dragon_down.gif");
               myBuffer.drawImage(dragon.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
            
         }
         else if(getdx() > 0)
         {    
            if(pos==1)
            {
               dragon=new ImageIcon("pos_dragon_up.gif");
               myBuffer.drawImage(dragon.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
            else if(pos==0)
            {
               dragon = new ImageIcon("pos_dragon_down.gif");
               myBuffer.drawImage(dragon.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
            
         } 
         else
         {
            myBuffer.drawImage(dragon.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
         }
      }
      
       public boolean getIsHere()
      {
         return isHere;
      }
   	
       public void setIsHere(boolean arg)
      {
         isHere=arg;
      }
      
       public void goAway()
      {
         setdx(1200-getX());
         setdy(900-getY());
         setX(getX()+getdx());
         setY(getY()+getdy());
      }
      
       public void eaten(int x, int y)
      {
         setX(1200);
         setY(900);
      }
      
       public String toString()
      {
         return "Boss Dragon";
      }
     
       public void move(int rightEdge, int bottomEdge)
      {
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
     
       public void move(int rightEdge, int bottomEdge, PlayerBird arg)
      {
         setdx((arg.getX()-getX())/30);
         setdy((arg.getY()-getY())/30);
         setX(getX()+getdx());
         setY(getY()+getdy());
         move(rightEdge, bottomEdge);
      }
   }