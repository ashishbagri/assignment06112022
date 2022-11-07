package jeff.question2.order;

import jeff.question2.Trade;
import jeff.question2.objects.Order;
import jeff.question2.objects.OrderCache;
import jeff.question2.objects.Side;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static jeff.question2.objects.Side.BUY;
import static jeff.question2.objects.Side.SELL;

public class OrderBook {
    private OrderCache buyCache = new OrderCache();
    private OrderCache sellCache = new OrderCache();

    public List<Trade> insertOrder(Order order, List<Trade> trades) {
        if(order.getSide() == BUY){
            return match(order, sellCache, (cache) -> cache.getFirstOrder(), trades, (ord, matchOrder) -> Double.compare(ord.getPrice(), matchOrder.getPrice())>=0);
        }else if(order.getSide() == SELL){
            return match(order, buyCache, (cache) -> cache.getLastEntry(), trades, (ord, matchOrder) -> Double.compare(matchOrder.getPrice(), ord.getPrice())>=0);
        }
        return trades;
    }

    public List<Trade> match(Order order, OrderCache cache, Function<OrderCache, Order> orderCacheFunc, List<Trade> trades, BiPredicate<Order,Order> sideCheck){
        if(cache.isEmpty()){
            addOrderToCache(order);
            return trades;
        }
        Order matchedOrder = orderCacheFunc.apply(cache);
        if(sideCheck.test(order, matchedOrder)) {
            final int quantity = order.getQnty() >= matchedOrder.getQnty() ? matchedOrder.getQnty() : order.getQnty();
            final double price = order.getSide() == BUY ? matchedOrder.getPrice() : order.getPrice();
            order.updateQnty(price, quantity);
            matchedOrder.updateQnty(price, quantity);
            trades.add(createTrade(order, matchedOrder, quantity, price));
            if(matchedOrder.getQnty() == 0){
                cache.remove(matchedOrder);
            }
            if(order.getQnty() == 0){
                return trades;
            }
        }else{
            addOrderToCache(order);
            return trades;
        }
        return match(order, cache, orderCacheFunc, trades, sideCheck);
    }

    private Trade createTrade(Order order, Order matchOrder, int qnty, double price) {
        long buyerId = order.getSide() == Side.BUY ? order.getId() : matchOrder.getId();
        long sellerId = order.getSide() == Side.SELL ? order.getId() : matchOrder.getId();

        return new Trade(buyerId,sellerId,order.getSymbol(), price, qnty);

    }

    private void addOrderToCache(Order order) {
        if(order.getQnty() > 0){
            if(order.getSide() == BUY)
                buyCache.add(order);
            else {
                sellCache.add(order);
            }
        }
    }

    public OrderCache getBuyCache() {
        return buyCache;
    }

    public void setBuyCache(OrderCache buyCache) {
        this.buyCache = buyCache;
    }

    public OrderCache getSellCache() {
        return sellCache;
    }

    public void setSellCache(OrderCache sellCache) {
        this.sellCache = sellCache;
    }
}
