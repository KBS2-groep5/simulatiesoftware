import java.util.List;

public interface TSPAlgorithm {
    List<Line> solve();
    List<Line> solveSteps(int n);
    List<City> getCityList();
    int getCityCount();
    void setCities(List<City> cities);
    long getSolveTime();
    int getLineLength();

    // Not sure if this is best practice
    static String NAME = "???";
}
