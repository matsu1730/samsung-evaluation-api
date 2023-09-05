package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.model.CurrencyModel;

@Service
public class CurrencyService {
    
    private String endpoint = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/currency";

    public ArrayList<CurrencyModel> listAll() {
        ArrayList<CurrencyModel> currencyList = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String responseBodyString = response.getBody();
        JSONObject responseBodyJson = new JSONObject(responseBodyString);
        
        JSONArray jsonArray = responseBodyJson.getJSONArray("");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String currencyId = object.getString("currencyId");
            String currencyCode = object.getString("currencyCode");
            String currencyDesc = object.getString("currencyDesc");

            CurrencyModel currency = new CurrencyModel(currencyId, currencyCode, currencyDesc);
            currencyList.add(currency);
        }

        return currencyList;
    }

}
