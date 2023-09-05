package com.samsung.evaluationapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationModel {
    
    private String documentNumber;
    private String documentDate;
    private String currencyCode;
    private String currencyDesc;
    private double documentValue;
    private double usdValue;
    private double penValue;
    private double brlValue;
    
}
