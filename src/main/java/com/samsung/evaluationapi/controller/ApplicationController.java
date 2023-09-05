package com.samsung.evaluationapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.samsung.evaluationapi.model.CurrencyModel;
import com.samsung.evaluationapi.model.DocsModel;
import com.samsung.evaluationapi.model.QuotationModel;
import com.samsung.evaluationapi.service.CurrencyService;
import com.samsung.evaluationapi.service.DocsService;
import com.samsung.evaluationapi.service.QuotationService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ApplicationController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    QuotationService quotationService;

    @Autowired
    DocsService docsService;
    
    @GetMapping(value="/currency")
    public ArrayList<CurrencyModel> getCurrency() {
        ArrayList<CurrencyModel> currencyList = currencyService.listAll();
        return currencyList;
    }
    
    @GetMapping(value="/quotation")
    public ArrayList<QuotationModel> getQuotation() {
        ArrayList<QuotationModel> quotationList = quotationService.listAll();
        return quotationList;
    }

    @GetMapping(value="/docs")
    public ArrayList<DocsModel> getDocs() {
        ArrayList<DocsModel> docsList = docsService.listAll();
        return docsList;
    }
}
