package sample.Vistas;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    /*
    @FXML
    JFXButton btnVentana1, btnVentana2, btnVentana3, btnVentana4, btnVentana5,
            btnVentana6, btnVentana7, btnVentana8, btnVentana9, btnVentana10,
            btnVentana11, btnVentana12, btnVentana13, btnVentana14,btnVentana15,
            btnVentana16;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainWindowController.buttonaction(btnVentana1, event, btnVentana2, btnVentana3, btnVentana4, btnVentana5);
        mainWindowController.buttonaction(btnVentana6, event, btnVentana7, btnVentana8, btnVentana9, btnVentana10);
        mainWindowController.buttonaction(btnVentana11, event, btnVentana12, btnVentana13, btnVentana14, btnVentana15);
        btnVentana16.setOnAction(event);
    }

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = new Stage();
            if (event.getSource().equals(btnVentana1)){
                showAddADdress(stage);
            }else if (event.getSource().equals(btnVentana2)){
                showAddClient(stage);
            }else if (event.getSource().equals(btnVentana3)){
                showAddUser(stage);
            }else if (event.getSource().equals(btnVentana4)){
                showLogin(stage);
            }else if (event.getSource().equals(btnVentana5)){
                showMakePackage(stage);
            }else if (event.getSource().equals(btnVentana6)){
                showPackageList(stage);
            }else if (event.getSource().equals(btnVentana7)){
                showProductEdit(stage);
            }else if (event.getSource().equals(btnVentana8)){
                showProductList(stage);
            }else if (event.getSource().equals(btnVentana9)){
                showProductsClient(stage);
            }else if (event.getSource().equals(btnVentana10)){
                //showProductsUser(stage);
            }else if (event.getSource().equals(btnVentana11)){
                // showSales(stage);
            }else if (event.getSource().equals(btnVentana12)){
                // showShoppingCart(stage);
            }else if (event.getSource().equals(btnVentana13)){
                // showVentanaPrueba(stage);
            }else if (event.getSource().equals(btnVentana14)){
                showProductAdd(stage);
            }else if (event.getSource().equals(btnVentana15)) {
                showPaymentStage(stage);
            }else if (event.getSource().equals(btnVentana16)) {
                showmainWindowStage(stage);
            }
        }
    };
/*
    public static Stage addAddressStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderAddAddress; // Esto tambien
    private void showAddADdress(Stage _addAddressStage){
        try {
            addAddressStage= _addAddressStage;
            addAddressStage.setTitle("addAddress");
            Parent root = null;
            loaderAddAddress = new FXMLLoader(getClass().getResource("/sample/addAddress/addAddressFormat.fxml"));
            AddAddressController addAddressController = new AddAddressController();si ves?
            loaderAddAddress .setController(addAddressController);
            root = loaderAddAddress .load();
            Scene scene=new Scene(root,700,650);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            addAddressStage.setScene(scene);
            addAddressStage.setResizable(false);
            addAddressStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage addClientStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderAddClient; // Esto tambien
    private void showAddClient(Stage _addClientStage){
        try {
            addClientStage= _addClientStage;
            addClientStage.setTitle("addClient");
            Parent root= null;
            loaderAddClient = new FXMLLoader(getClass().getResource("/sample/addClient/addClientFormat.fxml"));
            AddClientController addClientController = new AddClientController();
            loaderAddClient.setController(addClientController);
            root = loaderAddClient.load();
            Scene scene=new Scene(root,700,600);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            addClientStage.setScene(scene);
            addClientStage.setResizable(false);
            addClientStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage addUserStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderAddUser; // Esto tambien
    private void showAddUser(Stage _addUserStage){
        try {
            addUserStage= _addUserStage;
            addUserStage.setTitle("addUser");
            Parent root= null;
            loaderAddUser = new FXMLLoader(getClass().getResource("/sample/addUser/addUserFormat.fxml"));
            AddUserController addUserController = new AddUserController();
            loaderAddUser.setController(addUserController);
            root = loaderAddUser.load();
            Scene scene=new Scene(root, 700, 450);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            addUserStage.setScene(scene);
            addUserStage.setResizable(false);
            addUserStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage loginStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderLogin; // Esto tambien
    private void showLogin(Stage _loginStage){
        try {
            loginStage= _loginStage;
            loginStage.setTitle("login");
            Parent root= null;
            loaderLogin = new FXMLLoader(getClass().getResource("/sample/login/LoginFormat.fxml"));
            LoginController loginController = new LoginController();
            loaderLogin.setController(loginController);
            root = loaderLogin.load();
            Scene scene=new Scene(root, 500,650);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            loginStage.setScene(scene);
            loginStage.setResizable(false);
            loginStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage makePackageStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loadermakePackage; // Esto tambien
    private void showMakePackage(Stage _makePackage){
        try {
            makePackageStage= _makePackage;
            makePackageStage.setTitle("makePackage");
            Parent root= null;
            loadermakePackage = new FXMLLoader(getClass().getResource("/sample/makePackage/MakePackageFormat.fxml"));
            MakePackageController makePackageController  = new MakePackageController();
            loadermakePackage.setController(makePackageController);
            root = loadermakePackage.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            makePackageStage.setScene(scene);
            makePackageStage.setMaximized(true);
            makePackageStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage packageListStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderPackageList; // Esto tambien
    private void showPackageList(Stage _packageListStage){
        try {
            packageListStage= _packageListStage;
            packageListStage.setTitle("packageList");
            Parent root= null;
            loaderPackageList = new FXMLLoader(getClass().getResource("/sample/packagesList/PackageListFormat.fxml"));
            PackageListController packageListController = new PackageListController();
            loaderPackageList.setController(packageListController);
            root = loaderPackageList.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            packageListStage.setScene(scene);
            packageListStage.setMaximized(true);
            packageListStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage productEditStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderProductEdit; // Esto tambien
    private void showProductEdit(Stage _productEditStage){
        try {
            productEditStage= _productEditStage;
            productEditStage.setTitle("productEdit");
            Parent root= null;
            loaderProductEdit = new FXMLLoader(getClass().getResource("/sample/productEdit/ProductEditFormat.fxml"));
            ProductEditController productEditController = new ProductEditController();
            loaderProductEdit.setController(productEditController);
            root = loaderProductEdit.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            productEditStage.setScene(scene);
            productEditStage.setMaximized(true);
            productEditStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage productAddStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderProductAdd; // Esto tambien
    private void showProductAdd(Stage _productAddStage){
        try{
            Parent root= null;
            productAddStage= _productAddStage;
            productAddStage.setTitle("productAdd");
            loaderProductAdd= new FXMLLoader(getClass().getResource("/sample/productAdd/ProductAddFormat.fxml"));
            ProductAddController productAddController = new ProductAddController();
            loaderProductAdd.setController(productAddController);
            root = loaderProductAdd.load();
            Scene scene=new Scene(root, 600,600);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            productAddStage.setScene(scene);
            productAddStage.setResizable(false);
            productAddStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Stage productListStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderProductList; // Esto tambien
    private void showProductList(Stage _productListStage){
        try {
            productListStage= _productListStage;
            productListStage.setTitle("productList");
            Parent root= null;
            loaderProductList = new FXMLLoader(getClass().getResource("/sample/productList/ProductListFormat.fxml"));
            ProductListController productListController = new ProductListController();
            loaderProductList.setController(productListController);
            root = loaderProductList.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            productListStage.setScene(scene);
            productListStage.setMaximized(true);
            productListStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage productsClientStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderProductsClient; // Esto tambien
    private void showProductsClient(Stage _productsClientStage){
        try {
            productsClientStage= _productsClientStage;
            productsClientStage.setTitle("productsClient");
            Parent root= null;
            loaderProductsClient = new FXMLLoader(getClass().getResource("/sample/productsClient/ProductsClientFormat.fxml"));
            ProductsClientController productsClientController = new ProductsClientController();
            loaderProductsClient.setController(productsClientController);
            root = loaderProductsClient.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            productsClientStage.setScene(scene);
            productsClientStage.setMaximized(true);
            productsClientStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage productsUserStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderProductsUser; // Esto tambien
    private void showProductsUser(Stage _productsUserStage){
        try {
            productsUserStage= _productsUserStage;
            productsUserStage.setTitle("productsUser");
            Parent root= null;
            loaderProductsUser = new FXMLLoader(getClass().getResource("/sample/productsUser/ProductsUserFormat.fxml"));
            ProductsUserController productsUserController = new ProductsUserController();
            loaderProductsUser.setController(productsUserController);
            root = loaderProductsUser.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            productsUserStage.setScene(scene);
            productsUserStage.setMaximized(true);
            productsUserStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage salesStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderSales; // Esto tambien
    private void showSales(Stage _salesStage){
        try {
            salesStage= _salesStage;
            salesStage.setTitle("sales");
            Parent root= null;
            loaderSales = new FXMLLoader(getClass().getResource("/sample/sales/SalesFormat.fxml"));
            SalesController salesController = new SalesController();
            loaderSales.setController(salesController);
            root = loaderSales.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            salesStage.setScene(scene);
            salesStage.setMaximized(true);
            salesStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage shoppingCartStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderShoppingCart; // Esto tambien
    private void showShoppingCart(Stage _shoppingCartStage){
        try {
            shoppingCartStage= _shoppingCartStage;
            shoppingCartStage.setTitle("shoppingCart");
            Parent root= null;
            loaderShoppingCart = new FXMLLoader(getClass().getResource("/sample/shoppingCart/ShopingCartFormat.fxml"));
            ShoppingCartController shoppingCartController = new ShoppingCartController();
            loaderShoppingCart.setController(shoppingCartController);
            root = loaderShoppingCart.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            shoppingCartStage.setScene(scene);
            shoppingCartStage.setMaximized(true);
            shoppingCartStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public static Stage pruebaStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderPrueba; // Esto tambien
    private void showVentanaPrueba(Stage _pruebaStage){
        try {
            pruebaStage= _pruebaStage;
            pruebaStage.setTitle("InvoiceController");
            Parent root= null;
            loaderPrueba = new FXMLLoader(getClass().getResource("/sample/pruebaImagenes/interfaz.fxml"));
            ControllerImagenesBD controllerImagenesBD = new ControllerImagenesBD();
            loaderPrueba.setController(controllerImagenesBD);
            root = loaderPrueba.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            pruebaStage.setScene(scene);
            pruebaStage.setMaximized(true);
            pruebaStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public static Stage PaymentStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderPayment; // Esto tambien
    private void showPaymentStage(Stage _PaymentStage){
        try {
            Parent root = null;
            PaymentStage= _PaymentStage;
            PaymentStage.setTitle("PaymentController");
            loaderPayment = new FXMLLoader(getClass().getResource("/sample/payment/PaymentFormat.fxml"));
            PaymentController paymentController = new PaymentController();
            loaderPayment.setController(paymentController);
            root= loaderPayment.load();
            Scene scene=new Scene(root,700,600);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            PaymentStage.setScene(scene);
            PaymentStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public static Stage MainWindowStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderMainWindow; // Esto tambien
    private void showmainWindowStage(Stage _mainWindowStage){
        try {
            MainWindowStage= _mainWindowStage;
            MainWindowStage.setTitle("InvoiceController");
            Parent root = null;
            loaderMainWindow = new FXMLLoader(getClass().getResource("/sample/mainWindow/mainWindowFormat.fxml"));
            mainWindowController mainWindowController = new mainWindowController();
            loaderMainWindow.setController(mainWindowController);
            root= loaderMainWindow.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            MainWindowStage.setScene(scene);
            MainWindowStage.setMaximized(true);
            MainWindowStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }


// Metodo para abrir otro Stage o ventana------Solo renombrar lo que corresponde
    /* --- Dejar una copia comentada
    public static Stage pruebaStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderPrueba; // Esto tambien
    private void showInvoice(Stage _invoiceStage, boolean before){
        try {
            Parent root_ = null;
            pruebaStage= _pruebaStage;
            pruebaStage.setTitle("InvoiceController");
            loaderPrueba = new FXMLLoader(getClass().getResource("/sample/pruebaImagenes/interfaz.fxml"));
            ControllerImagenesBD controllerImagenesBD = new ControllerImagenesBD();
            loaderPrueba.setController(controllerImagenesBD);
            root= loaderPrueba.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/resources/ThemeKrispyKreme.css");
            pruebaStage.setScene(scene);
            pruebaStage.setMaximized(true);
            pruebaStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    */
    //------------------------

}
