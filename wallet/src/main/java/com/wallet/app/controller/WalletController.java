package com.wallet.app.controller;

import com.wallet.app.entity.Wallet;
import com.wallet.app.service.WalletService;
import com.wallet.app.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.WALLET_PATH)
public class WalletController {
    WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public Wallet save(@RequestBody Wallet wallet){
        return walletService.save(wallet);
    }

    @GetMapping
    public List<Wallet> getAll(){
        return walletService.getAll();
    }
}
