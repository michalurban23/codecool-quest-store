package com.codecool.rmbk.model;

import com.codecool.rmbk.dao.SQLBacklog;

public class Wallet {

    private int id;
    private SQLBacklog sqlBacklog = new SQLBacklog();

    public Wallet(Integer id) {
        this.id = id;
    }

    public int getCoins() {

        return sqlBacklog.getCurrentCoins(id);
    }

    public void updateWallet(String[] data) {

        sqlBacklog.saveToBacklog(data);
    }
}
