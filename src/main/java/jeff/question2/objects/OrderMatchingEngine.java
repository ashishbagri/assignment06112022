package jeff.question2.objects;

import jeff.question2.Trade;
import jeff.question2.order.OrderBook;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderMatchingEngine {

    private final Map<String, OrderBook> obMap = new HashMap<>();

    public List<Trade> placeOrder(Order order) {

        return getOrderBook(order.getSymbol()).insertOrder(order, new ArrayList<>());
    }

    public OrderBook getOrderBook(String symbol) {
        OrderBook ob = obMap.get(symbol);
        if (ob == null) {
            ob = new OrderBook();
            obMap.put(symbol, ob);
        }
        return ob;
    }

    public List<Order> getBuyOrders(){
        return getOrders(val -> val.getBuyCache());
    }

    public List<Order> getSellOrders(){
        return getOrders(val -> val.getSellCache());
    }

    public List<Order> getOrders(Function<OrderBook, OrderCache> fun){
        return obMap.values().stream()
                .map( fun::apply)
                .map( cache -> cache.getOrders())
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }

    /**
     * Buyers Sellers
     *  50,000 99 | 105 14,600
     *  25,500 98 |
     * @return
     */
    public void getSummary() {
        obMap.values().stream()
                .forEach(ob -> {
                    System.out.println(ob.getBuyCache());
                    System.out.println(ob.getSellCache());
                });
    }
}