package com.wallet.app.service;

import com.wallet.app.entity.Wallet;

import java.util.List;

public interface WalletService {

    public Wallet save(Wallet wallet);
    public List<Wallet> getAll();
}
