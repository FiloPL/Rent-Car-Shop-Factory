package FiloPL.carrentshop.invoice;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface InvoiceViewDetailItemsRepository extends JpaRepository<InvoiceViewDetailItem, Integer>{
    
    public List<InvoiceViewDetailItem> findAllByInvoiceId(int id);
    
}
