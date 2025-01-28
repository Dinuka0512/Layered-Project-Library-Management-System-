package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.AuthorDto;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.CategoryDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookTm;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class ManabeBooksViewModel {
    public String saveNewBook(BookDto bookDto, String authorId, String categoryId) throws ClassNotFoundException, SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        //TRANSACTION PART
        try{
            con.setAutoCommit(false);
            String bookSql = "insert into Book values (?,?,?,?,?)";
            boolean bookRes = CrudUtil.execute(
                    bookSql,
                    bookDto.getBookId(),
                    bookDto.getName(),
                    bookDto.getQty(),
                    bookDto.getPrice(),
                    bookDto.getBookShelfId()
            );

            boolean isBookSaved = bookRes;
            if(isBookSaved){
                boolean isCategorySaved = true;

                String categorySql = "insert into Book_Category values (?,?)";
                boolean categoryRes = CrudUtil.execute(
                  categorySql,
                  bookDto.getBookId(),
                  categoryId
                );

                if(!categoryRes){
                    isCategorySaved = false;
                }

                if(isCategorySaved){
                    boolean isAuthorSaved = true;
                    String authorSql = "insert into Author_Book values (?,?)";
                    boolean authorRes = CrudUtil.execute(
                            authorSql,
                            bookDto.getBookId(),
                            authorId
                    );

                    if(!authorRes){
                        isAuthorSaved = false;
                    }

                    if(isAuthorSaved){
                        con.commit();
                        return "saved Successfully";
                    }else{
                        con.rollback();
                        return "Author saving error";
                    }
                }else{
                    con.rollback();
                    return "Category saving error";
                }
            }else{
                con.rollback();
                return "Book saving error";
            }
        }catch(Exception e){
            con.rollback();
            throw e;
        }finally {
            con.setAutoCommit(true);
        }
    }

    public boolean updateBook(BookTm dto,String bookShelfId, String categoryId , String authorId) throws SQLException, ClassNotFoundException{
        Connection con = DBConnection.getInstance().getConnection();

        //TRANSACTION PART
        try{
            con.setAutoCommit(false);
            String bookSql = "update Book set name = ?, qty = ? , price = ?, bookshelf_Id = ? where Book_Id = ? ";
            boolean bookRes = CrudUtil.execute(
                    bookSql,
                    dto.getName(),
                    dto.getQty(),
                    dto.getPrice(),
                    bookShelfId,
                    dto.getBookId()
            );

            boolean isUpdate = bookRes;
            if(isUpdate){
                boolean isCategoryUpdate = true;

                String categorySql = "update book_category set Category_Id = ? where Book_Id = ? ";
                boolean categoryRes = CrudUtil.execute(
                        categorySql,
                        categoryId,
                        dto.getBookId()
                );

                if(!categoryRes){
                    isCategoryUpdate = false;
                }

                if(isCategoryUpdate){
                    boolean isAuthorUpdate = true;
                    String authorSql = "update author_Book set Author_Id = ? where Book_Id = ?";
                    boolean authorRes = CrudUtil.execute(
                            authorSql,
                            authorId,
                            dto.getBookId()
                    );

                    if(!authorRes){
                        isAuthorUpdate = false;
                    }

                    if(isAuthorUpdate){
                            con.commit();
                            return true;
                    }else{
                        //THE AUTHOR IS NOT SAVED
                        con.rollback();
                        return false;
                    }
                }else{
                    //CATEGORY IS NOT SAVED
                    con.rollback();
                    return false;
                }
            }else{
                //BOOK IS NOT SAVED
                con.rollback();
                return false;
            }
        }catch(Exception e){
            con.rollback();
            throw e;
        }finally {
            con.setAutoCommit(true);
        }
    }
}
