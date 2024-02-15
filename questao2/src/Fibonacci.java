import java.util.Scanner;

public class Fibonacci {
    /**
     * Verifica se o número informado pertence à sequência de Fibonacci ou não.
     * @param number número a ser verificado
     * @return true se o número pertencer à sequência, false do contrário
     */
    private static boolean isFibonacci(int number) {
        int x = 0;
        int y = 1;
        if (number == x || number == y) return true;

        int currentFibNumber = nextFibonacci(x, y);
        while (currentFibNumber <= number) {
            if (currentFibNumber == number) return true;

            x = y;
            y = currentFibNumber;
            currentFibNumber = nextFibonacci(x, y);
        }
        return false;
    }

    /**
     * Calcula o próximo elemento da sequência de Fibonacci.
     * @param x elemento anteanterior
     * @param y elemento anterior
     * @return próximo elemento
     */
    private static int nextFibonacci(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        System.out.println("=============== QUESTÃO 2 ===============\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe um número para saber se ele pertence ou não à sequência de Fibonacci:");
        int numberToBeTested = scanner.nextInt();
        System.out.println(isFibonacci(numberToBeTested) ? "Pertence à sequência" : "Não pertence à sequência");
    }

}
