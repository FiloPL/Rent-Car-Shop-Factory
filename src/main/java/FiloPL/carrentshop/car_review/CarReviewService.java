package FiloPL.carrentshop.car_review;

import FiloPL.carrentshop.car.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarReviewService {
    private final CarReviewRepository carReviewRepository;

    public CarReviewService(CarReviewRepository carReviewRepository) {
        this.carReviewRepository = carReviewRepository;
    }

    public List<CarReview> getAllCarReviews(){
        return carReviewRepository.findAll();
    }

    public List<CarReview> getCarReviewByCarId(int carId) {
        return carReviewRepository.findByCar_Id(carId);
    }

    public CarReview getCarReviewById(int id) {
        return carReviewRepository.findById(id).orElseThrow(()-> new CarReviewNotException("Brak przeglądu o numerze Id: "+ id));
    }

    public void addOrUpdateCarReview(CarReview carReview){
        carReviewRepository.save(carReview);
    }

    public Car getCarById(int id) {
        Optional<CarReview> carReviewOptional = carReviewRepository.findById(id);
        if(carReviewOptional.isPresent()){
            return carReviewOptional.get().getCar();
        } else {
            return null;
        }
    }
}
