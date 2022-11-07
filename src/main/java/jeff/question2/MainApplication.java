package jeff.question2;

import jeff.question2.objects.Order;
import jeff.question2.objects.OrderMatchingEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        OrderMatchingEngine engine = new OrderMatchingEngine();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        LOOP:
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            try {
                switch (line) {
                    case "":
                        // ignore
                        break;
                    case "quit":
                        break LOOP;
                    case "list":
                        System.out.println("BUY:");
                        engine.getBuyOrders().stream().map(Order::toString).forEach(System.out::println);
                        System.out.println("SELL:");
                        engine.getSellOrders().stream().map(Order::toString).forEach(System.out::println);
                        break;
                    default: // order
                        engine.placeOrder(Order.parse(line)).stream().map(Trade::toString).forEach(System.out::println);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Bad input: " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.err.println("Sorry, this command is not supported yet: " + e.getMessage());
            }finally {

            }
        }
        engine.getSummary();
    }
}
