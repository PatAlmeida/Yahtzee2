import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DicePane extends HBox {

    private static final int NUM_DICE = 5;

    private DiceImageView[] diceViews;

    public DicePane() {
        setAlignment(Pos.CENTER);
        addDiceViews();
    }

    private void addDiceViews() {
        diceViews = new DiceImageView[NUM_DICE];
        for (int i = 0; i < NUM_DICE; i++) {
            diceViews[i] = new DiceImageView(1);
            diceViews[i].addToDicePane(this);
        }
    }

    public void reset() {
        for (DiceImageView view : diceViews) {
            view.reset();
        }
    }

    public int[] getDiceValues() {
        int[] vals = new int[NUM_DICE];
        for (int i = 0; i < NUM_DICE; i++) {
            vals[i] = diceViews[i].getValue();
        }
        return vals;
    }

    public void roll() {
        for (int i = 0; i < NUM_DICE; i++) {
            diceViews[i].roll();
        }
    }

}
