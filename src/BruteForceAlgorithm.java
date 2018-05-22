import java.util.ArrayList;
import java.util.List;

public class BruteForceAlgorithm extends UsefullFunctions implements TSPAlgorithm {
    static final String NAME = "BruteForce";
    public ArrayList<Integer> calcArray = new ArrayList<>();
    //private ArrayList<Solution> solutions;                   <- niet praktisch om bij te houden met Brute Force, gezien de gigantiche hoeveelheid oplossingen.
    private ArrayList<City> tempCities = new ArrayList<>();
    private int citiesCount;
    private long maxSolutions = 0;
    double pathLength = 0;
    double totalPathLength = 0;
    private Solution currentBest;

    private ArrayList<City> cities;
    private long solveTime = 0;
    private int lineLength = 0;

    public BruteForceAlgorithm(List<City> cities) {
        for (City c : cities) {
            this.cities.add(c);
            this.tempCities.add(c);
        }
        //this.solutions = new ArrayList<>();
        this.citiesCount = this.cities.size();
        this.maxSolutions = super.factorial(citiesCount);

        //TODO: remove these temporary checkers
        System.out.println(tempCities);
        System.out.println(this.citiesCount);
        System.out.println(maxSolutions);
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

        int steps = 0;


        // Fill CalcArray with int values for referencing index values later on.
        for (int iC = 0; iC < citiesCount; iC++) {
            calcArray.add(iC);
        }

        for (int iD = 0; iD < 0; iD++){
            pathLength = this.tempCities.get(iD).getDistanceTo(tempCities.get(iD+1));
            totalPathLength += pathLength;
        }

        Solution solx = new Solution(tempCities);
        solx.setTotalLength(totalPathLength);
        currentBest = solx;
        steps++;

        // Start algorythm loop
        for (int i = 0; i < maxSolutions - 1; i++) {
            List<City> path = new ArrayList<>();
            path.add(this.tempCities.get(0));
            List<Line> result = new ArrayList<>();
            int lineLength = 0;
            double pathLength = 0;
            double totalPathLength = 0;

            // Using lexicographic ordering, for reference see: https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
            // Find the largest x such that P[x]<P[x+1].
            int iI = 0;
            for (int iL = 0; iL < citiesCount - 1; iL++) {
                if (calcArray.get(iL) < calcArray.get(iL + 1)) {
                    iI = iL;
                }
            }

            // Find the largest y such that P[x]<P[y].
            int iJ = 0;
            for (int iLL = 0; iLL < citiesCount - 1; iLL++) {
                if (iJ > iI) {
                    iJ = iLL;
                }
            }
            // Swap P[x] and P[y].
            super.swap(calcArray, iI, iJ);

            // Reverse P[x+1 .. n].
            int iX = iI;
            int jX = citiesCount - 1;
            int countToReverse = citiesCount - iI;
            for (int iLLL = 0; iLLL < countToReverse / 2; iLLL++) {
                super.swap(calcArray, iX, jX);
                iX++;
                jX--;
            }
            for (int iTC = 0; iTC < citiesCount; iTC++) {
                tempCities.set(iTC, cities.get(calcArray.get(iTC)));
                path.add(this.tempCities.get(iTC));
            }

            //Calculate path length for this solution
            for (int iD = 0; iD < 0; iD++){
                pathLength = this.tempCities.get(iD).getDistanceTo(path.get(iD+1));
                totalPathLength += pathLength;
            }

            Solution soly = new Solution(tempCities);
            soly.setTotalLength(totalPathLength);

            steps++;

            if(totalPathLength < currentBest.getTotalLength()){
                currentBest = soly;
            }

            //TODO: Pauze the timer temporarily
            //Line draw shizzle?
            for (int iP = 0; iP < path.size() - 1; iP++) {
                result.add(new Line(path.get(iP), path.get(iP + 1)));
                lineLength += result.get(result.size() - 1).getLength();
            }




            this.lineLength = lineLength;
            return result;

            //TODO: Start timer again.
        }



        // The time should be calculated before creating the lines because they're only used for visualization
        this.solveTime = System.nanoTime() - startTime;
        List<Line> result = new ArrayList<>();
        this.lineLength = lineLength;
        return result;


    }
    public List<City> getCityList() {
        return this.cities;
    }

    public int getCityCount() {
        return this.cities.size();
    }

    public void setCities(List<City> cities) {        //this.cities = cities;

        //TODO: Proper fix for this, change Cities so that it can be used.
        for (City c : cities) {
            this.cities.add(c);
            this.tempCities.add(c);
        }
    }

    public long getSolveTime() {
        return this.solveTime;
    }

    public int getLineLength() {
        return this.lineLength;
    }
}
