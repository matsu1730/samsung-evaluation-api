package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.model.QuotationModel;

@Service
public class QuotationService {
    
    private String endpoint = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/quotation";

    public ArrayList<QuotationModel> listAll() {
        ArrayList<QuotationModel> quotationList = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String responseBodyString = response.getBody();
        
        JSONArray jsonArray = new JSONArray(responseBodyString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String fromCurrencyCode = object.getString("fromCurrencyCode");
            String toCurrencyCode = object.getString("toCurrencyCode");
            String cotacao = object.getString("cotacao");
            String dataHoraCotacao = object.getString("dataHoraCotacao");
            
            QuotationModel quotation = new QuotationModel(fromCurrencyCode, toCurrencyCode, cotacao, dataHoraCotacao);
            quotationList.add(quotation);
        }

        return quotationList;
    }

}
