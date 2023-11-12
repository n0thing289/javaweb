package exceptions;

/**
 * 余额不足异常
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
