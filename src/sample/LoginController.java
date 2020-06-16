package sample;

import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Label statusMessage;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    public void Login(ActionEvent event) throws Exception{

        Connection connection = null;
        Statement statement = null;

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();

            //Get User
            String sql = "SELECT user_id FROM users WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String userId = result.getString(1);
                if (userId != null) {
                    System.out.println("result : " + userId);
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Mainview.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene((scene));
                    primaryStage.setTitle("Main");
                    primaryStage.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
