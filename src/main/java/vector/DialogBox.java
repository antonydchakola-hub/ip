package vector;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Make the avatar circular
        Circle clip = new Circle(49.5, 49.5, 49.5);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user dialog box.
     *
     * @param text The text to display.
     * @param img The image representing the speaker.
     * @return A dialog box representing the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("user-bubble");
        return db;
    }

    /**
     * Changes the dialog style based on the command type.
     *
     * @param commandType The type of the command.
     */
    private void changeDialogStyle(String commandType) {
        switch (commandType) {
            case "AddCommand":
                dialog.getStyleClass().add("add-label");
                break;
            case "MarkCommand":
                dialog.getStyleClass().add("marked-label");
                break;
            case "DeleteCommand":
                dialog.getStyleClass().add("delete-label");
                break;
            case "Error":
                dialog.getStyleClass().add("error-label");
                break;
            default:
                // Do nothing
        }
    }

    /**
     * Creates a Vector dialog box.
     *
     * @param text The text to display.
     * @param img The image representing the speaker.
     * @param commandType The command type to determine styling.
     * @return A dialog box representing Vector.
     */
    public static DialogBox getVectorDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("vector-bubble");
        db.changeDialogStyle(commandType);
        return db;
    }
}
