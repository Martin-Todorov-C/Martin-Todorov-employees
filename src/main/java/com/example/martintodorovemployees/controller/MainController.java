package com.example.martintodorovemployees.controller;

import com.example.martintodorovemployees.service.CsvLoader;
import com.example.martintodorovemployees.service.PairCalculator;
import com.example.martintodorovemployees.model.PairDetail;
import com.example.martintodorovemployees.model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller for the main UI.
 *
 * Handles the “Select CSV File” button,
 * pulls data with CsvLoader, computes overlaps,
 * and populates the TableView.
 */
public class MainController {
    @FXML
    private Button selectFileBtn;
    @FXML
    private TableView<PairDetail> table;
    @FXML
    private TableColumn<PairDetail, Integer> colEmp1;
    @FXML
    private TableColumn<PairDetail, Integer> colEmp2;
    @FXML
    private TableColumn<PairDetail, Integer> colProj;
    @FXML
    private TableColumn<PairDetail, Long> colDays;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Runs automatically after FXML fields are injected.
     * Set up columns and wire button click.
     */
    @FXML
    public void initialize() {
        colEmp1.setCellValueFactory(new PropertyValueFactory<>("emp1"));
        colEmp2.setCellValueFactory(new PropertyValueFactory<>("emp2"));
        colProj.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("days"));

        // Start with an empty table
        table.setItems(FXCollections.observableArrayList());

        // When the user clicks “Select CSV File”
        selectFileBtn.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv")
            );
            File file = chooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    // Read raw records from CSV
                    List<Record> records = CsvLoader.load(file);
                    // Compute each pair’s shared days
                    List<PairDetail> details = PairCalculator.calculate(records);
                    // Display in the table
                    ObservableList<PairDetail> data = FXCollections.observableArrayList(details);
                    table.setItems(data);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}