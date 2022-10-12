import java.awt.*;

public class Constants {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int MAX_VALUE = 450;
    public static final int MIN_VALUE = 25;
    public static final String[] SORTING_OPTIONS = {"Choose", "Bubble", "Insertion", "Merge", "Quick", "Selection"};

    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final Color CURRENT_INDEX_COLOR = Color.RED;
    public static final Color COMPARE_INDEX_COLOR = Color.orange;
    public static final GradientPaint BACKGROUND_GRADIENT = new GradientPaint(0, 0, Color.lightGray, WINDOW_WIDTH, WINDOW_HEIGHT, Color.darkGray);
}