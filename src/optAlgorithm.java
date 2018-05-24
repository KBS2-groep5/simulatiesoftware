
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author binh_
 */
public class optAlgorithm implements TSPAlgorithm {
	static final String NAME = "Opt";
    private List<City> cities;
	private List<City> prevRoute;
    private long solveTime = 0;
    private int lineLength = 0;
	
	optAlgorithm(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public List<Line> solve() {
		return this.solveSteps(this.cities.size());
	}
//	oude swap functie
//	public List<City> swapCities(List<City> cities) {
//		Random rand = new Random();
//		int a = rand.nextInt(cities.size()) - 0;
//		int b = rand.nextInt(cities.size()) - 0;
//		prevRoute = cities;
//		City x = cities.get(a);
//		City y = cities.get(b);
//		cities.set(a, y);
//		cities.set(b, x);
//		
//		return cities;
//	}
	public List<City> swapCities(List<City> cities, int i, int k) {
		System.out.println(cities);
		List<City> newRoute = new ArrayList<>();
		
		newRoute = cities.subList(0, i-1);
		List<City> reverse = new ArrayList<>();
		
		for(int o = i-1; o < k; o++){
			reverse.add(cities.get(o));
		}
		
		Collections.reverse(reverse);
		
		for(int j = 0;j<reverse.size();j++){
			newRoute.add(reverse.get(j));
		}
//		System.out.println(newRoute);
		for(int b = k+3; b < cities.size();b++){
			newRoute.add(cities.get(b));
			b++;
		}
		System.out.println(newRoute);
		return newRoute;
	}
	
	private int getPathLength(List<City> cities) {
		int result = 0;
		for (int i = 0; i < cities.size() - 1; i++) {
			result += new Line(cities.get(i), cities.get(i + 1)).getLength();
		}
		return result;
	}
	
	public void revertSwap() {
		cities = prevRoute;
	}
	
	@Override
	public List<Line> solveSteps(int n) {
		
		if (this.cities.size() < 2) {
            this.solveTime = 0;
            return new ArrayList<>();
        }

        long startTime = System.nanoTime();

        List<City> path = new ArrayList<>();
        path.add(this.cities.get(0));
        int steps = 0;

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
            steps++;
        }
		
        // The time should be calculated before creating the lines because they're only used for visualization
        this.solveTime = System.nanoTime() - startTime;

		// het tekenen van de lijnen in een array gooien
//			for (int i = 0; i < path.size() - 1; i++) {
//				currResult.add(new Line(path.get(i), path.get(i + 1)));
//				
//			}

		//	Testing van routes
//		System.out.println(cities);
//		cities = this.swapCities(cities, 2, 4);
//		System.out.println(cities);
		
		
		
			// Moet nog hetzelfde begin en eindpunt als het greedy nemen en houden
			
			List<Line> result = new ArrayList<>();
			List<City> newRoute = new ArrayList();

			int lineLength = 0;

			
			int newLength = this.getPathLength(cities);
			List<City> bestRoute = new ArrayList<>();

			int counter = 0;
			int tries = 0;
			int finalLength = 0;
			
			while(tries <= 100) {
				int bestLength = this.getPathLength(cities);
				for(int i = 1; i < cities.size() - 1; i++) {
					for(int k = i + 1; k < cities.size(); k++) {
						newRoute = this.swapCities(cities, i, k);
						newLength = this.getPathLength(newRoute);
						
						if(newLength<bestLength) {
							this.cities = newRoute;
						}
						
					}
				}
				tries++;
			}
			
			for (int i = 0; i < path.size() - 1; i++) {
				result.add(new Line(path.get(i), path.get(i + 1)));
			}
			
			
		this.lineLength = lineLength;
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
