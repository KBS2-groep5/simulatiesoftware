import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AlgorithmPainter extends JPanel {
    private List<City> cities;
    private List<Line> path;

    AlgorithmPainter(List<City> cities, List<Line> path) {
        this.cities = cities;
        this.path = path;
        setPreferredSize(new Dimension(480, 480));
        setBackground(Color.WHITE);
    }

    void setCities(List<City> cities) {
        this.cities = cities;
    }

    void setPath(List<Line> path) {
        this.path = path;
    }
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (Line l : this.path) {
            g.setColor(l.getColor());
            g.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
        }

        for (City c : this.cities) {
            g.fillOval(c.getX() - 3, c.getY() - 3, 7, 7);
        }
    }
}
