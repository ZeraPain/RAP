package de.h_da.library.datamanagement.manager;

import java.util.List;

import javax.ejb.Local;

import de.h_da.library.datamanagement.entity.BookOnStock;;

@Local
public interface BookOnStockManager {

    void edit(BookOnStock bookOnStock);

    void destroy(BookOnStock bookOnStock);

    BookOnStock findById(Long id);

    List<BookOnStock> findAll();

    BookOnStock create(BookOnStock bookOnStock);
}
