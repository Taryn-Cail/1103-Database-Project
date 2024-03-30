import java.sql.*;
import java.util.Scanner;

/*****************************************************************************
 * CS1103 - Database Project - Music Database
 * 
 * Purpose: To create a database project of a music database and accomplish 
 *          different tasks such as:
 *          
 *          - Insert and delete songs
 *          - Insert and delete artists
 *          - Insert and delete playlists
 *          - Change a songs playlist
 *          - Change an artists networth
 *          - Change a playlists number of songs
 *          
 *          
 * @author - Taryn Cail
 * @version - 1.0
 * @date - March 29th, 2024
 *****************************************************************************/
 
 public class MusicDatabase
 {
     // Class variables
     static Scanner input = new Scanner(System.in);
     
     /*****************************************************
      * Method: Main Method
      * 
      * Purpose: To create the application using a switch
      *          case depending on user input.
      *****************************************************/
     public static void main (String[] args)
     {
         // Printing out a welcome message
         System.out.println("Hello! Welcome to Taryn's Music Databse!");
         System.out.println();
         
         // Declaring necessary variables
         boolean valid = false;
         String userInput = "";
         
             
         while (!valid)
         {
             // Doing the first round of commands
             int selection = welcome();
             selection(selection);
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("Would you like to complete another task?");
             input.nextLine();
             userInput = input.nextLine();
             System.out.println("-----------------------------------------");
             if (userInput.equalsIgnoreCase("yes"))
             {
                 valid = false;
             }
             else
             {
                 valid = true;
             }
         }
     }
     
     public static int welcome()
     {
         int results = 0;
         System.out.println("-----------------------------------------");
         System.out.println("Here are your options:");
         System.out.println("1. Print song table");
         System.out.println("2. Print playlist table");
         System.out.println("3. Print artist table");
         System.out.println("4. Insert a song into the song table");
         System.out.println("5. Delete a song from the song table");
         System.out.println("6. Insert an artist into the artist table");
         System.out.println("7. Delete an artist from the artist table");
         System.out.println("8. Insert a playlist into the playlist table");
         System.out.println("9. Delete a playlist from the playlist table");
         System.out.println("10. Change a songs playlist in the song table");
         System.out.println("11. Change an artists Net Worth in the artist table");
         System.out.println("12. Change a Playlists number of songs in the playlist table");
         System.out.println("13. Find the playlist with the most songs");
         System.out.println("14. Exit");
         System.out.println("-----------------------------------------");
         System.out.println("What would you like to do? Input the number corresponding to your selection.");
         results = input.nextInt();
         System.out.println("");
         return results;
     }
     
     public static void selection(int x)
     {
         switch(x)
         {
             case 1:
                 {
                     printSongDB();
                     break;
                 }
             case 2:
                 {
                     printPlaylistDB();
                     break;
                 }
             case 3:
                 {
                     printArtistDB();
                     break;
                 }
             case 4:
                 {
                     insertSong();
                     break;
                 }
             case 5:
                 {
                     deleteSong();
                     break;
                 }
             case 6:
                 {
                     insertArtist();
                     break;
                 }
             case 7:
                 {
                     deleteArtist();
                     break;
                 }
             case 8:
                 {
                     insertPlaylist();
                     break;
                 }
             case 9:
                 {
                     deletePlaylist();
                     break;
                 }
             case 10:
                 {
                     changeSongsPlaylist();
                     break;
                 }
             case 11:
                 {
                     changeArtistsNetworth();
                     break;
                 }
             case 12:
                 {
                     changePlaylistsNumSongs();
                     break;
                 }
             case 13:
                 {
                     findBiggestPlaylist();
                     break;
                 }
             default:
                 {
                     System.exit(0);
                 }
         }
     }
     
     
     
     /*****************************************************
      * Method: printSongDB()
      * 
      * Purpose: To print entire song database in a
      *          a formatted display.
      *****************************************************/
     public static void printSongDB()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Executing the statement
             results = stmnt.executeQuery("SELECT * FROM song");
             conn.commit();
             
             // Retrieving the data from database to print it out
             ResultSetMetaData metaData = results.getMetaData();
             int columnCount = metaData.getColumnCount();
             
             // Getting Column names
             for (int i = 1; i <= columnCount; i++) 
             {
                System.out.format("%-30s", metaData.getColumnName(i));
             }
             System.out.println();
            
             // Getting the data from the table and printing it out
             while (results.next()) 
             {
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) 
                {
                    System.out.printf("%-30s", results.getString(i));
                }
                System.out.println();
             }
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: printPlaylistDB()
      * 
      * Purpose: To print entire playlist database in a
      *          a formatted display.
      *****************************************************/
     public static void printPlaylistDB()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Executing the statement
             results = stmnt.executeQuery("SELECT * FROM playlist");
             conn.commit();
             
             // Retrieving the data from statement to print it out
             ResultSetMetaData metaData = results.getMetaData();
             int columnCount = metaData.getColumnCount();
             
             // Getting Column names
             for (int i = 1; i <= columnCount; i++) 
             {
                System.out.format("%-30s", metaData.getColumnName(i));
             }
             System.out.println();
            
             // Getting the data from the table
             while (results.next()) 
             {
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) 
                {
                    System.out.printf("%-30s", results.getString(i));
                }
                System.out.println();
             }
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: printArtistDB()
      * 
      * Purpose: To print entire artist database in a
      *          a formatted display.
      *****************************************************/
     public static void printArtistDB()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Executing the statement
             results = stmnt.executeQuery("SELECT * FROM artist");
             conn.commit();
             
             // Retrieving the data from statement to print it out
             ResultSetMetaData metaData = results.getMetaData();
             int columnCount = metaData.getColumnCount();
             
             // Getting Column names
             for (int i = 1; i <= columnCount; i++) 
             {
                System.out.format("%-30s", metaData.getColumnName(i));
             }
             System.out.println();
            
             // Getting the data from the table
             while (results.next()) 
             {
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) 
                {
                    System.out.printf("%-30s", results.getString(i));
                }
                System.out.println();
             }
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: insertSong()
      * 
      * Purpose: To insert a new song into the song database 
      *          and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void insertSong()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int songID;
             String songName;
             String artistName;
             double duration;
             int playlistID;
             
             // Preparing the statement to be executed
             String sql = "INSERT INTO song (song_id, song_name, artist_name, duration, playlist_id) VALUES (?, ?, ?, ?, ?)";
             
             // Asking user for the new song data
             System.out.println("Please give me the 3 digit ID of the song:");
             songID = input.nextInt();
             input.nextLine();
             System.out.println("Please give me the name of the song:");
             songName = input.nextLine();
             System.out.println("Please give me the artist of the song:");
             artistName = input.nextLine();
             System.out.println("Please give me the duration of the song:");
             duration = input.nextDouble();
             System.out.println("Please give me the 3 digit playlist ID for the song:");
             playlistID = input.nextInt();
             
             // Executing the previously prepared statement with the data
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, songID);
                statement.setString(2, songName);
                statement.setString(3, artistName);
                statement.setDouble(4, duration);
                statement.setInt(5, playlistID);
    
                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new song database:");
             System.out.println();
             printSongDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: deleteSong()
      * 
      * Purpose: To delete a song from the song database 
      *          and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void deleteSong()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int songID;
             
             // Preparing the statement to be executed
             String sql = "DELETE FROM song WHERE song_id = ?";
             
             // Asking user for the new song data
             System.out.println("Please give the ID of the song you wish to delete:");
             songID = input.nextInt();
             
             // Executing the previously prepared statement with the given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, songID);
    
                // Execute the statement
                int rowsDeleted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new song database:");
             System.out.println();
             printSongDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }

     
     
     /*****************************************************
      * Method: insertArtist()
      * 
      * Purpose: To insert a new artist into the artist 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void insertArtist()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int artistID;
             String artistName;
             String netWorth;
             
             // Preparing the statement to execute
             String sql = "INSERT INTO artist (artist_id, artist_name, net_worth) VALUES (?, ?, ?)";
             
             // Asking user for the new song data
             System.out.println("Please give the ID of the artist:");
             artistID = input.nextInt();
             System.out.println("Please give the name of the artist:");
             artistName = input.nextLine();
             input.nextLine();
             System.out.println("Please give the artist's networth:");
             netWorth = input.nextLine();
             
             // Executing the previously prepared statement with given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, artistID);
                statement.setString(2, artistName);
                statement.setString(3, netWorth);
    
                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new artist database:");
             System.out.println();
             printArtistDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: deleteArtist()
      * 
      * Purpose: To delete an artist from the artist 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void deleteArtist()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int artistID;
             
             // Preparing the statement to be executed
             String sql = "DELETE FROM artist WHERE artist_id = ?";
             
             // Asking user for the new song data
             System.out.println("Please give the ID of the artist you wish to delete:");
             artistID = input.nextInt();
             
             // Executing the previously prepared statement with the given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, artistID);
    
                // Execute the statement
                int rowsDeleted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("This is your new artist database:");
             System.out.println();
             printArtistDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: insertPlaylist()
      * 
      * Purpose: To insert a new playlist into the playlist 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void insertPlaylist()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int playlistID;
             String playlistName;
             String numSongs;
             
             // Preparing the statement to be executed
             String sql = "INSERT INTO playlist (playlist_id, playlist_name, num_songs) VALUES (?, ?, ?)";
             
             // Asking user for the new song data
             System.out.println("Please give the ID of the playlist:");
             playlistID = input.nextInt();
             input.nextLine();
             System.out.println("Please give the name of the playlist:");
             playlistName = input.nextLine();
             System.out.println("Please give the number of songs in the playlist:");
             numSongs = input.nextLine();
             
             // Executing the previously prepared statement with given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, playlistID);
                statement.setString(2, playlistName);
                statement.setString(3, numSongs);
    
                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new playlist database:");
             System.out.println();
             printPlaylistDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: deletePlaylist()
      * 
      * Purpose: To delete a playlist from the playlist 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void deletePlaylist()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int playlistID;
             
             // Preparing the statement to be executed
             String sql = "DELETE FROM playlist WHERE playlist_id = ?";
             
             // Asking user for the new song data
             System.out.println("Please give the ID of the playlist you wish to delete:");
             playlistID = input.nextInt();
             
             // Executing the previously prepared statement with given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, playlistID);
    
                // Execute the statement
                int rowsDeleted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new playlist database:");
             System.out.println();
             printPlaylistDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: changeSongsPlaylist()
      * 
      * Purpose: To change a songs playlist in the song 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void changeSongsPlaylist()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int playlistID;
             int songID;
             
             // Preparing the statement to be executed
             String sql = "UPDATE song SET playlist_id = ? WHERE song_id = ?";
             
             // Asking user for the new song data
             System.out.println("Please give the ID of the playlist that you want to give the song:");
             playlistID = input.nextInt();
             input.nextLine();
             System.out.println("Please give the ID of the song:");
             songID = input.nextInt();
             
             // Executing the prepared statement with the given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, playlistID);
                statement.setInt(2, songID);
    
                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new song database:");
             System.out.println();
             printSongDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: changeArtistsNetWorth()
      * 
      * Purpose: To change an artists networth in the artist 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void changeArtistsNetworth()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             String netWorth;
             int artistID;
             
             // Preparing the statement to be executed
             String sql = "UPDATE artist SET net_worth = ? WHERE artist_id = ?";
             
             // Asking user for the new song data
             System.out.println("Please give the new networth of the artist:");
             input.nextLine();
             netWorth = input.nextLine();
             System.out.println("Please give the ID of the artist:");
             artistID = input.nextInt();
             
             // Executing the previously prepared statement with the given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setString(1, netWorth);
                statement.setInt(2, artistID);
    
                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new artist database:");
             System.out.println();
             printArtistDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     
     
     /*****************************************************
      * Method: changePlaylistsNumSongs()
      * 
      * Purpose: To change a playlists num_songs in the playlist 
      *          database and display the new database in a
      *          formatted display.
      *****************************************************/
     public static void changePlaylistsNumSongs()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating the data for the new song
             int playlistID;
             int numSongs;
             
             // Preparing the statement to be executed
             String sql = "UPDATE playlist SET num_songs = ? WHERE playlist_id = ?";
             
             // Asking user for the new song data
             System.out.println("Please give the new number of songs in the playlist:");
             numSongs = input.nextInt();
             input.nextLine();
             System.out.println("Please give the ID of the playlist you're changing:");
             playlistID = input.nextInt();
             
             // Executing the previously prepared statement with the given values
             try (PreparedStatement statement = conn.prepareStatement(sql)) 
             {
                // Set the values for parameters in the prepared statement
                statement.setInt(1, numSongs);
                statement.setInt(2, playlistID);
    
                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                conn.commit();
             }    
             catch (SQLException sqle) 
             {
                // Handle any SQL exceptions
                sqle.printStackTrace();
             }
             System.out.println();
             System.out.println("-----------------------------------------");
             System.out.println("This is your new playlist database:");
             System.out.println();
             printPlaylistDB();
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
     
     public static void findBiggestPlaylist()
     {
         Connection conn = null;
         try
         {
             // Connecting to the JDBC database in the file
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:music.db");
             
             // Setting AutoCommit to false so it doesn't automatcially complete queries
             conn.setAutoCommit(false);
             conn.commit();
             
             // Creating the statement
             Statement stmnt = conn.createStatement();
             
             // Creating the Result Set
             ResultSet results = null;
             
             // Creating needed variables
             int id = 0;
             String playlistName = "";
             int numSongs = 0;
             
             // Executing Statement
             results = stmnt.executeQuery("SELECT * FROM playlist ORDER BY num_songs DESC LIMIT 1");
             
             // Printing out statement results
             while (results.next()) 
             {
                id = results.getInt("playlist_id");
                playlistName = results.getString("playlist_name");
                numSongs = results.getInt("num_songs");
             }
             System.out.println("The biggest play list is: ");
             System.out.println(playlistName + ", Number of Songs: " + numSongs + ", ID: " + id);
         }
         catch (SQLException | ClassNotFoundException e)
         {
             e.printStackTrace();
         }
         finally
         {
             try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException sqle) 
            {
                sqle.printStackTrace();
            }
         }
     }
}
