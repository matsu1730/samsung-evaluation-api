package com.samsung.evaluationapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.samsung.evaluationapi.model.CurrencyModel;
import com.samsung.evaluationapi.service.CurrencyService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ApplicationController {

    @Autowired
    CurrencyService currencyService;
    
    @GetMapping(value="/currency")
    public ArrayList<CurrencyModel> getCurrency() {
        ArrayList<CurrencyModel> currencyList = currencyService.listAll();
        return currencyList;
    }
    
}
