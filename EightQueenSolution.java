import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class EightQueenSolution extends Applet implements Runnable, MouseListener, KeyListener, MouseMotionListener
{
   public int worldx;
   public int worldy;
   public boolean finished=false;
   public int size=25;
   public int numberSolutions=0;
   public class Queen{
      public int row, column;
      public Queen(int r){
         row=r;
         column=-1;
      }
   }
   public ArrayList<Queen[]> possibleSolutions = new ArrayList<Queen[]>();
   
   Graphics bufferGraphics; //Set up double buffer
   Image offscreen;
   Thread thread;//Sets up a Thread called thread

   public void init()
   {
      worldx=1400;//Sets the world size
      worldy=1000;//Sets the world size
      Queen[] queens = new Queen[size];
      for(int i=0;i<queens.length;i++){
         queens[i] = new Queen(i);
      }
      isValidToPlace(queens,0);

      offscreen = createImage(worldx,worldy); //create a new image that's the size of the applet DOUBLE BUFFER SET UP
      bufferGraphics = offscreen.getGraphics(); //set bufferGraphics to the graphics of the offscreen image. DOUBLE BUFFER SET UP
      
      addKeyListener(this);//setup all the listeners
      addMouseListener(this);//setup all the listeners
      addMouseMotionListener(this);//setup all the listeners
      thread = new Thread(this);  //constructs a new thread
      thread.start();             //starts the thread
   }//init()
   public void isValidToPlace(Queen[] queens, int currentQueen){
      if(currentQueen==queens.length){
         finished=true;
         numberSolutions++;
         printQueens(queens, numberSolutions);
      }
      else{
         for(int i=0;i<queens.length;i++){
            queens[currentQueen].column=i;
            if(isValid(queens,currentQueen)){
               isValidToPlace(queens, currentQueen+1);
            }
         }
      }
   }
   public boolean isValid(Queen[] queens, int currentQueen) {
        for (int i = 0; i < currentQueen; i++) {
            if (queens[i].column == queens[currentQueen].column){
               return false;
            }   // same column
            if ((queens[i].column - queens[currentQueen].column) == (currentQueen - i)){ 
               return false;   // same down-right diagonal
            }
            if ((queens[currentQueen].column - queens[i].column) == (currentQueen - i)){ 
               return false;   // same up-right diagonal
            }
        }
        return true;
    }
   public void printQueens(Queen[] queens, int numSol){
      System.out.println("--------Start Solution "+numSol+" --------");
      for(int i=0;i<size;i++){
            for(int x=0;x<size;x++){
               if(x!=queens[i].column){
                  System.out.print("*");
               }
               else{
                  System.out.print("Q");
               }
            }
            System.out.println();
         }
      System.out.println("--------END--------");
   }
   public void paint(Graphics g) 
   {// paint() is used to display things on the screen
      setSize(worldx,worldy);
      bufferGraphics.clearRect(0,0,worldx,worldy); //clear the offscreen image
      bufferGraphics.setColor(Color.black);
      g.drawImage(offscreen,0,0,worldx,worldy,this);//Draw the screen
   }// paint()
   public void mouseDragged(MouseEvent e) {
   	
   }
   public void mouseMoved(MouseEvent e){
   
   }
   public void mousePressed(MouseEvent e) 
   {
   
   }
   public void mouseReleased(MouseEvent e) 
   {
   
   }
   public void mouseEntered(MouseEvent e) 
   {
      System.out.println("Mouse entered");
   }
   public void mouseExited(MouseEvent e) 
   {
      System.out.println("Mouse exited");
   }
   public void mouseClicked(MouseEvent e) 
   {
      System.out.println("Mouse clicked (# of clicks: "+ e.getClickCount() + ")");
      
   }
   public void keyPressed( KeyEvent event ) 
   {
      String keyin; // define a non‐public variable to hold the string representing the key input
      keyin = ""+event.getKeyText( event.getKeyCode()); 
      System.out.println("Key pressed "+keyin);
   }//keyPressed()
   public void keyReleased( KeyEvent event ) 
   {
      String keyin;
      keyin = ""+event.getKeyText( event.getKeyCode()); 
      System.out.println ("Key released: "+ keyin);
   }//keyReleased()
   public void keyTyped( KeyEvent event ) 
   {
      char keyin;
      keyin = event.getKeyChar(); //getKeyChar() returns the character of the printable key pressed. 
      System.out.println ("Key Typed: "+ keyin);
   }//keyTyped()
  
   public void update (Graphics g) 
   {
      paint(g); 
   }//Update the graphics
   public void run() 
   {
      while(true) // this thread loop forever and runs the paint method and then sleeps.
      {
         
         repaint();
         try {
            thread.sleep(50);
         }
         catch (Exception e){ }
      }//while
   }// run()

}//Applet
