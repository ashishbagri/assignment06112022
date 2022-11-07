package jeff.question2.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class OrderCache {
    private List<Order> orders = new ArrayList<>();
    private final TreeMap<Double, List<Order>> priceMap = new TreeMap<>();

    public TreeMap<Double, List<Order>> getPriceMap() {
        return priceMap;
    }

    public Order getFirstOrder() {
        List<Order> order = priceMap.firstEntry().getValue();
        if(order == null || order.isEmpty()){
            return null;
        }
        return order.get(0);
    }

    public void add(Order order) {
        final double price = order.getPrice();
        if(!priceMap.containsKey(Double.valueOf(price))){
            priceMap.put(Double.valueOf(price), new ArrayList<>());
        }
        priceMap.get(Double.valueOf(price)).add(order);
        orders.add(order);
    }

    public boolean isEmpty() {
        return priceMap.size() == 0;
    }

    public Order getLastEntry() {
        return priceMap.lastEntry().getValue().get(0);
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void remove(Order matchedOrder) {
        List<Order> orders = priceMap.get(matchedOrder.getPrice());
        if(orders != null && !orders.isEmpty()){
            orders.remove(matchedOrder);
        }
        if(orders == null || orders.isEmpty()){
            priceMap.remove(matchedOrder.getPrice());
        }

    }

    @Override
    public String toString() {
        return "OrderCache{" +
                "orders=" + orders.toString() +
                ", priceMap=" + priceMap +
                '}';
    }
}
