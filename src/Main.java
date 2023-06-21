import entities.UtenteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teatro2", "root", "mrobot4a");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from utente");
            List<UtenteDTO> users = new ArrayList<>();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                String indirizzo = resultSet.getString("indirizzo");
                UtenteDTO utenteDTO = new UtenteDTO(id, nome, cognome, email, indirizzo);
                users.add(utenteDTO);
            }
            PreparedStatement preparedStatement = connection.prepareStatement("select * from utente where id = ?");
            preparedStatement.setInt(1, 1);
            preparedStatement.executeQuery();
            System.out.println(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public UtenteDTO getUtenteById(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from utente where id = ? and nome = ?");
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "Marco");
        ResultSet resultSet = preparedStatement.executeQuery();
        UtenteDTO utenteDTO = null;
        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String cognome = resultSet.getString("cognome");
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String indirizzo = resultSet.getString("indirizzo");
            utenteDTO = new UtenteDTO(id, nome, cognome, email, indirizzo);
        }
        return utenteDTO
    }

}
