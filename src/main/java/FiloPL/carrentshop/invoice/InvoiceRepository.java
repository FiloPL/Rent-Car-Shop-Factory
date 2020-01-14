package FiloPL.carrentshop.invoice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
    
    Set<Invoice> findByNumberContains(String number);
    
    @Query(value = "select i.* from invoice i join rent_history rh on rh.invoice_id=i.id where rh.client_id=:clientId", nativeQuery = true)
    Set<Invoice> findByClientId(int clientId);

}
