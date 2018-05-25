import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BruteForceAlgorithm extends UsefullFunctions implements TSPAlgorithm {
    static final String NAME = "BruteForceAlgorithm";
    public ArrayList<Integer> calcArray = new ArrayList<>();
    private ArrayList<Solution> solutions;
    private ArrayList<City> tempCities = new ArrayList<>();
    private int citiesCount = 0;
    private int maxSolutions = 0;
    double pathLength = 0;
    double totalPathLength = 0;
    private Solution currentBest;

    private ArrayList<City> cities = new ArrayList<>();
    private long solveTime = 0;
    private int lineLength = 0;

    BruteForceAlgorithm(List<City> cities) {
        for (City c : cities) {
            this.cities.add(c);
            this.tempCities.add(c);
        }

        this.solutions = new ArrayList<>();
        this.citiesCount = this.cities.size();
        this.maxSolutions = super.factorial(citiesCount);

        // Fill CalcArray with int values for referencing index values later on.
        for (int iC = 0; iC < citiesCount; iC++) {
            calcArray.add(iC);
        }
        System.out.println(calcArray);
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




        this.solutions.add(new Solution(tempCities));
        this.solutions.get(0).setTotalLength(totalPathLength);
        currentBest = solutions.get(0);
        steps++;

        // Start algorythm loop
        for (int i = 0; i < maxSolutions -1; i++) {
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
                    for(int iK : calcArray){
                        if(iK == iL){
                            iI = iL;
                        }
                    }
                }
            }
            System.out.println(iI);

            // Find the largest y such that P[x]<P[y].
            int iJ = 0;
            for (int iLL = iI+1; iLL < citiesCount; iLL++) {
                if (calcArray.get(iLL) > calcArray.get(iI)) {
                    for(int iK : calcArray){
                        if(iK == iLL){
                            iJ = iK;
                        }
                    }
                }
            }
            System.out.println(iJ);
            // Swap P[x] and P[y].
            super.swap(calcArray, iI, iJ);
            System.out.println(calcArray);

            // Reverse INDEX P[x+1 .. n].
            int iX = 0;
            for(int iK : calcArray){
                if(iK == iI){
                    iX = iK+1;
                }
            }
            int jX = citiesCount - 1;
            int countToReverse = citiesCount - iI;
            if(iX < jX) {
                for (int iLLL = 0; iLLL < countToReverse / 2; iLLL++) {
                    super.swap(calcArray, iX, jX);
                    iX++;
                    jX--;
                }
            }
            System.out.println(calcArray);
            for (int iTC = 0; iTC < citiesCount-1; iTC++) {
                this.tempCities.set(iTC, cities.get(calcArray.get(iTC)));
                path.add(this.tempCities.get(iTC));
            }

            //Calculate path length for this solution
            for (int iD = 0; iD < 0; iD++){
                pathLength = this.tempCities.get(iD).getDistanceTo(path.get(iD+1));
                totalPathLength += pathLength;
            }

            this.solutions.add(new Solution(tempCities));
            this.solutions.get(i).setTotalLength(totalPathLength);

            if(solutions.get(i).getTotalLength() < currentBest.getTotalLength()){
                currentBest = solutions.get(i);
            }
            System.out.println(path.size());


            //TODO: Pauze the timer temporarily
            //Line draw for current solution.
            /*
            for (int iP = 0; iP < path.size()-1; iP++) {
                Line lY = new Line(path.get(iP), path.get(iP + 1));
                result.add(lY);
                lineLength += result.get(result.size() - 1).getLength();
                lineLength += lY.getLength();
            }
            */


            System.out.println(result.size());

            //TODO: Start timer again.
        }

        steps++;


        // The time should be calculated before creating the lines because they're only used for visualization
        this.solveTime = System.nanoTime() - startTime;
        List<Line> result = new ArrayList<>();
        this.lineLength = lineLength;

        System.out.println(currentBest.getSolCities());
        // Draw current best.
        for(int iB = 0; iB < currentBest.getSolCities().size()-1; iB++){
            Line lX = new Line(currentBest.getSolCity(iB),currentBest.getSolCity(iB+1));
            result.add(lX);
            lX.setColor(Color.red);
            lineLength += lX.getLength();
        }
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
