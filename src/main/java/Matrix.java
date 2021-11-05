public class Matrix {
    private final int departureCount;
    private final int destinationCount;
    private final int[] stocks;
    private final int[] needs;
    private final int[][] prices;

    public int getDepartureCount() {
        return departureCount;
    }

    public int getDestinationCount() {
        return destinationCount;
    }

    public int[] getStocks() {
        return stocks;
    }

    public int[] getNeeds() {
        return needs;
    }

    public int[][] getPrices() {
        return prices;
    }

    public boolean empty() {
        int sum = 0;

        for (int i = 0; i < departureCount; ++i) {
            sum += stocks[i];
        }
        for (int i = 0; i < destinationCount; ++i) {
            sum += needs[i];
        }

        return sum == 0;
    }

    public Matrix(int departureCount, int destinationCount, int[] stocks, int[] needs, int[][] prices) {
        this.departureCount = departureCount;
        this.destinationCount = destinationCount;
        this.stocks = stocks;
        this.needs = needs;
        this.prices = prices;
    }
}