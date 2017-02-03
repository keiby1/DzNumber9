package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.model.*;


public class Controller {
    //список элементов управления
    @FXML
    private Button buttonAddWarrior1;
    @FXML
    private Button buttonAddWarrior2;
    @FXML
    private Button buttonBattle;
    @FXML
    private Button removeWarriorButton1;
    @FXML
    private Button removeWarriorButton2;
    @FXML
    private Button infoButton1;
    @FXML
    private Button infoButton2;
    @FXML
    private ComboBox<String> comboBoxArrayWarrior1;
    @FXML
    private ComboBox<String> comboBoxArrayWarrior2;
    @FXML
    private TextField nameSquad1;
    @FXML
    private TextField nameSquad2;
    @FXML
    private ListView<String> listViewSquad1;
    @FXML
    private ListView<String> listViewSquad2;
    @FXML
    private TextArea textArea;

    //два списка воинов
    ObservableList<String> listSquad1 = FXCollections.observableArrayList();
    ObservableList<String> listSquad2 = FXCollections.observableArrayList();

    private void addWarrior(ObservableList<String> listSquad, ListView<String> listViewSquad, ComboBox<String> comboBoxArrayWarrior) {
        String getValueComboBox = comboBoxArrayWarrior.getValue();
        listSquad.add(getValueComboBox);
        listViewSquad.setItems(listSquad);
    }

    private void removeWarrior(ListView<String> listViewSquad, ObservableList<String> listSquad) {
        int selectedId = listViewSquad.getSelectionModel().getSelectedIndex();
        String itemToRemove = listViewSquad.getSelectionModel().getSelectedItems().toString();
        listViewSquad.getItems().remove(selectedId);
        listSquad.remove(itemToRemove);
    }

    private boolean step(Squad squad1, Squad squad2) {
        Warrior w1 = squad1.getRandomWarrior(),
                w2 = squad2.getRandomWarrior();

        textArea.appendText(w1.toString() + " атакует " + w2.toString() + "\r\n");
        textArea.appendText(w2.toString() + " HP: " + w2.takeDamage(w1.attack()) + "\r\n");

        if (!squad2.hasAliveWarriors()) {
            textArea.appendText("Команда " + w1.getSquadName() + " победила!\r\n");
            return true;
        }
        return false;
    }

    private boolean fight(Squad squad1, Squad squad2) {
        if (step(squad1, squad2))
            return true;
        if (step(squad2, squad1))
            return true;
        return false;
    }

    @FXML
    public void initialize() {
        comboBoxArrayWarrior1.setItems(FXCollections.observableArrayList(new WarriorArrayFactory().getTypeWarrior()));
        comboBoxArrayWarrior2.setItems(FXCollections.observableArrayList(new WarriorArrayFactory().getTypeWarrior()));

        buttonAddWarrior1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addWarrior(listSquad1, listViewSquad1, comboBoxArrayWarrior1);
            }
        });

        buttonAddWarrior2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addWarrior(listSquad2, listViewSquad2, comboBoxArrayWarrior2);
            }
        });

        buttonBattle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        textArea.clear();
                        Squad squad1 = new Squad();
                        Squad squad2 = new Squad();
                        squad1.createSquad(listSquad1, nameSquad1.getText());
                        squad2.createSquad(listSquad2, nameSquad2.getText());

                        while (squad1.hasAliveWarriors() && squad2.hasAliveWarriors()) {
                            if (fight(squad1, squad2))
                                break;
                        }
                    }
                }
        );

        removeWarriorButton1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeWarrior(listViewSquad1, listSquad1);
            }
        });

        removeWarriorButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeWarrior(listViewSquad2, listSquad2);
            }
        });
    }
}
