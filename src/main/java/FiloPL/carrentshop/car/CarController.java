package FiloPL.carrentshop.car;

import FiloPL.carrentshop.car_model.CarModelService;
import FiloPL.carrentshop.rent_point.RentPoint;
import FiloPL.carrentshop.rent_point.RentPointService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final CarModelService carModelService;
    private final RentPointService rentPointService;

    public CarController(CarService carService, CarModelService carModelService, RentPointService rentPointService) {
        this.carService = carService;
        this.carModelService=carModelService;
        this.rentPointService=rentPointService;
    }

    @GetMapping("")
    public ModelAndView getCars(@RequestParam(required = false) String searchWhat, @RequestParam(required = false) String searchBy) {
        ModelAndView modelDetails = new ModelAndView("carDetail");
        modelDetails.addObject("carModels", carModelService.getAllCarModels());
        modelDetails.addObject("rentPoints", rentPointService.getAllRentPoints());
        modelDetails.addObject("update", false);
        return modelDetails;
    }

    @GetMapping("/{id}")
    public ModelAndView getCarDetailsToUpdate(@PathVariable int id) {
        ModelAndView modelDetails = new ModelAndView("carDetail");
        modelDetails.addObject("carModels",carModelService.getAllCarModels());
        modelDetails.addObject("rentPoints",rentPointService.getAllRentPoints());
        modelDetails.addObject("update",true);
        modelDetails.addObject("car", carService.getCarById(id));
        return modelDetails;
    }

    @PostMapping("/save")
    public String saveCarDetails(@RequestParam int carModelId, @RequestParam String plateNr, @RequestParam String registrationDate,
                                 @RequestParam String vin, @RequestParam String color, @RequestParam double pricePerDay,
                                 @RequestParam(required = false) Integer id, @RequestParam RentPoint beginRentPoint){
        Car car = new Car(carModelService.getCarModelById(carModelId), LocalDate.parse(registrationDate), plateNr, vin, color, pricePerDay, beginRentPoint);
        if(id!=null){
            car.setId(id);
        }
        carService.addOrUpdateCar(car);
        return "redirect:/car/";
    }
}
