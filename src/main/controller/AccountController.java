package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.accountsub.AccountFacade;
import main.accountsub.AccountService;
import main.model.Account;
import main.statusenums.AccountStatus;
import main.statusenums.Status;
import main.statusenums.UserRole;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dora on 4/21/2017.
 */
public class AccountController implements Initializable, IController {

    @FXML
    private Label lblAccountTitle;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox cboAccountStatus;
    @FXML
    private ComboBox cboUserRole;
    @FXML
    private ComboBox cboStatus;
    @FXML
    private Label lblErrMessage;

    private ObservableList<String> accountStatusList = FXCollections.observableArrayList(
            AccountStatus.ACTIVE.toString(),
            AccountStatus.SUSPENDED.toString(),
            AccountStatus.BLOCKED.toString()
    );
    private ObservableList<String> userRoleList = FXCollections.observableArrayList(
            UserRole.ADMIN.toString(),
            UserRole.MANAGER.toString(),
            UserRole.USER.toString()
    );
    private ObservableList<String> statusList = FXCollections.observableArrayList(
            Status.ENABLE.toString(),
            Status.DISABLE.toString()
    );

    private AccountControllerListener listener;

    private AccountService accountService;
    private Stage accountStage;
    private boolean isEditAccount = false;
    private Account account;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize Account Service
        this.accountService = new AccountFacade();

        // Load data into ComboBox
        loadDataIntoComboBox();

        // Hide Error Message
        lblErrMessage.setVisible(false);
    }

    @Override
    public void setStage(Stage stage) {
        this.accountStage = stage;
    }

    public void setAccount(Account account) {
        this.account = account;
        this.isEditAccount = true;
        lblAccountTitle.setText("Account Edit Form");
        txtUserName.setText(this.account.getUserName());
        txtPassword.setText(this.account.getPassword());
        cboAccountStatus.setValue(this.account.getAccountStatus());
        cboUserRole.setValue(this.account.getUserRole());
        cboStatus.setValue(this.account.getStatus());
    }

    public void onBtnAccountCancelClicked() {
        this.accountStage.close();
    }

    public void onBtnAccountSaveClicked() {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String status = (String) cboStatus.getSelectionModel().getSelectedItem();
        String userRole = (String) cboUserRole.getSelectionModel().getSelectedItem();
        String accountStatus = (String) cboAccountStatus.getSelectionModel().getSelectedItem();

        if (userName.isEmpty()) {
            lblErrMessage.setVisible(true);
            lblErrMessage.setText("User Name is required!");
        } else if (password.isEmpty()) {
            lblErrMessage.setVisible(true);
            lblErrMessage.setText("Password is required!");
        } else {
            if (account == null) {
                account = new Account();
            }
            this.account.setUserName(userName);
            this.account.setPassword(password);
            this.account.setStatus(status);
            this.account.setUserRole(userRole);
            this.account.setAccountStatus(accountStatus);

            if (isEditAccount) {
                editAccount();
            } else {
                saveAccount();
            }
        }
    }

    public void saveAccount() {
        int result = this.accountService.saveAccount(this.account);
        this.accountStage.close();
        if (result != 0) {
            this.listener.onSaveSuccess(this.account);
        } else {
            this.listener.onSaveFail("Save fail!");
        }
    }

    public void editAccount() {
        System.out.println("Update Account");
        System.out.println("Account " + this.account.toString());
        int result = this.accountService.updateAccount(this.account);
        System.out.println("Result = " + result);
        this.accountStage.close();
        if (result != 0) {
            this.listener.onUpdateSuccess(this.account);
        } else {
            this.listener.onSaveFail("Save fail!");
        }
    }

    public void loadDataIntoComboBox() {
        cboAccountStatus.setItems(accountStatusList);
        cboUserRole.setItems(userRoleList);
        cboStatus.setItems(statusList);
        cboAccountStatus.setValue(AccountStatus.ACTIVE.toString());
        cboUserRole.setValue(UserRole.ADMIN.toString());
        cboStatus.setValue(Status.ENABLE.toString());
    }

    public void setAccountControllerListener(AccountControllerListener listener) {
        this.listener = listener;
    }

    public interface AccountControllerListener {
        public void onSaveSuccess(Account account);
        public void onUpdateSuccess(Account account);
        public void onSaveFail(String errMessage);
    }
}
