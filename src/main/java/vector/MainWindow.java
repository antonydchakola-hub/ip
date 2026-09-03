package vector;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Vector vector;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image vectorImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Vector instance.
     *
     * @param v The Vector logic component.
     */
    public void setVector(Vector v) {
        vector = v;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Vector's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = vector.getResponse(input);
        String commandType = vector.getCommandType();

        try {
            AudioClip sendSound = new AudioClip(this.getClass().getResource("/audio/send.wav").toExternalForm());
            sendSound.play();
        } catch (Exception e) {
            // Ignore sound error
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVectorDialog(response, vectorImage, commandType)
        );
        userInput.clear();
    }
}
