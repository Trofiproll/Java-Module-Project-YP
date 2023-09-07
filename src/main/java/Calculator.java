import java.util.Scanner;

public class Calculator {
    private final int guestsNumber;
    private int productsNumber;
    private double[] prices;
    private String[] products;


    Calculator(int guestsNumber){
        this.guestsNumber = guestsNumber;
        productsNumber = 0;
        prices = new double[10];
        products = new String[10];
        System.out.printf("Счёт на %d человек создан!\nДобавляйте товары в формате \"Товар рубли.копейки\". Для завершения добавления введите \"Завершить\".\n", guestsNumber);
    }

    void addProducts(){
        Scanner in = new Scanner(System.in);
        String inputString = in.next();
        double inputDouble;

        while(!inputString.equalsIgnoreCase("Завершить")){
            if(productsNumber >= products.length) update();

            while(true) {
                if(in.hasNextDouble()){
                    inputDouble = in.nextDouble();
                    if(inputDouble < 0){
                        System.out.printf("Введено число меньше нуля. Введите стоимость товара \"%s\" ещё раз.\n", inputString);
                        continue;
                    }
                    break;
                }
                else if(in.hasNext()){
                    System.out.printf("Это не число. Введите стоимость товара \"%s\" ещё раз.\n", inputString);
                    in.next();
                }
            }

            products[productsNumber] = inputString;
            prices[productsNumber++] = inputDouble;
            System.out.printf("Товар \"%s\" успешно добавлен.\n", products[productsNumber - 1]);
            inputString = in.next();
        }
        System.out.println("Добавление товаров завершено.\n");
    }

    void calcutate(){
        double sumAllPrices = 0;
        System.out.println("Добавленные товары:");
        for(int i = 0; i < productsNumber; i++){
            System.out.printf("%s %.2f\n", products[i], prices[i]);
            sumAllPrices += prices[i];
        }
        double priceForEach = sumAllPrices / guestsNumber;
        String rouble = switch ((int)priceForEach % 10){
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
        System.out.printf("С каждого %.2f %s.\n", priceForEach, rouble);
    }


    private void update(){
        double[] pricesCopy = new double[prices.length];
        String[] productsCopy = new String[products.length];
        System.arraycopy(prices, 0, pricesCopy, 0, prices.length);
        System.arraycopy(products, 0, productsCopy, 0, products.length);
        prices = new double[pricesCopy.length * 2];
        products = new String[productsCopy.length * 2];
        System.arraycopy(pricesCopy, 0, prices, 0, pricesCopy.length);
        System.arraycopy(productsCopy, 0, products, 0, productsCopy.length);
    }
}
