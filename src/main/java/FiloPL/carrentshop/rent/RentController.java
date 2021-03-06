package FiloPL.carrentshop.rent;

import FiloPL.carrentshop.car.CarService;
import FiloPL.carrentshop.client.ClientService;
import FiloPL.carrentshop.employee.Employee;
import FiloPL.carrentshop.employee.EmployeeService;
import FiloPL.carrentshop.promotion.PromotionService;
import FiloPL.carrentshop.rent_history.RentHistory;
import FiloPL.carrentshop.rent_history.RentHistoryService;
import FiloPL.carrentshop.rent_point.RentPoint;
import FiloPL.carrentshop.rent_point.RentPointService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;
    private final CarService carService;
    private final ClientService clientService;
    private final PromotionService promotionService;
    private final RentPointService rentPointService;
    private final EmployeeService employeeService;
    private final RentHistoryService rentHistoryService;
    private String username;

    public RentController(RentService rentService, CarService carService, ClientService clientService, PromotionService promotionService,
                          RentPointService rentPointService, EmployeeService employeeService, RentHistoryService rentHistoryService) {
        this.rentService = rentService;
        this.carService = carService;
        this.clientService = clientService;
        this.promotionService = promotionService;
        this.rentPointService = rentPointService;
        this.employeeService = employeeService;
        this.rentHistoryService = rentHistoryService;
    }

    @GetMapping("")
    public ModelAndView getRentView(@RequestParam(required = false) String searchWhat, @RequestParam(required = false) String searchBy) {
        ModelAndView modelAndView = new ModelAndView("rentList");
        modelAndView.addObject("searchByOptions", SearchRentOption.values());
        if (searchWhat == null || searchBy == null || searchWhat.isEmpty() || searchBy.isEmpty()) {
            modelAndView.addObject("isFiltered", false);
            modelAndView.addObject("rents", rentService.getAllRents());
        } else {
            modelAndView.addObject("isFiltered", true);
            modelAndView.addObject("rents", rentService.searchRents(searchWhat, SearchRentOption.valueOf(searchBy)));
        }
        return modelAndView;
    }

    @GetMapping("/borrow")
    public ModelAndView getRentBorrow(@PathVariable(required = false) Integer id, @SessionAttribute Employee employee,
                                      @PathVariable(required = false) Integer clientId, @PathVariable(required = false) Integer carId) {
        Rent rent = new Rent();
        rent.setRentTimeStart(LocalDate.now());
        username = employee.getUsername();
        if (id != null) {
            rent.setId(id);
        }
        ModelAndView modelAndView = new ModelAndView("rentDetail");
        modelAndView.addObject("todayDate", LocalDate.now());
        modelAndView.addObject("maxDate", LocalDate.now().plusDays(5));
        modelAndView.addObject("rent", rent);
        modelAndView.addObject("rentPoint", employeeService.getEmployeeByUsername(username).getRentPoint());
        modelAndView.addObject("cars", carService.getCarsByActualRentPoint(employeeService.getEmployeeByUsername(username).getRentPoint()));
        modelAndView.addObject("clients", clientService.getAllClients());
        if (carId == null && clientId == null) {
            modelAndView.addObject("promotions", promotionService.getAllPromotions());
        } else {
            modelAndView.addObject("promotions", promotionService.getAllMatchedPromotions(carId, clientId));
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getRentEdit(@PathVariable Integer id, @SessionAttribute Employee employee) {
        username = employee.getUsername();
        ModelAndView modelAndView = new ModelAndView("rentDetail");
        modelAndView.addObject("todayDate", LocalDate.now());
        modelAndView.addObject("rent", rentService.getRentById(id));
        modelAndView.addObject("rentPoint", employeeService.getEmployeeByUsername(username).getRentPoint());
        modelAndView.addObject("cars", carService.getCarsByActualRentPoint(employeeService.getEmployeeByUsername(username).getRentPoint()));
        modelAndView.addObject("clients", clientService.getAllClients());
        modelAndView.addObject("promotions", promotionService.getAllPromotions());
        return modelAndView;
    }

    @PostMapping("/borrow")
    public String postRentBorrow(@ModelAttribute Rent rent, @RequestParam(required = false) Integer id) {
        if (id != null) {
            rent.setId(id);
        }
        rentService.addOrUpdateRent(rent);
        return "redirect:/rent";
    }

    @GetMapping("/return")
    public ModelAndView getRentReturn() {
        return new ModelAndView("rentReturnByPlate");
    }

    @GetMapping("/return/{id}")
    public ModelAndView getRentReturnById(@PathVariable(required = false) Integer id, @SessionAttribute Employee employee) {
        ModelAndView modelAndView = new ModelAndView("rentReturnDetail");
        RentHistory rentHistory = new RentHistory();
        rentHistory.setRentTimeEnd(LocalDate.now());
        username = employee.getUsername();
        if (id != null) {
            rentHistory.setId(id);
            modelAndView.addObject("rent", rentService.getRentById(id));
        }
        modelAndView.addObject("rentHistory", rentHistory);
        modelAndView.addObject("todayDate", LocalDate.now());
        modelAndView.addObject("minCounter", rentService.getRentById(id).getCounterStateStart());
        modelAndView.addObject("rentPoint", employeeService.getEmployeeByUsername(username).getRentPoint());
        return modelAndView;
    }

    @PostMapping("/return")
    public ModelAndView postRentReturnByPlateNr(@RequestParam(required = false) String plateNr) {
        ModelAndView modelAndView = new ModelAndView("rentReturnDetail");
        RentHistory rentHistory = new RentHistory();
        rentHistory.setRentTimeEnd(LocalDate.now());
        if (plateNr != null) {
            modelAndView.addObject("rent", rentService.getRentByPlateNr(plateNr));
        }
        modelAndView.addObject("rentHistory", rentHistory);
        modelAndView.addObject("todayDate", LocalDate.now());
        modelAndView.addObject("minCounter", rentService.getRentByPlateNr(plateNr).getCounterStateStart());
        modelAndView.addObject("rentPoint", employeeService.getEmployeeByUsername(username).getRentPoint());
        return modelAndView;
    }

    @PostMapping("/return/{id}")
    public String postRentReturnProcedure(@PathVariable int id, @ModelAttribute Rent rent, @ModelAttribute RentHistory rentHistory,
                                          @RequestParam RentPoint rentPointEnd, @SessionAttribute Employee employee) {
        rentHistory.setId(id);
        rentHistory.setCar(rent.getCar());
        rentHistory.setClient(rent.getClient());
        rentHistory.setPromotion(rent.getPromotion());
        rentHistory.setRentPointStart(rent.getRentPointStart());
        rentHistory.setEmployeeStart(rent.getEmployeeStart());
        rentHistory.setRentTimeStart(rent.getRentTimeStart());
        rentHistory.setCounterStateStart(rent.getCounterStateStart());
        rentHistory.setNotesStart(rent.getNotes());
        rentHistory.setEmployeeEnd(employee);
        rentHistory.setRentPointEnd(rentPointEnd);
        rentHistoryService.addRentHistory(rentHistory);
        rentService.deleteRent(rent);
        return "redirect:/rent";
    }
}
