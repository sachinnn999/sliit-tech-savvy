package controller;

import exception.GameOverException;
import exception.SoundException;

import java.util.InputMismatchException;
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
            try {
                int x = scanner.nextInt();
                scanner = new Scanner(System.in);
                int y = scanner.nextInt();
                myBoard.moveHunter(x, y);
            }catch (SoundException ignored){
            }catch (GameOverException go){
                break;
            }catch (InputMismatchException e){
                System.out.println("Numbers are only accepted!!");
            }catch (Exception e){
                System.out.println("Something went wrong! Try again Later!");
            }
        }
    }

}
