package com.example.miniapp.controller;

import com.example.miniapp.bean.Etudiant;
import com.example.miniapp.repository.EtudiantRepository;
import com.example.miniapp.service.EtudiantService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class UserController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private Label etudiantId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField groupe;

    @FXML
    private Button reset;

    @FXML
    private TableView<Etudiant> etudiantTable;

    @FXML
    private TableColumn<Etudiant, Long> colEtudiantId;

    @FXML
    private TableColumn<Etudiant, String> colFirstName;

    @FXML
    private TableColumn<Etudiant, String> colLastName;

    @FXML
    private TableColumn<Etudiant, String> colGroupe;

    @FXML
    private TableColumn<Etudiant, Boolean> colEdit;

    @FXML
    private MenuItem deleteEtudiants;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private EtudiantService etudiantService;

    private ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();

    @FXML
    private void exit(ActionEvent event){
        Platform.exit();
    }
    @FXML
    private void saveEtudiant(ActionEvent event){
        if(validate("First Name", getFirstName(),"[a-zA-Z]+") &&
           validate("Last Name", getLastName(),"[a-aZ-Z]+"){
            if(etudiantId.getText() == null || etudiantId.getText() == ""){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(etudiant.getId());
                etudiant.setFirstName(etudiant.getFirstName());
                etudiant.setLastName(etudiant.getLastName());
                etudiant.setGroupe(etudiant.getGroupe());

                Etudiant newEtudiant = EtudiantService.save(etudiant);
                saveAlert(newEtudiant);
            }
            else{
                Etudiant etudiant = EtudiantService.find(Long.parseLong(etudiantId.getText()));
                etudiant.setFirstName(etudiant.getFirstName());
                etudiant.setLastName(etudiant.getLastName());
                etudiant.setGroupe(etudiant.getGroupe());
                Etudiant updatedEtudiant = EtudiantService.update(etudiant);
                updateAlert(updatedEtudiant);
            }
            clearFields();
            loadEtudiantDetails();
        }

    }
    @FXML
    private void deleteEtudiants(ActionEvent event){
        List<Etudiant> etudiants = etudiantTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer l'étudiant séléctionné?");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.OK){
            etudiantService.deleteInBatch(etudiants);
            loadEtudiantDetails();
        }

    }
    private void clearFields(){
        etudiantId.setText(null);
        firstName.clear();
        lastName.clear();
        groupe.clear();
    }

    private void saveAlert(Etudiant etudiant){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Etudiant ajouté avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("L'étudiant" +etudiant.getFirstName() + "" +
                etudiant.getLastName() + "a été bien ajouté à la liste \nGroupe:" +etudiant.getGroupe()+ "et matricule:" + etudiant.getId());
        alert.showAndWait();
    }
    private void updateAlert(Etudiant etudiant){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Etudiant mis à jour avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Les informations de l'étudiant :" +etudiant.getFirstName() + "" + etudiant.getLastName() + "ont été bien misent à jour.");
        alert.showAndWait();
    }
    private String getFirstName(){
        return firstName.getText();
    }
    private String getLastName(){
        return lastName.getText();
    }
    private String getGroupe(){
        return groupe.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        etudiantTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        setColumnProperties();

        //Ajouter tous les étudiants dans la table
        loadEtudiantDetails;
    }

    //Mettre toutes les propriétés des colonnes de la table Etudiant
    private void setColumnProperties(){
        colEtudiantId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Etudiant, Boolean>, TableCell<Etudiant, Boolean>> cellFactory =
            new Callback<TableColumn<Etudiant, Boolean>, TableCell<Etudiant, Boolean>>(){
        @Override
                public TableCell<Etudiant, Boolean> call( final TableColumn<Etudiant, Boolean> param){
            final TableCell<Etudiant, Boolean> cell = new TableCell<Etudiant, Boolean>()
            {
                private void updateEtudiant(Etudiant etudiant){
                    etudiantId.setText(Long.toString());
                }
            }
        }
            }
}
