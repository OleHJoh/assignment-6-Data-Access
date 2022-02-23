package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    private final String CONNECTION_STRING = "jdbc:sqlite:/main/resources/Chinook_Sqlite.sqlite";

    @Override
    public List<Customer> getAll() {
        List<Customer> returnCustomers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email "
                            + "FROM Customer");
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnCustomers.add(
                        new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnCustomers;
    }

    @Override
    public Customer getById(String id) {
        Customer returnCustomer = null;
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email "
                            + "FROM Customer WHERE Id = ?");
            preparedStatement.setString(1,id);
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnCustomer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnCustomer;
    }

    @Override
    public int add(Customer customer) {
        int result = 0;
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer(CustomerId,FirstName,LastName, Country, PostalCode, Phone, Email) VALUES(?,?,?,?,?,?,?)");
            //Execute query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Customer customer) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}