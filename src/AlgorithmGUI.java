import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AlgorithmGUI extends JFrame implements ActionListener, ChangeListener {
    private TSPAlgorithm algorithm;
    private int cursor = 0;

    private final JButton nextButton;
    private final JButton previousButton;
    private final JLabel cursorLabel;
    private final AlgorithmPainter panel;
    private final JLabel cityCountLabel;
    private final JSlider cityCountSlider;
    private final JLabel stepsLabel;
    private final JLabel stepButtonsLabel;
    private final JLabel solveFullyLabel;
    private final JButton solveFullyButton;

    AlgorithmGUI(TSPAlgorithm algorithm) {
        super();

        this.algorithm = algorithm;

        setTitle("Algorithm visualiser");
        setSize(800, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new FlowLayout());
        setLayout(null);

        panel = new AlgorithmPainter(this.algorithm);
        panel.setBounds(10, 10, 480, 480);
        add(panel);

        stepsLabel = new JLabel("Aantal stappen:");
        stepsLabel.setBounds(510, 20, 120, 20);
        add(stepsLabel);

        cursorLabel = new JLabel("" + this.cursor);
        cursorLabel.setBounds(640, 20, 180, 20);
        add(cursorLabel);

        stepButtonsLabel = new JLabel("Stap voor stap:");
        stepButtonsLabel.setBounds(510, 60, 120, 20);
        add(stepButtonsLabel);

        previousButton = new JButton("<--");
        previousButton.setBounds(640, 60, 50, 20);
        add(previousButton);
        previousButton.addActionListener(this);

        nextButton = new JButton("-->");
        nextButton.setBounds(700, 60, 50, 20);
        add(nextButton);
        nextButton.addActionListener(this);

        cityCountLabel = new JLabel("Aantal punten:");
        cityCountLabel.setBounds(510, 100, 120, 20);
        add(cityCountLabel);

        cityCountSlider = new JSlider(JSlider.HORIZONTAL, 2, 50, 10);
        cityCountSlider.setBounds(633, 100, 125, 20);
        add(cityCountSlider);
        cityCountSlider.addChangeListener(this);

        solveFullyLabel = new JLabel("Algoritme starten:");
        solveFullyLabel.setBounds(510, 140, 120, 20);
        add(solveFullyLabel);

        solveFullyButton = new JButton("Start");
        solveFullyButton.setBounds(640, 138, 110, 24);
        add(solveFullyButton);
        solveFullyButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton) {
            this.cursor += 1;
            this.cursorLabel.setText("" + this.cursor);
            panel.setCursor(this.cursor);
        }

        if(e.getSource() == previousButton) {
            if(this.cursor > 0) {
                this.cursor -= 1;
            }
            this.cursorLabel.setText("" + this.cursor);
            panel.setCursor(this.cursor);
        }

        if(e.getSource() == solveFullyButton) {
            this.cursor = this.algorithm.getCityCount();
            panel.setCursor(this.cursor);
            this.cursorLabel.setText("" + this.cursor);
        }

        repaint();
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if(source == cityCountSlider && !source.getValueIsAdjusting()) {
            List<City> cities = new ArrayList<City>();

            for (int i = 0; i < source.getValue(); i++) {
                cities.add(new City(
                        ThreadLocalRandom.current().nextInt(10, 470),
                        ThreadLocalRandom.current().nextInt(10, 470)
                ));
            }

            this.algorithm.setCities(cities);
            this.panel.setCities(cities);

            repaint();
        }
    }
}
