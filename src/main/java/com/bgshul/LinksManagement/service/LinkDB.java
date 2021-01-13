package com.bgshul.LinksManagement.service;

import java.sql.*;

public class LinkDB {

    static String url = "jdbc:postgresql://localhost:5432/Links";
    static String user = "postgres";
    static String password = "Daspostgrespas67";
    static Connection connection = null;

    public void addNewLink(String original, String link){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql = "INSERT INTO links (original, link, count) " +
                    "VALUES ('"+original+"', '"+link+"', 1)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String updateCount(String link){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql = "SELECT original FROM links where link = '"+link+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String original = (resultSet.getString(1)).trim();
            sql = "UPDATE links " +
                    "SET count = count +1 " +
                    "WHERE link = '"+link+"'";
            statement.executeUpdate(sql);
            return original;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Error";
        }
    }

    public LinkJSON getSingleLinkStats(String link){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql = "SELECT original, link, count, dense_rank() " +
                    "over (ORDER BY count DESC) " +
                    "FROM links " +
                    "WHERE link = '"+link+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new LinkJSON(resultSet.getString(2).trim(),
                    resultSet.getString(1).trim(),
                    resultSet.getInt(4),
                    resultSet.getInt(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new LinkJSON();
        }
    }

    public LinkJSON getLinksStats(int page, int count, int counter){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql = "WITH cte AS (\n" +
                    "    SELECT original, link, count, dense_rank() over (ORDER BY count DESC) as rank, row_number() over ()  as row\n" +
                    "    FROM links\n" +
                    "    ORDER BY count DESC)\n" +
                    "SELECT original, link, count, rank, row\n" +
                    "FROM cte\n" +
                    "WHERE row = " + (count*(page-1)+counter);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new LinkJSON(resultSet.getString(2).trim(),
                    resultSet.getString(1).trim(),
                    resultSet.getInt(4),
                    resultSet.getInt(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new LinkJSON(throwables.toString(),""+page+count+counter,count*(page-1)+counter,0);
        }
    }

    public int getCurrentID(){
        int ID;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql = "SELECT MAX(id) FROM links";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            ID = resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            ID = -1;
        }
        return ID;
    }
}
