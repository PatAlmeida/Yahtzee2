import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DiceImageView extends ImageView {

    private static final int SIZE = 80;
    private static final int NUM_SIDES = 6;

    private int dieNum;
    private boolean hovering, clicked;

    public DiceImageView(int die) {

        super(new Image("file:images/" + die + ".png", SIZE, SIZE, true, true));

        dieNum = die;
        hovering = false;
        clicked = false;

    }

    public int getValue() { return dieNum; }

    private void updateImage() {
        if (clicked) {
            setImage(new Image("file:images/" + dieNum + "c.png", SIZE, SIZE, true, true));
        } else if (hovering) {
            setImage(new Image("file:images/" + dieNum + "h.png", SIZE, SIZE, true, true));
        } else {
            setImage(new Image("file:images/" + dieNum + ".png", SIZE, SIZE, true, true));
        }
    }

    public void reset() {
        clicked = false;
        dieNum = 1;
        setImage(new Image("file:images/1.png", SIZE, SIZE, true, true));
    }

    public void roll() {
        if (!clicked) {
            dieNum = (int) (Math.random() * NUM_SIDES + 1);
            updateImage();
        }
    }

    public void addToDicePane(DicePane dicePane) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5));
        pane.setOnMouseEntered(e -> {
            hovering = true;
            updateImage();
        });
        pane.setOnMouseExited(e -> {
            hovering = false;
            updateImage();
        });
        pane.setOnMouseClicked(e -> {
            clicked = !clicked;
            updateImage();
        });
        pane.getChildren().add(this);
        dicePane.getChildren().add(pane);
    }

}
