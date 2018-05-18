import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class BruteForceAlgorithm extends UsefullFunctions implements TSPAlgorithm {
    public ArrayList<Integer> calcArray = new ArrayList<>();
    private ArrayList<Solution> solutions;
    private ArrayList<City> tempCities = new ArrayList<>();
    private int citiesCount;
    private long maxSolutions = 0;

    private ArrayList<City> cities;
    private long solveTime = 0;
    private int lineLength = 0;


    //Random rand = new Random(maxIndex);

    static final String NAME = "Brute Force";

    BruteForceAlgorithm(List<City> cities) {
        for(City c :cities){
            this.cities.add(c);
            this.tempCities.add(c);
        }
        this.solutions = new ArrayList<>();
        this.citiesCount = this.cities.size();
        this.maxSolutions = super.factorial(citiesCount);
    }

    // This method is only used in TSPAlgoritmTimer
    public List<Line> solve() {
        return this.solveSteps(this.cities.size()); //TODO : Cause i have more steps then there are cities, this needs to be bigger?
    }


    // This is the method used by AlgorithmGUI
    public List<Line> solveSteps(int n) {
        if (this.cities.size() < 2) {
            this.solveTime = 0;
            return new ArrayList<>();
        }

        long startTime = System.nanoTime();

        List<City> path = new ArrayList<>();
        path.add(this.cities.get(0));

        int steps = 0;

        //Fill CalcArray with int values for referencing index values later on.
        for(int iC = 0; iC < citiesCount; iC++){
            calcArray.add(iC);
        }
        new Solution(cities);
        steps++;

        //Start algorythm loop
        for (int i = 0; i < maxSolutions-1; i++) {

            if(ix < jx){

            }

            super.swap(calcArray, ix, jx);
            new Solution();






            for (int isol = 0; isol < cities.size(); isol++) {
                //Storage of solution being worked on:
                Solution sol = new Solution(); //TODO: Check if new Solution objects are made, or if it overides the same object. Possible solution: Factory.

                //Make temporary array which it can empty without problems.
                List<City> tempCities = new ArrayList<>();
                for (City c : cities) {
                    tempCities.add(c);
                }
                sol.addCity(tempCities.get(i));
                tempCities.remove(i);

                while (tempCities.size() > 0) {
                    //Check to see if this solution already exists at second to last city:
                    int similarityCount = 0;
                    int attemptFinal = 0;
                    for (Solution s : solutions) {
                        for (int icomp = 0; icomp < sol.getSolCities().size(); icomp++) {
                            if (sol.getSolCity(icomp) == s.getSolCity(icomp)) {
                                similarityCount++;
                            }
                        }
                    }
                    //Check if there is still a solution available in this branch. Because at this point only 2 different sollutions are avaiable.
                    if(tempCities.size() < 3){
                        int solAvailable = 2;
                        for (Solution s : solutions) {
                            int dupeCheck = 0;
                            for (int ifin = 0; ifin < sol.getSolCities().size(); ifin++) {
                                if (sol.getSolCity(ifin) == s.getSolCity(ifin)) {
                                    dupeCheck++;
                                }
                                if(dupeCheck == sol.getSolCities().size()){
                                    solAvailable--;
                                }
                            }
                        }
                        if(solAvailable < 1){

                        }

                    }
                    // If there are less similarities then the amount of cities squared, -1 , it means the solution is still unique.
                    if((similarityCount < (sol.getSolCities().size() * sol.getSolCities().size()) && tempCities.size() > 2)){
                        int x = rand.nextInt();
                        sol.addCity(tempCities.get(x));
                        tempCities.remove(x);
                        variableRandMax--;
                        steps++;
                    }
                    //TODO: FIX THIS!!
                    List<Line> result = new ArrayList<>();
                    int lineLength = 0;
                    for(int i = 0; i < path.size() - 1; i++) {
                        result.add(new Line(path.get(i), path.get(i + 1)));
                        lineLength += result.get(result.size() - 1).getLength();
                    }
                    //TODO FIX THIS ^^^^

                    this.lineLength = lineLength;
                    return result;
                }


                solutions.add(sol);

            }
        }

        //TODO: Integrate Time, steps, previous and Next correctly into algorythm.

        while (steps < n && this.cities.size() > path.size()) {

            double shortestDistance = 99999;
            Integer shortestIndex = null;
            for (int i = 0; i < this.cities.size(); i++) {
                if (path.contains(this.cities.get(i))) continue;
                double dist = this.cities.get(i).getDistanceTo(path.get(path.size() - 1));
                if (dist < shortestDistance) {
                    shortestDistance = dist;
                    shortestIndex = i;
                }
            }
            //noinspection ConstantConditions
            path.add(this.cities.get(shortestIndex));

        }

        // The time should be calculated before creating the lines because they're only used for visualization
        this.solveTime = System.nanoTime() - startTime;

        List<Line> result = new ArrayList<>();
        int lineLength = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            result.add(new Line(path.get(i), path.get(i + 1)));
            lineLength += result.get(result.size() - 1).getLength();
        }

        this.lineLength = lineLength;
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
