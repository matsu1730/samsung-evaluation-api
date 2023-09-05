package com.samsung.evaluationapi.DTO;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.samsung.evaluationapi.model.CurrencyModel;

public class CurrencyDTO {

    public ArrayList<CurrencyModel> dataToModel(JSONArray jsonArray) {
        ArrayList<CurrencyModel> currencyList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            int currencyId = object.getInt("currencyId");
            String currencyCode = object.getString("currencyCode");
            String currencyDesc = object.getString("currencyDesc");

            CurrencyModel currency = new CurrencyModel(currencyId, currencyCode, currencyDesc);
            currencyList.add(currency);
        }

        return currencyList;
    }
}
