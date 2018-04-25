import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            cities.add(new City(
                    ThreadLocalRandom.current().nextInt(10, 470),
                    ThreadLocalRandom.current().nextInt(10, 470)
            ));
        }

        TSPAlgorithm greedy = new GreedyAlgorithm(cities);

        new AlgorithmGUI(greedy);
    }
}
