import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MatrixCalculator {
    private static Matrix createMatrix(BufferedReader reader) throws IOException {
        int departureCount;
        int destinationCount;
        int[] stocks;
        int[] needs;
        int[][] prices;
        String[] input;

        System.out.print("Введите через пробел количество пунктов отправления и назначения: ");
        input = reader.readLine().split(" ");
        departureCount = Integer.parseInt(input[0]);
        destinationCount = Integer.parseInt(input[1]);

        stocks = new int[departureCount];
        needs = new int[destinationCount];
        prices = new int[departureCount][destinationCount];

        for (int i = 0; i < departureCount; ++i) {
            System.out.printf("Введите через пробел цену доставки из пункта A%d в каждый пункт: ", i + 1);
            input = reader.readLine().split(" ");
            for (int j = 0; j < destinationCount; ++j) {
                prices[i][j] = Integer.parseInt(input[j]);
            }
        }

        System.out.print("Введите через пробел запасы во всех пунктах отправления: ");
        input = reader.readLine().split(" ");
        for (int i = 0; i < departureCount; ++i) {
            stocks[i] = Integer.parseInt(input[i]);
        }

        System.out.print("Введите через пробел потребности во всех пунктах назначения: ");
        input = reader.readLine().split(" ");
        for (int i = 0; i < destinationCount; ++i) {
            needs[i] = Integer.parseInt(input[i]);
        }

        return new Matrix(departureCount, destinationCount, stocks, needs, prices);
    }

    public static void northWestCornerMethod(BufferedReader reader) throws IOException {
        Matrix matrix = createMatrix(reader);

        int amount = 0;

        for (int i = 0; i < matrix.getDepartureCount(); ++i) {
            for (int j = 0; j < matrix.getDestinationCount(); ++j) {
                if (matrix.getStocks()[i] == 0) break;
                if (matrix.getNeeds()[j] == 0) continue;

                int delivery = Math.min(matrix.getStocks()[i], matrix.getNeeds()[j]);
                int deliveryPrice = delivery * matrix.getPrices()[i][j];
                amount += deliveryPrice;

                matrix.getStocks()[i] -= delivery;
                matrix.getNeeds()[j] -= delivery;

                System.out.printf("A%d -> B%d. Доставлено: %d, запасы A%d: %d, потребности B%d: %d, стоимость доставки: %d%n",
                        i + 1, j + 1, delivery, i + 1, matrix.getStocks()[i], j + 1, matrix.getNeeds()[j], deliveryPrice);
            }
        }

        System.out.printf("Сумма: %d%n", amount);
    }

    private static int minimumElementMethodDelivery(int price, Matrix matrix) {
        int deliveryPrice = 0;
        int departureIndex = 0;
        int destinationIndex = 0;

        boolean finished = false;
        for (int i = 0; i < matrix.getDepartureCount(); ++i) {
            if (finished) break;

            for (int j = 0; j < matrix.getDestinationCount(); ++j) {
                if (price == matrix.getPrices()[i][j]) {
                    matrix.getPrices()[i][j] = -1;
                    departureIndex = i;
                    destinationIndex = j;
                    finished = true;
                    break;
                }
            }
        }
        if (matrix.getStocks()[departureIndex] > 0 && matrix.getNeeds()[destinationIndex] > 0) {
            int delivery = Math.min(matrix.getStocks()[departureIndex], matrix.getNeeds()[destinationIndex]);
            deliveryPrice = delivery * price;

            matrix.getStocks()[departureIndex] -= delivery;
            matrix.getNeeds()[destinationIndex] -= delivery;

            System.out.printf("A%d -> B%d. Доставлено: %d, запасы A%d: %d, потребности B%d: %d, стоимость доставки: %d%n",
                    departureIndex + 1, destinationIndex + 1, delivery, departureIndex + 1, matrix.getStocks()[departureIndex],
                    destinationIndex + 1, matrix.getNeeds()[destinationIndex], deliveryPrice);
        }

        return deliveryPrice;
    }

    public static void minimumElementMethod(BufferedReader reader) throws IOException {
        Matrix matrix = createMatrix(reader);

        int amount = 0;
        ArrayList<Integer> prices = new ArrayList<>();

        for (int i = 0; i < matrix.getDepartureCount(); ++i) {
            for (int j = 0; j < matrix.getDestinationCount(); ++j) {
                prices.add(matrix.getPrices()[i][j]);
            }
        }
        Collections.sort(prices);

        for (Integer price : prices) {
            amount += minimumElementMethodDelivery(price, matrix);
        }

        System.out.printf("Сумма: %d%n", amount);
    }
}
