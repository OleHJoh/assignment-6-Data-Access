package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import no.noroff.accelerate.assignment6DataAccess.models.Country;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    private final String CONNECTION_STRING = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";

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
                            + "FROM Customer WHERE CustomerId = ?");
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
    public List<Customer> getByName(String name) {
        List<Customer> returnCustomers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email "
                            + "FROM Customer WHERE FirstName LIKE ?");
            preparedStatement.setString(1, "'%"+ name + "%'");
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
        System.out.printf(String.valueOf(returnCustomers));
        return returnCustomers;
    }

    @Override
    public List<Customer> getOffsetLimit(String offset, String limit){
        List<Customer> returnCustomers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email "
                            + "FROM Customer LIMIT ?,?");
            preparedStatement.setString(1, offset);
            preparedStatement.setString(2, limit);
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
    public int add(Customer customer) {
        int result = 0;
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer(CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,customer.getCustomerId());
            preparedStatement.setString(2,customer.getFirstName());
            preparedStatement.setString(3,customer.getLastName());
            preparedStatement.setString(4,customer.getCountry());
            preparedStatement.setString(5,customer.getPostalCode());
            preparedStatement.setString(6,customer.getPhone());
            preparedStatement.setString(7,customer.getEmail());
            //Execute query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Customer customer) {
        int result = 0;
        //SQL query
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET firstName = ?, lastName = ?, country = ?, postalCode = ?, phone = ?, email = ? WHERE customerId = ?");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setString(7, customer.getCustomerId());
            //Execute query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public List<Country> customerListForCountries(){
        List<Country> returnCountry = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT country, COUNT(customerId) FROM Customer GROUP By country ORDER BY COUNT(customerId) DESC");
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnCountry.add(
                        new Country(
                                resultSet.getString("country"),
                                resultSet.getString("people")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnCountry;
    }
}
