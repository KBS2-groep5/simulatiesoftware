import java.util.ArrayList;
import java.util.List;

public class GreedyAlgorithm implements TSPAlgorithm {
    private List<City> cities;
    private long solveTime = 0;
    private int lineLength = 0;

    public static final String NAME = "Greedy";

    GreedyAlgorithm(List<City> cities) {
        this.cities = cities;
    }

    public List<Line> solve() {
        return this.solveSteps(this.cities.size());
    }

    public List<Line> solveSteps(int n) {
        if(this.cities.size() < 2) {
            this.solveTime = 0;
            return new ArrayList<>();
        }

        long startTime = System.nanoTime();

        List<City> path = new ArrayList<>();
        path.add(this.cities.get(0));

        int steps = 0;
        while(steps < n && this.cities.size() > path.size()) {
            double shortestDistance = 99999;
            Integer shortestIndex = null;
            for(int i = 0; i < this.cities.size(); i++) {
                if(path.contains(this.cities.get(i))) continue;
                double dist = this.cities.get(i).getDistanceTo(path.get(path.size() - 1));
                if(dist < shortestDistance) {
                    shortestDistance = dist;
                    shortestIndex = i;
                }
            }
            path.add(this.cities.get(shortestIndex));
            steps++;
        }

        List<Line> result = new ArrayList<>();
        int lineLength = 0;
        for(int i = 0; i < path.size() - 1; i++) {
            result.add(new Line(path.get(i), path.get(i + 1)));
            lineLength += result.get(result.size() - 1).getLength();
        }

        this.lineLength = lineLength;
        this.solveTime = System.nanoTime() - startTime;
        return result;
    }

    public List<City> getCityList() {
        return this.cities;
    }

    public int getCityCount() {
        return this.cities.size();
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public long getSolveTime() {
        return this.solveTime;
    }

    public int getLineLength() {
        return this.lineLength;
    }
}

