package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.DTO.CurrencyDTO;
import com.samsung.evaluationapi.model.CurrencyModel;

@Service
public class CurrencyService {
    
    private String endpoint = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/currency";

    public ArrayList<CurrencyModel> listAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String responseBodyString = response.getBody();
        
        JSONArray jsonArray = new JSONArray(responseBodyString);

        CurrencyDTO dto = new CurrencyDTO();
        ArrayList<CurrencyModel> currencyList = dto.dataToModel(jsonArray);

        return currencyList;
    }

}
