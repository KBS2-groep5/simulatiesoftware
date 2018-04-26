import java.util.ArrayList;
import java.util.List;

public class RandomAlgorithm implements TSPAlgorithm {
    public final static String NAME = "Random";
    private List<City> cities;
    private long solveTime = 0;
    private int lineLength = 0;

    public RandomAlgorithm(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public List<Line> solve() {
        return this.solveSteps(this.cities.size());
    }

    @Override
    public List<Line> solveSteps(int n) {
        List<Line> result = new ArrayList<Line>();
        result.add(new Line(new City(100, 100), new City(200, 200)));
        return result;
    }

    @Override
    public List<City> getCityList() {
        return this.cities;
    }

    @Override
    public int getCityCount() {
        return this.cities.size();
    }

    @Override
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public long getSolveTime() {
        return this.solveTime;
    }

    @Override
    public int getLineLength() {
        return this.lineLength;
    }
}
