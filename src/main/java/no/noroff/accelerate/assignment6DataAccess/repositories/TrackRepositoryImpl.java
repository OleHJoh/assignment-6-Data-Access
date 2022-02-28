package no.noroff.accelerate.assignment6DataAccess.repositories;

import no.noroff.accelerate.assignment6DataAccess.models.Track;
import no.noroff.accelerate.assignment6DataAccess.models.Customer;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackRepositoryImpl implements TrackRepository{

    private final String CONNECTION_STRING = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";

    //Get request for every track in the database
    @Override
    public List<Track> getAll() {
        List<Track> returnTracks = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            //Generates a SQL query that inner joins several tables, and gets the values: trackId, trackName, albumTitle, artistName, genre
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Track.TrackId AS 'Id', Track.Name AS 'Name', Album.Title AS 'Album', Artist.Name AS 'Artist', Genre.Name AS 'Genre' "
                            + "FROM Track INNER JOIN Album On Album.AlbumId = Track.AlbumId INNER JOIN Artist On Artist.ArtistId = Album.ArtistId "
                            + "INNER JOIN Genre ON Genre.GenreId = Track.GenreId ");
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnTracks.add(
                        new Track(
                                resultSet.getString("Id"),
                                resultSet.getString("Name"),
                                resultSet.getString("Album"),
                                resultSet.getString("Artist"),
                                resultSet.getString("Genre")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnTracks;
    }

    //Get request for five random tracks from the database
    @Override
    public List<Track> getFive() {
        //Uses a local get random function to get a random offset number
        String offset = getRandomInteger(1,3498);
        String limit = "5";
        List<Track> returnTracks = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            //Generates a SQL query that inner joins several tables, and gets the values: trackId, trackName, albumTitle, artistName, genre. Uses Limit to sort out five tracks
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Track.TrackId AS 'Id', Track.Name AS 'Name', Album.Title AS 'Album', Artist.Name AS 'Artist', Genre.Name AS 'Genre' "
                            + "FROM Track INNER JOIN Album On Album.AlbumId = Track.AlbumId INNER JOIN Artist On Artist.ArtistId = Album.ArtistId "
                            + "INNER JOIN Genre ON Genre.GenreId = Track.GenreId LIMIT ?,?");
            preparedStatement.setString(1,offset);
            preparedStatement.setString(2,limit);
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnTracks.add(
                        new Track(
                                resultSet.getString("Id"),
                                resultSet.getString("Name"),
                                resultSet.getString("Album"),
                                resultSet.getString("Artist"),
                                resultSet.getString("Genre")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnTracks;
    }

    //Get request for getting a track by its name. It doesn't work for some reason
    @Override
    public Track getTrackByName(String name) {
        Track returnTrack = null;
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            //SQL query
            //Generates a SQL query that inner joins several tables, and gets the values: trackId, trackName, albumTitle, artistName, genre. Uses Like to get a track matching to the letters, but doesn't work for some reason.
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Track.TrackId AS 'Id', Track.Name AS 'Name', Album.Title AS 'Album', Artist.Name AS 'Artist', Genre.Name AS 'Genre' "
                            + "FROM Track INNER JOIN Album On Album.AlbumId = Track.AlbumId INNER JOIN Artist On Artist.ArtistId = Album.ArtistId "
                            + "INNER JOIN Genre ON Genre.GenreId = Track.GenreId WHERE Track.Name LIKE ?");
            preparedStatement.setString(1, "'" + name + "'");
            //Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                returnTrack = new Track(
                                resultSet.getString("Id"),
                                resultSet.getString("Name"),
                                resultSet.getString("Album"),
                                resultSet.getString("Artist"),
                                resultSet.getString("Genre")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnTrack;
    }


    //Get random integer value
    private String getRandomInteger(int min, int max){
        //Returns a random integer between 1 and the value received
        return String.valueOf(((int) (Math.random()*(max - min))) + min);
    }
}
