package fi.tuni.tamk.tiko.objectorientedprogramming;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.HBox;
import java.io.FileWriter;
import java.io.IOException;
import fi.tuni.tamk.tiko.parser.*;


/**
 * Class ShoppingList creates attributes for javafx program
 * <p>
 * ShoppingList Class is the main class for GUI. It has attributes and methods
 * which create GUI.
 * </p>
 * @author Samu Willman
 * @version 4.0
 */
public class ShoppingList extends Application {

    private HBox hbox = new HBox();
    private TableColumn productCol = new TableColumn("Product");
    private TableColumn amountCol = new TableColumn("Amount");
    private TextField addProduct = new TextField();
    private TextField addAmount = new TextField();
    private String writeString = "";

    private TableView<Item> table = new TableView<>();
    private ObservableList<Item> data =
            FXCollections.observableArrayList(
                    new Item("Milk", "2"),
                    new Item("Beer", "7"),
                    new Item("Energy Drink", "20"),
                    new Item("Chicken", "1"),
                    new Item("Juice", "3"),
                    new Item("Bread", "3")
            );

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start method that creates the javafx program
     * <p>
     * Creates table, columns, textfield and buttons.
     * </p>
     */
    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());

        setStage(stage);

        table.setEditable(true);


        productCol.setMinWidth(100);
        productCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("product"));
        productCol.setMinWidth(215);
        productCol.setMaxWidth(430);

        productCol.setCellFactory(TextFieldTableCell.forTableColumn());
        productCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Item, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setProduct(t.getNewValue()));

        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("amount"));
        amountCol.setMinWidth(100);
        amountCol.setMaxWidth(100);

        amountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        amountCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Item, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAmount(t.getNewValue())
        );


        table.setItems(data);
        table.getColumns().addAll(productCol, amountCol);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addProduct.setPromptText("Product");
        addProduct.setMaxWidth(productCol.getPrefWidth());
        addAmount.setMaxWidth(amountCol.getPrefWidth());
        addAmount.setPromptText("Amount");


        Button addButton = new Button("Add product");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (addProduct.getText().equals("")) {

                } else {
                    data.add(new Item(addProduct.getText(), addAmount.getText()));
                    addProduct.clear();
                    addAmount.clear();
                }
            }
        });

        Button removeAllButton = new Button("Remove all");

        removeAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                data.clear();
            }
        });

        Button WriteJson = new Button("JSON format");

        WriteJson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                try {
                    String file = "Shopping_List.json";

                    FileWriter fileWriter = null;

                    JsonObject jsonObj = new JsonObject();

                    fileWriter = new FileWriter(file);

                    jsonObj.setSize(data.size());

                    for(int i = 0; i < data.size(); i++) {
                        jsonObj.add(data.get(i));
                        writeString = jsonObj.toStringArray();
                    }

                    fileWriter.write(writeString);

                    fileWriter.close();

                } catch (IOException | NullPointerException e) {

                    e.printStackTrace();

                }
            }
        });

        hbox.setSpacing(2);

        hbox.getChildren().addAll(addProduct, addAmount, addButton, removeAllButton, WriteJson);

        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hbox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method used for modifying stage.
     * @param stage receives stage from start class.
     */
    public void setStage(Stage stage) {
        stage.setTitle("Shopping List");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(true);
        stage.setWidth(450);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMinWidth(450);
        stage.setMaxHeight(500);
        stage.setMaxWidth(450);
        stage.centerOnScreen();
    }
}
