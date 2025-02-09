package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOimpl implements BookDAO {
    //HERE GET THE ALL BOOK DETAILS
    @Override
    public ArrayList<Book> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from Book";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<Book> dtos =  new ArrayList<>();
        while(res.next()){
            Book dto = new Book(
                    res.getString("Book_Id"),
                    res.getString("name"),
                    res.getInt("qty"),
                    res.getDouble("price"),
                    res.getString("bookshelf_Id")
            );

            dtos.add(dto);
        }
        return dtos;
    }

    //HERE GET THE BOOK DETAILS
    @Override
    public Book getBookDetails(String bookId) throws ClassNotFoundException, SQLException {
        String sql = "select * from Book where Book_Id = ?";
        ResultSet res = CrudUtil.execute(
                sql,
                bookId
        );

        if(res.next()){
            Book dto = new Book(
                    res.getString("Book_Id"),
                    res.getString("name"),
                    res.getInt("qty"),
                    res.getDouble("price"),
                    res.getString("bookshelf_Id")
            );
            return dto;
        }
        return null;
    }

    //HERE BOOK SHELF ID
    @Override
    public String getBookShelfId(String bookId) throws SQLException, ClassNotFoundException {
        String sql = "select bookshelf_Id from book where book_Id = ?";
        ResultSet res = CrudUtil.execute(sql,bookId);
        if(res.next()){
            return res.getString("bookshelf_Id");
        }
        return null;
    }

    //HERE GET THE ALL BOOK IDS
    @Override
    public ArrayList<String> getAllBookIds() throws ClassNotFoundException, SQLException {
        String sql = "select book_Id from Book";
        ResultSet res = CrudUtil.execute(sql);

        ArrayList<String> ids = new ArrayList<>();
        while(res.next()){
            String id = res.getString("book_Id");
            ids.add(id);
        }

        return ids;
    }

    @Override
    public ArrayList<Book> searchBook(String name) throws SQLException, ClassNotFoundException {
        ArrayList<Book> bookList = new ArrayList<>();

        // First, try to find the exact match
        String searchByFullName = "SELECT * FROM Book WHERE name = ?";
        ResultSet res1 = CrudUtil.execute(searchByFullName, name);
        addResultsToList(res1, bookList);


        // If no results, try to find names ending with the search term
        if (bookList.isEmpty()) {
            String searchByEnding = "SELECT * FROM Book WHERE name LIKE ?";
            ResultSet res2 = CrudUtil.execute(searchByEnding, "%" + name);
            addResultsToList(res2, bookList);
        }

        // If still no results, try to find names starting with the search term
        if (bookList.isEmpty()) {
            String searchByStarting = "SELECT * FROM Book WHERE name LIKE ?";
            ResultSet res3 = CrudUtil.execute(searchByStarting, name + "%");
            addResultsToList(res3, bookList);
        }

        return bookList;
    }

    private void addResultsToList(ResultSet rs, ArrayList<Book> bookList) throws SQLException {
        while (rs != null && rs.next()) {
            Book book = new Book(
                    rs.getString("Book_Id"),
                    rs.getString("name"),
                    rs.getInt("qty"),
                    rs.getDouble("price"),
                    rs.getString("bookshelf_Id")
            );
            bookList.add(book);
        }
    }

    //GENERATE THE ID
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select Book_Id from Book Order by Book_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Book_Id"); //B001
            String subString = lastId.substring(1);  //001
            int i = Integer.parseInt(subString); //1
            int newIndex = i + 1; //2
            return String.format("B%03d",newIndex); //B002
        }

        return "B001";
    }

    //DELETE BOOK
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "Delete from Book where Book_Id = ?";
        boolean res = CrudUtil.execute(sql, id);
        return res;
    }


    //--------------->>>

    @Override
    public boolean save(Book dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Book dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Book search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
