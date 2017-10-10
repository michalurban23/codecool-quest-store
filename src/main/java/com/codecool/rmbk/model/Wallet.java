package com.codecool.rmbk.model;

import com.codecool.rmbk.dao.SQLBacklog;

public class Wallet {

    private int coins;
    private SQLBacklog sqlBacklog = new SQLBacklog();

    public Wallet(int id) {
        this.coins = sqlBacklog.getCurrentCoins(id);
    }

    public int getCoins() {

        return this.coins;
    }
}
