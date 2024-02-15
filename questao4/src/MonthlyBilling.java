import java.util.HashMap;

public class MonthlyBilling {
    private HashMap<String, Double> monthlyBilling;

    public MonthlyBilling(HashMap<String, Double> monthlyBilling) {
        this.monthlyBilling = monthlyBilling;
    }

    /**
     * Calcula o percentual de representação que cada estado teve dentro do valor total mensal da distribuidora.
     * Resultados apresentados com precisão de 2 casas decimais.
     */
    private void getStatePercentage() {
        double aux = 100 / getTotalBilling();

        System.out.println("Percentual de representação de cada estado:");
        for (var entry : monthlyBilling.entrySet()) {
            double stateBilling = entry.getValue() * aux;
            System.out.printf("%s: %.2f%%%n", entry.getKey(), stateBilling);
        }
    }

    /**
     * Calcula o faturamento mensal total da distribuidora.
     * @return o faturamento mensal total
     */
    private double getTotalBilling() {
        double total = 0;
        for (double x : monthlyBilling.values()) {
            total += x;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=============== QUESTÃO 4 ===============\n");

        HashMap<String, Double> monthlyBillingMap = new HashMap<>();
        monthlyBillingMap.put("SP", 67836.43);
        monthlyBillingMap.put("RJ", 36678.66);
        monthlyBillingMap.put("MG", 29229.88);
        monthlyBillingMap.put("ES", 27165.48);
        monthlyBillingMap.put("Outros", 19849.53);

        MonthlyBilling mb = new MonthlyBilling(monthlyBillingMap);
        mb.getStatePercentage();
    }
}
