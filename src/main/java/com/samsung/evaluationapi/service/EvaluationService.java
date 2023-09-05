package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.DTO.EvaluationDTO;
import com.samsung.evaluationapi.model.EvaluationModel;

@Service
public class EvaluationService {
    
    private String endpointCurrency = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/currency";
    private String endpointQuotation = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/quotation";
    private String endpointDocs = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/docs";

    public ArrayList<EvaluationModel> getEvaluationByCurrencyCode(String currencyCodeParam) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseCurrency = template.getForEntity(endpointCurrency, String.class);
        String responseCurrencyBodyString = responseCurrency.getBody();

        ResponseEntity<String> responseQuotation = template.getForEntity(endpointQuotation, String.class);
        String responseQuotationBodyString = responseQuotation.getBody();

        ResponseEntity<String> responseDocs = template.getForEntity(endpointDocs, String.class);
        String responseDocsBodyString = responseDocs.getBody();

        EvaluationDTO dto = new EvaluationDTO();
        ArrayList<EvaluationModel> evaluation = dto.dataToModelByCurrencyCode(responseCurrencyBodyString, responseQuotationBodyString, responseDocsBodyString, currencyCodeParam);

        return evaluation;
    }
}
