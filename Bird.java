
   import java.awt.*;
  /**************************************************************************************************************************************
   * A Bird is an Object that maintains information about its width, height, how many times its animation "flaps" or redraws the position,
   * its x and y coordinates, and the distance it needs to change in order to move across the screen.
   * Birds are comparable using the Comparable<E>Interface.A Bird knows how to return and set all its fields.  It has 4 abstract methods,
   * making it an abstract class.
   
   * @author    Anudeep Mangu
   * @version     FinalProject.Bird                                                                                                        
   **************************************************************************************************************************************/

    public abstract class Bird implements Comparable<Bird>
   {
      private int myFlapCount, mySize, myX, myY, dx, dy, myWidth, myLength;
      private String myType;
     /*********************************************************************************************
      * Constructs a bird with default values without arguments.
      *********************************************************************************************/
       public Bird()
      {
         myX = 100;
         myY = 100;
         dx = 10;
         dy = 10;
         myLength=100;
         myWidth=100;
         myFlapCount = (int)(Math.random() * 100 + 1);
         mySize = 100;
         myType = "Bird";
      }
    /**********************************************************************************************************************************
      * Constructs a bird with initial x coordinate specified by x, initial y coordinate specified by y, initial horizontal movement
      * specified by xx, initial vertical movement specified by yy, initial picture position(wings up or down) by flap, initial size
      * specified by size, initial length specified by length, initial width specified by width, and itial class type specified by s.
      
      * @param x    initial width
      * @param y    initial length
      * @param xx   initial dx
      * @param yy   initial dy
      * @param flap initial wing position
      * @param size ititial comparative size
      * @param length initial length
      * @param width  initial width
      * @param s      initial class type
      *********************************************************************************************/
       public Bird(int x, int y, int xx, int yy, int flap,int length, int width, String s )
      {
         myX = x;    
         myY = y;
         dx = xx;
         dy = yy;
         myLength=length;
         myWidth=width;
         myFlapCount = flap;        
         mySize = myLength * myWidth;    
         myType = s;
      }
    /***************************************************************
      * Returns the Bird's x coordinate.
      * @return     myX
      ***************************************************************/
       public int getX()
      {
         return myX;
      }
    /***************************************************************
      * Returns the Bird's y coordinate.
      * @return     myY
      ***************************************************************/
       public int getY()
      {
         return myY;
      }
    /***************************************************************
      * Returns the vertical distance the Bird will move.
      * @return     dy
      ***************************************************************/
       public int getdy()
      {
         return dy;
      }    
     /***************************************************************
      * Returns the horizontal distance the Bird will move.
      * @return     dx
      ***************************************************************/
       public int getdx()
      {
         return dx;
      }
    /***************************************************************
      * Returns the Bird's length.
      * @return     myLength
      ***************************************************************/
       public int getLength()
      {
         return myLength;
      }
    /***************************************************************
      * Returns the Bird's width.
      * @return     myWidth
      ***************************************************************/
       public int getWidth()
      {
         return myWidth;
      }
    /***************************************************************
      * Returns the amount of times the image of the Bird changes from up position to down position.
      * @return     myFlapCount
      ***************************************************************/
       public int getFlapCount()
      {
         return myFlapCount;
      }
    /***************************************************************
      * Returns the Bird's comparative size.
      * @return     mySize
      ***************************************************************/
       public int getSize()
      {
         return mySize;
      }
       public String getType()
      {
         return myType;
      }
    /***************************************************************
      * Sets the x position to the input number.                
      * @param x     assigns x to myX                                    
      ***************************************************************/
       public void setX(int x)
      {
         myX = x;
      }
    /***************************************************************
      * Sets the y position to the input number.                
      * @param y     assigns y to myY                                    
      ***************************************************************/
       public void setY(int y)
      {
         myY = y;
      }
    /***************************************************************
      * Sets the dy distance to the input number.                
      * @param y     assigns y to dy                                    
      ***************************************************************/
       public void setdy(int y)
      {
         dy = y;
      }    
     /***************************************************************
      * Sets the dx distance to the input number.                
      * @param x     assigns x to dx                                    
      ***************************************************************/
       public void setdx(int x)
      {
         dx = x;
      }
    /***************************************************************
      * Sets the Bird's length to the input number.                
      * @param length assigns length to myLength                                    
      ***************************************************************/
       public void setLength(int length)
      {
         myLength = length;
      }
    /***************************************************************
      * Sets the Bird's width to the input number.                
      * @param width     assigns width to myWidth                                    
      ***************************************************************/
       public void setWidth(int width)
      {
         myWidth = width;
      }
    /***************************************************************
      * Sets the count of the wing flaps to the input number.                
      * @param x     assigns x to myFlapCount                                    
      ***************************************************************/
       public void setFlapCount(int x)
      {
         myFlapCount = x;
      }
    /***************************************************************
      * Sets the relative size to the input number.                
      * @param x     assigns x to mySize                                    
      ***************************************************************/
       public void setSize(int x)
      {
         mySize = x;
      }
     /***************************************************************
      * Method from the Comparable<E>Interface. Compares Bird objects
      * by size, and returns the differenece.
      * @return (int)mySize - (int)arg.getSize()
      ***************************************************************/
       public int compareTo(Bird arg)
      {
         return mySize - arg.getSize();
      }
     /***************************************************************
      * Changes the x and y positions by adding dx and dy.  Checks to
      * make sure objects don't touch the sides of the frame.
      * @param rightEdge stores the right edge size of the frame
      * @param bottomEdge stores teh bottom edge size of the frame
      ***************************************************************/
       public void move(int rightEdge, int bottomEdge)
      {
         setX(getX()+ dx);
         setY(getY()+ dy);
       
         if(getY() >=bottomEdge-getWidth())
         {
            setY(bottomEdge-getWidth());
            dy=dy*-1;
         }
         else if(getY() <=0)
         {
            setY(getWidth());
            dy=dy*-1;
         }
         else if(getX() >= rightEdge - getLength())
         {
            setX(rightEdge - getLength());
            dx = dx * -1;
         }
         else if(getX() <=0)
         {
            setX(getLength());
            dx= dx*-1;
         }
        
      }
   
     
       public abstract void draw(Graphics myBuffer, int pos);
     
       public abstract String toString();
     
       public abstract void eaten(int x, int y);                    
     
   }
