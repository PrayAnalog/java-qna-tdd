package restapi;

import java.math.BigInteger;

public class Core {
    private int limit;
    private String remaining;
    private BigInteger reset;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public BigInteger getReset() {
        return reset;
    }

    public void setReset(BigInteger reset) {
        this.reset = reset;
    }

    @Override
    public String toString() {
        return "Core{" +
                "limit=" + limit +
                ", remaining='" + remaining + '\'' +
                ", reset=" + reset +
                '}';
    }
}
