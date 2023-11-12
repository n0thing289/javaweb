package service;

import exceptions.AppException;
import exceptions.NotEnoughMoneyException;

public interface AccountService {
    void transfer(String fromActNo, String toActNo, double money) throws NotEnoughMoneyException, AppException;
}
