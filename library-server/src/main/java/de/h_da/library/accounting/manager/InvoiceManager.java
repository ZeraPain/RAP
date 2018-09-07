package de.h_da.library.accounting.manager;

import java.util.List;
import javax.ejb.Local;
import de.h_da.library.accounting.entity.Invoice;

@Local
public interface InvoiceManager {
    void edit(Invoice invoice);

    void destroy(Invoice invoice);

    Invoice findById(Long id);

    List<Invoice> findAll();

    Invoice create(Invoice invoice);
}
