package com.example.assignment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoryView extends TableView implements ModelSubscriber {
    protected Model model;

    public HistoryView() {
        this.setEditable(false);

        TableColumn taskCol = new TableColumn("Task Name");
        taskCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, String>("taskName"));

        TableColumn monthCol = new TableColumn("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, Integer>("month"));

        TableColumn yearCol = new TableColumn("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, Integer>("year"));

        TableColumn totalCol = new TableColumn("Total Time");
        totalCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, String>("totalDuration"));

        taskCol.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        monthCol.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        yearCol.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        totalCol.prefWidthProperty().bind(this.widthProperty().multiply(0.25));

        this.getColumns().addAll(taskCol, monthCol, yearCol, totalCol);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setController(Controller newController) {
    }

    //update the history table
    public void updateHistory() {
        this.refresh();
        ObservableList<TaskMonthlyDuration> list = FXCollections.observableArrayList();;
        for (Task task : model.getTasks()){
            list.addAll(task.monthlyDurations);
        }
        this.setItems(list);
    }

    @Override
    public void modelTaskAdded(Task task) {
    }

    @Override
    public void modelTaskHistoryChanged() {
        updateHistory();
    }
}
