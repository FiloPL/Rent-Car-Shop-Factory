package FiloPL.carrentshop.invoice;


import org.springframework.stereotype.Service;

@Service
public class InvoiceViewDetailService{
    
    private final InvoiceViewDetailRepository invoiceViewDetailRepository;
    
    
    public InvoiceViewDetailService(InvoiceViewDetailRepository invoiceViewDetailRepository){
        this.invoiceViewDetailRepository = invoiceViewDetailRepository;
    }
    
    
    public InvoiceViewDetail getInvoiceDetail(int invoiceId){
        return invoiceViewDetailRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotExistException("invoiceId: " + invoiceId));
    }
}
