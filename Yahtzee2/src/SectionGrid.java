import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SectionGrid extends GridPane {

    private static final int NUM_CATEGORIES = 9;

    private String sectionName;
    private Label titleLabel;
    private CategoryLabel categoryTitles[];
    private CategoryLabel categoryScores[];

    public SectionGrid(String name) {
        setupGrid(name);
        addTitleLabel();
        addSectionLabels();
    }

    private void setupGrid(String name) {
        sectionName = name;
        setAlignment(Pos.CENTER);
    }

    private void addTitleLabel() {
        titleLabel = new Label(sectionName + " Section");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        titleLabel.setPadding(new Insets(5));
        setHalignment(titleLabel, HPos.CENTER);
        add(titleLabel, 0, 0, 2, 1);
    }

    public void setLocked(Category cat) {
        for (CategoryLabel label : categoryTitles) {
            if (cat == label.getCategory()) label.setLocked(true);
        }
        for (CategoryLabel label : categoryScores) {
            if (cat == label.getCategory()) label.setLocked(true);
        }
    }

    public int getSubtotal() {
        int sub = 0;
        for (CategoryLabel label : categoryScores) {
            if (label.getLocked()) {
                Category cat = label.getCategory();
                if (cat == Category.ONES || cat == Category.TWOS || cat == Category.THREES ||
                    cat == Category.FOURS || cat == Category.FIVES || cat == Category.SIXES) {
                    sub += Integer.parseInt(label.getText());
                }
            }
        }
        return sub;
    }

    public int getLowerTotal() {
        int lower = 0;
        for (int i = 0; i < NUM_CATEGORIES; i++) {
            if (i < 8 && categoryScores[i].getLocked()) {
                lower += Integer.parseInt(categoryScores[i].getText());
            }
        }
        return lower;
    }

    public void updateScores(int[] values) {
        for (CategoryLabel label : categoryScores) {
            if (!label.getLocked()) label.updateScore(values);
        }
    }

    public void resetScores() {
        for (CategoryLabel label : categoryScores) {
            if (!label.getLocked()) {
                Category cat = label.getCategory();
                if (cat != Category.SUBTOTAL && cat != Category.BONUS && cat != Category.UPPER &&
                    cat != Category.LOWER && cat != Category.GRAND) {
                    label.resetScore();
                }
            }
        }
    }

    private void addSectionLabels() {

        categoryTitles = new CategoryLabel[NUM_CATEGORIES];
        categoryScores = new CategoryLabel[NUM_CATEGORIES];

        if (sectionName.equals("Upper")) {
            categoryTitles[0] = new CategoryLabel(Category.ONES, true);
            categoryScores[0] = new CategoryLabel(Category.ONES, false);
            categoryTitles[1] = new CategoryLabel(Category.TWOS, true);
            categoryScores[1] = new CategoryLabel(Category.TWOS, false);
            categoryTitles[2] = new CategoryLabel(Category.THREES, true);
            categoryScores[2] = new CategoryLabel(Category.THREES, false);
            categoryTitles[3] = new CategoryLabel(Category.FOURS, true);
            categoryScores[3] = new CategoryLabel(Category.FOURS, false);
            categoryTitles[4] = new CategoryLabel(Category.FIVES, true);
            categoryScores[4] = new CategoryLabel(Category.FIVES, false);
            categoryTitles[5] = new CategoryLabel(Category.SIXES, true);
            categoryScores[5] = new CategoryLabel(Category.SIXES, false);
            categoryTitles[6] = new CategoryLabel(Category.SUBTOTAL, true);
            categoryScores[6] = new CategoryLabel(Category.SUBTOTAL, false);
            categoryTitles[7] = new CategoryLabel(Category.BONUS, true);
            categoryScores[7] = new CategoryLabel(Category.BONUS, false);
            categoryTitles[8] = new CategoryLabel(Category.UPPER, true);
            categoryScores[8] = new CategoryLabel(Category.UPPER, false);
        } else if (sectionName.equals("Lower")) {
            categoryTitles[0] = new CategoryLabel(Category.TRIPS, true);
            categoryScores[0] = new CategoryLabel(Category.TRIPS, false);
            categoryTitles[1] = new CategoryLabel(Category.QUADS, true);
            categoryScores[1] = new CategoryLabel(Category.QUADS, false);
            categoryTitles[2] = new CategoryLabel(Category.FULL, true);
            categoryScores[2] = new CategoryLabel(Category.FULL, false);
            categoryTitles[3] = new CategoryLabel(Category.SMALL, true);
            categoryScores[3] = new CategoryLabel(Category.SMALL, false);
            categoryTitles[4] = new CategoryLabel(Category.LARGE, true);
            categoryScores[4] = new CategoryLabel(Category.LARGE, false);
            categoryTitles[5] = new CategoryLabel(Category.YAHTZEE, true);
            categoryScores[5] = new CategoryLabel(Category.YAHTZEE, false);
            categoryTitles[6] = new CategoryLabel(Category.CHANCE, true);
            categoryScores[6] = new CategoryLabel(Category.CHANCE, false);
            categoryTitles[7] = new CategoryLabel(Category.LOWER, true);
            categoryScores[7] = new CategoryLabel(Category.LOWER, false);
            categoryTitles[8] = new CategoryLabel(Category.GRAND, true);
            categoryScores[8] = new CategoryLabel(Category.GRAND, false);
        }

        for (int i = 0; i < NUM_CATEGORIES; i++) {
            categoryTitles[i].addToGrid(this, i);
            categoryScores[i].addToGrid(this, i);
        }

    }

}
