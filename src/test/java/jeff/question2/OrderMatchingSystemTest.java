package jeff.question2;

import jeff.question2.objects.Order;
import jeff.question2.objects.OrderMatchingEngine;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderMatchingSystemTest {

    @Test
    public void testOrderMatchSystem(){

        String[] oderLine = new String[]{"10000,700.HK,B,98,25500",
                "10005,700.HK,S,105,20000",
                "10001,700.HK,S,100,500",
                "10002,700.HK,S,100,10000",
                "10003,700.HK,B,99,50000",
                "10004,700.HK,S,103,100",
                "10006,700.HK,B,105,16000"};

        OrderMatchingEngine engine = new OrderMatchingEngine();
        List<Trade> trades = new ArrayList<>();
        for(String oder : oderLine){
            trades.addAll(engine.placeOrder(Order.parse(oder)));
        }
        List<Trade> expectedTrades = new ArrayList<>();;
        /**
         * trade 10006,10001,700.HK,100,500
         * trade 10006,10002,700.HK,100,10000
         * trade 10006,10004,700.HK,103,100
         * trade 10006,10005,700.HK,105,5400
         */

        Assert.assertTrue(trades.contains(new Trade(10006,10001,"700.HK", 100.0,500)));
        Assert.assertTrue(trades.contains(new Trade(10006,10002,"700.HK", 100.0,10000)));
        Assert.assertTrue(trades.contains(new Trade(10006,10004,"700.HK", 103.0,100)));
        Assert.assertTrue(trades.contains(new Trade(10006,10005,"700.HK", 105.0,5400)));

    }
}
