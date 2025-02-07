package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookReturningBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookReturningDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.BookReturningTmEnt;

import java.sql.SQLException;
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
}
