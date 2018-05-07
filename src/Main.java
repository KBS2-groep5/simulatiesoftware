import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Algorithms are implementations of TSPAlgorithm. You can look at GreedyAlgorithm for
a fully functional implementation. You also need to add it to the combo box in
AlgorithmGUI, and to its event handler near the bottom of that document. Don't forget
to make sure the path length and calculation time are stored somehow.
 */

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();

        // We use the same seed everywhere so you can reproduce and compare results easily
        Random rand = new Random(5);

        for (int i = 0; i < 8; i++) {
            cities.add(new City(10 + rand.nextInt(460), 10 + rand.nextInt(460)));
        }

        TSPAlgorithm greedy = new GreedyAlgorithm(cities);

        new AlgorithmGUI(greedy);
    }
}
