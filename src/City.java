public class City {
    private int x;
    private int y;

    City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistanceTo(City c2) {
        double a = Math.max(this.x, c2.getX()) - Math.min(this.x, c2.getX());
        double b = Math.max(this.y, c2.getY()) - Math.min(this.y, c2.getY());
        return Math.sqrt(a*a + b*b);
    }

    @Override
    public String toString() {
        return "City{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
