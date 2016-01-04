   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.util.Scanner;
   import java.io.*;
  
   public class FinalPanel extends JPanel
   {
      private static FinalDisplay display;
      private JPanel score, subpanel;
      private static String[] values, highscores;
      private static JLabel time, lives, biggest_food, prompt, info, gameover;
      private static JLabel[] highList;
      private static JTextField name;
      private static JButton pause, quit;
      private int miltime = 0, runtime;
      private static int level;
      private static boolean isPaused = false, showsInstr = false, showsScores=false;
      private final int numVal = 4 ;
      private static Timer timer;
      private static HighScore[] scoreList;
      private static HighScore player;
      private static JPanel scorepanel, westpanel, centerpanel;
      private static JButton start, menuquit, scorebutton, playagain;
      private static JLabel title, instr;
      public FinalPanel() throws FileNotFoundException
      {
         setLayout(new BorderLayout());				//defines the layout
      	
         instr=new JLabel("", SwingConstants.CENTER);		//instantiates the label used for the instruction image
         do
         {
            try
            {
               level = (int)(Double.parseDouble(JOptionPane.showInputDialog("Enter Game Speed, 1-7, 1 being fastest (-1 = quit)")));
               runtime = 5 * (2+level);
               if(runtime == 5)
                  System.exit(1);
               if(14 > runtime || runtime > 45)
                  throw new Exception();
            }
               catch(Exception g)
               {
                  JOptionPane.showMessageDialog(null, "Not a valid choice");
               }
         }while(14 > runtime || runtime > 45);
      	
         scoreList = input();	//the highscore array is set to the values from a text file
         display = new FinalDisplay();			//create actual gameplay object in the middle of the screen
         add(display, BorderLayout.CENTER);
       	
      	//the panel for holding reset and the gameover label after the game  
         Dimension newsize = new Dimension(100, 100);
         centerpanel = new JPanel();
         centerpanel.setLayout(new GridLayout(2, 1));
         centerpanel.setSize(newsize);
      	
      	//reset button
         playagain = new JButton("Play Again");
         playagain.addActionListener(new ResetListener());
      	
         //adds a scoreboard
         score=new JPanel();
         score.setLayout(new GridLayout(1, 5));
         Font all=new Font("Arial", Font.PLAIN, 16);		//makes a font for all the labels to use
         
         info = new JLabel("P=pause, Q=quit, D=directions.");	//sets the default text for all the labels
         info.setFont(all);
         score.add(info);
         
         time=new JLabel("0:00");
         time.setFont(all);
         score.add(time);
         
         lives=new JLabel("Lives: 3");
         lives.setFont(all);
         score.add(lives);
         
         biggest_food=new JLabel("Largest Prey: Butterfly");
         biggest_food.setFont(all);
         score.add(biggest_food);
         
         prompt=new JLabel("-");
         prompt.setFont(all);
         score.add(prompt);
         
         gameover=new JLabel("", SwingConstants.CENTER);		//makes a currently unused label that is displayed at the end of the game
         gameover.setForeground(Color.WHITE);
         gameover.setFont(new Font("Arial", Font.PLAIN, 50));
         
         add(score, BorderLayout.NORTH);
         
         values = new String[numVal];		//the array to hold update info from display is created
         addKeyListener(new Key());
         setFocusable(true);				//allows mouse and keyboard input
         timer = new Timer(runtime, new Listener());            //begins the timer, final refresh rate = 15
         timer.start();
         
      }
      
      
      private class Listener implements ActionListener    //the main game timer
      {   
         public void actionPerformed(ActionEvent e)
         {
            display.game(runtime);  								//calls the gameplay method in display
            miltime += runtime;
            values = display.update(values);
            values[0] = calctime(miltime);
            if(miltime >= 300000)
            {
               prompt.setText("You Win!");
               values[3] = "You Win!";
            }
            update(values);
            check();
         }
      }
      
      public String calctime(int x)
      {
         int sec = x / 1000;
         int min = sec / 60;
         sec = sec % 60;
         return min + ":" + sec;
      }
      
      private class Key extends KeyAdapter
      {
         public void keyPressed( KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_P)
            {
               if(isPaused == false)
               {
                  timer.stop();
                  isPaused = true;
               }
               else
               {
                  timer.start();
                  isPaused = false;
               }
            }
            if(e.getKeyCode() == KeyEvent.VK_Q)
            {
               System.exit(5);
            }
            if(e.getKeyCode() == KeyEvent.VK_D)
            {
               if(showsInstr == false)
               {
                  instr.setIcon(new ImageIcon("instructions.jpg"));
                  add(instr, BorderLayout.SOUTH);
                  validate();
                  timer.stop();
                  showsInstr = true;
               }
               else
               {
                  remove(instr);
                  validate();
                  timer.start();
                  showsInstr = false;
               }
               
            }
         }
      }
      
      private static void update(String[] arg)
      {
         time.setText("Time: "+arg[0]);
         lives.setText(arg[1]);
         biggest_food.setText(arg[2]);
         prompt.setText(arg[3]);
      }
      private void check()
      {
         if(prompt.getText().equals("No Lives - You Lose!") || prompt.getText().equals("You Win!"))
         {
            timer.stop();
            remove(display);
            validate();
            setBackground(Color.BLUE.brighter());
            //add(centerpanel, BorderLayout.CENTER);
            if(prompt.getText().equals("No Lives - You Lose!"))
            {
               gameover.setText("Game Over!");
               player=new HighScore("temp"+" "+ calctime(miltime));
               player.setMinutes((5 - player.getMinutes()) + 5);
               player.setSeconds((60 - player.getSeconds()));
            }
            else
            {
               gameover.setText("You Win!");
               player=new HighScore("temp"+" "+ calctime(miltime));
            }
            add(gameover, BorderLayout.CENTER);
            add(playagain, BorderLayout.EAST);
            if(player.compareTo(scoreList[9])<0)
            {
               name=new JTextField("Enter Name(less than 20 characters):", 30);
               scorepanel = new JPanel();
               scorepanel.setLayout(new FlowLayout());
               add(scorepanel, BorderLayout.SOUTH);
               scorebutton = new JButton("Enter");
               scorebutton.addActionListener(new LoadListener());
               scorepanel.add(name);
               scorepanel.add(scorebutton);
            }
            else
            {
               gameover.setText("No High Score.");
            }
         }
      }
      private HighScore[] input()
      {
         Scanner infile = null;
         try
         {
            infile = new Scanner(new File("highscores" + level + ".txt"));
         }
            catch(FileNotFoundException e)
            {
            }
         HighScore[] scoreList = new HighScore[10];
         for(int k = 0; k < scoreList.length; k++)
         {
            scoreList[k] = new HighScore(infile.nextLine());
         }
         infile.close();
         return scoreList;
      }
      private void sortHighscore()
      {
         PrintStream outfile = null;
         try
         {
            outfile = new PrintStream(new FileOutputStream("highscores" + level + ".txt"));
         }
            catch(FileNotFoundException e)
            {
            }
         int pos = 9;
         for(int x = 0; x < scoreList.length; x++)
         {
            if(player.compareTo(scoreList[x])< 0)
            {
               pos = x;
               break;
            }
         }
         for(int y = scoreList.length - 1; y > pos; y--)
         {
            scoreList[y] = scoreList[y-1];
         }
         scoreList[pos] = player;
         for(int z = 0; z < scoreList.length; z++)
         {
            outfile.println(scoreList[z].toString());
         }
         
         westpanel=new JPanel();
         westpanel.setBackground(Color.CYAN);
         westpanel.setLayout(new GridLayout(11,1));
         add(westpanel, BorderLayout.WEST);
         showsScores=true;
         highList=new JLabel[11];
         for(int o=0; o<highList.length; o++)
         {
            highList[o]=new JLabel("");
            highList[o].setFont(new Font("SansSarif", Font.ITALIC|Font.BOLD, 15));
            westpanel.add(highList[o]);
         }
         highList[0].setText("  Level " + level + " High Scores");
         for(int k=1; k<highList.length; k++)
         {
            if(!scoreList[k-1].toString().equals("----- 11:0"))
               highList[k].setText("  " + (k)+". "+scoreList[k-1].toString() + "  ");
            else
               highList[k].setText("  " + (k)+". ");
         }
         highList[pos+1].setOpaque(true);
         highList[pos+1].setBackground(Color.YELLOW);
         
      }
      
      private class LoadListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               if(name.getText().length()>20)
               {
                  throw new Exception();   
               }
               player.setName(name.getText());
               sortHighscore();
               gameover.setText("High Score Saved! Congratulations!");
               scorebutton.setEnabled(false);
            }
               catch(Exception f)
               {
                  name.setText("Invalid name type-try again. Rememeber, less than 20 characters");
               }
            
         }
      }
      
      private class ResetListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            remove(gameover);
            validate();
            remove(scorepanel);
            validate();
            if(showsScores==true)
            {
               remove(westpanel);
               validate();
               showsScores=false;
            }
            remove(playagain);
            validate();
            display = new FinalDisplay();
            add(display, BorderLayout.CENTER);
            validate();
            update(display.reset(values));
            miltime = 0;
            do
            {
               try
               {
                  level = (int)(Double.parseDouble(JOptionPane.showInputDialog("Enter Game Speed, 1-7, 1 being fastest (-1 = quit)")));
                  runtime = 5 * (2+level);
                  if(runtime == 5)
                     System.exit(1);
                  if(14 > runtime || runtime > 45)
                     throw new Exception();
               }
                  catch(Exception g)
                  {
                     JOptionPane.showMessageDialog(null, "Not a valid choice");
                  }
            }while(14 > runtime || runtime > 45);
            timer.setDelay(runtime);
            timer.start();
         }
      }
   	
   }