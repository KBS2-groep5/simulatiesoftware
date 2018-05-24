
import java.util.ArrayList;
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
public class EigenAlgorithm extends UsefullFunctions implements TSPAlgorithm {
	static final String NAME = "Eigen";
    private List<City> cities;
	private List<City> prevRoute;
    private long solveTime = 0;
    private int lineLength = 0;
	
	EigenAlgorithm(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public List<Line> solve() {
		return this.solveSteps(this.cities.size());
	}
	
	public List<City> swapCities(List<City> cities) {
		Random rand = new Random();
		int a = rand.nextInt(cities.size()) - 0;
		int b = rand.nextInt(cities.size()) - 0;
		prevRoute = cities;
		City x = cities.get(a);
		City y = cities.get(b);
		cities.set(a, y);
		cities.set(b, x);
		
		return cities;
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

		
		
        List<Line> result = new ArrayList<>();
		List<City> newRoute = new ArrayList();
        
		int lineLength = 0;
		
		int bestLength = this.getPathLength(path);
		System.out.println("current Best: " + bestLength);
		this.swapCities(path);
		int newLength = this.getPathLength(path);
		
		
		// het tekenen van de lijnen in een array gooien
//			for (int i = 0; i < path.size() - 1; i++) {
//				currResult.add(new Line(path.get(i), path.get(i + 1)));
//				
//			}

for (int i = 0; i < path.size() - 1; i++) {
				result.add(new Line(path.get(i), path.get(i + 1)));
				
				
			}

		//	Testing van routes
//			this.swapCities(path);
//			newLength = this.getPathLength(path);
//			System.out.println(newLength);
			
			int counter = 0;

			while(newLength > bestLength) {
				if(counter >= 100) {
					break;
				}
				for(int i =1; i<path.size() - 1; i++) {
					for(int k=i+1;k<path.size();k++) {
						newRoute = this.swapCities(path);
						newLength = this.getPathLength(newRoute);

						System.out.println("best: " + bestLength);
						System.out.println("         new route: " + newLength);
						
						
						if(newLength<bestLength) {
							
							path = newRoute;
							
							System.out.println("Jawel hij is korter!");
						}
						
					}
					
				}
				counter++;
				System.out.println("                    tijd:"+ counter);
			}
			
			lineLength = this.getPathLength(path);
			
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
