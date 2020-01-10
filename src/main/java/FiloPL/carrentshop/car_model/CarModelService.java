package FiloPL.carrentshop.car_model;

import FiloPL.carrentshop.car.CarModelNotExistException;
import FiloPL.carrentshop.car_model.CarModel;
import FiloPL.carrentshop.car_model.CarModelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CarModelService {
    private final CarModelRepository carModelRepository;

    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }

    public List<CarModel> searchCarModels(String searchWhat, SearchCarModelOption findBy) {
        switch (findBy) {
            case BY_MARK:
                return carModelRepository.findByMarkContainsIgnoreCase(searchWhat);

            case BY_MODEL:
                return carModelRepository.findByModelContainsIgnoreCase(searchWhat);
            case BY_SEGMENT:
                return carModelRepository.findBySegmentContains(searchWhat);
            case BY_TYPE:
                return carModelRepository.findByTypeContainsIgnoreCase(searchWhat);
            case BY_PRODUCTION_YEAR:
                return carModelRepository.findByProductionYear(Integer.parseInt(searchWhat));
            case BY_AGE:
                int todayYear = LocalDate.now().getYear();
                return carModelRepository.findByProductionYearBetween(todayYear - Integer.parseInt(searchWhat), todayYear);
            default:
                return Collections.emptyList();
        }
    }

    public CarModel getCarModelById(int id) {
        return carModelRepository.findById(id).orElseThrow(() -> new CarModelNotExistException("carModelId: " + id));
    }

    public CarModel addCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public CarModel updateCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }
}
