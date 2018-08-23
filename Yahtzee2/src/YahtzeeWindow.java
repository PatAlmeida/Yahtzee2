import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class YahtzeeWindow extends VBox {

    private Label titleLabel;
    private SectionGrid upperSectionGrid, lowerSectionGrid;
    private DicePane dicePane;
    private RollButton rollButton;

    public YahtzeeWindow() {
        setAlignment(Pos.CENTER);
        addTitleLabel();
        addSections();
        addDice();
        addButton();
    }

    private void addTitleLabel() {
        titleLabel = new Label("JavaFX Yahtzee");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        titleLabel.setPadding(new Insets(10));
        getChildren().add(titleLabel);
    }

    private void addSections() {
        upperSectionGrid = new SectionGrid("Upper");
        lowerSectionGrid = new SectionGrid("Lower");
        HBox sections = new HBox();
        sections.setAlignment(Pos.CENTER);
        sections.getChildren().addAll(upperSectionGrid, lowerSectionGrid);
        getChildren().add(sections);
    }

    public void roll() {
        dicePane.roll();
    }

    public int getLowerTotal() {
        return lowerSectionGrid.getLowerTotal();
    }

    public void updateScores() {
        upperSectionGrid.updateScores(dicePane.getDiceValues());
        lowerSectionGrid.updateScores(dicePane.getDiceValues());
    }

    public void updateButton(int rollCount) {
        rollButton.update(rollCount);
    }

    public int getSubtotal() {
        return upperSectionGrid.getSubtotal();
    }

    public void setLocked(Category cat) {
        upperSectionGrid.setLocked(cat);
        lowerSectionGrid.setLocked(cat);
    }

    public void resetScores() {
        upperSectionGrid.resetScores();
        lowerSectionGrid.resetScores();
    }

    public void resetDice() {
        dicePane.reset();
    }

    private void addDice() {
        dicePane = new DicePane();
        getChildren().add(dicePane);
    }

    private void addButton() {
        rollButton = new RollButton();
        rollButton.addToPane(this);
    }

}
