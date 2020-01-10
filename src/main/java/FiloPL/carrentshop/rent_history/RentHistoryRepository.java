package FiloPL.carrentshop.rent_history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface RentHistoryRepository extends JpaRepository<RentHistory, Integer> {

    Set<RentHistory> findByCar_CarModelMarkContainsIgnoreCase(String mark);
    Set<RentHistory> findByCar_PlateNrContainsIgnoreCase(String plateNr);
    Set<RentHistory> findByCar_CarModelModelContainsIgnoreCase(String model);
    Set<RentHistory> findByClientNameContainsIgnoreCase(String name);
    Set<RentHistory> findByClientEmailContainsIgnoreCase(String email);
    Set<RentHistory> findByPromotionNameContainsIgnoreCase(String name);
    Set<RentHistory> findByRentPointStartNameContainsIgnoreCase(String name);
    Set<RentHistory> findByRentPointEndNameContainsIgnoreCase(String name);
    Set<RentHistory> findByEmployeeStartSurnameContainsIgnoreCase(String surname);
    Set<RentHistory> findByEmployeeEndSurnameContainsIgnoreCase(String surname);
    Set<RentHistory> findByInvoiceNumberContainsIgnoreCase(String number);
    Set<RentHistory> findByRentTimeStartAfter(LocalDate rentTimeStart);
    Set<RentHistory> findByRentTimeEnd(LocalDate rentTimeEnd);
    Set<RentHistory> findByEmployeeStartUsernameContainsIgnoreCase(String username);
    Set<RentHistory> findByEmployeeEndUsernameContainsIgnoreCase(String username);

}
