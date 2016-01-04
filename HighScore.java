//Name______________________________ Date_____________

    public class HighScore implements Comparable<HighScore>
   {
   	//data fields
      private String myName;
      private int myMinutes, mySeconds;
   
   	//constructors
       public HighScore(String toBeParsed)
      {
         myName = toBeParsed.substring(0,toBeParsed.indexOf(" "));
         myMinutes = Integer.parseInt(toBeParsed.substring(toBeParsed.lastIndexOf(" ")+1, toBeParsed.indexOf(":")));
         mySeconds = Integer.parseInt(toBeParsed.substring(toBeParsed.indexOf(":") + 1));
      }
     
   	//accessors and modifiers
      
       public int getMinutes()
      {
         return myMinutes;
      }
      
       public int getSeconds()
      {
         return mySeconds;
      }
      
       public String getName()
      {
         return myName;
      }		
   	
       public void setMinutes(int x)
      {
         myMinutes = x;
      }
     
       public void setSeconds(int x)
      {
         mySeconds = x;
      }
      
       public void setName(String s)
      {
         myName = s;
      }
   	
       
      //other methods:  compareTo(), equals(), toString()
      
       public int compareTo(HighScore s)
      {
         int myTotal = (myMinutes * 60) + mySeconds;
         int sTotal = (s.getMinutes() * 60) + s.getSeconds();
         return myTotal - sTotal;
      }
   	
       public boolean equals(HighScore s)
      {
         return compareTo(s) == 0;
      }
   	
       public String toString()
      {
         return myName + " " + myMinutes + ":" + mySeconds;
      }
   	
   }