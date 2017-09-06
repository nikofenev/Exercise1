package edu.matc.persistence;

import edu.matc.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM users";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SearchUser.getAllUsers()...SQL Exception: ", e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: ", e);
        }
        return users;
    }

    //TODO add a method or methods to return a single user based on search criteria
    public List<User> searchUser(String searchTerm) {
        Database database = Database.getInstance();
        Connection connection = null;
        String queryString;

        List<User> searchUser = new ArrayList<User>();

        queryString = "Select * FROM users WHERE last_name LIKE '" + searchTerm + "%';";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(queryString);

            iterateOverResultSet(searchUser, results);

            database.disconnect();
        } catch (SQLException e) {
            logger.error("SearchUser.getAllUsers()...SQL Exception: ", e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: ", e);
        }

        return searchUser;
    }

    private void iterateOverResultSet(List<User> searchUser, ResultSet results) throws SQLException {
        while (results.next()) {
            searchUser.add(createUserFromResults(results));
        }
    }

    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setUserid(results.getString("id"));
        user.setFirstName(results.getString("first_name"));
        user.setBirthDate(results.getDate("date_of_birth").toLocalDate());
        return user;
    }

}