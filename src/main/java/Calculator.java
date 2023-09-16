import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private int guestsNumber;
    private double totalSum = 0;
    private ArrayList<String> items;
    private ArrayList<Double> prices;



    Calculator(int guestsNumber){
        this.guestsNumber = guestsNumber;
        items = new ArrayList<>();
        prices = new ArrayList<>();
        String persons;
        if(guestsNumber >= 11 && guestsNumber <= 14) persons = "человек";
        else persons = switch (guestsNumber%10){
            case 1,2,3,4 -> "человека";
            default -> "человек";
        };
        System.out.printf("Счёт на %d %s создан!\n" +
                "Добавляйте товары в формате \"<Товар> <стоимость>\".\n" +
                "Название товара может состоять из нескольких слов.\n" +
                "Для завершения добавления введите \"Завершить\".\n\n", guestsNumber, persons);
    }

    void addProducts(){
        Scanner in = new Scanner(System.in);
        String inputItem = "";
        double inputPrice;
        while(true){
            while(in.hasNext() && !in.hasNextDouble()){
                if(inputItem.isEmpty()) inputItem += in.next();
                else inputItem += ' ' + in.next();
                if(inputItem.equalsIgnoreCase("завершить")){
                    System.out.println("Добавление товаров завершено");
                    return;
                }
            }
            if(in.hasNextDouble()){
                if(!inputItem.isEmpty()){
                    inputPrice = in.nextDouble();
                    items.add(inputItem);
                    prices.add(inputPrice);
                    totalSum += inputPrice;
                    System.out.printf("Товар '%s' по цене %.2f успешно добавлен\n", inputItem, inputPrice);
                    inputItem = "";
                }
                else in.next();
            }
        }
    }

    void calcutate(){
        System.out.println("\nДобавленные товары:");
        for(int i = 0; i < items.size(); i++){
            System.out.printf("%s %.2f\n", items.get(i), prices.get(i));
        }
        System.out.printf("\nВсего на %.2f %s.\n", totalSum, rouble(totalSum));
        double priceForEach = totalSum / guestsNumber;
        System.out.printf("\nС каждого %.2f %s.\n", priceForEach, rouble(priceForEach));
    }

    private String rouble(double price){
        if(price > 10 && price < 15) return "рублей";
        else return switch ((int)price % 10){
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
    }
}