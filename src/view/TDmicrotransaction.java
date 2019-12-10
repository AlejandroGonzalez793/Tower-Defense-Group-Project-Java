package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TDmicrotransaction extends Stage {
	private BorderPane microPane = new BorderPane();
	private Label currentPay;
	private Label currentGold;
	TextField nameField;
	TextField cardField;
	TextField dateField;
	TextField codeField;
	private boolean bought = false;
	private GridPane stageBox;

	public TDmicrotransaction() {
		Label payLabel = new Label("Pay Amount: $");
		Label goldLabel = new Label("Gold Amount: ");
		currentPay = new Label("19.99");
		currentGold = new Label("500");

		HBox payBox = new HBox(payLabel, currentPay);
		HBox goldBox = new HBox(goldLabel, currentGold);

		Button micro1Btn = new Button("AustensAustere\n  100 Gold\n  +0 Gold\n $4.99");
		micro1Btn.setPadding(new Insets(10, 10, 10, 10));
		micro1Btn.setOnAction(new MicroButton("4.99", "100"));

		Button micro2Btn = new Button("JamesJicama\n  200 Gold\n  +50 Gold\n $9.99");
		micro2Btn.setPadding(new Insets(10, 10, 10, 10));
		micro2Btn.setOnAction(new MicroButton("9.99", "250"));

		Button micro3Btn = new Button("MisurdasMatsuda\n  400 Gold\n  +100 Gold\n $19.99");
		micro3Btn.setPadding(new Insets(10, 10, 10, 10));
		micro3Btn.setOnAction(new MicroButton("19.99", "500"));

		Button micro4Btn = new Button("TimsTorture\n  1000 Gold\n  +250 Gold\n $49.99");
		micro4Btn.setPadding(new Insets(10, 10, 10, 10));
		micro4Btn.setOnAction(new MicroButton("49.99", "1250"));

		Button micro5Btn = new Button("RyansRoast\n  2000 Gold\n  +500 Gold\n $99.99");
		micro5Btn.setPadding(new Insets(10, 10, 10, 10));
		micro5Btn.setOnAction(new MicroButton("99.99", "2500"));

		HBox microBox = new HBox(micro1Btn, micro2Btn, micro3Btn, micro4Btn, micro5Btn);
		microBox.setAlignment(Pos.TOP_CENTER);
		microBox.setSpacing(5);

		Image image = null;
		try {
			image = new Image(new FileInputStream("resources/images/credit_cards.jpg"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(75);
		imageView.setFitWidth(400);

		Label name = new Label("Name (as it appears on your card)");
		nameField = new TextField();
		Label card = new Label("Card number (no dashes or spaces)");
		cardField = new TextField();
		Label date = new Label("Expiration date (m/y)");
		dateField = new TextField();
		Label code = new Label("CVC");
		codeField = new TextField();

		stageBox = new GridPane();
		stageBox.add(payBox, 0, 0);
		stageBox.add(goldBox, 0, 1);
		stageBox.add(imageView, 0, 2);
		stageBox.add(name, 0, 3);
		stageBox.add(nameField, 0, 4);
		stageBox.add(card, 0, 5);
		stageBox.add(cardField, 0, 6);
		stageBox.add(date, 0, 7);
		stageBox.add(dateField, 0, 9);
		stageBox.add(code, 0, 10);
		stageBox.add(codeField, 0, 11);
		stageBox.setAlignment(Pos.CENTER);
		stageBox.setVgap(10);

		GridPane.setHalignment(imageView, HPos.CENTER);
		BorderPane.setAlignment(microBox, Pos.CENTER);

		Button okBtn = new Button("Buy Towers");
		okBtn.setPadding(new Insets(10, 10, 10, 10));
		okBtn.setOnAction(e -> {
			if (!nameField.getText().isEmpty() && cardField.getText().length() == 16 && isNumeric(cardField.getText())
					&& !dateField.getText().isEmpty() && dateField.getText().length() < 6
					&& codeField.getText().length() == 3 && isNumeric(codeField.getText())) {
				bought = true;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thank you for your purchase!");
				alert.setHeaderText(
						"$" + currentPay.getText() + " has been successfully been withdrawn from your account.");
				alert.setContentText(currentGold.getText() + " gold will be added to your game!");
				alert.showAndWait();

				Node source = (Node) e.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			} else {
				nameField.clear();
				cardField.clear();
				dateField.clear();
				codeField.clear();

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Invalid information");
				alert.setContentText("Please try again");
				alert.showAndWait();
			}
		});

		Button cancelBtn = new Button("Cancel");
		cancelBtn.setAlignment(Pos.BASELINE_RIGHT);
		cancelBtn.setPadding(new Insets(10, 10, 10, 10));
		cancelBtn.setOnAction(e -> {
			Node source = (Node) e.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		});

		BorderPane commandPane = new BorderPane();
		commandPane.setLeft(okBtn);
		commandPane.setRight(cancelBtn);

		microPane.setTop(microBox);
		microPane.setCenter(stageBox);
		microPane.setBottom(commandPane);
		BorderPane.setMargin(microBox, new Insets(10, 0, 0, 0));
		BorderPane.setMargin(commandPane, new Insets(20, 20, 20, 20));
		Scene scene = new Scene(microPane);
		this.setScene(scene);
	}

	public boolean isNumeric(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isBought() {
		return bought;
	}

	public int getBoughtGold() {
		return Integer.parseInt(currentGold.getText());
	}

	class MicroButton implements EventHandler<ActionEvent> {
		private String payAmount;
		private String goldAmount;

		public MicroButton(String payAmount, String goldAmount) {
			this.payAmount = payAmount;
			this.goldAmount = goldAmount;
		}

		public void handle(ActionEvent e) {
			currentPay.setText(payAmount);
			currentGold.setText(goldAmount);
		}
	}
}
