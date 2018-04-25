import java.util.ArrayList;
import java.util.List;

public interface TSPAlgorithm {
    public List<Line> solve();
    public List<Line> solveSteps(int n);
    public List<City> solveUntil(int steps);
    public List<City> getCityList();
    public int getCityCount();
    public void setCities(List<City> cities);
    public long getSolveTime();
}
