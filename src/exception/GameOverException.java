package exception;

public class GameOverException extends Exception{
    public GameOverException(String message) {
        System.out.println(message);
    }
}
