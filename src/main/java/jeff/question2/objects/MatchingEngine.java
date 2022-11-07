package jeff.question2.objects;

import jeff.question2.order.OrderBook;

public interface MatchingEngine {

    public void start();
    public void stop();
    public OrderBook getOrderBook();
}
