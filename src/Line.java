import java.awt.*;

public class Line {
    private City a;
    private City b;
    private Color color;

    Line(City a, City b) {
        this.a = a;
        this.b = b;
        this.color = Color.BLACK;
    }

    Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    int getX1() {
        return this.a.getX();
    }

    int getY1() {
        return this.a.getY();
    }

    int getX2() {
        return this.b.getX();
    }

    int getY2() {
        return this.b.getY();
    }

    @Override
    public String toString() {
        return "(" + this.a.getX() + 'x' + this.a.getY() + " -> " + this.b.getX() + 'x' + this.b.getY() + ')';
    }
}
