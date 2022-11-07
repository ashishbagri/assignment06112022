package jeff.question2.transport;

import com.sun.org.apache.xpath.internal.operations.Or;
import jeff.question2.objects.Order;
import jeff.question2.objects.Side;

public class MessageDecoderEncode {

    public Order inbound(String orderLine){
        String[] data = orderLine.split(",");
        return new Order(Long.parseLong(data[0]),data[1], Side.of(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4]));
    }

    public void outbound(Order order){
        //write how to outbound the message
    }
}
