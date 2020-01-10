package FiloPL.carrentshop.invoice;


import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceViewItemService{
    
    
    private InvoiceViewItemRepository invoiceViewItemRepository;
    
    
    public InvoiceViewItemService(InvoiceViewItemRepository invoiceViewItemRepository){
        this.invoiceViewItemRepository = invoiceViewItemRepository;
    }
    
    
    public Set<InvoiceViewItem> getAllInvoices(){
        return invoiceViewItemRepository.findAllInvoices();
    }
    
    
    public Set<InvoiceViewItem> getAllClientInvoices(int clientId){
        return invoiceViewItemRepository.findByClientId(clientId);
    }
    
}
