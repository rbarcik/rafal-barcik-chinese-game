package com.kodilla;

import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.*;

public class Board {


    private Image pawnTransparent = new Image("static/pawnTransparent.png");
    private FlowPane pawnBlue1 = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane pawnRed1 = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane pawnGreen1 = new FlowPane(Orientation.HORIZONTAL);
    private Player currentPlayer;
    private LinkedList<Player> players = new LinkedList<>();
    private Integer numberOfTries;

    public Integer getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(Integer numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addPlayerToList(Player player){
        players.add(player);
    }


    public void initializeBoard(GridPane grid, Player player1,Player player2, Player player3, Player player4, HashMap<Player, Integer> pawnsOnBoard){
        for(int k = 0; k < 11; k++){
            for(int i  = 0; i < 11; i++){
                ImageView imgPawnTransparent = new ImageView(pawnTransparent);
                FlowPane pawnTransp = new FlowPane(Orientation.VERTICAL);
                pawnTransp.getChildren().add(imgPawnTransparent);
                grid.add(pawnTransp, i, k, 1, 1);
            }
        }
        for(int i = 0; i < player1.getPawnsInTray().size(); i++){
            ((FlowPane) grid.getChildren().get(player1.getPawnsInTray().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player1.getPawnsInTray().get(i))).getChildren().add(new ImageView(player1.getPawnColor()));
        }
        for(int i = 0; i < player1.getPawnsInHome().size(); i++){
            ((FlowPane) grid.getChildren().get(player1.getPawnsInHome().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player1.getPawnsInHome().get(i))).getChildren().add(new ImageView(player1.getPawnColor()));
        }

        for(int i = 0; i < player2.getPawnsInTray().size(); i++){
            ((FlowPane) grid.getChildren().get(player2.getPawnsInTray().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player2.getPawnsInTray().get(i))).getChildren().add(new ImageView(player2.getPawnColor()));
        }
        for(int i = 0; i < player2.getPawnsInHome().size(); i++){
            ((FlowPane) grid.getChildren().get(player2.getPawnsInHome().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player2.getPawnsInHome().get(i))).getChildren().add(new ImageView(player2.getPawnColor()));
        }

        for(int i = 0; i < player3.getPawnsInTray().size(); i++){
            ((FlowPane) grid.getChildren().get(player3.getPawnsInTray().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player3.getPawnsInTray().get(i))).getChildren().add(new ImageView(player3.getPawnColor()));
        }
        for(int i = 0; i < player3.getPawnsInHome().size(); i++){
            ((FlowPane) grid.getChildren().get(player3.getPawnsInHome().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player3.getPawnsInHome().get(i))).getChildren().add(new ImageView(player3.getPawnColor()));
        }

        for(int i = 0; i < player4.getPawnsInTray().size(); i++){
            ((FlowPane) grid.getChildren().get(player4.getPawnsInTray().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player4.getPawnsInTray().get(i))).getChildren().add(new ImageView(player4.getPawnColor()));
        }
        for(int i = 0; i < player4.getPawnsInHome().size(); i++){
            ((FlowPane) grid.getChildren().get(player4.getPawnsInHome().get(i))).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(player4.getPawnsInHome().get(i))).getChildren().add(new ImageView(player4.getPawnColor()));
        }
    }

    public Boolean movePawnAtNewPosition(Integer cube, GridPane grid, Player player1, HashMap<Player, Integer> pawnsOnBoard, LinkedList<Player> winnersList){

        Integer playerActualGridPosition = pawnsOnBoard.get(player1);
        Integer playerActualStep = player1.getPathOnBoard().indexOf(playerActualGridPosition);
        Integer playerNewStep = playerActualStep + cube;
        if(playerNewStep <= player1.getPathOnBoard().size()-1){
            Integer playerNewGridPosition = player1.getPathOnBoard().get(playerNewStep);
            pawnsOnBoard.replace(player1, playerNewGridPosition);
            ((FlowPane) grid.getChildren().get(playerActualGridPosition)).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(playerActualGridPosition)).getChildren().add(new ImageView(pawnTransparent));
            ((FlowPane) grid.getChildren().get(playerNewGridPosition)).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(playerNewGridPosition)).getChildren().add(new ImageView(player1.getPawnColor()));
            return true;

        } else {
            pawnsOnBoard.remove(player1);
            ((FlowPane) grid.getChildren().get(playerActualGridPosition)).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(playerActualGridPosition)).getChildren().add(new ImageView(pawnTransparent));
            Integer pawnHomePosition = player1.getPawnsHomePositions().getLast();
            player1.getPawnsInHome().add(pawnHomePosition);
            player1.getPawnsHomePositions().remove(pawnHomePosition);
            ((FlowPane) grid.getChildren().get(pawnHomePosition)).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(pawnHomePosition)).getChildren().add(new ImageView(player1.getPawnColor()));
            if(player1.getPawnsInHome().size() == 4){
                winnersList.add(player1);
                players.remove(player1);
            }
            return false;
        }
    }

    public Integer throwCube(){
        Integer cube = 0;
        Random randomGenerator = new Random();
        cube = randomGenerator.nextInt(6)+1;
        return cube;
    }


    public Player getNextPlayer(LinkedList<Player> players, Player player){
        int playerIndex = players.indexOf(player);
        if(playerIndex < players.size()-1){
            return players.get(playerIndex + 1);
        } else {
            if(players.size() > 0){
                return players.get(0);
            } else {
                return player;
            }
        }
    }

    public void insertPawnOnBoard(GridPane grid, Player player, HashMap<Player, Integer> pawnsOnBoard){
        if(player.getPawnsInTray().size() > 0){
            Integer pawnInTray = player.getPawnsInTray().getLast();
            player.getPawnsInTray().remove(pawnInTray);
            ((FlowPane) grid.getChildren().get(pawnInTray)).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(pawnInTray)).getChildren().add(new ImageView(pawnTransparent));
            Integer playerStartPosition = player.getPathOnBoard().get(0);
            pawnsOnBoard.put(player, playerStartPosition);
            ((FlowPane) grid.getChildren().get(playerStartPosition)).getChildren().remove(0,1);
            ((FlowPane) grid.getChildren().get(playerStartPosition)).getChildren().add(new ImageView(player.getPawnColor()));
        }
    }
}
