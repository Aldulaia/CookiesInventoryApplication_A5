package aldulaia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prog24178.labs.objects.Cookies;

/**
 *
 * @author Altaher AL-Dulaimi
 */
public class Assign5FXMLController implements Initializable {

    @FXML
    Button btnAdd, btnSell, btnExit, btnPrint;
    @FXML
    TextField txt1, txt2;
    @FXML
    ComboBox cmbCookies;

    // get selected combo.
    public int cmbChoosen() {
        int selectedIndex = cmbCookies.getSelectionModel().getSelectedIndex();
        int id = 0;
        for (Cookies c : Cookies.values()) {
            if (selectedIndex == c.ordinal()) {
                id = c.getId();
            }
        }
        return id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Files.deleteIfExists(Paths.get("print.dat"));
            Files.deleteIfExists(Paths.get("Cookies.dat"));
        } catch (Exception a) {
            System.out.println(a.getMessage());
        }

        //File creation
        File cookiesFile = new File("Cookies.dat");

        //ComboBox settings
        ObservableList<Cookies> obsList = FXCollections.observableArrayList();
        for (Cookies e : Cookies.values()) {
            obsList.add(e);
        }
        cmbCookies.setItems(obsList);
        cmbCookies.getSelectionModel().select(3);

        //Sell Button
        btnSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                boolean goIn = true;

                //first Error message
                if (txt1.getText().trim().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Data Entry Error");
                    alert.setHeaderText("Please Enter the number of cookies Sold.");
                    alert.showAndWait();
                    txt1.requestFocus();
                    goIn = false; // will not go through TRY, CATCH
                }

                if (goIn) {

                    try {
                        int sell = Integer.parseInt(txt1.getText());
                        boolean checkInside = true;
                        boolean insideWhileCheck = true;
                        boolean checkInsideLastIf = true;
                        boolean lastElse = true;
                        if (sell <= 0) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Data Entry Error");
                            alert.setHeaderText("You must enter a quantity that is greater than 0.");
                            alert.showAndWait();
                            txt1.requestFocus();
                            checkInside = false;
                        } else if (checkInside && cookiesFile.exists()) {

                            Scanner sc = new Scanner(cookiesFile);
                            while (sc.hasNextLine()) {
                                String record = sc.nextLine();
                                String[] field = record.split("\\|");

                                if (cmbChoosen() == Integer.parseInt(field[0])) {
                                    if (Integer.parseInt(field[1]) >= sell) {
                                        insideWhileCheck = false;
                                        System.out.println("It has been sold");
                                        //break;

                                    } else if (Integer.parseInt(field[1]) < sell) {
                                        String name = "";
                                        for (Cookies c : Cookies.values()) {
                                            if (c.getId() == cmbChoosen()) {
                                                name = c.getName();
                                            }
                                        }

                                        int moreThanZero = 0;

                                        if (Integer.parseInt(field[1]) > 0) {
                                            moreThanZero = Integer.parseInt(field[1]);
                                            checkInsideLastIf = false;
                                            Alert alert = new Alert(AlertType.ERROR);
                                            alert.setTitle("Data Entry Error");
                                            alert.setHeaderText("Soory, not enough " + name + "Cookies available to sell " + " You only have " + moreThanZero + " left ");
                                            alert.showAndWait();
                                        }

                                    }

                                }

                            }

                        } else {
                            //last thing fix - try
                            lastElse = false;
                            String name = "";
                            for (Cookies c : Cookies.values()) {
                                if (c.getId() == cmbChoosen()) {
                                    name = c.getName();
                                }
                            }

                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Data Entry Error");
                            alert.setHeaderText("Soory, not enough " + name + " Cookies available to sell ");
                            alert.showAndWait();
                            //  System.out.println("last else");
//                    break;
                        }
                        if (insideWhileCheck && checkInsideLastIf && lastElse) {
                            String name = "";
                            for (Cookies c : Cookies.values()) {
                                if (c.getId() == cmbChoosen()) {
                                    name = c.getName();
                                }
                            }
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Data Entry Error");
                            alert.setHeaderText("Soory, not enough " + name + " Cookies available to sell ");
                            alert.showAndWait();
                            System.out.println("last else");
                        }

                    } catch (NumberFormatException a) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Invalid input");
                        alert.setHeaderText("You must enter a valid numeric value.");
                        alert.showAndWait();
                        txt1.requestFocus();

                    } catch (IOException b) {

                    }
                }
            }

        });

        //Add Button
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                boolean goIn = true;

                //first Error message
                if (txt2.getText().trim().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Data Entry Error");
                    alert.setHeaderText("Please Enter the number of cookies Added.");
                    alert.showAndWait();
                    txt2.requestFocus();
                    goIn = false; // will not go through TRY, CATCH
                }

                if (goIn) {

                    try {
                        int add = Integer.parseInt(txt2.getText());

                        if (add <= 0) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Data Entry Error");
                            alert.setHeaderText("You must enter a quantity that is greater than 0");
                            alert.showAndWait();
                            txt2.requestFocus();

                        } else {
                            FileWriter fw = new FileWriter(cookiesFile, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter output = new PrintWriter(bw);
                            output.println(cmbChoosen() + "|" + add);
                            output.close();

                        }

                    } catch (NumberFormatException a) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Invalid input ");
                        alert.setHeaderText("You must enter a valid numeric value");
                        alert.showAndWait();
                        txt2.requestFocus();

                    } catch (IOException b) {
                    }

                }

            }

        });

        //Exit Button
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit");
                alert.setContentText("Exit Program ?");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(okButton, noButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {

                        System.exit(0);
                    }

                });
            }

        });

        //Print button
        btnPrint.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                int printButtonClicked = 0;
                printButtonClicked += 1;
                CookieInventoryFile c1 = new CookieInventoryFile(cookiesFile);
                File printFile = new File("print.dat");
                c1.writeToFile(printFile);

                Stage secondStage = new Stage();
                secondStage.initModality(Modality.APPLICATION_MODAL);
                VBox vb = new VBox(20);

                Scene secondStageScene = new Scene(vb, 550, 400);
                secondStage.setScene(secondStageScene);
                secondStage.setTitle("Inventory Of Baking");

                String record = "";

                if (printFile.exists()) {
                    try {
                        Scanner sc = new Scanner(printFile);

                        while (sc.hasNextLine()) {

                            record += sc.nextLine() + "\n";
                        }
                    } catch (Exception a) {
                        System.out.println(a.getMessage());
                    }
                }

                Label lb = new Label(record);
                lb.setFont(new Font(24));
                vb.getChildren().add(lb);
                secondStage.show();

            }
        });

    }
}
