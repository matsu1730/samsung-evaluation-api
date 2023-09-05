package com.samsung.evaluationapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyModel {

    private int currencyId;
    private String currencyCode;
    private String currencyDesc;
    
}
