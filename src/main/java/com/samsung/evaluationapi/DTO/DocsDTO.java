package com.samsung.evaluationapi.DTO;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.samsung.evaluationapi.model.DocsModel;

public class DocsDTO {
    
    public ArrayList<DocsModel> dataToModel(JSONArray jsonArray) {
        ArrayList<DocsModel> docsList = new ArrayList<>();
        
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
