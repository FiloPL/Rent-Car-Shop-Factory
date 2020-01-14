package FiloPL.carrentshop.invoice;


import org.springframework.stereotype.Service;

import java.util.Set;



@Service
public class AvailableInvoiceService{
    
    private AvailableInvoiceRepository availableInvoiceRepository;
    
    
    public AvailableInvoiceService(AvailableInvoiceRepository availableInvoiceRepository){
        this.availableInvoiceRepository = availableInvoiceRepository;
    }
    
    
    public Set<AvailableInvoice> getAllClientInvoices(int clientId){
        return availableInvoiceRepository.findByClientId(clientId);
    }
}
