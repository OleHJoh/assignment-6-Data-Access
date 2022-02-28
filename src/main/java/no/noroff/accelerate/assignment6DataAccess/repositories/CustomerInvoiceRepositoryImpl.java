package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.CustomerInvoiceJoined;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerInvoiceRepositoryImpl implements CustomerInvoiceRepository{

    private final String CONNECTION_STRING = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";

    //Get request for joined tables of customer and invoice
    @Override
    public List<CustomerInvoiceJoined> getSpenderList() {
        List<CustomerInvoiceJoined> returnList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            //SQL query
            //Generates a sql query to join the two tables and get the total amount of money the customer have used
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT firstname, lastname, SUM(total) FROM Invoice "
                            + "INNER JOIN Customer on Customer.CustomerId = Invoice.CustomerId GROUP BY Invoice.CustomerId ORDER BY SUM(total) DESC");
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnList.add(
                        new CustomerInvoiceJoined(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("SUM(total)")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnList;
    }
}
