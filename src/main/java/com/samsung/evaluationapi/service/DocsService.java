package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.model.DocsModel;

@Service
public class DocsService {
    
    private String endpoint = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/docs";

    public ArrayList<DocsModel> listAll() {
        ArrayList<DocsModel> docsList = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String responseBodyString = response.getBody();
        
        JSONArray jsonArray = new JSONArray(responseBodyString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            int documentId = object.getInt("documentId");
            String documentNumber = object.getString("documentNumber");
            String notaFiscal = object.getString("notaFiscal");
            String documentDate = object.getString("documentDate");
            double documentValue = object.getDouble("documentValue");
            String currencyCode = object.getString("currencyCode");

            DocsModel docs = new DocsModel(documentId, documentNumber, notaFiscal, documentDate, documentValue, currencyCode);
            docsList.add(docs);
        }

        return docsList;
    }

}
