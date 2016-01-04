   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;

    public class EnemyBird extends Bird
   {
      private static ImageIcon butterfly, condor, airplane;
       public EnemyBird(int x, int y, int flap, String s,int xx, int yy, int imagex, int imagey)
      {
         super(x,y,xx,yy, flap, imagex, imagey, s);
      }
       public void eaten(int height, int width)
      {
         setFlapCount((int)(Math.random() * 20));
         setY((int)(Math.random() * (height - 251))); //starts at random height
         int coin = (int)(Math.random()* 2);
         switch(coin)
         {
            case 0: setX((int)(Math.random()* -100 - 277)); //starts from offscreen left
               break;
            case 1: setX((int)(Math.random()* 100 + (width + 277))); //starts from offscreen right
               break;
         }
         if(toString().equals("Butterfly"))
         {
            int changeX = (int)(Math.random() * 5 + 1);
            if(coin == 1)
               changeX = changeX * -1;  
            setdx(changeX);      
            setdy((int)(Math.random() * 10 - 5));
         }
         else if(toString().equals("Condor"))
         {
            int changeX = (int)(Math.random() * 10 + 1);
            if(coin == 1)
               changeX = changeX * -1;  
            setdx(changeX);      
            setdy((int)(Math.random() * 20 - 10));
         }
         else if(toString().equals("Airplane"))
         {      
            int changeX = (int)(Math.random() * 15 + 1);
            if(coin == 1)
               changeX = changeX * -1;  
            setdx(changeX);      
            setdy((int)(Math.random() * 30 - 15));
         }
      }
       public String toString()
      {
         return getType();
      }
     
       public void move(int rightEdge, int bottomEdge)
      {
         setX(getX()+ getdx());
         setY(getY()+ getdy());
      
         if(getY() >= bottomEdge)
         {
            setY(bottomEdge);
            setdy(getdy() * - 1);
         }
         else if(getY() <= 0 - getWidth())
         {
            setY(0 - getWidth());
            setdy(getdy() * - 1);
         }
         else if(getX() >= rightEdge)
         {
            setX(rightEdge);
            setdx(getdx() * - 1);
         }
         else if(getX() <=0 - getLength())
         {
            setX(0 - getLength());
            setdx(getdx() * - 1);
         }
      
      }
     
     
       public void draw(Graphics myBuffer, int pos)
      {
         String s = toString();
      
         if(getdx() < 0)
         {    
            if(s.equals("Butterfly"))
            {
               if(pos==1)
               {
                  butterfly=new ImageIcon("neg_butterfly_closed.gif");
                  myBuffer.drawImage(butterfly.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
               
               else if(pos==0)
               {
                  butterfly = new ImageIcon("neg_butterfly_open.gif");
                  myBuffer.drawImage(butterfly.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
            }
            else if(s.equals("Condor"))
            {
               if(pos==1)
               {
                  condor=new ImageIcon("neg_condor_up.gif");
                  myBuffer.drawImage(condor.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
               
               else if(pos==0)
               {
                  condor = new ImageIcon("neg_condor_down.gif");
                  myBuffer.drawImage(condor.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
            }
            else if(s.equals("Airplane"))
            {
               if(pos==1)
               {
                  airplane=new ImageIcon("neg_airplane.gif");
                  myBuffer.drawImage(airplane.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
               
               else if(pos==0)
               {
                  airplane = new ImageIcon("neg_airplane.gif");
                  myBuffer.drawImage(airplane.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
            }
         }
         else if(getdx() > 0)
         {    
            if(s.equals("Butterfly"))
            {
               if(pos==1)
               {
                  butterfly=new ImageIcon("pos_butterfly_closed.gif");
                  myBuffer.drawImage(butterfly.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
               
               else if(pos==0)
               {
                  butterfly = new ImageIcon("pos_butterfly_open.gif");
                  myBuffer.drawImage(butterfly.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
            }
            else if(s.equals("Condor"))
            {
               if(pos==1)
               {
                  condor=new ImageIcon("pos_condor_up.gif");
                  myBuffer.drawImage(condor.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
               
               else if(pos==0)
               {
                  condor = new ImageIcon("pos_condor_down.gif");
                  myBuffer.drawImage(condor.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
            }
            else if(s.equals("Airplane"))
            {
               if(pos==1)
               {
                  airplane=new ImageIcon("pos_airplane.gif");
                  myBuffer.drawImage(airplane.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
               
               else if(pos==0)
               {
                  airplane = new ImageIcon("pos_airplane.gif");
                  myBuffer.drawImage(airplane.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
               }
            }
         }
         else if(getdx() == 0)
         {    
            if(s.equals("Butterfly"))
            {
               myBuffer.drawImage(butterfly.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
            else if(s.equals("Condor"))
            {
               myBuffer.drawImage(condor.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
            else if(s.equals("Airplane"))
            {
               myBuffer.drawImage(airplane.getImage(), getX(), getY(), getLength(), getWidth(), null, null);
            }
         }
      }
   }