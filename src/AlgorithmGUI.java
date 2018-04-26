import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AlgorithmGUI extends JFrame implements ActionListener, ChangeListener {
    private TSPAlgorithm algorithm;
    private TSPAlgorithmTimer algorithmTimer;
    private int cursor = 0;

    private final JComboBox<String> algorithmSelector;
    private final JButton nextButton;
    private final JButton previousButton;
    private final JLabel cursorLabel;
    private final AlgorithmPainter panel;
    private final JSlider cityCountSlider;
    private final JButton solveFullyButton;
    private final JLabel timeTaken;
    private final JLabel lineLength;

    AlgorithmGUI(TSPAlgorithm algorithm) {
        super();

        this.algorithm = algorithm;
        this.algorithmTimer = new TSPAlgorithmTimer(algorithm);

        setTitle("Algorithm visualiser");
        setSize(800, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new FlowLayout());
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        panel = new AlgorithmPainter(this.algorithm);
        panel.setBounds(10, 10, 480, 480);
        add(panel);

        JLabel algorithmSelectorLabel = new JLabel("Algorithme:");
        algorithmSelectorLabel.setBounds(510, 20, 120, 20);
        add(algorithmSelectorLabel);

        algorithmSelector = new JComboBox<>(new String[] { "Greedy", "Random" });
        algorithmSelector.setBounds(640, 20, 110, 20);
        algorithmSelector.addActionListener(this);
        add(algorithmSelector);

        JLabel stepsLabel = new JLabel("Aantal stappen:");
        stepsLabel.setBounds(510, 50, 120, 20);
        add(stepsLabel);

        cursorLabel = new JLabel("" + this.cursor);
        cursorLabel.setBounds(640, 50, 180, 20);
        add(cursorLabel);

        JLabel stepButtonsLabel = new JLabel("Stap voor stap:");
        stepButtonsLabel.setBounds(510, 80, 120, 20);
        add(stepButtonsLabel);

        previousButton = new JButton("<--");
        ImageIcon previousIcon = loadIcon("back.png");
        if(previousIcon != null) {
            previousButton.setText("");
            previousButton.setIcon(previousIcon);
        }
        previousButton.setBounds(640, 80, 50, 20);
        add(previousButton);
        previousButton.addActionListener(this);

        nextButton = new JButton("-->");
        ImageIcon nextIcon = loadIcon("next.png");
        if(nextIcon != null) {
            nextButton.setText("");
            nextButton.setIcon(nextIcon);
        }
        nextButton.setBounds(700, 80, 50, 20);
        add(nextButton);
        nextButton.addActionListener(this);

        JLabel cityCountLabel = new JLabel("Aantal punten:");
        cityCountLabel.setBounds(510, 110, 120, 20);
        add(cityCountLabel);

        cityCountSlider = new JSlider(JSlider.HORIZONTAL, 2, 100, 10);
        cityCountSlider.setBounds(633, 110, 125, 20);
        add(cityCountSlider);
        cityCountSlider.addChangeListener(this);

        JLabel solveFullyLabel = new JLabel("Algoritme starten:");
        solveFullyLabel.setBounds(510, 140, 120, 20);
        add(solveFullyLabel);

        solveFullyButton = new JButton("Start");
        solveFullyButton.setBounds(640, 138, 110, 24);
        add(solveFullyButton);
        solveFullyButton.addActionListener(this);

        JLabel timeLabel = new JLabel("Tijd: ");
        timeLabel.setBounds(510, 170, 120, 20);
        add(timeLabel);

        timeTaken = new JLabel(this.algorithmTimer.getHumanReadableAverageTime(algorithm.getCityList(), 500));
        timeTaken.setBounds(640, 170, 120, 20);
        add(timeTaken);

        JLabel lineLengthLabel = new JLabel("Lengte: ");
        lineLengthLabel.setBounds(510, 210, 120, 20);
        add(lineLengthLabel);

        lineLength = new JLabel("" + this.algorithm.getLineLength());
        lineLength.setBounds(640, 210, 120, 20);
        add(lineLength);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton) {
            this.cursor += 1;
            this.cursorLabel.setText("" + this.cursor);
            this.lineLength.setText("" + this.algorithm.getLineLength());
            panel.setCursor(this.cursor);
        }

        if(e.getSource() == previousButton) {
            if(this.cursor > 0) {
                this.cursor -= 1;
            }
            this.cursorLabel.setText("" + this.cursor);
            this.lineLength.setText("" + this.algorithm.getLineLength());
            panel.setCursor(this.cursor);
        }

        if(e.getSource() == solveFullyButton) {
            this.cursor = this.algorithm.getCityCount();
            panel.setCursor(this.cursor);
            this.cursorLabel.setText("" + this.cursor);
            this.lineLength.setText("" + this.algorithm.getLineLength());
        }

        if(e.getSource() == algorithmSelector) {
            @SuppressWarnings("unchecked")
            JComboBox<String> source = (JComboBox<String>) e.getSource();
            String selected = (String) source.getSelectedItem();
            if(selected == null) return;

            if(selected.equals(GreedyAlgorithm.NAME)) {
                System.out.println("WEEEEE");
//                List<City> cities =
                this.algorithm = new GreedyAlgorithm(new ArrayList<>());
            }
            if(selected.equals(RandomAlgorithm.NAME)) {
                System.out.println("WOOOOO");
                this.algorithm = new RandomAlgorithm(new ArrayList<>());
            }
        }

        repaint();
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if(source == cityCountSlider && !source.getValueIsAdjusting()) {
            List<City> cities = new ArrayList<>();

            for (int i = 0; i < source.getValue(); i++) {
                cities.add(new City(
                        ThreadLocalRandom.current().nextInt(10, 470),
                        ThreadLocalRandom.current().nextInt(10, 470)
                ));
            }

            this.panel.setCities(cities);

            this.timeTaken.setText(this.algorithmTimer.getHumanReadableAverageTime(cities, 500));

            this.lineLength.setText("" + this.algorithm.getLineLength());

            repaint();
        }
    }

    private ImageIcon loadIcon(String name) {
        try {
            java.net.URL path = this.getClass().getResource(name);
            return new ImageIcon(path);
        }
        catch(Exception e) {
            System.out.println("Failed to load icon: " + name);
            return null;
        }
    }
}
