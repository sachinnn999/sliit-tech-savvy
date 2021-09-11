package controller;

import exception.GameOverException;
import exception.SoundException;
import model.*;

import java.util.Arrays;

public class BoardController {
    private Object[] objects;
    private final static int xBoundry = 250;
    private final static int yBoundry = 360;
    private final static int dots = 97;
    private final static String[] superDots = {"knife", "gun", "hand"};
    private final static String[] soldiers = {"green", "red", "yellow"};

    public BoardController() {
    }

    public void initGame() {
        Object[] objectArr = new Object[1];
        objectArr[0] = new Hunter("Maha Deva", "Brown", 0, 0);
        int i;
        for (i = 0; i < dots; i++) {
            objectArr = extendArray(objectArr);
            Position temp = getRandomFreePosition(objectArr);
            objectArr[objectArr.length - 1] = new Dot(temp.getX(), temp.getY());
        }
        for (i = 0; i < superDots.length; i++) {
            objectArr = extendArray(objectArr);
            Position temp = getRandomFreePosition(objectArr);
            objectArr[objectArr.length - 1] = new SuperDot(superDots[i], temp.getX(), temp.getY());
        }
        for (i = 0; i < soldiers.length; i++) {
            objectArr = extendArray(objectArr);
            Position temp = getRandomFreePosition(objectArr);
            objectArr[objectArr.length - 1] = new Soldier(soldiers[i], temp.getX(), temp.getY());
        }
        objects = objectArr;
    }

    private  Position getRandomFreePosition(Object[] arr) {
        while (true) {
            int x = (int) (Math.random() * (xBoundry - 1)) + 1;
            int y = (int) (Math.random() * (yBoundry - 1)) + 1;
            for (int i = 0; i < arr.length; i++) {
                Position temp = (Position) arr[i];
                if (temp.getX() == x && temp.getY() == y) {
                    break;
                } else {
                    return new Position(x, y);
                }
            }
        }
    }

    public void moveHunter(int x, int y) throws SoundException, GameOverException {
        Hunter hunter = (Hunter) objects[0];
        if (hunter.getX() + x > xBoundry) {
            throw new SoundException();
        }
        if (hunter.getY() + y> yBoundry) {
            throw new SoundException();
        }
        System.out.printf("hunter is moving to x:%d y:%d \n", hunter.getX() + x, hunter.getY() + y);
        Object obj = getCurrentLocationObj(objects, hunter.getX() + x, hunter.getY() + y);
        if (obj instanceof Soldier) {
            Soldier soldier = (Soldier) obj;
            throw new GameOverException("hunter killed by " + soldier.getColour() + " soldier");
        }else if (obj instanceof SuperDot){
            System.out.println("hunting super dots");
            SuperDot superDot = (SuperDot) obj;
            killSoldier(superDot);
            removeFromArray((Position) obj);
        }else if (obj instanceof Dot){
            System.out.println("hunting dots");
            removeFromArray((Position) obj);
        }
        moveSoldiers();
        if(objects.length == 1){
            throw new GameOverException("Congrats! You won the Game");
        }
       hunter.setX(hunter.getX() + x);
       hunter.setY(hunter.getY() + y);
       objects[0] = hunter;
    }

    private void moveSoldiers() {
        for (Object obj: objects) {
            if(obj instanceof Soldier){
                Soldier soldier = (Soldier) obj;
                Position temp = getRandomFreePosition(objects);
                soldier.setX(temp.getX());
                soldier.setY(temp.getY());
            }
        }
    }

    private void killSoldier(SuperDot superDot) {
        for (Object obj: objects) {
            if(obj instanceof Soldier){
                Soldier soldier = (Soldier) obj;
                System.out.printf("hunter killed %s soldier using %s \n", soldier.getColour(), superDot.getWeapon());
                removeFromArray((Position) obj);
            }
        }
    }

    private static Object getCurrentLocationObj(Object[] objects, int x, int y) {
        for (int i = 0; i < objects.length; i++) {
            Position temp =  (Position) objects[i];
            if (temp.getX() == x && temp.getY() == y){
                return objects[i];
            }
        }
        return null;
    }

    private Object[] extendArray(Object[] arr) {
        Object[] response = Arrays.copyOf(arr, arr.length + 1);
        response[arr.length] = new Position();
        return response;
    }

    private void removeFromArray(Position obj) {
        Object[] response = new Object[objects.length - 1];
        int k = 0;
        for (int i = 0; i < objects.length; i++) {
            Position temp =  (Position) objects[i];
            if (!(temp.getX() == obj.getX() && temp.getY() == obj.getY())){
                response[k] = objects[i];
                k++;
            }
        }
        objects = response;
    }
}