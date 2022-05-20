package com.company.repository;

import com.company.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class BookRepository {
    public void saveBook(Book book) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "insert into book(name, author) values(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());

            statement.execute();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public boolean isExistBook(String name) {
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from book where name='" + name + "';");
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public List<Book> selectAllBook() {
        List<Book> bookList = new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select * from book";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                bookList.add(book);
            }
            stmt.execute(sql);
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return bookList;
    }

    public void updateBook(int id, String name, String author) {

        try {

            Connection connection = DBConnection.getConnection();
            String sql = "update book set name='" + name + "', author='" + author + "' where id=" + id + "";
            PreparedStatement statement = connection.prepareStatement(sql);

            System.out.println("succsess");
            statement.execute();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void deleteBook(int id) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "DELETE FROM book  where id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            System.out.println("succsess");
            statement.execute();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void changeStatusActive(String s) {

        try {

            Connection connection = DBConnection.getConnection();
            String sql = "update student set status='ACTIVE' where phone='" + s + "'";
            PreparedStatement statement = connection.prepareStatement(sql);

            System.out.println("Activeted  " + s);
            statement.execute();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public boolean isExsistBookId(int id) {
        try {

            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from book where id='" + id + "'");
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return false;
    }


}
