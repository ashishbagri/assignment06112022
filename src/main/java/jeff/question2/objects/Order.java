package jeff.question2.objects;

public class Order implements Comparable{
    private long id;
    private String symbol;
    private Side side;
    private double price;
    private int qnty;

    private int executedQty;
    private int openQty;
    private double lastExecutedPrice;
    private int lastExecutedQnty;

    public Order(long id, String symbol, Side side, double price, int qnty) {
        this.id = id;
        this.symbol = symbol;
        this.side = side;
        this.price = price;
        this.qnty = qnty;
        this.openQty = qnty;
    }

    //10000,700.HK,B,98,25500
    public static Order parse(String line) {
        String[] data = line.split(",");
        return new Order(Long.parseLong(data[0]),data[1], Side.of(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4]));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
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

    public void updateQnty(double price, int quantity) {
        qnty-=quantity;
        executedQty+=quantity;
        lastExecutedPrice = price;
        lastExecutedQnty = quantity;

    }

    public int getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(int executedQty) {
        this.executedQty = executedQty;
    }

    public int getOpenQty() {
        return openQty;
    }

    public void setOpenQty(int openQty) {
        this.openQty = openQty;
    }

    public double getLastExecutedPrice() {
        return lastExecutedPrice;
    }

    public void setLastExecutedPrice(double lastExecutedPrice) {
        this.lastExecutedPrice = lastExecutedPrice;
    }

    public int getLastExecutedQnty() {
        return lastExecutedQnty;
    }

    public void setLastExecutedQnty(int lastExecutedQnty) {
        this.lastExecutedQnty = lastExecutedQnty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", side=" + side +
                ", price=" + price +
                ", qnty=" + qnty +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o == null)
            return 0;
        Order order = (Order) o;
        return Integer.compare(this.getQnty(), order.qnty);
    }
}
