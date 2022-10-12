import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main {

    public static int currentIndex;
    public static int compareIndex;

    private static Panel panel;
    private static JCheckBox chooseAnimate;

    private static int sortDelay;

    private JButton sortButton;
    private JComboBox<String> sortOptions;
    private JButton resetButton;
    private JSlider delaySlider;
    private JLabel delayLabel;
    private JSlider sizeSlider;
    private JLabel sizeLabel;

    private int unitWidth;
    private int[] numbers;
    private boolean sorting = false;
    private boolean shuffling = false;
    private String sortChoice;

    public static void main(String[] args) {
        Main gui = new Main();
        gui.go();
    }

    private void go() {
        JFrame frame = new JFrame();
        frame.setTitle("Sorting Algorithm Animator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        frame.getContentPane().add(panel);
        frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        sortButton = new JButton("Sort");
        sortButton.setBounds(10, 10, 80, 30);
        sortButton.addActionListener(new MainActionListener());
        panel.add(sortButton);

        sortOptions = new JComboBox<>(Constants.SORTING_OPTIONS);
        sortOptions.setBounds(90, 10, 110, 32);
        sortOptions.addActionListener(new MainActionListener());
        panel.add(sortOptions);

        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 10, 80, 30);
        resetButton.addActionListener(new MainActionListener());
        panel.add(resetButton);

        chooseAnimate = new JCheckBox("Animate");
        chooseAnimate.setFocusable(false);
        chooseAnimate.setBounds(280, 10, 150, 30);
        panel.add(chooseAnimate);

        delaySlider = new JSlider();
        delaySlider.setMinimum(1);
        delaySlider.setMaximum(100);
        delaySlider.setValue(50);
        delaySlider.setOrientation(SwingConstants.HORIZONTAL);
        delaySlider.setBounds(480, 18, 100, 15);
        delaySlider.addChangeListener(new SliderListener());
        panel.add(delaySlider);

        sortDelay = delaySlider.getValue();

        delayLabel = new JLabel("Delay: " + sortDelay);
        delayLabel.setBounds(405, 18, 100, 15);
        panel.add(delayLabel);

        sizeSlider = new JSlider();
        sizeSlider.setMinimum(1);
        sizeSlider.setMaximum(100);
        sizeSlider.setValue(50);
        sizeSlider.setOrientation(SwingConstants.HORIZONTAL);
        sizeSlider.setBounds(680, 18, 100, 15);
        sizeSlider.addChangeListener(new SliderListener());
        panel.add(sizeSlider);

        unitWidth = sizeSlider.getValue();

        sizeLabel = new JLabel("Size: " + unitWidth);
        sizeLabel.setBounds(615, 18, 100, 15);
        panel.add(sizeLabel);

        numbers = new int[Constants.WINDOW_WIDTH / unitWidth];

        frame.setVisible(true);
        shuffle();
        run();
    }

    private void run() {
        while (true) {
            if (sorting && !shuffling) {
                sort();
            }
            else if (shuffling && !sorting) {
                shuffle();
            }
        }
    }

    private void shuffle() {
        disableUI();

        for (int i = 0; i < numbers.length - 1; i++) {
            currentIndex = i;
            Random random = new Random();
            numbers[i] = random.nextInt(Constants.MAX_VALUE - Constants.MIN_VALUE) + Constants.MIN_VALUE;
            animate();
        }
        enableUI();
        shuffling = false;
    }

    private void sort() {
        disableUI();

        try {
            switch (sortChoice) {
                case "Bubble" -> new Bubble(numbers);
                case "Insertion" -> new Insertion(numbers);
                case "Merge" -> new Merge(numbers);
                case "Quick" -> new Quick(numbers, 0, numbers.length - 1);
                case "Selection" -> new Selection(numbers);
            }
        } catch (NullPointerException ignored) {}

        enableUI();
        sorting = false;
    }

    private void disableUI() {
        sortButton.setEnabled(false);
        resetButton.setEnabled(false);
        sortOptions.setEnabled(false);
    }

    private void enableUI() {
        sortButton.setEnabled(true);
        resetButton.setEnabled(true);
        sortOptions.setEnabled(true);
    }

    private boolean isShuffled() {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static void animate() {
        panel.repaint();

        if (chooseAnimate.isSelected()) {
            try {
                Thread.sleep(sortDelay);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class Panel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(Constants.BACKGROUND_GRADIENT);
            g2d.fillRect(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

            for (int i = 0; i < numbers.length - 1; i++) {
                g.setColor(Constants.DEFAULT_COLOR);

                if (isShuffled()) {
                    if ((sorting || shuffling) && i == currentIndex) {
                        g.setColor(Constants.CURRENT_INDEX_COLOR);

                        if (i == numbers.length - 2) {
                            g.setColor(Constants.DEFAULT_COLOR);
                        }
                    }
                    else if (sorting && i == compareIndex) {
                        g.setColor(Constants.COMPARE_INDEX_COLOR);
                    }
                }

                g.fillRect((i * unitWidth) + unitWidth, Constants.WINDOW_HEIGHT - numbers[i], unitWidth, numbers[i]);
            }
        }
    }

    public class MainActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(sortButton)) {
                sorting = true;
            }
            else if (e.getSource().equals(sortOptions)) {
                sortChoice = (String) sortOptions.getSelectedItem();
            }
            else if (e.getSource().equals(resetButton)) {
                shuffling = true;
            }
        }
    }

    public class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource().equals(delaySlider)) {
                sortDelay = delaySlider.getValue();
                delayLabel.setText("Delay: " + sortDelay);
            }
            else if (e.getSource().equals(sizeSlider)) {
                sizeLabel.setText("Size: " + sizeSlider.getValue());

                if (!sorting) {
                    unitWidth = sizeSlider.getValue();
                    numbers = new int[Constants.WINDOW_WIDTH / unitWidth];
                }
            }
        }
    }
}
