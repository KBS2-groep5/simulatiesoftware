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

    public void swap (Integer[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
