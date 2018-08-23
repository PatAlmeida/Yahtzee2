import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RollButton extends Button {

    public RollButton() {

        super("Roll (3)");

        setPrefSize(150, 20);
        setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        setOnAction(e -> {
            Game.game.roll();
        });

    }

    public void addToPane(YahtzeeWindow window) {
        addSeparator(window);
        window.getChildren().add(this);
        addSeparator(window);
    }

    public void update(int rollCount) {
        setText("Roll (" + rollCount + ")");
    }

    private void addSeparator(YahtzeeWindow window) {
        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setVisible(false);
        window.getChildren().add(separator);
    }

}
