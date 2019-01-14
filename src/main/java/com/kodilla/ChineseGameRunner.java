package com.kodilla;


import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ChineseGameRunner extends Application {
    private Image imageback = new Image("static/board.png");
    private Image pawnBlue = new Image("static/pawnBlue.png");
    private Image pawnRed = new Image("static/pawnRed.png");
    private Image pawnGreen = new Image("static/pawnGreen.png");
    private Image pawnYellow = new Image("static/pawnYellow.png");
    private Image pawnTransparent = new Image("static/pawnTransparent.png");
    private Label cubeDigit = new Label("Liczba oczek");
    private Label currentPlayerLabel = new Label();
    private Label winnersListLabel = new Label();

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100,100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);

        cubeDigit.setTextFill(Color.web("#FFF"));
        cubeDigit.setFont(new Font("Arial", 24));

        currentPlayerLabel.setTextFill(Color.web("#FFF"));
        currentPlayerLabel.setFont(new Font("Arial", 24));

        winnersListLabel.setTextFill(Color.web("#FFF"));
        winnersListLabel.setFont(new Font("Arial", 24));

        Player player1 = new Player();
        player1.setName("Red");
        LinkedList<Integer> player1PositionsInPath = new LinkedList<>(Arrays.asList(47,48,49,50,51,40,29,18,7,8,9,20,31,42,53,54,55,56,57,68,79,78,77,76,75,86,97,108,119,118,117,106,95,84,73,72,71,70,69,58));
        player1.setPathOnBoard(player1PositionsInPath);
        LinkedList<Integer> player1PawnsHomePositions = new LinkedList<>(Arrays.asList(59,60,61,62));
        player1.setPawnsHomePositions(player1PawnsHomePositions);
        LinkedList<Integer> player1PawnsInTray = new LinkedList<>(Arrays.asList(3,4,14,15));
        player1.setPawnsInTray(player1PawnsInTray);
        player1.setPawnColor(pawnRed);

        Player player2 = new Player();
        player2.setName("Blue");
        LinkedList<Integer> player2PositionsInPath = new LinkedList<>(Arrays.asList(9,20,31,42,53,54,55,56,57,68,79,78,77,76,75,86,97,108,119,118,117,106,95,84,73,72,71,70,69,58,47,48,49,50,51,40,29,18,7,8));
        player2.setPathOnBoard(player2PositionsInPath);
        LinkedList<Integer> player2PawnsHomePositions = new LinkedList<>(Arrays.asList(19,30,41,52));
        player2.setPawnsHomePositions(player2PawnsHomePositions);
        LinkedList<Integer> player2PawnsInTray = new LinkedList<>(Arrays.asList(12,13,23,24));
        player2.setPawnsInTray(player2PawnsInTray);
        player2.setPawnColor(pawnBlue);

        Player player3 = new Player();
        player3.setName("Green");
        LinkedList<Integer> player3PositionsInPath = new LinkedList<>(Arrays.asList(79,78,77,76,75,86,97,107,119,118,117,106,95,84,73,72,71,70,69,58,47,48,49,50,51,40,29,18,7,8,9,20,31,42,53,54,55,56,57,68));
        player3.setPathOnBoard(player3PositionsInPath);
        LinkedList<Integer> player3PawnsHomePositions = new LinkedList<>(Arrays.asList(67,66,65,64));
        player3.setPawnsHomePositions(player3PawnsHomePositions);
        LinkedList<Integer> player3PawnsInTray = new LinkedList<>(Arrays.asList(111,112,122,123));
        player3.setPawnsInTray(player3PawnsInTray);
        player3.setPawnColor(pawnGreen);

        Player player4 = new Player();
        player4.setName("Yellow");
        LinkedList<Integer> player4PositionsInPath = new LinkedList<>(Arrays.asList(117,106,95,84,73,72,71,70,69,58,47,48,49,50,51,40,29,18,7,8,9,20,31,42,53,54,55,56,57,68,79,78,77,76,75,86,97,108,119,118));
        player4.setPathOnBoard(player4PositionsInPath);
        LinkedList<Integer> player4PawnsHomePositions = new LinkedList<>(Arrays.asList(107,96,85,74));
        player4.setPawnsHomePositions(player4PawnsHomePositions);
        LinkedList<Integer> player4PawnsInTray = new LinkedList<>(Arrays.asList(102,103,113,114));
        player4.setPawnsInTray(player4PawnsInTray);
        player4.setPawnColor(pawnYellow);

        HashMap<Player, Integer> pawnsOnBoard = new HashMap<>();
        LinkedList<Player> winnersList = new LinkedList<>();

        Board board = new Board();
        board.addPlayerToList(player1);
        board.addPlayerToList(player2);
        board.addPlayerToList(player3);
        board.addPlayerToList(player4);
        board.setCurrentPlayer(player1);
        board.setNumberOfTries(3);
        currentPlayerLabel.setText(board.getCurrentPlayer().getName());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(29, 25, 29, 25));
        grid.setHgap(29.5);
        grid.setVgap(29.5);
        grid.setBackground(background);

        Button throwCubeBT = new Button();
        throwCubeBT.setText("Rzut kostką");
        throwCubeBT.setOnAction((e)->{
            Integer cube;
            if(pawnsOnBoard.containsKey(board.getCurrentPlayer())){
                cube = board.throwCube();
                cubeDigit.setText("Liczba oczek " + String.valueOf(cube));
                board.movePawnAtNewPosition(cube, grid, board.getCurrentPlayer(),pawnsOnBoard, winnersList);
                if(cube < 6){
                    board.setCurrentPlayer(board.getNextPlayer(board.getPlayers(), board.getCurrentPlayer()));
                    currentPlayerLabel.setText(board.getCurrentPlayer().getName());
                }
            } else {
                if(board.getPlayers().size() > 0){
                    if (board.getNumberOfTries() > 1 ){
                        cube = board.throwCube();
                        cubeDigit.setText("Liczba oczek " + String.valueOf(cube));
                        if(cube == 6){
                            board.insertPawnOnBoard(grid, board.getCurrentPlayer(), pawnsOnBoard);
                            board.setNumberOfTries(3);
                        } else {
                            board.setNumberOfTries(board.getNumberOfTries()-1);
                        }
                    } else {
                        cube = board.throwCube();
                        cubeDigit.setText("Liczba oczek " + String.valueOf(cube));
                        if(cube == 6){
                            board.insertPawnOnBoard(grid, board.getCurrentPlayer(), pawnsOnBoard);
                            board.setNumberOfTries(3);
                        } else {
                            board.setCurrentPlayer(board.getNextPlayer(board.getPlayers(), board.getCurrentPlayer()));
                            currentPlayerLabel.setText(board.getCurrentPlayer().getName());
                            board.setNumberOfTries(3);
                        }
                    }
                } else {
                    currentPlayerLabel.setText("Koniec gry");
                    cubeDigit.setText("");
                }
            }
            if(winnersList.size() > 0){
                winnersListLabel.setText("");
                for(int i = 0; i < winnersList.size(); i++){
                    winnersListLabel.setText(winnersListLabel.getText() + "\n" + (i+1) + " " + winnersList.get(i).getName());
                }
            }
        });

        grid.add(throwCubeBT,12,2,1,1 );
        grid.add(cubeDigit,12,5,1,1 );
        grid.add(currentPlayerLabel,12,4,1,1 );

        board.initializeBoard(grid, player1, player2, player3, player4, pawnsOnBoard);
        grid.add(winnersListLabel, 12, 6, 1,3);
        //grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 1200, 804, Color.BLACK);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Chinese Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}