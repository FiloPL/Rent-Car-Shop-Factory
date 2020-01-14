package FiloPL.carrentshop.invoice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AvailableInvoiceRepository extends JpaRepository<AvailableInvoice, Integer>{
    
    @Query(value = "select i.* from view_avail_invoices i where i.client_id = :id", nativeQuery = true)
    Set<AvailableInvoice> findByClientId(@Param("id") int clientId);
    
}
