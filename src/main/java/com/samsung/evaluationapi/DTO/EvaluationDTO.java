package com.samsung.evaluationapi.DTO;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.samsung.evaluationapi.model.EvaluationModel;

public class EvaluationDTO {
    
    private static final DecimalFormat decfor = new DecimalFormat("0.00"); 

    public ArrayList<EvaluationModel> dataToModelByCurrencyCode(String responseCurrency, String responseQuotation, String responseDocs, String currencyCodeParam) {
        ArrayList<EvaluationModel> evaluationList = new ArrayList<>();
        String currencyCode = "";
        String currencyDesc = "";

        JSONArray currencyJsonArray = new JSONArray(responseCurrency);
        JSONArray quotationJsonArray = new JSONArray(responseQuotation);
        JSONArray docsJsonArray = new JSONArray(responseDocs);

        for (int i = 0; i < currencyJsonArray.length(); i++) {
            JSONObject object = currencyJsonArray.getJSONObject(i);
            if (object.getString("currencyCode").equalsIgnoreCase(currencyCodeParam)) {
                currencyCode = object.getString("currencyCode");
                currencyDesc = object.getString("currencyDesc");
            }
        }

        for (int i = 0; i < docsJsonArray.length(); i++) {
            EvaluationModel evaluation = new EvaluationModel();
            evaluation.setCurrencyCode(currencyCode);
            evaluation.setCurrencyDesc(currencyDesc);

            JSONObject object = docsJsonArray.getJSONObject(i);
            if (object.getString("currencyCode").equalsIgnoreCase(currencyCode)) {
                evaluation.setDocumentNumber(object.getString("documentNumber"));
                evaluation.setDocumentDate(object.getString("documentDate"));
                evaluation.setDocumentValue(object.getDouble("documentValue"));
                
                switch (currencyCodeParam) {
                    case "USD":
                        evaluation.setUsdValue(evaluation.getDocumentValue());
                        break;
                    case "PEN":
                        evaluation.setPenValue(evaluation.getDocumentValue());
                        break;
                    case "BRL":
                        evaluation.setBrlValue(evaluation.getDocumentValue());
                        break;
                }

                evaluationList.add(evaluation);
            }
        }

        for (int i = 0; i < quotationJsonArray.length(); i++) {
            JSONObject object = quotationJsonArray.getJSONObject(i);
            switch (currencyCodeParam) {
                case "USD":
                    if (object.getString("fromCurrencyCode").equalsIgnoreCase(currencyCodeParam)) {
                        for (EvaluationModel evaluation : evaluationList) {
                            ArrayList<Double> listaResultadosCalculo = new ArrayList<>();
                            listaResultadosCalculo = calculaCotacao(evaluation, object);

                            double penValue = listaResultadosCalculo.get(0);
                            double brlValue = listaResultadosCalculo.get(1);

                            if (penValue != 0.0) {
                                evaluation.setPenValue((double) Math.round(penValue * 100) / 100);
                            }
                            if (brlValue != 0.0) {
                                evaluation.setBrlValue((double) Math.round(brlValue * 100) / 100);
                            }
                        }
                    }
                    break;
                case "PEN":
                    if (object.getString("fromCurrencyCode").equalsIgnoreCase(currencyCodeParam)) {
                        for (EvaluationModel evaluation : evaluationList) {
                            ArrayList<Double> listaResultadosCalculo = new ArrayList<>();
                            listaResultadosCalculo = calculaCotacao(evaluation, object);

                            double brlValue = listaResultadosCalculo.get(1);
                            double usdValue = listaResultadosCalculo.get(2);

                                if (usdValue != 0.0) {
                                    evaluation.setUsdValue((double) Math.round(usdValue * 100) / 100);
                                }
                                if (brlValue != 0.0) {
                                    evaluation.setBrlValue((double) Math.round(brlValue * 100) / 100);
                                }
                        }
                    }
                    break;
                case "BRL":
                    if (object.getString("fromCurrencyCode").equalsIgnoreCase(currencyCodeParam)) {
                        for (EvaluationModel evaluation : evaluationList) {
                            ArrayList<Double> listaResultadosCalculo = new ArrayList<>();
                            listaResultadosCalculo = calculaCotacao(evaluation, object);

                            double penValue = listaResultadosCalculo.get(0);
                            double usdValue = listaResultadosCalculo.get(2);
                                if (penValue != 0.0) {
                                    evaluation.setPenValue((double) Math.round(penValue * 100) / 100);
                                }
                                if (usdValue != 0.0) {
                                    evaluation.setUsdValue((double) Math.round(usdValue * 100) / 100);
                                }
                        }
                    }
                    break;
            }
        }

        return evaluationList;

    }

    private ArrayList<Double> calculaCotacao(EvaluationModel evaluation, JSONObject object) {
        double penValue = 0.0;
        double brlValue = 0.0;
        double usdValue = 0.0;
        ArrayList<Double> resultadosCalculo = new ArrayList<>();

        if (evaluation.getDocumentDate().equalsIgnoreCase(object.getString("dataHoraCotacao"))) {
            if (object.getString("toCurrencyCode").equalsIgnoreCase("PEN")) {
                double cotacao = object.getDouble("cotacao");
                penValue = evaluation.getDocumentValue() * cotacao;
            }
            else if (object.getString("toCurrencyCode").equalsIgnoreCase("BRL")) {
                double cotacao = object.getDouble("cotacao");
                brlValue = evaluation.getDocumentValue() * cotacao;
            }
            else if (object.getString("toCurrencyCode").equalsIgnoreCase("USD")) {
                double cotacao = object.getDouble("cotacao");
                usdValue = evaluation.getDocumentValue() * cotacao;
            }
        }
        resultadosCalculo.add(0, penValue);
        resultadosCalculo.add(1, brlValue);
        resultadosCalculo.add(2, usdValue);

        return resultadosCalculo;
    }
}
