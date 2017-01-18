package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


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

    private void addWarrior(String getValueComboBox, ObservableList<String> listSquad, ListView<String> listViewSquad) {
        try {
            if (getValueComboBox.equals("Берсерк"))
                listSquad.add("Берсерк");
            else if (getValueComboBox.equals("Лучник"))
                listSquad.add("Лучник");
            else if (getValueComboBox.equals("Викинг"))
                listSquad.add("Викинг");
            else if (getValueComboBox.equals("Защитник"))
                listSquad.add("Защитник");
            listViewSquad.setItems(listSquad);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void outInfo(String selectedItem) {
        if (selectedItem.equals("Берсерк"))
            ShowAlert(selectedItem, new Berserk().getInfo());
        else if (selectedItem.equals("Лучник"))
            ShowAlert(selectedItem, new Archer().getInfo());
        else if (selectedItem.equals("Защитник"))
            ShowAlert(selectedItem, new Defender().getInfo());
        else if (selectedItem.equals("Викинг"))
            ShowAlert(selectedItem, new Viking().getInfo());
    }

    private void step(Warrior w1, Warrior w2, Squad squad1, Squad squad2) {
        w2 = squad2.getRandomWarrior();
        w1 = squad1.getRandomWarrior();

        textArea.appendText(w1.toString() + " атакует " + w2.toString() + "\r\n");
        textArea.appendText(w2.toString() + " HP: " + w2.takeDamage(w1.attack()) + "\r\n");
    }

    @FXML
    public void initialize() {
        comboBoxArrayWarrior1.setItems(FXCollections.observableArrayList("Берсерк",
                "Лучник",
                "Викинг",
                "Защитник"));
        comboBoxArrayWarrior2.setItems(FXCollections.observableArrayList("Берсерк",
                "Лучник",
                "Викинг",
                "Защитник"));

        buttonAddWarrior1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String getValueComboBox = comboBoxArrayWarrior1.getValue();
                addWarrior(getValueComboBox, listSquad1, listViewSquad1);
            }
        });

        buttonAddWarrior2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String getValueComboBox = comboBoxArrayWarrior2.getValue();
                addWarrior(getValueComboBox, listSquad2, listViewSquad2);
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

                        Warrior w1 = null, w2 = null;

                        while (squad1.hasAliveWarriors() && squad2.hasAliveWarriors()) {
                            //*************
                            step(w1, w2, squad1, squad2);
                            if (!squad2.hasAliveWarriors()) {
                                textArea.appendText("Первая команда победила!\r\n");
                                break;
                            }

                            step(w2, w1, squad2, squad1);
                            if (!squad1.hasAliveWarriors()) {
                                textArea.appendText("Вторая команда победила!\r\n");
                                break;
                            }
                        }
                    }
                }
        );

        removeWarriorButton1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedId = listViewSquad1.getSelectionModel().getSelectedIndex();
                String itemToRemove = listViewSquad1.getSelectionModel().getSelectedItems().toString();
                listViewSquad1.getItems().remove(selectedId);
                listSquad1.remove(itemToRemove);

            }
        });

        removeWarriorButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedId = listViewSquad2.getSelectionModel().getSelectedIndex();
                String itemToRemove = listViewSquad2.getSelectionModel().getSelectedItems().toString();
                listViewSquad2.getItems().remove(selectedId);
                listSquad2.remove(itemToRemove);
            }
        });

        infoButton1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedItem = listViewSquad1.getSelectionModel().getSelectedItem();
                outInfo(selectedItem);
            }
        });

        infoButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedItem = listViewSquad2.getSelectionModel().getSelectedItem();
                outInfo(selectedItem);
            }
        });
    }

    public void ShowAlert(String classWarrior, String infoAboutWarrior) { //метод для отображения уведоблений
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(classWarrior);
        alert.setHeaderText(infoAboutWarrior);
        alert.showAndWait();
    }

}
