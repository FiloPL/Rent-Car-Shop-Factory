package FiloPL.carrentshop.car_review;

import FiloPL.carrentshop.car.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/carReview")
public class CarReviewController {
    private final CarReviewService carReviewService;
    private final CarService carService;

    public CarReviewController(CarReviewService carReviewService, CarService carService) {
        this.carReviewService = carReviewService;
        this.carService = carService;
    }

    @GetMapping("/{carId}")
    public ModelAndView getCarReview(@PathVariable int carId) {
        ModelAndView modelDetails = new ModelAndView("carReviewList");
        modelDetails.addObject("carId",carId);
        modelDetails.addObject("todayDate", LocalDate.now());
        modelDetails.addObject("carReviews", carReviewService.getCarReviewByCarId(carId));
        return modelDetails;
    }


    @GetMapping("/add/{carId}")
    public ModelAndView getCarReviewAdd(@PathVariable int carId) {
        ModelAndView modelDetails = new ModelAndView("carReviewDetail");
        modelDetails.addObject("todayDate",LocalDate.now());
        modelDetails.addObject("car", carService.getCarById(carId));
        CarReview carReview = new CarReview();
        carReview.setReviewDate(LocalDate.now());
        modelDetails.addObject("carReview", carReview);
        return modelDetails;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getCarReviewEdit(@PathVariable int id) {
        ModelAndView modelDetails = new ModelAndView("carReviewDetail");
        modelDetails.addObject("car",carReviewService.getCarById(id));
        modelDetails.addObject("todayDate",LocalDate.now());
        modelDetails.addObject("carReview", carReviewService.getCarReviewById(id));
        return modelDetails;
    }

    @PostMapping("/save")
    public String postCarReviewSave(@RequestParam int carId, @RequestParam(required = false) int id, @RequestParam int counterState, @RequestParam String reviewDate, @RequestParam String notes) {
        if (id != 0) {
            CarReview carReview = carReviewService.getCarReviewById(id);
            carReview.setCounterState(counterState);
            carReview.setReviewDate(LocalDate.parse(reviewDate));
            carReviewService.addOrUpdateCarReview(carReview);
        } else {
            carReviewService.addOrUpdateCarReview(new CarReview(carService.getCarById(carId), counterState, LocalDate.parse(reviewDate),notes));
        }
        return "redirect:/carReview/"+carId;
    }
}
