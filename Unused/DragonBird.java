   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;

    public class DragonBird extends Bird
   {
       public DragonBird(int x, int y)
      {
         super(x, y, 0, 0, 0, 400, 200, "Boss Dragon" );
      }
     
       public void draw(Graphics myBuffer, int pos)    //sets the method for drawing the up flap
      {
         if(pos==1)
         {
            ImageIcon dragonBird=new ImageIcon("dragonUp.jpg");
            myBuffer.drawImage(dragonBird.getImage(), getX(), getY(), getLength(), getWidth(), null);
         }
         
         else if(pos==0)
         {
            ImageIcon dragonBird=new ImageIcon("dragonDown.jpg");
            myBuffer.drawImage(dragonBird.getImage(), getX(), getY(), getLength(), getWidth(), null);
         }
      }
     
       public void eaten(int x, int y)
      {
         setX(3000);
         setY(1000);
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
         setdx((arg.getX()-getX())/10);
         setdy((arg.getY()-getY())/10);
         setX(getX()+getdx());
         setY(getY()+getdy());
         move(rightEdge, bottomEdge);
      }
   }