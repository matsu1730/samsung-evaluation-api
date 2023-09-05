package com.samsung.evaluationapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.samsung.evaluationapi.model.EvaluationModel;
import com.samsung.evaluationapi.service.EvaluationService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;

    @GetMapping("/getByCurrencyCode/{currencyCode}")
    public ArrayList<EvaluationModel> getEvaluationByCurrencyCode(@PathVariable("currencyCode") String currencyCodeParam) {
        ArrayList<EvaluationModel> evaluation = evaluationService.getEvaluationByCurrencyCode(currencyCodeParam);
        return evaluation;
    }
    
}
