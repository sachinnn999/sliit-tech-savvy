package controller;

import exception.GameOverException;
import exception.SoundException;

import java.util.Scanner;

public class MainApp {


    public static void main(String[] args){
        System.out.println("Welcome to TechSavvy");
        BoardController myBoard = new BoardController();
        myBoard.initGame();
        Scanner scanner;
        while (true){
            System.out.println("X: Y: pls?");
            scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            scanner = new Scanner(System.in);
            int y = scanner.nextInt();
            try {
                myBoard.moveHunter(x, y);
            }catch (SoundException ignored){
            }catch (GameOverException go){
                break;
            }
        }
    }

}
