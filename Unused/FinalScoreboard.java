     import javax.swing.*;
  import java.awt.*;
  import java.awt.event.*;
  import java.awt.image.*;

    public class FinalScoreboard extends JPanel
  {
     private JLabel time, lives, biggest_food, prompt;
     private JButton pause, quit;
      public FinalScoreboard()
     {
        setLayout(new FlowLayout());
      
        Font all=new Font("Arial", Font.PLAIN, 50);
         
        time=new JLabel("10:00");
        time.setFont(all);
        add(time);
        
        lives=new JLabel("Lives:");
        lives.setFont(all);
        add(lives);
         
        biggest_food=new JLabel("Biggest Food You Can Eat:");
        biggest_food.setFont(all);
        add(biggest_food);
         
        prompt=new JLabel("You Were Last Eaten By:");
        prompt.setFont(all);
        add(prompt);
     
        pause=new JButton("Pause");
        pause.setFont(all);
        add(pause);
         
        quit=new JButton("Quit");
        quit.setFont(all);
        add(quit);
     }
  }