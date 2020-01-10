package FiloPL.carrentshop.rent_point;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentPointRepository extends JpaRepository<RentPoint, Integer> {
    List<RentPoint> findByNameContains(String name);
    List<RentPoint> findByAddressContains(String address);
    List<RentPoint> findByPostCode(String postcode);
    List<RentPoint> findByCityContains(String city);
}
