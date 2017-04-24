package main.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Shared.WindowNavigation;
import main.accountsub.AccountFacade;
import main.accountsub.AccountService;
import main.model.Account;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by gebre on 4/23/2017.
 */
public class AccountTapController implements Initializable, AccountController.AccountControllerListener {

    @FXML
    private TextField txtAccountSearch;
    @FXML
    private Button btnAccountSearch;
    @FXML
    private Button btnAccountAdd;
    @FXML
    private Button btnAccountRefresh;
    @FXML
    private Button btnAccountEdit;
    @FXML
    private Button btnAccountDelete;
    @FXML
    private TableView tblAccount;
    @FXML
    private TableColumn<Account, Void> num;
    @FXML
    private TableColumn<Account, Integer> idAccount;
    @FXML
    private TableColumn<Account, String> userName;
    @FXML
    private TableColumn<Account, String> password;
    @FXML
    private TableColumn<Account, String> status;
    @FXML
    private TableColumn<Account, String> userRole;
    @FXML
    private TableColumn<Account, String> accountStatus;


    private AccountService accountService;
    private ObservableList<Account> originalAccountList = FXCollections.observableArrayList();
    private ObservableList<Account> modifiedAccountList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize Database service
        this.accountService = new AccountFacade();

        // Load Account list from Database
        originalAccountList = FXCollections.observableArrayList(this.accountService.getAllAccount());

        // Load Account list into TableView;
        loadAccountListIntoTableView();
    }

    @Override
    public void onSaveSuccess(Account account) {
        originalAccountList.add(account);
        //onBtnAccountRefreshClicked();
        refreshAccountTableView();
    }

    @Override
    public void onUpdateSuccess(Account account) {
        //onBtnAccountRefreshClicked();
        refreshAccountTableView();
    }

    @Override
    public void onSaveFail(String errMessage) {

    }

    public void loadAccountListIntoTableView() {
        num = new TableColumn<>("No");
        idAccount = new TableColumn<>("ID");
        userName = new TableColumn<>("UserName");
        password = new TableColumn<>("Passport");
        password.setVisible(false);
        status = new TableColumn<>("Status");
        userRole = new TableColumn<>("User Role");
        accountStatus = new TableColumn<>("Account Status");

        num.setCellFactory(col -> {
            TableCell<Account, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null;
                } else {
                    return Integer.toString(cell.getIndex() + 1);
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell;
        });
        idAccount.setCellValueFactory(new PropertyValueFactory<>("code"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        accountStatus.setCellValueFactory(new PropertyValueFactory<>("accountStatus"));

        tblAccount.setItems(originalAccountList);
        tblAccount.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblAccount.getColumns().addAll(num, idAccount, userName, status, userRole, accountStatus);
    }

    public void onTxtAccountSearch(){

    }

    public void onBtnAccountSearchClicked(){
        String textSearch = txtAccountSearch.getText();
        if (!textSearch.isEmpty()) {
            searchAccount(textSearch);
        }
    }

    public void onBtnAccountAddClicked(){
        AccountController accountController = (AccountController) new WindowNavigation().navigateToWindow("Add New Account",
                "../../resource/view/Account.fxml");
        accountController.setAccountControllerListener(this);
    }

    public void onBtnAccountRefreshClicked(){
        //tblAccount.setItems(originalAccountList);
        refreshAccountTableView();
    }

    public void onBtnAccountEditClicked(){
        Account accountToEdit = (Account) tblAccount.getSelectionModel().getSelectedItem();
        if (accountToEdit != null) {
            AccountController accountController = (AccountController) new WindowNavigation().navigateToWindow("Add New Account",
                    "../../resource/view/Account.fxml");
            accountController.setAccountControllerListener(this);
            accountController.setAccount(accountToEdit);
        } else {
            JOptionPane.showMessageDialog(null, "Please select record to edit!");
        }
    }

    public void onBtnAccountDeleteClicked(){
        Account deletedAccount = (Account) tblAccount.getSelectionModel().getSelectedItem();
        int result = this.accountService.deleteAccount(deletedAccount);
        if (result != 0) {
            originalAccountList.remove(deletedAccount);
        }
        //onBtnAccountRefreshClicked();
        refreshAccountTableView();
    }

    public void refreshAccountTableView() {
        tblAccount.setItems(originalAccountList);
    }

    public void searchAccount(String value) {
        final String searchText = value.toLowerCase();
        modifiedAccountList = originalAccountList.stream()
                .filter(a -> (a.getUserName().toLowerCase().contains(searchText))
                || a.getAccountStatus().toLowerCase().contains(searchText)
                || a.getStatus().toLowerCase().contains(searchText)
                || a.getUserRole().toLowerCase().contains(searchText))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        tblAccount.setItems(modifiedAccountList);
    }
}
