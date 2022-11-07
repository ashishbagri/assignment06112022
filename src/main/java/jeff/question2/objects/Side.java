package jeff.question2.objects;

public enum Side {

    BUY,
    SELL;

    public static Side of(String side){
        if(side.equalsIgnoreCase("B"))
            return BUY;
        else
            return SELL;
    }
}
