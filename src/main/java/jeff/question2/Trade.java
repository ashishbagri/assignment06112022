package jeff.question2;

import java.util.Objects;

/**
 * "trade Buyer OrderID,Seller OrderID,Exec Px,Exec Qty"
 */
public class Trade {

    private long buyerOrderID;
    private long sellerOrderID;
    private String symbol;
    private double price;
    private int qnty;

    public Trade(long buyerOrderID, long sellerOrderID, String symbol, double price, int qnty) {
        this.buyerOrderID = buyerOrderID;
        this.sellerOrderID = sellerOrderID;
        this.symbol = symbol;
        this.price = price;
        this.qnty = qnty;
    }

    public long getBuyerOrderID() {
        return buyerOrderID;
    }

    public void setBuyerOrderID(long buyerOrderID) {
        this.buyerOrderID = buyerOrderID;
    }

    public long getSellerOrderID() {
        return sellerOrderID;
    }

    public void setSellerOrderID(long sellerOrderID) {
        this.sellerOrderID = sellerOrderID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "buyerOrderID=" + buyerOrderID +
                ", sellerOrderID=" + sellerOrderID +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", qnty=" + qnty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return buyerOrderID == trade.buyerOrderID && sellerOrderID == trade.sellerOrderID && Double.compare(trade.price, price) == 0 && qnty == trade.qnty && Objects.equals(symbol, trade.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerOrderID, sellerOrderID, symbol, price, qnty);
    }
}
