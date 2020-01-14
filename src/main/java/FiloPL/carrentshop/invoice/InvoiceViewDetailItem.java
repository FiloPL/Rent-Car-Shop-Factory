package FiloPL.carrentshop.invoice;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name = "view_invoice_detail_items")
public class InvoiceViewDetailItem{
    
    @Id
    private int id;
    private int invoiceId;
    private String     car;
    private int        distance;
    private int        rentDays;
    private String     promotionName;
    private BigDecimal value;
    
    
    public InvoiceViewDetailItem(){
    }
    
    
    public int getId(){
        return id;
    }
    
    public int getInvoiceId(){
        return invoiceId;
    }
    
    public String getCar(){
        return car;
    }
    
    
    public int getDistance(){
        return distance;
    }
    
    
    public int getRentDays(){
        return rentDays;
    }
    
    
    public String getPromotionName(){
        return promotionName;
    }
    
    
    public BigDecimal getValue(){
        return value;
    }
    
    
}
