import java.awt.*;
import java.util.List;
import javax.swing.*;

public class AlgorithmPainter extends JPanel {
    private TSPAlgorithm algorithm;
    private List<City> cities;
    private int cursor = 0;

    AlgorithmPainter(TSPAlgorithm algorithm) {
        this.algorithm = algorithm;
        this.cities = algorithm.getCityList();
        setPreferredSize(new Dimension(480, 480));
        setBackground(Color.WHITE);
    }

    public void setCursor(int cursor) {
        if(cursor >= 0 && cursor <= this.algorithm.getCityCount()) {
            this.cursor = cursor;
        }
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for(int i = 0; i < this.cities.size(); i++) {
            City c = this.cities.get(i);
            g.fillOval(c.getX() - 5, c.getY() - 5, 10, 10);
//            g.drawChars(("" + i).toCharArray(), 0, ("" + i).length(), c.getX() + 6, c.getY());
//            g.drawChars(("" + c.getX() + "x" + c.getY()).toCharArray(), 0, ("" + c.getX() + "x" + c.getY()).length(), c.getX() + 6, c.getY() + 10);
        }

        List<Line> lines = algorithm.solve_n_lines(this.cursor);
        for (Line l : lines) {
            g.setColor(l.getColor());
            g.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
        }
    }
}
