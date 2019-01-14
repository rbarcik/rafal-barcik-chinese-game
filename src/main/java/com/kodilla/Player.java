package com.kodilla;

import javafx.scene.image.Image;

import java.util.LinkedList;

public class Player {
    private String name;
    private Image pawnColor;
    private LinkedList<Integer> pathOnBoard;
    private LinkedList<Integer> pawnsInHome = new LinkedList<>();
    private LinkedList<Integer> pawnsHomePositions;
    private LinkedList<Integer> pawnsInTray;

    public LinkedList<Integer> getPawnsInTray() {
        return pawnsInTray;
    }

    public void setPawnsInTray(LinkedList<Integer> pawnsInTray) {
        this.pawnsInTray = pawnsInTray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Integer> getPathOnBoard() {
        return pathOnBoard;
    }

    public void setPathOnBoard(LinkedList<Integer> pathOnBoard) {
        this.pathOnBoard = pathOnBoard;
    }

    public LinkedList<Integer> getPawnsInHome() {
        return pawnsInHome;
    }

    public void setPawnsInHome(LinkedList<Integer> pawnsInHome) {
        this.pawnsInHome = pawnsInHome;
    }

    public LinkedList<Integer> getPawnsHomePositions() {
        return pawnsHomePositions;
    }

    public void setPawnsHomePositions(LinkedList<Integer> pawnsHomePositions) {
        this.pawnsHomePositions = pawnsHomePositions;
    }
    public void addPositionsToPath(Integer position) {
        this.pathOnBoard.add(position);
    }

    public Image getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(Image pawnColor) {
        this.pawnColor = pawnColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (pawnColor != null ? !pawnColor.equals(player.pawnColor) : player.pawnColor != null) return false;
        if (pathOnBoard != null ? !pathOnBoard.equals(player.pathOnBoard) : player.pathOnBoard != null) return false;
        if (pawnsInHome != null ? !pawnsInHome.equals(player.pawnsInHome) : player.pawnsInHome != null) return false;
        if (pawnsHomePositions != null ? !pawnsHomePositions.equals(player.pawnsHomePositions) : player.pawnsHomePositions != null)
            return false;
        return pawnsInTray != null ? pawnsInTray.equals(player.pawnsInTray) : player.pawnsInTray == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pawnColor != null ? pawnColor.hashCode() : 0);
        result = 31 * result + (pathOnBoard != null ? pathOnBoard.hashCode() : 0);
        result = 31 * result + (pawnsInHome != null ? pawnsInHome.hashCode() : 0);
        result = 31 * result + (pawnsHomePositions != null ? pawnsHomePositions.hashCode() : 0);
        result = 31 * result + (pawnsInTray != null ? pawnsInTray.hashCode() : 0);
        return result;
    }
}
