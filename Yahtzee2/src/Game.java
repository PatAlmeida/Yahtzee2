import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game {

    public static Game game;

    private YahtzeeWindow window;
    private int rollCount;

    public Game(Stage stage) {
        makeAndShowWindow(stage);
        rollCount = 3;
    }

    private void makeAndShowWindow(Stage stage) {
        StackPane root = new StackPane();
        window = new YahtzeeWindow();
        root.getChildren().add(window);
        stage.setScene(new Scene(root));
        stage.setTitle("Yahtzee");
        stage.show();
    }

    public void roll() {
        if (rollCount > 0) {
            window.roll();
            window.updateScores();
            rollCount--;
            window.updateButton(rollCount);
        }
    }

    public int getBonus() {
        if (getSubtotal() > 62) return 35;
        else return 0;
    }

    public int getUpperTotal() {
        return getSubtotal() + getBonus();
    }

    public int getLowerTotal() {
        return window.getLowerTotal();
    }

    public int getGrandTotal() {
        return getUpperTotal() + getLowerTotal();
    }

    public int getSubtotal() {
        return window.getSubtotal();
    }

    public void setLocked(Category cat) {
        window.setLocked(cat);
        window.updateScores();
        window.resetDice();
        rollCount = 3;
        window.updateButton(rollCount);
        window.resetScores();
    }

}
