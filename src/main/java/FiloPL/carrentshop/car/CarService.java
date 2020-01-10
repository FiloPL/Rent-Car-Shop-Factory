package FiloPL.carrentshop.car;

import FiloPL.carrentshop.rent_point.RentPoint;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllActiveCars() {
        return carRepository.findByActiveEquals(true);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> searchActiveCars(String searchWhat, SearchCarOption searchCarOption) {
        switch (searchCarOption) {
            case BY_CAR_MODEL:
                return carRepository.findByCarModel_ModelContainsIgnoreCase(searchWhat);
            case BY_PLATE_NR:
                return carRepository.findByPlateNrContainsIgnoreCaseAndActiveIs(searchWhat, true);
            case BY_VIN:
                return carRepository.findByVinContainsIgnoreCaseAndActiveIs(searchWhat, true);
            case BY_SEGMENT:
                return carRepository.findByCarModelSegmentContainsIgnoreCaseAndActiveIs(searchWhat, true);
            case BY_TYPE:
                return carRepository.findByCarModelTypeContainsIgnoreCaseAndActiveIs(searchWhat, true);
            case BY_COLOR:
                return carRepository.findByColorContainsIgnoreCaseAndActiveIs(searchWhat, true);
            default:
                return Collections.emptyList();
        }
    }

    public List<Car> searchCars(String searchWhat, SearchCarOption searchCarOption) {
        switch (searchCarOption) {
            case BY_PLATE_NR:
                return carRepository.findByPlateNrContainsIgnoreCase(searchWhat);
            case BY_VIN:
                return carRepository.findByVinContainsIgnoreCase(searchWhat);
            case BY_SEGMENT:
                return carRepository.findByCarModelSegmentContains(searchWhat);
            case BY_TYPE:
                return carRepository.findByCarModelTypeContainsIgnoreCase(searchWhat);
            case BY_COLOR:
                return carRepository.findByColorContainsIgnoreCase(searchWhat);
            default:
                return Collections.emptyList();
        }
    }


    public Car getCarByPlateNr(String plateNr) {
        return carRepository.findByPlateNr(plateNr).orElseThrow(() -> new CarModelNotExistException("carPlateNr" + plateNr));
    }


    public Car getCarByVin(String vin) {
        return carRepository.findByVin(vin).orElseThrow(() -> new CarModelNotExistException("carVin" + vin));
    }


    public Car addOrUpdateCar(Car car) {
        return carRepository.save(car);
    }

    public void removeCar(int id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setActive(false);
            carRepository.save(car);
        }
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElseThrow(()->new CarModelNotExistException("carId "+id));
    }

    public Set<Car> getCarsByActualRentPoint(RentPoint rentPoint){
        return carRepository.findByActualRentPoint(rentPoint.getId());
    }
}
