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

    //Get request for every customer in the database
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

    //Get request for a customer by their customerId
    @Override
    public Customer getById(String id) {
        Customer returnCustomer = null;
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            //Generates a SQL query that gets the customer that matches the id sent
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

    //Get request for a customer or multiple customers if their name contains the letter parameter
    @Override
    public List<Customer> getByName(String name) {
        List<Customer> returnCustomers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            //Generates a SQL query that gets all customers where their name matches the letters string it's looking for
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

    //Get request for customers where you can limit how many you will see and where it starts in the database
    @Override
    public List<Customer> getOffsetLimit(String offset, String limit){
        List<Customer> returnCustomers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            //Generates a SQL query that sets a limit and a offset
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

    //Post request to add a customer to the database
    @Override
    public int add(Customer customer) {
        int result = 0;
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            //Generates a SQL query that sends the customer element to get registered in the database
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

    //Patch request to update a customer in the database
    @Override
    public int update(Customer customer) {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            //Generates a SQL query that updates the customer data using customerId as the object key
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

    //Delete request is not used, and haven't been made
    @Override
    public int delete(String id) {
        return 0;
    }

    //Get request for getting every country and the registered customers to that country
    @Override
    public List<Country> customerListForCountries(){
        List<Country> returnCountry = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            //Generates a SQL query that gets every country and a number that represent the amount of registered customers to that country
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT country, COUNT(customerId) AS 'people' FROM Customer GROUP By country ORDER BY COUNT(customerId) DESC");
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
