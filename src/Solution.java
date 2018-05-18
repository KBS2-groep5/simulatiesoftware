import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int totalLength;
    private ArrayList<City> solCities;

    public Solution(){
        this.totalLength = 0;
    }
    public Solution(ArrayList<City> solC) {
        this.solCities = solC;
        this.totalLength = 0;
    }

    public void addCity(City c) {
        solCities.add(c);
    }

    public City getSolCity(int x){
        City y = solCities.get(x);
        return y;
    }

    public List<City> getSolCities() {
        return solCities;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public void setSolCities(ArrayList<City> solCities) {
        this.solCities = solCities;
    }

    public int getTotalLength() {

        return totalLength;
    }
}
