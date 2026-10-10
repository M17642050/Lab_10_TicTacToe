
//Output "Play Tic Tac Toe?(Y/n): "
//Input userChoice
//if userChoice = "Y"/"y" then
//  Output "Player X, enter your move: "
//  Input xMove
//  if xMove is valid then
//      board[xMove] = "X"
//  end if
//  display board[]
//  if X has won then
//      Output "Player X wins."
//      exit
//  end if
//  Output "Player O, enter your move: "
//  Input oMove
//  if oMove is valid then
//      board[oMove] = "O"
//  end if
//  display board[]
//  if O has won then
//      Output "Player O wins."
//      exit
//  end if
//  if there's a tie then
//      Output "It's a Tie. No one wins."
//      exit
//  end if
//else if userChoice = "N"/"n"
//  exit
//end if

import java.util.Scanner;
public class TicTacToe{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        boolean loop = false;
        do{
            boolean userChoice = SafeInput.getYNConfirm(in,"Play Tic Tac Toe?(Y/n): ");
            if(userChoice){
                loop = true;
                gameRules();
                while(!isTie()) {
                    boolean xLoop = true;
                    do {
                        int xRowMove = SafeInput.getRangedInt(in, "Player X, enter the row number for your move:", 1, 3);
                        int xColMove = SafeInput.getRangedInt(in, "Player X, enter the column number for your move:", 1, 3);
                        if (isValidMove(xRowMove - 1, xColMove - 1)) {
                            board[xRowMove - 1][xColMove - 1] = "X";
                            xLoop = false;
                        }
                    }
                    while (xLoop);
                    display();
                    if (isWin("X")) {
                        System.out.println("Player X wins.");
                        break;
                    }
                    else if (isTie()) {
                        System.out.println("It's a Tie. No one wins.");
                        break;
                    }
                    boolean oLoop = true;
                    do {
                        int oRowMove = SafeInput.getRangedInt(in, "Player O, enter the row number for your move:", 1, 3);
                        int oColMove = SafeInput.getRangedInt(in, "Player O, enter the column number for your move:", 1, 3);
                        if (isValidMove(oRowMove - 1, oColMove - 1)) {
                            board[oRowMove - 1][oColMove - 1] = "O";
                            oLoop = false;
                        }
                    }
                    while (oLoop);
                    display();
                    if (isWin("O")) {
                        System.out.println("Player O wins.");
                        break;
                    }
                    else if (isTie()) {
                        System.out.println("It's a Tie. No one wins.");
                        break;
                    }
                }
            }
            else{
                loop = false;
            }
        }
        while(loop);
    }
    private static void clearBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                board[i][j] = "-";
            }
        }
    }
    private static void display(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                System.out.print("\t"+board[i][j]);
            }
            System.out.println();
        }
    }
    private static boolean isValidMove(int row, int col){
        if(board[row][col].equals("-")){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean isWin(String player) {
        boolean winFound = false;
        if(player.equalsIgnoreCase("X") || player.equalsIgnoreCase("O")){
            if(isColWin("X") || isColWin("O") || isRowWin("X") || isRowWin("O") || isDiagonalWin("X") || isDiagonalWin("O")){
                winFound = true;
            }
        }
        return winFound;
    }
    private static boolean isColWin(String player){
        boolean winFound = false;
        if(player.equalsIgnoreCase("X") || player.equalsIgnoreCase("O")){
            String col0 = "";
            String col1 = "";
            String col2 = "";
            for(int i = 0; i < COLS; i++) {
                for (int j = 0; j < ROWS; j++) {
                    if (j == 0) {
                        col0 = board[i][j];
                    }
                    if (j == 1) {
                        col1 = board[i][j];
                    }
                    if (j == 2) {
                        col2 = board[i][j];
                    }
                }
                if (col0.equals(player) && col1.equals(player) && col2.equals(player)){
                    winFound = true;
                }
            }
        }
        return winFound;
    }
    private static boolean isRowWin(String player){
        boolean winFound = false;
        if(player.equalsIgnoreCase("X") || player.equalsIgnoreCase("O")){
            String row0 = "";
            String row1 = "";
            String row2 = "";
            for(int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (j == 0) {
                        row0 = board[i][j];
                    }
                    if (j == 1) {
                        row1 = board[i][j];
                    }
                    if (j == 2) {
                        row2 = board[i][j];
                    }
                }
                if (row0.equals(player) && row1.equals(player) && row2.equals(player)){
                    winFound = true;
                }
            }
        }
        return winFound;
    }
    private static boolean isDiagonalWin(String player){
        boolean winFound = false;
        if(player.equalsIgnoreCase("X") || player.equalsIgnoreCase("O")){
            String dia00 = "";
            String dia11 = "";
            String dia22 = "";
            String dia02 = "";
            String dia20 = "";
            for(int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (i == j) {
                        if (i == 0){
                            dia00 = board[i][j];
                        }
                        if (i == 1){
                            dia11 = board[i][j];
                        }
                        if (i == 2){
                            dia22 = board[i][j];
                        }
                    }
                    else if (i == 0 && j == 2) {
                        dia02 = board[i][j];
                    }
                    else if (i == 2 && j ==0) {
                        dia20 = board[i][j];
                    }
                }
            }
            if (dia00.equals(player) && dia11.equals(player) && dia22.equals(player)){
                winFound = true;
            }
            else if (dia02.equals(player) && dia20.equals(player) && dia11.equals(player)){
                winFound = true;
            }
        }
        return winFound;
    }
    private static boolean isTie(){
        int count = 0;
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(board[i][j].equalsIgnoreCase("X") || board[i][j].equalsIgnoreCase("O")){
                    count++;
                }
            }
        }
        if(count==9){
            return true;
        }
        else{
            return false;
        }
    }
    private static void gameRules(){
        System.out.println("\n---The Rules of the Game:\n---The board is a 9x9 grid with three rows and columns, each assigned an integer from 1 through 3, as such:");
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                board[i][j] = (i+1)+""+(j+1)+" ";
            }
        }
        display();
        clearBoard();
        System.out.println("---To make a move, enter the row and column number for that move as shown above.\n");
    }
}