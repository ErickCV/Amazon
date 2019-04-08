package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_pais;
import sample.Tablas.Tabla_Pais;

public class Vista_pais extends Stage {
    private JFXTextField textFielCountry;
    private Label lbCountry;
    private HBox hBox1;
    private VBox vBox1, vBox2;
    private JFXButton btnAceptar, btnCancelar;
    private modelo_pais objP;
    private Tabla_Pais objT;
    private Scene scene;
    int opci;

    public Vista_pais(int opci, modelo_pais modelo){
        this.opci = opci;
        this.objP =  modelo;
        crearGUI();
        scene = new Scene(hBox1,200,200);
        scene.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(scene);
        this.setTitle("Registro");
        this.show();
    }
    public void crearGUI(){
        hBox1 = new HBox();
        vBox1 = new VBox();
        vBox2 = new VBox();
        textFielCountry = new JFXTextField();
        lbCountry = new Label("Nombre: ");
        btnAceptar = new JFXButton("Aceptar");
        btnCancelar = new JFXButton("Cancelar");
        btnAceptar.setId("button_options");
        btnCancelar.setId("button_options");
        btnAceptar.setOnAction(event -> Acciones(1));
        btnCancelar.setOnAction(event -> Acciones(2));
        vBox1.getChildren().addAll(lbCountry,btnAceptar);
        vBox1.setSpacing(20);
        vBox2.getChildren().addAll(textFielCountry,btnCancelar);
        vBox2.setSpacing(10);

        if (opci == 2) {
            textFielCountry.setText(String.valueOf(objP.getIdCountry()));

        }

        hBox1.getChildren().addAll(vBox1,vBox2);
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(10));
    }

    private void Acciones(int opc) {
        if(opci==1)
            switch (opc) {
                case 1:
                    objP = new modelo_pais();
                    objP.setName(textFielCountry.getText());
                    objP.Insertar();
                    break;
                case 2:
                    break;
            }
        else{

            objP.setName((textFielCountry.getText()));
            objP.Actualizar();
        }

        this.close();
    }
}

