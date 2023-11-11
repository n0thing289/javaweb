package exceptions;

public class NotEnoughBalanceException extends Exception{
    public NotEnoughBalanceException() {
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
