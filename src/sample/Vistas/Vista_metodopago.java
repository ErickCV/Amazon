package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_metodopago;
import sample.Tablas.Tabla_MetodoPago;

public class Vista_metodopago extends Stage {
    private JFXTextField textFieldType,textFieldDescription;
    private Label lbType,lbDesc;
    private VBox vBox1,vBox2;
    private HBox hBox1,hBox2;
    private JFXButton btnAceptar, btnCancelar;
    private modelo_metodopago objMP;
    private Scene scene;
    int opci = 0;

    public Vista_metodopago(int opci, modelo_metodopago modelo){
        this.opci = opci;
        this.objMP = modelo;
        crearGUI();
        scene = new Scene(hBox1,500,200);
        scene.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(scene);
        this.setTitle("Registro");
        this.show();
    }
    public void crearGUI(){
        vBox1 = new VBox();
        vBox2 = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        textFieldType = new JFXTextField();
        textFieldDescription = new JFXTextField();
        lbType = new Label("Nombre: ");
        lbType.setId("label_blancos");
        lbDesc = new Label("Descripcion");
        lbDesc.setId("label_blancos");
        btnAceptar = new JFXButton("Aceptar");
        btnCancelar = new JFXButton("Cancelar");
        btnAceptar.setId("button_options");
        btnCancelar.setId("button_options");
        btnAceptar.setOnAction(event -> Acciones(1));
        btnCancelar.setOnAction(event -> Acciones(2));
        vBox1.getChildren().addAll(lbType,lbDesc);
        vBox1.setSpacing(55);
        vBox2.getChildren().addAll(textFieldType,textFieldDescription);
        vBox2.setSpacing(10);
        hBox2.getChildren().addAll(btnAceptar,btnCancelar);
        hBox2.setSpacing(10);

        if (opci == 2) {
            textFieldType.setText(objMP.getTypePayment());
            textFieldDescription.setText(objMP.getDescription());
        }

        hBox1.getChildren().addAll(vBox1,vBox2,hBox2);
        hBox1.setPadding(new Insets(10));
        hBox1.setSpacing(10);
    }

    private void Acciones(int opc) {
        if(opci==1)
        switch (opc){
            case 1:
                objMP = new modelo_metodopago();
                objMP.setTypePayment(textFieldType.getText());
                objMP.setDescription(textFieldDescription.getText());
                objMP.Insertar();
                this.close();
                break;
            case 2:
                this.close();
        }
        else{
            objMP.setTypePayment(textFieldType.getText());
            objMP.setDescription(textFieldDescription.getText());
            objMP.Actualizar();
            this.close();
        }
    }
}
