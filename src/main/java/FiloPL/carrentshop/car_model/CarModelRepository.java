package FiloPL.carrentshop.car_model;

import FiloPL.carrentshop.car_model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    List<CarModel> findByMarkContainsIgnoreCase(String mark);
    List<CarModel> findByMarkIgnoreCase(String mark);
    List<CarModel> findByModelContainsIgnoreCase(String model);
    List<CarModel> findByProductionYear(int year);
    List<CarModel> findByProductionYearBetween(int year1, int year2);
    List<CarModel> findBySegmentContains(String segment);
    List<CarModel> findByTypeContainsIgnoreCase(String type);
}
