import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyAlgorithm implements TSPAlgorithm {
    private List<City> cities;
    private int solve_time;

    GreedyAlgorithm(List<City> cities) {
        this.cities = cities;
    }

    public List<Line> solve() {
        return this.solve_n_lines(this.cities.size());
    }

    public List<City> solve_until(int cursor) {
        return this.cities.subList(0, cursor);
    }

    public List<Line> solve_n_lines(int n) {
        if(this.cities.size() < 2) return new ArrayList<Line>();

        List<City> path = new ArrayList<City>();
        path.add(this.cities.get(0));

        int steps = 0;
        while(steps < n && this.cities.size() > path.size()) {
            double shortest_distance = 99999;
            Integer shortest_index = null;
            for(int i = 0; i < this.cities.size(); i++) {
                if(path.contains(this.cities.get(i))) continue;
                double dist = this.cities.get(i).getDistanceTo(path.get(path.size() - 1));
                // System.out.println("Dist to " + i + ": " + dist + " (" + path.get(path.size() - 1) + " -> " + this.cities.get(i));
                if(dist < shortest_distance) {
                    shortest_distance = dist;
                    shortest_index = i;
                }
            }
            path.add(this.cities.get(shortest_index));
            steps++;
        }

        List<Line> result = new ArrayList<Line>();
        for(int i = 0; i < path.size() - 1; i++) {
            result.add(new Line(path.get(i), path.get(i + 1)));
        }

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
}

