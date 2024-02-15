import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class DailyBilling {
    private HashMap<Integer, Double> dailyBilling;

    public DailyBilling(String filename) {
        readDataFromJSON(filename);
    }

    /**
     * Calcula o número de dias no mês em que o valor de faturamento diário foi superior à média mensal.
     * @return número de dias
     */
    private int numOfDaysHigherThanAverage() {
        int count = 0;
        double average = getBillingAverage();
        ArrayList<Double> arrayList = getValuesInAscendingOrder();
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (arrayList.get(i) <= average) break;
            count++;
        }
        return count;
    }

    /**
     * Calcula a média mensal do faturamento.
     * @return a média mensal
     */
    private double getBillingAverage() {
        double sum = 0;
        for (double x : dailyBilling.values()) {
            sum += x;
        }
        return sum / dailyBilling.size();
    }

    /**
     * Obtém o maior valor de faturamento ocorrido em um dia do mês.
     * @return o valor do respectivo faturamento
     */
    private double getHighestBilling() {
        ArrayList<Double> values = getValuesInAscendingOrder();
        return values.get(values.size() - 1);
    }

    /**
     * Obtém o menor valor de faturamento ocorrido em um dia do mês.
     * @return o valor do respectivo faturamento
     */
    private double getLowestBilling() {
        ArrayList<Double> values = getValuesInAscendingOrder();
        return values.get(0);
    }

    /**
     * Organiza os valores de faturamento em ordem crescente.
     * @return a lista contendo os valores em ordem crescente
     */
    private ArrayList<Double> getValuesInAscendingOrder() {
        ArrayList<Double> valuesList = new ArrayList<>(dailyBilling.values());
        Collections.sort(valuesList);
        return valuesList;
    }

    /**
     * Lê os dados do faturamento mensal do arquivo json e armazena no HashMap dailyBilling.
     * Dias sem faturamento são ignorados conforme o recomendado pelo enunciado da questão.
     * @param filename nome do arquivo fonte de dados
     */
    private void readDataFromJSON(String filename) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(Objects.requireNonNull(DailyBilling.class.getClassLoader().getResource(filename)).getFile());
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            this.dailyBilling = new HashMap<>();
            for (Object object : jsonArray) {
                JSONObject billingContent = (JSONObject) object;
                double value = (double) billingContent.get("valor");
                if (value > 0) {
                    Number day = (Number) billingContent.get("dia");
                    this.dailyBilling.put(day.intValue(), value);
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler arquivo JSON: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DailyBilling db = new DailyBilling("dados.json");

        System.out.println("=============== QUESTÃO 3 ===============\n");
        System.out.printf("Menor valor de faturamento: R$%.2f%n", db.getLowestBilling());
        System.out.printf("Maior valor de faturamento: R$%.2f%n", db.getHighestBilling());
        System.out.printf("Número de dias no mês em que o valor de faturamento diário foi superior à média mensal: %d dia(s)%n", db.numOfDaysHigherThanAverage());
    }
}
