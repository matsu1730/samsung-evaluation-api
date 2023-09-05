package com.samsung.evaluationapi.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samsung.evaluationapi.DTO.DocsDTO;
import com.samsung.evaluationapi.model.DocsModel;

@Service
public class DocsService {
    
    private String endpoint = "https://sdshealthcheck.cellologistics.com.br/sds-devs-evaluation/evaluation/docs";

    public ArrayList<DocsModel> listAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String responseBodyString = response.getBody();
        
        JSONArray jsonArray = new JSONArray(responseBodyString);
        DocsDTO dto = new DocsDTO();
        ArrayList<DocsModel> docsList = dto.dataToModel(jsonArray);

        return docsList;
    }

}
