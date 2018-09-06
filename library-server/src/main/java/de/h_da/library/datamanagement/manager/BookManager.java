package de.h_da.library.datamanagement.manager;

import java.util.List;
import de.h_da.library.datamanagement.entity.Book;
import javax.ejb.Local;


@Local
public interface BookManager {
    void edit(Book book);

    void destroy(Book book);

    Book findById(Long id);

    List<Book> findAll();

    Book create(Book book);
}
