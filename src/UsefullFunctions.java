import java.util.ArrayList;
import java.util.Collection;

public abstract class UsefullFunctions {

    public long factorial(int x){
        long i,fact=1;
        for(i=1;i<=x;i++) {
            fact = fact * i;
        }
        return fact;
    }

    public void swap (ArrayList<Integer> a, int i, int j){
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}
