package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookReturningBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookReturningDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.BookReturningTmEnt;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.ReturnBookQueree;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookReturningBOimpl implements BookReturningBO {
    private BookReturningDAO bookReturningDAO = (BookReturningDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKRETURNIG);
    @Override
    public ArrayList<BookReturningTm> loadTabel() throws SQLException, ClassNotFoundException {
        ArrayList<BookReturningTmEnt> arrayList = bookReturningDAO.loadTabel();
        ArrayList<BookReturningTm> arrayListTm = new ArrayList<>();
        for(BookReturningTmEnt ent : arrayList){
            BookReturningTm returningTm = new BookReturningTm(
                    ent.getIssueID(),
                    ent.getBookId(),
                    ent.getMemID(),
                    ent.getMemName(),
                    ent.getMemEmail(),
                    ent.getBookName(),
                    ent.getIssueDate(),
                    ent.getIssueTime()
            );

            arrayListTm.add(returningTm);
        }

        return arrayListTm;
    }

    @Override
    public boolean returnBook(String id, BookDto bookDetails, String issueId, double fee) throws SQLException, ClassNotFoundException {
        //HERE GET THE DATE
        String date = String.valueOf(LocalDate.now());

        //HERE GET THE LOCAL TIME WITH FORMAT
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        String formattedTime = now.format(formatter).toLowerCase();

        return bookReturningDAO.returnBook(new ReturnBookQueree(
                id,
                bookDetails.getBookId(),
                bookDetails.getName(),
                bookDetails.getQty(),
                bookDetails.getPrice(),
                bookDetails.getBookShelfId(),
                issueId,
                fee,
                formattedTime,
                date)
        );
    }
}