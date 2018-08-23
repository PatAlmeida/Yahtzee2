import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CategoryLabel extends Label {

    private static final Font NORMAL_FONT = Font.font("Times New Roman", FontWeight.NORMAL, 18);
    private static final Font BOLD_FONT = Font.font("Times New Roman", FontWeight.BOLD, 18);
    private static int NUM_DICE = 6;

    private Category category;
    private boolean isTitle, isScore, locked;

    public CategoryLabel(Category cat, boolean title) {

        category = cat;
        isTitle = title;
        isScore = !isTitle;
        locked = false;

        // For now
        if (isTitle) setText(category.toString());
        if (isScore) setText("0");
        setFont(NORMAL_FONT);

    }

    public Category getCategory() { return category; }
    public boolean getLocked() { return locked; }

    public void setLocked(boolean lock) {
        locked = lock;
        setTextFill(Color.RED);
    }

    public void updateScore(int[] values) {
        if (isScore) {
            int[] valCount = new int[NUM_DICE];
            int valSum = 0;
            for (int val : values) {
                valCount[val-1]++;
                valSum += val;
            }
            switch (category) {
                case ONES: setText(Integer.toString(valCount[0] * 1)); break;
                case TWOS: setText(Integer.toString(valCount[1] * 2)); break;
                case THREES: setText(Integer.toString(valCount[2] * 3)); break;
                case FOURS: setText(Integer.toString(valCount[3] * 4)); break;
                case FIVES: setText(Integer.toString(valCount[4] * 5)); break;
                case SIXES: setText(Integer.toString(valCount[5] * 6)); break;
                case SUBTOTAL: setText(Integer.toString(Game.game.getSubtotal())); break;
                case BONUS: setText(Integer.toString(Game.game.getBonus())); break;
                case UPPER: setText(Integer.toString(Game.game.getUpperTotal())); break;
                case TRIPS:
                    boolean found = false;
                    for (int val : valCount) {
                        if (val >= 3) { found = true; break; }
                    }
                    if (found) setText(Integer.toString(valSum));
                    else setText("0");
                    break;
                case QUADS:
                    found = false;
                    for (int val : valCount) {
                        if (val >= 4) { found = true; break; }
                    }
                    if (found) setText(Integer.toString(valSum));
                    else setText("0");
                    break;
                case FULL:
                    int[] cases = new int[NUM_DICE];
                    for (int val : valCount) {
                        cases[val]++;
                    }
                    if ((cases[0] == 4 && cases[1] == 0 && cases[2] == 1 &&
                        cases[3] == 1 && cases[4] == 0 && cases[5] == 0) || cases[5] == 1) {
                        setText("25");
                    } else setText("0");
                    break;
                case SMALL:
                    if ((valCount[0] > 0 && valCount[1] > 0 && valCount[2] > 0 && valCount[3] > 0) ||
                        (valCount[1] > 0 && valCount[2] > 0 && valCount[3] > 0 && valCount[4] > 0) ||
                        (valCount[2] > 0 && valCount[3] > 0 && valCount[4] > 0 && valCount[5] > 0)) {
                        setText("30");
                    } else setText("0");
                    break;
                case LARGE:
                    if ((valCount[0] == 1 && valCount[1] == 1 && valCount[2] == 1 &&
                            valCount[3] == 1 && valCount[4] == 1) ||
                        (valCount[1] == 1 && valCount[2] == 1 && valCount[3] == 1 &&
                            valCount[4] == 1 && valCount[5] == 1)) {
                        setText("40");
                    } else setText("0");
                    break;
                case YAHTZEE:
                    found = false;
                    for (int val : valCount) {
                        if (val == 5) { found = true; break; }
                    }
                    if (found) setText("50");
                    else setText("0");
                    break;
                case CHANCE:
                    setText(Integer.toString(valSum));
                    break;
                case LOWER: setText(Integer.toString(Game.game.getLowerTotal())); break;
                case GRAND: setText(Integer.toString(Game.game.getGrandTotal())); break;
            }
        }
    }

    public void addToGrid(SectionGrid grid, int i) {
        StackPane pane = new StackPane();
        pane.setPrefSize(120, 30);
        pane.setOnMouseEntered(e -> {
            Category cat = category;
            if (cat == Category.ONES || cat == Category.TWOS || cat == Category.THREES ||
                cat == Category.FOURS || cat == Category.FIVES || cat == Category.SIXES ||
                cat == Category.TRIPS || cat == Category.QUADS || cat == Category.FULL ||
                cat == Category.SMALL || cat == Category.LARGE ||
                cat == Category.YAHTZEE || cat == Category.CHANCE) {
                setFont(BOLD_FONT);
                setTextFill(Color.RED);
            }
        });
        pane.setOnMouseExited(e -> {
            Category cat = category;
            if (cat == Category.ONES || cat == Category.TWOS || cat == Category.THREES ||
                cat == Category.FOURS || cat == Category.FIVES || cat == Category.SIXES ||
                cat == Category.TRIPS || cat == Category.QUADS || cat == Category.FULL ||
                cat == Category.SMALL || cat == Category.LARGE ||
                cat == Category.YAHTZEE || cat == Category.CHANCE) {
                setFont(NORMAL_FONT);
                if (!locked) setTextFill(Color.BLACK);
            }
        });
        pane.setOnMouseClicked(e -> {
            Category cat = category;
            if (cat == Category.ONES || cat == Category.TWOS || cat == Category.THREES ||
                cat == Category.FOURS || cat == Category.FIVES || cat == Category.SIXES ||
                cat == Category.TRIPS || cat == Category.QUADS || cat == Category.FULL ||
                cat == Category.SMALL || cat == Category.LARGE ||
                cat == Category.YAHTZEE || cat == Category.CHANCE) {
                if (!locked) Game.game.setLocked(category);
            }
        });
        pane.getChildren().add(this);
        if (isTitle) grid.add(pane, 0, i+1);
        else grid.add(pane, 1, i+1);
    }

    public void resetScore() {
        if (isScore) setText("0");
    }

}
