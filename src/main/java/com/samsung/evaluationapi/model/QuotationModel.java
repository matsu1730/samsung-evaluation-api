package com.samsung.evaluationapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuotationModel {
    
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private String cotacao;
    private String dataHoraCotacao;

}
