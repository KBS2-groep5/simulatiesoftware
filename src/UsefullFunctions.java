import java.util.ArrayList;
import java.util.Collection;

public abstract class UsefullFunctions {

    public int factorial(int x){
        int i, fact = 1;
        if(x > 12){
            fact = 2147483646;
        }else {
            for (i = 1; i <= x; i++) {
                fact = fact * i;
            }
        }
        return fact;
    }

    public void swap (ArrayList<Integer> a, int i, int j){
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}
