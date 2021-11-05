import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String NW_COMMAND = "nw";
    private static final String MIN_COMMAND = "min";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String command = reader.readLine();

                if (NW_COMMAND.equals(command)) {
                    MatrixCalculator.northWestCornerMethod(reader);
                } else if (MIN_COMMAND.equals(command)) {
                    MatrixCalculator.minimumElementMethod(reader);
                } else if (HELP_COMMAND.equals(command)) {
                    System.out.println("Команды:\nnw - метод СЗ угла\nmin - метод минимального элемента\nexit - выход");
                } else if (EXIT_COMMAND.equals(command)) {
                    break;
                } else {
                    System.out.println("Некорректный ввод!");
                }
            } catch (IOException e) {
                System.out.println("Некорректный ввод!");
            }
        }

        reader.close();
    }
}