package FiloPL.carrentshop.invoice;


import FiloPL.carrentshop.rent_history.RentHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("invoice")
public class InvoiceController{
    
    private final InvoiceService          invoiceService;
    private final InvoiceViewItemService  invoiceViewItemService;
    private final AvailableInvoiceService availableInvoiceService;
    private final InvoiceViewDetailService invoiceViewDetailService;
    private final InvoiceViewDetailItemsService invoiceViewDetailItemsService;
    
    public InvoiceController(InvoiceViewItemService invoiceViewItemService, AvailableInvoiceService availableInvoiceService, InvoiceService invoiceService, RentHistoryService rentHistoryService, InvoiceViewDetailService invoiceViewDetailService, InvoiceViewDetailItemsService invoiceViewDetailItemsService){
        this.invoiceViewItemService = invoiceViewItemService;
        this.availableInvoiceService = availableInvoiceService;
        this.invoiceService = invoiceService;
        this.invoiceViewDetailService = invoiceViewDetailService;
        this.invoiceViewDetailItemsService = invoiceViewDetailItemsService;
    }
    
    
    @GetMapping
    public ModelAndView getAllInvoices(){
        ModelAndView allInvoices = new ModelAndView("invoiceList");
        allInvoices.addObject("invoices", invoiceViewItemService.getAllInvoices());
        return allInvoices;
    }
    
    
    @GetMapping("/client/{clientId}")
    public ModelAndView getAllClientInvoices(@PathVariable Integer clientId){
        ModelAndView allClientInvoices = new ModelAndView("invoiceList");
        allClientInvoices.addObject("invoices", invoiceViewItemService.getAllClientInvoices(clientId));
        allClientInvoices.addObject("clientId", clientId);
        return allClientInvoices;
    }
    
    
    @GetMapping("/available/{clientId}")
    public ModelAndView getAllClientAvailableInvoices(@PathVariable Integer clientId){
        ModelAndView allClientInvoices = new ModelAndView("availableInvoiceList");
        allClientInvoices.addObject("available", availableInvoiceService.getAllClientInvoices(clientId));
        allClientInvoices.addObject("clientId", clientId);
        return allClientInvoices;
    }
    
    
    @GetMapping("/{invoiceId}")
    public ModelAndView getInvoice(@PathVariable Integer invoiceId){
        ModelAndView invoiceView = new ModelAndView("invoice");
        
        InvoiceViewDetail invoiceById = invoiceViewDetailService.getInvoiceDetail(invoiceId);
        List<InvoiceViewDetailItem> invoiceItems = invoiceViewDetailItemsService.getInvoiceItems(invoiceId);
    
        invoiceView.addObject("invoice", invoiceById);
        invoiceView.addObject("items", invoiceItems);
        
        return invoiceView;
    }
}
