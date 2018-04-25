import java.util.ArrayList;
import java.util.List;

public interface TSPAlgorithm {
    public List<Line> solve();
    public List<Line> solve_n_lines(int n);
    public List<City> solve_until(int steps);
    public List<City> getCityList();
    public int getCityCount();
    public void setCities(List<City> cities);
}
