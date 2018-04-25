import java.util.List;

public class TSPAlgorithmTimer {
    private TSPAlgorithm algorithm;

    public TSPAlgorithmTimer(TSPAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public long getAverageTime(List<City> cities, int repetitions) {
        this.algorithm.setCities(cities);
        long totalTime = 0;
        for(int i = 0; i < repetitions; i++) {
            algorithm.solve();
            totalTime += algorithm.getSolveTime();
        }
        return totalTime / repetitions;
    }

    public String getHumanReadableAverageTime(List<City> cities, int repetitions) {
        return "" + String.valueOf(this.getAverageTime(cities, repetitions) / 1000) + " μs";
    }
}
