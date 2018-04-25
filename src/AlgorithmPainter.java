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

        for (City c : this.cities) {
            g.fillOval(c.getX() - 3, c.getY() - 3, 7, 7);
        }

        List<Line> lines = algorithm.solveSteps(this.cursor);
        for (Line l : lines) {
            g.setColor(l.getColor());
            g.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
        }
    }
}
