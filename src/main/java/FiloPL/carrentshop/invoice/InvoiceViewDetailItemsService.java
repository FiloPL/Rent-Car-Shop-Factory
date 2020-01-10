package FiloPL.carrentshop.invoice;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class InvoiceViewDetailItemsService{
    
    private final InvoiceViewDetailItemsRepository invoiceViewDetailItemsRepository;
    
    
    public InvoiceViewDetailItemsService(InvoiceViewDetailItemsRepository invoiceViewDetailItemsRepository){
        this.invoiceViewDetailItemsRepository = invoiceViewDetailItemsRepository;
    }
    
    public List<InvoiceViewDetailItem> getInvoiceItems(int invoiceId){
        return invoiceViewDetailItemsRepository.findAllByInvoiceId(invoiceId);
    }
}
