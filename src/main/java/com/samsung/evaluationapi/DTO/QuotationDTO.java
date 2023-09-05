package com.samsung.evaluationapi.DTO;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.samsung.evaluationapi.model.QuotationModel;

public class QuotationDTO {
    
    public ArrayList<QuotationModel> dataToModel(JSONArray jsonArray) {
        ArrayList<QuotationModel> quotationList = new ArrayList<>();

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
