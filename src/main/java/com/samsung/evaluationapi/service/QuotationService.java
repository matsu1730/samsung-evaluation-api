package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.DTO.QuotationDTO;
import com.samsung.evaluationapi.model.QuotationModel;

@Service
public class QuotationService {
    
    private String endpoint = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/quotation";

    public ArrayList<QuotationModel> listAll() {
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String responseBodyString = response.getBody();
        
        JSONArray jsonArray = new JSONArray(responseBodyString);
        QuotationDTO dto = new QuotationDTO();
        ArrayList<QuotationModel> quotationList = dto.dataToModel(jsonArray);

        return quotationList;
    }

}
