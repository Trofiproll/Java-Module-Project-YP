import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Scanner in = new Scanner(System.in);
        System.out.println("На сколько человек нужно разделить счёт?");
        int guestsNumber;
        while(true) {
            if (in.hasNextInt()) {
                guestsNumber = in.nextInt();
                if(guestsNumber > 1) break;
                else System.out.println("Введено число меньше 2. Повторите попытку");
            } else if (in.hasNext()) {
                System.out.println("Некорректный ввод. Повторите попытку");
                in.next();
            }
        }

        Calculator bill = new Calculator(guestsNumber);
        bill.addProducts();
        bill.calcutate();
    }
}