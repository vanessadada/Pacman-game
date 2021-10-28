import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.io.*;

/* siyu meng
 * cs11wnb
 * smeng@ucsd.edu
 * 2/23/2019
 * GuiPacman
 * This class aims visualize the pacman game through ImageView
  and play the game through control of keyboard
*/


public class GuiPacman extends Application
{
  private String outputBoard; // The filename for where to save the Board
  private Board board; // The Game Board

  // Fill colors to choose
  private static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.73);
  private static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242);
  private static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101);

  /** Add your own Instance Variables here */
  private StackPane finalResult; //the pane that is used to take the gridpane
  private GridPane back;// the pane contain the game information
  private GridPane over;// the pane cantain the gameover information
  private Scene scene;



  /*
   * Name:      start
   * Purpose:   Start and keep the game running.
   * Parameter:
   * Return:
   */
  @Override
  public void start(Stage primaryStage)
  {
    // Process Arguments and Initialize the Game Board
    processArgs(getParameters().getRaw().toArray(new String[0]));

    /** Add your Code for the GUI Here */
    back = new GridPane();
    finalResult = new StackPane();
    back.setStyle("-fx-background-color: BLACK");//set the background color
    back.setAlignment(Pos.CENTER);
    back.setPadding(new Insets(11.5,12.5,13.5,14.5)); // Set the padding of the pane.
    back.setHgap(5.5);
    back.setVgap(5.5);
    //Initialize the game image into gridpane
    for(int i = 0;i<board.getGrid().length;i++){
      for(int j =0;j<board.getGrid().length;j++){
        Tile toAdd = new Tile(board.getGrid()[i][j]);
        back.add(toAdd.getNode(),i,j+1);
        Text nameOfgame = new Text();
        nameOfgame.setText("Pac-Man");
        nameOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,40));
        nameOfgame.setFill(Color.WHITE);
        back.add(nameOfgame,0,0,4,1);
        Text scoreOfgame = new Text();
        scoreOfgame.setText("Score : "+board.getScore());
        scoreOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        scoreOfgame.setFill(Color.WHITE);
        back.add(scoreOfgame,board.GRID_SIZE/2,0,2,1);
      }
    }
    //add the gridpane to StackPane
    finalResult.getChildren().add(back);
    scene = new Scene(finalResult);
    primaryStage.setTitle("GuiPacman");
    primaryStage.setScene(scene);
    primaryStage.show();
    scene.setOnKeyPressed(new myKeyHandler());//throw the arrow keyboard to control the move
  }




  /** Add your own Instance Methods Here */

  /*
   * Name:       myKeyHandler
   *
   * Purpose: convert the keyboard information get from user to the game image
   *
   *
   */
  private class myKeyHandler implements EventHandler<KeyEvent> {

   /*
    * Name:      handle
    * Purpose:   handle the KeyEvent of user's input.
    * Parameter: KeyEvent e used to accept the user's input about move direction of pacman
    * Return:
    */
    @Override
    public void handle (KeyEvent e) {
      // TODO
      //if the game is over, pacman will not move
      if(board.isGameOver()){
        return;
      }
      else{
        //if the pacman move up
        if(e.getCode().equals(KeyCode.UP)){
          if(board.canMove(Direction.LEFT)){
            board.move(Direction.LEFT);
            System.out.println("Moving"+Direction.UP);

            back.getChildren().clear();// clear the old pane
            //Initialize the new game image
            for(int i = 0;i<board.getGrid().length;i++){
              for(int j =0;j<board.getGrid().length;j++){
                if(board.getGrid()[i][j]=='P'){
                  Tile toAdd = new Tile(board.getGrid()[i][j]);
                  toAdd.getNode().setRotate(270);//rotate the direction of pacman's mouth
                  back.add(toAdd.getNode(),i,j+1);
                }
                //set the other image except pacman
                else{
                  Tile toAdd = new Tile(board.getGrid()[i][j]);
                  back.add(toAdd.getNode(),i,j+1);
                }
                //set the new title and score point
                Text nameOfgame = new Text();
                nameOfgame.setText("Pac-Man");
                nameOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,40));
                nameOfgame.setFill(Color.WHITE);
                back.add(nameOfgame,0,0,4,1);
                Text scoreOfgame = new Text();
                scoreOfgame.setText("Score : "+board.getScore());
                scoreOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
                scoreOfgame.setFill(Color.WHITE);
                back.add(scoreOfgame,board.GRID_SIZE/2,0,2,1);
                gameIsOver();//check game ove after move
              }
            }
          }
        }
        //if the pacman moves down
        if(e.getCode().equals(KeyCode.DOWN)){
          if(board.canMove(Direction.RIGHT)){
            board.move(Direction.RIGHT);
            System.out.println("Moving"+Direction.DOWN);

            back.getChildren().clear();// clear the old pane
            //Initialize the new game image
            for(int i = 0;i<board.getGrid().length;i++){
              for(int j =0;j<board.getGrid().length;j++){
                if(board.getGrid()[i][j]=='P'){
                  Tile toAdd = new Tile(board.getGrid()[i][j]);
                  toAdd.getNode().setRotate(90);//rotate the direction of pacman's mouth
                  back.add(toAdd.getNode(),i,j+1);
                }
                //set the other image except pacman
                else{
                  Tile toAdd = new Tile(board.getGrid()[i][j]);
                  back.add(toAdd.getNode(),i,j+1);
                }
                //set the new title and score point
                Text nameOfgame = new Text();
                nameOfgame.setText("Pac-Man");
                nameOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,40));
                nameOfgame.setFill(Color.WHITE);
                back.add(nameOfgame,0,0,4,1);
                Text scoreOfgame = new Text();
                scoreOfgame.setText("Score : "+board.getScore());
                scoreOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
                scoreOfgame.setFill(Color.WHITE);
                back.add(scoreOfgame,board.GRID_SIZE/2,0,2,1);
                gameIsOver();//check game ove after move
              }
            }
          }
        }
        //if the pacman moves left
        if(e.getCode().equals(KeyCode.LEFT)){
          if(board.canMove(Direction.UP)){
            board.move(Direction.UP);
            System.out.println("Moving"+Direction.LEFT);

            back.getChildren().clear();// clear the old pane
            //Initialize the new game image
            for(int i = 0;i<board.getGrid().length;i++){
              for(int j =0;j<board.getGrid().length;j++){
                if(board.getGrid()[i][j]=='P'){
                  Tile toAdd = new Tile(board.getGrid()[i][j]);
                  toAdd.getNode().setRotate(180);//rotate the direction of pacman's mouth
                  back.add(toAdd.getNode(),i,j+1);
                }
                //set the other image except pacman
                else{
                  Tile toAdd = new Tile(board.getGrid()[i][j]);
                  back.add(toAdd.getNode(),i,j+1);
                }
                //set the new title and score point
                Text nameOfgame = new Text();
                nameOfgame.setText("Pac-Man");
                nameOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,40));
                nameOfgame.setFill(Color.WHITE);
                back.add(nameOfgame,0,0,4,1);
                Text scoreOfgame = new Text();
                scoreOfgame.setText("Score : "+board.getScore());
                scoreOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
                scoreOfgame.setFill(Color.WHITE);
                back.add(scoreOfgame,board.GRID_SIZE/2,0,2,1);
                gameIsOver();//check game ove after move
              }
            }
          }
        }
        //if the pacman moves right
        if(e.getCode().equals(KeyCode.RIGHT)){
          if(board.canMove(Direction.DOWN)){
            board.move(Direction.DOWN);
            System.out.println("Moving"+Direction.RIGHT);

            back.getChildren().clear(); // clear the old pane
            //Initialize the new game image
            for(int i = 0;i<board.getGrid().length;i++){
              for(int j =0;j<board.getGrid().length;j++){
                Tile toAdd = new Tile(board.getGrid()[i][j]);
                back.add(toAdd.getNode(),i,j+1);
                //set the new title and score point
                Text nameOfgame = new Text();
                nameOfgame.setText("Pac-Man");
                nameOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,40));
                nameOfgame.setFill(Color.WHITE);
                back.add(nameOfgame,0,0,4,1);
                Text scoreOfgame = new Text();
                scoreOfgame.setText("Score : "+board.getScore());
                scoreOfgame.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
                scoreOfgame.setFill(Color.WHITE);
                back.add(scoreOfgame,board.GRID_SIZE/2,0,2,1);
                gameIsOver();//check game ove after move
              }
            }
          }
        }
        //if user press s, it will save the board
        if(e.getCode().equals(KeyCode.S)){
          try {
            board.saveBoard(outputBoard);
          }
          catch (IOException error) {
            System.out.println("saveBoard threw an Exception");
          }
          System.out.println("Saving board to "+outputBoard);
        }
      }
    }

    /*
     * Name:      gameIsOver
     * Purpose:   Check if the game is over and show the gameover board.
     * Parameter:
     * Return:
     */
    private void gameIsOver() {
      //TODO
      //check the game is over or not
      if(board.isGameOver()){
        over = new GridPane();
        //set the gameover word
        Text gameOver = new Text();
        gameOver.setText("Game Over!");
        gameOver.setFont(Font.font("Times New Roman",FontWeight.BOLD,40));
        over.setAlignment(Pos.CENTER);
        //set the semi- transparent layer
        over.setStyle("-fx-background-color: rgb(238, 228, 218, 0.02)");
        over.add(gameOver,5,5,5,5);
        finalResult.getChildren().add(over);
      }
    }
  } // End of Inner Class myKeyHandler.



  /*
   * Name:        Tile
   *
   * Purpose:     This class tile helps to make the tiles in the board
   *              presented using JavaFX. Whenever a tile is needed,
   *              the constructor taking one char parameter is called
   *              and create certain ImageView fit to the char representation
   *              of the tile.
   *
   *
   */
  private class Tile {

    private ImageView repr;   // This field is for the Rectangle of tile.

    /*
     * Constructor
     *
     * Purpose: according to the charater in the board, set the image view of the game
     * Parameter: char tileAppearance : get from the board information
     *
     */
    public Tile(char tileAppearance) {
      //TODO
      //get the character information from board and set the image to game
      //set the uneaten dot
      if(tileAppearance == '*'){
        Image dotUneat = new Image("dot_uneaten.png");
        repr = new ImageView(dotUneat);
        repr.setFitWidth(50);
        repr.setFitHeight(50);
      }
      // set the eaten dot
      if(tileAppearance == ' '){
        Image dotEat = new Image("dot_eaten.png");
        repr = new ImageView(dotEat);
        repr.setFitWidth(50);
        repr.setFitHeight(50);
      }
      //set the pacman image
      if(tileAppearance == 'P'){
        Image pacmanLive = new Image("pacman_right.png");
        repr = new ImageView(pacmanLive);
        repr.setFitWidth(50);
        repr.setFitHeight(50);
      }
      //set the pacman dead image
      if(tileAppearance == 'X'){
        Image pacmanDie = new Image("pacman_dead.png");
        repr = new ImageView(pacmanDie);
        repr.setFitWidth(50);
        repr.setFitHeight(50);
      }
      //set the ghosts image
      if(tileAppearance == 'G'){
        Image redGhost = new Image("blinky_left.png");
        repr = new ImageView(redGhost);
        repr.setFitWidth(50);
        repr.setFitHeight(50);
      }
      //set the cherry image
      if(tileAppearance == 'C'){
        Image cherry = new Image("cherry.png");
        repr = new ImageView(cherry);
        repr.setFitWidth(50);
        repr.setFitHeight(50);
      }
    }
    /*
     * getNode method
     *
     * Purpose: get the ImageView information
     * return : the ImageView of each charater and dot in board
     *
     */

    public ImageView getNode() {
      //TODO
      return repr;//return the ImageView of each character 
    }

  }  // End of Inner class Tile




  /** DO NOT EDIT BELOW */

  // The method used to process the command line arguments
  private void processArgs(String[] args)
  {
    String inputBoard = null;   // The filename for where to load the Board
    int boardSize = 0;          // The Size of the Board

    // Arguments must come in pairs
    if((args.length % 2) != 0)
    {
      printUsage();
      System.exit(-1);
    }

    // Process all the arguments
    for(int i = 0; i < args.length; i += 2)
    {
      if(args[i].equals("-i"))
      {   // We are processing the argument that specifies
        // the input file to be used to set the board
        inputBoard = args[i + 1];
      }
      else if(args[i].equals("-o"))
      {   // We are processing the argument that specifies
        // the output file to be used to save the board
        outputBoard = args[i + 1];
      }
      else if(args[i].equals("-s"))
      {   // We are processing the argument that specifies
        // the size of the Board
        boardSize = Integer.parseInt(args[i + 1]);
      }
      else
      {   // Incorrect Argument
        printUsage();
        System.exit(-1);
      }
    }

    // Set the default output file if none specified
    if(outputBoard == null)
      outputBoard = "Pac-Man.board";
    // Set the default Board size if none specified or less than 2
    if(boardSize < 3)
      boardSize = 10;

    // Initialize the Game Board
    try{
      if(inputBoard != null)
        board = new Board(inputBoard);
      else
        board = new Board(boardSize);
    }
    catch (Exception e)
    {
      System.out.println(e.getClass().getName() + " was thrown while creating a " +
          "Board from file " + inputBoard);
      System.out.println("Either your Board(String, Random) " +
          "Constructor is broken or the file isn't " +
          "formated correctly");
      System.exit(-1);
    }
  }

  // Print the Usage Message
  private static void printUsage()
  {
    System.out.println("GuiPacman");
    System.out.println("Usage:  GuiPacman [-i|o file ...]");
    System.out.println();
    System.out.println("  Command line arguments come in pairs of the form: <command> <argument>");
    System.out.println();
    System.out.println("  -i [file]  -> Specifies a Pacman board that should be loaded");
    System.out.println();
    System.out.println("  -o [file]  -> Specifies a file that should be used to save the Pac-Man board");
    System.out.println("                If none specified then the default \"Pac-Man.board\" file will be used");
    System.out.println("  -s [size]  -> Specifies the size of the Pac-Man board if an input file hasn't been");
    System.out.println("                specified.  If both -s and -i are used, then the size of the board");
    System.out.println("                will be determined by the input file. The default size is 10.");
  }
}
