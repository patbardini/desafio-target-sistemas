import java.util.Scanner;

public class ReverseString {
    /**
     * Recebe uma string e inverte seus caracteres.
     * @param str string a ser invertida
     * @return string invertida
     */
    public static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            reversed.insert(0, str.charAt(i));
        }
        return reversed.toString();
    }

    public static void main(String[] args) {
        System.out.println("=============== QUESTÃO 5 ===============\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe uma string para inverter:");
        String str = scanner.nextLine();
        System.out.println(reverse(str));
    }
}
