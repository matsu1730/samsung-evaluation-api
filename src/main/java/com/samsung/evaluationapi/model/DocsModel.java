package com.samsung.evaluationapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocsModel {
    
    private int documentId;
    private String documentNumber;
    private String notaFiscal;
    private String documentDate;
    private double documentValue;
    private String currencyCode;

}
