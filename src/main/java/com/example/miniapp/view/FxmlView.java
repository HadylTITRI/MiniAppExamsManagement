package com.example.miniapp.view;
import java.util.ResourceBundle;

public enum FxmlView {

    ETUDIANT {
        @Override
        public String getTitle(){
            return getStringFromResourceBundle("etudiant.title");
        }
        @Override
        public String getFxmlFile(){
            return "/fxml/Etudiant.fxml";
        }
    };
    public abstract String getTitle();
    public abstract String getFxmlFile();
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
